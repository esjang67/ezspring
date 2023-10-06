<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="../includes/header.jsp"%>

<style>

   .uploadResult {
      width: 100%;
      background-color: gray;
   }
   
   .uploadResult ul {
      display: flex;
      flex-flow: row;
      justify-content: center;
      align-items: center;
   }
   
   .uploadResult ul li {
      list-style: none;
      padding: 10px;
   }
   
   .uploadResult ul li img {
      width: 60px;
   }
   
   .uploadResult ul li span {
      color: white;
   }
   
   .bigPictureWrapper {
      position: absolute;
      display: none;
      justify-content: center;
      align-items: center;
      top: 0%;
      width: 100%;
      height: 100%;
      background-color: gray;
      z-index: 100;
      background: rgba(255, 255, 255, 0.5);
   }
   
   .bigPicture {
      position: relative;
      display: flex;
      justify-content: center;
      align-items: center;
   }
   
   .bigPicture img {
      width: 600px;
   }

</style>

<div class="row">
   <div class="col-lg-12">
      <h1 class="page-header">Board Register</h1>
   </div>
</div>

<div class="row">
   <div class="col-lg-12">
      <div class="panel panel-default">
         <div class="panel-heading">Board Register</div>
         
         <div class="panel-body">
         
            <form role="form" action="/board/add" method="post">
               <div class="form-group">
                  <label>Title</label>
                  <input class="form-control" name="title">
               </div>
               
               <div class="form-group">
                  <label>Text Area</label>
                  <textarea class="form-control" rows="3" name="content"></textarea>
               </div>
               
               <div class="form-group">
                  <label>Writer</label>
                  <input class="form-control" name="writer" value='<sec:authentication property="principal.username"/>' readonly="readonly" >
                  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">	
               </div>
               
               <!-- 첨부파일 -->
               <div class="form-group">
	               <div class="row">
					   <div class="col-lg-12">
					      <div class="panel panel-default">
					         <div class="panel-heading">첨부 파일</div>
					         <div class="panel-body">
					            <div class="form-group uploadDiv">
					               <input type="file" name="uploadFile" multiple>
					            </div>
					            
					            <div class="uploadResult">
					               <ul>
					               </ul>
					            </div>
					         </div>
					      </div>
					   </div>
					</div>
				</div>
               
               <button type="submit" class="btn btn-default">Submit Button</button>
               <button type="reset" class="btn btn-default">Reset Button</button>
               
            </form>

         </div>
      </div>
   </div>
</div>

<!-- 첨부파일 js -->
<!-- 첨부파일을 서버에 올려야 섬네일이 보임 -->
<script>
	let formObj = $('form');
	$('button[type="submit"]').on('click', function(e) {
		e.preventDefault();
		
		let str = '';
		// li에 dataset에 저장된 정보를 셋팅함
		$('.uploadResult ul li').each(function(i, obj) {
			let jobj = $(obj);
			// BoardAttachVO와 맞춰서 작업함
			str += '<input type="hidden" name="attachList[' + i + '].fileName" value="' + jobj.data('filename') + '">';
			str += '<input type="hidden" name="attachList[' + i + '].uuid" value="' + jobj.data('uuid') + '">';
			str += '<input type="hidden" name="attachList[' + i + '].uploadPath" value="' + jobj.data('path') + '">';
			str += '<input type="hidden" name="attachList[' + i + '].fileType" value="' + jobj.data('type') + '">';
		})
		
		formObj.append(str).submit();
		
	})
	
	const regex = new RegExp('(.*?)\.(exe|zip|alz)$');
	const maxSize = 5242880;
	
	function checkFile(fileName, fileSize){
		if(fileSize > maxSize){
			alert("파일사이즈 초과");
			return false;
		}
		if(regex.test(fileName)){
			alert("해당 확장자는 업로드 불가");
			return false;
		}
		return true;	
	}
	
	$('.uploadResult').on('click','button', function(e){
		e.preventDefault();
		let fileName = $(this).data('file');
		let type = $(this).data('type');
		let li = $(this).closest('li');	// 부모 li를 찾아줘
		
		$.ajax({
			url: '/deleteFile',
			type: 'post',
			data: {
				fileName: fileName,
				type: type
			},
			success: function(result){
				li.remove();	// 화면 li 태그 지움
			}
		})
		
		
	})
	
	function showUploadResult(uploadResultArr) {
	      if(!uploadResultArr || uploadResultArr.length == 0) {
	         return;
	      }
	      
	      let uploadUL = $('.uploadResult ul');
	      let str = '';
	      
	      $(uploadResultArr).each(function(i, obj) {
	         
	         if(obj.image) {
	            let filePath = obj.uploadPath + '/s_' + obj.uuid + '_' + obj.fileName;
	            filePath = encodeURIComponent(filePath);
	            
	            str += '<li data-path="' + obj.uploadPath + '" data-uuid="' + obj.uuid + '" data-filename="' + obj.fileName + '" data-type="' + obj.image + '"><div>';
	            str += '<span>' + obj.fileName + '</span>';
	            str += '<button class="deleteBtn" data-type="image" data-file="' + filePath + '">x</button><br>';
	            str += '<img src="/display?fileName=' + filePath + '">';
	            str += '</div></li>';
	         } else {
	            let filePath = obj.uploadPath + '/' + obj.uuid + '_' + obj.fileName;
	            filePath = encodeURIComponent(filePath);
	            
	            str += '<li data-path="' + obj.uploadPath + '" data-uuid="' + obj.uuid + '" data-filename="' + obj.fileName + '" data-type="' + obj.image + '"><div>';
	            str += '<span>' + obj.fileName + '</span>';
	            str += '<button class="deleteBtn" data-type="file" data-file="' + filePath + '">x</button><br>';
	            str += '<img src="/resources/img/clip.png">';
	            str += '</div></li>';
	         }   
	      })
	      uploadUL.append(str);   
	}
	
	$('input[type="file"]').change(function(){
		let formData = new FormData();
		let inputFile = $('input[type="file"]');	//[0].files에 파일정보들이 있음
		let files = inputFile[0].files;
		
		for(let i=0; i<files.length;i++){
			if(!checkFile(files[i].name, files[i].size)){
				return false;
			}
			
			formData.append('uploadFile', files[i]);
		}
		
		$.ajax({
			url: '/uploadAjax',
			type: 'post',
			processData: false,
			contentType: false,
			data: formData,
			
			success:function(result){
				console.log(result);
				showUploadResult(result);
			}
		})
		
	})
	
	

</script>

<%@ include file="../includes/footer.jsp"%>