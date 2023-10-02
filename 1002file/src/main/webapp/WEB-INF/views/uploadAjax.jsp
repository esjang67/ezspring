<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Client Upload</title>

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
      width: 30px;
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

</head>
<body>
	<div class="uploadDiv">
		<input type="file" name="uploadFile" multiple>
	</div>
	<br><br>
	<button id="uploadBtn">전송</button>
	
	<div class="uploadResult">
		<ul>
		<!-- script 요기!! -->
		</ul>
	</div>
	
	<div class="bigPictureWrapper">
		<div class="bigPicture">
			<!-- script 요기!! -->
		</div>
	</div>
	
<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
	
<script>
	
	// div 복사
	const cloneDiv = $('.uploadDiv').clone();
	const uploadResult = $('.uploadResult ul');
	
	const regex = new RegExp('(.*?)\.(exe|sh|zip|alz)$');
	const maxSize = 5242880; // 5mb
	
	// x button
	$('.uploadResult').on('click', 'span', function(){
		let path = $(this).data('file');
		let type = $(this).data('type');
		let li = $(this).parent();
		
		$.ajax({
			url:"/controller/deleteFile",
			type:"post",
			data: {
				fileName:path,
				type:type
				},
			
			success: function(result){
				li.remove();
				alert(result);
			}
		})
	})
	
	
	function showImage(filePath) {

	      $('.bigPictureWrapper').css('display', 'flex').show();
	      
	      $('.bigPicture').html('<img src="/controller/display?fileName=' + encodeURI(filePath) + '">')
	                  .animate({width: '100%', height: '100%'}, 1000);
	      
   }
   
   $('.bigPictureWrapper').on('click', function() {
      $('.bigPicture').animate({width: '0%', height: '0%'}, 1000);
      
      setTimeout(() => {
         $(this).hide();
      }, 1000)
      
   })   
	
	// 서버에서 받아온 정보 출력
	function showUploadFile(uploadResultArr){
		
		let str = "";
		
		$(uploadResultArr).each(function(i, obj){
			
			if(!obj.image){
				let path = obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName;
				path = encodeURIComponent(path);		// 요청주소를 url로 만들어줌
				
				str += "<li>";
				str += "<a href='/controller/download?fileName=" + path + "'>";
				str += "<img src='resources/img/clip.png'>" + obj.fileName
				str += "</a>"
				str += '<span data-file=\"' + path + '\" data-type="file"> X </span>';
				str += "</li>"; 	
			} else {
				
				// 서버에 이미지 파일 가져오기
				let path = obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName;
				
				path = encodeURIComponent(path);		// 요청주소를 url로 만들어줌		
				
				let oriPath =  obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName;
				oriPath = oriPath.replace(new RegExp(/\\/g), '/');
				
				//console.log(obj.uploadPath.replaceAll('\\','/'));
				
				str += "<li>";
				// <a href='javascript:showImage("가나다")'>
				str += '<a href=\'javascript:showImage(\"' + oriPath + '\")\'>';
				str += "<img src='/controller/display?fileName=" + path + "'>";
				str += "</a>"
				str += '<span data-file=\"' + path + '\" data-type="image"> X </span>';
				str += "</li>"; 
				
			}
		})	
		uploadResult.append(str);
		
	}
	
	
	// 파일사이즈, 파일확장자 검사
	function checkFile(fileName, fileSize){
		if(fileSize >= maxSize){
			alert("파일 크기 초과");
			return false;
		}
		if(regex.test(fileName)){
			alert('해당 확장자 업로드 불가');
			return false;
		}
		return true;			
	}

	$('#uploadBtn').on('click', function(){
		let formData = new FormData();
		let inputFile = $('input');
		let files = inputFile[0].files;
		
		console.log(files);
		
		for(let i=0;i<files.length;i++){
			if(!checkFile(files[i].name, files[i].size)){
				return false;
			}
			formData.append('uploadFile',files[i]);
		}
		
		$.ajax({
			url:'/controller/uploadAjax',
			processData: false,		// 자체 객체처리 막음
			contentType: false,		// formData에 각각 contentType이 설정되어있으므로.
			data: formData,
			type: 'post',
			
			success: function(result){
				console.log(result);
				if(result.length > 0){
					alert('업로드 완료');
					
					// input 이 업데이트가 되지 않으므로 복사된 내용 바꿈
					$('.uploadDiv').html(cloneDiv.html());
					
					showUploadFile(result);
					
				}
			}
		})
	})
	

</script>
</body>
</html>