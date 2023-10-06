<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
      <h1 class="page-header">Board Detail</h1>
   </div>
</div>

<div class="row">
   <div class="col-lg-12">
      <div class="panel panel-default">
         <div class="panel-heading">Board Detail</div>
         
         <div class="panel-body">
         	<form method="" action="">
              <div class="form-group">
                 <label>No</label>
                 <input class="form-control" name="bno" readonly="readonly" value="${board.bno}">
              </div>
            
              <div class="form-group">
                 <label>Title</label>
                 <input class="form-control" name="title" value="${board.title}">
              </div>
              
              <div class="form-group">
                 <label>Text Area</label>
                 <textarea class="form-control" rows="3" name="content" >${board.content}</textarea>
              </div>
              
              <div class="form-group">
                 <label>Writer</label>
                 <input class="form-control" name="writer" value="${board.writer}">
              </div>
              
              <!-- 첨부파일 -->
              <div class="form-group">
					<label>Attach Files</label>
			        <div class="form-group uploadDiv">
			           <input type="file" name="uploadFile" multiple>
			        </div>
				        
			        <div class="uploadResult">
			           <ul>
			           </ul>
			        </div>
			  </div>              
              
              <button data-oper="modify" class="btn btn-primary">수정</button>
              <button data-oper="remove" class="btn btn-danger">삭제</button>
              <button data-oper="list" class="btn btn-default">목록</button>
              
              
			  <input type="hidden" class="type" name="type" value="${paging.type}"> 
			  <input type="hidden" class="keyword" name="keyword" value="${paging.keyword}">               
              <input type="hidden" class="pageNum" name="pageNum" value="${paging.pageNum}">
			  <input type="hidden" class="amount" name="amount" value="${paging.amount}">
			  
			</form>
         </div>
      </div>
   </div>
</div>

<script>
	/* 첨부파일 */
	
	const bno = '<c:out value="${board.bno}"/>';
	
	$.getJSON('/board/getAttachList', {bno:bno}, function(attachList){
		
		let uploadUL = $('.uploadResult ul');
		let str = '';
        
        $(attachList).each(function(i, obj) {
 	         
	        if(obj.fileType) {
	           let filePath = obj.uploadPath + '/s_' + obj.uuid + '_' + obj.fileName;
	           filePath = encodeURIComponent(filePath);
	           
	           str += '<li data-path="' + obj.uploadPath + '" data-uuid="' + obj.uuid + '" data-filename="' + obj.fileName + '" data-type="' + obj.fileType + '"><div>';
	           str += '<span>' + obj.fileName + '</span>';
	           str += '<button class="deleteBtn" data-type="image" data-file="' + filePath + '">x</button><br>';
               str += '<img src="/display?fileName=' + filePath + '">';
	           str += '</div></li>';
	        } else {
	           let filePath = obj.uploadPath + '/' + obj.uuid + '_' + obj.fileName;
	           filePath = encodeURIComponent(filePath);
	           
	           str += '<li data-path="' + obj.uploadPath + '" data-uuid="' + obj.uuid + '" data-filename="' + obj.fileName + '" data-type="' + obj.fileType + '"><div>';
	           str += '<span>' + obj.fileName + '</span>';
	           str += '<button class="deleteBtn" data-type="file" data-file="' + filePath + '">x</button><br>';
	           str += '<img src="/resources/img/clip.png">';
	           str += '</div></li>';
	        }   
        })
        
		uploadUL.append(str);   	

	})
	
	$('.uploadResult').on('click','button', function(e){
		e.preventDefault();
		
		if(confirm("정말 삭제하시겠습니까?")){
			let li = $(this).closest('li');	// 부모 li를 찾아줘
			li.remove();
		}
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

<script>

// 버튼 데이터셋 활용 form tag 변경 submit하기
	$('button').on('click', function(e) {
		e.preventDefault()
		
		const op = $(this).data("oper");
		const formTag = $('form');
		
		if(op == "remove") {
			formTag.attr('action', "/board/remove")
			formTag.attr('method', "get")
			
		} else if(op == "modify") {
			formTag.attr('action', "/board/modify")
			formTag.attr('method', "post")
			
			/* 첨부파일 관련 */
			/* 기존 저장된 파일을 전부 삭제하고 재등록할것임! */
			let str = '';
			$('.uploadResult ul li').each(function(i, obj){
				let jobj = $(obj);
				// BoardAttachVO와 맞춰서 작업함
				str += '<input type="hidden" name="attachList[' + i + '].fileName" value="' + jobj.data('filename') + '">';
				str += '<input type="hidden" name="attachList[' + i + '].uuid" value="' + jobj.data('uuid') + '">';
				str += '<input type="hidden" name="attachList[' + i + '].uploadPath" value="' + jobj.data('path') + '">';
				str += '<input type="hidden" name="attachList[' + i + '].fileType" value="' + jobj.data('type') + '">';				
			})
			formTag.append(str);
			
		} else {
			const pageNum = $('.pageNum').val();
			const amount = $('.amount').val();
			const type = $('.type').val();
			const keyword = $('.keyword').val();
			location.href='/board/list?pageNum=' + pageNum + '&amount=' + amount + '&type=' + type + '&keyword=' + keyword ;
			return;
		}
		
		formTag.submit();
		
	})

</script>

<%@ include file="../includes/footer.jsp"%>