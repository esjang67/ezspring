<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../includes/header.jsp"%>

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
				<div class="form-group">
					<label>No</label> 
					<input class="form-control" name="bno"
						readonly="readonly" value="${board.bno}">
				</div>

				<div class="form-group">
					<label>Title</label> 
					<input class="form-control" name="title"
						readonly="readonly" value="${board.title}">
				</div>

				<div class="form-group">
					<label>Text Area</label>
					<textarea class="form-control" rows="3" name="content"
						readonly="readonly">${board.content}</textarea>
				</div>

				<div class="form-group">
					<label>Writer</label> 
					<input class="form-control" name="writer"
						readonly="readonly" value="${board.writer}">
				</div>

				<%-- <button class="btn btn-default" onclick="location.href='/board/modify?pageNum=${paging.pageNum}&amount=${paging.amount}&bno=${board.bno}'">게시글 수정</button> --%>
				<button class="btn btn-default" data-oper="modify">게시글 수정</button>
				<button class="btn btn-default" data-oper="list">목록</button>

				<form id="actionForm" action="/board/modify" method="get">
					<input type="hidden" name="bno" value="${board.bno}"> 
					<input type="hidden" name="type" value="${paging.type}"> 
					<input type="hidden" name="keyword" value="${paging.keyword}"> 
					<input type="hidden" name="pageNum" value="${paging.pageNum}"> 
					<input type="hidden" name="amount" value="${paging.amount}">
				</form>

			</div>
				
		</div>
	</div>
</div>

<!-- 댓글 표시 -->

<div class='row'>
   <div class='col-lg-12'>
      <div class='panel panel-default'>
         <div class='panel-heading'>
            <i class='fa fa-comments fa-fw'></i> 댓글
            <button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'>댓글달기</button>
         </div>
         
         <div class='panel-body'>
            <ul class='chat'>
            <!-- 
               <li class='left clearfix' data-rno='12'>
                  <div>
                     <div class='header'>
                        <strong class='primary-font'>댓글작성자</strong>
                        <small class='pull-right text-muted'>댓글작성일자</small>
                     </div>
                     <p>댓글 내용</p>
                  </div>
               </li>
                -->
            </ul>
         </div>
         <!-- 댓글 페이지 표시 -->
         <div class="panel-footer">
         
         </div>
      </div>
   </div>
</div>

<!-- 댓글 추가 모달창 -->
<div class='modal fade' id='myModal' tabindex='-1' role='dialog' aria-labelledby='myModalLabel' aria-hidden='true'>
   <div class='modal-dialog'>
      <div class='modal-content'>
         <div class='modal-header'>
            <button type='button' class='close' data-dismiss='modal' aria-hidden='true'>&times;</button>
            <h4 class='modal-title' id='myModalLabel'>댓글 작성창</h4>
         </div>
         
         <div class='modal-body'>
            <div class='form-group'>
               <label>댓글</label>
               <input class='form-control' name='reply' value=''>
            </div>
            <div class='form-group'>
               <label>댓글 작성자</label>
               <input class='form-control' name='replyer' value=''>
            </div>
            <div class='form-group'>
               <label>작성일자</label>
               <input class='form-control' name='replyDate' value=''>
            </div>
         </div>
         
         <div class='modal-footer'>
            <button id='modalModBtn' type='button' class='btn btn-warning' data-dismiss='modal'>수정</button>
            <button id='modalRemoveBtn' type='button' class='btn btn-danger' data-dismiss='modal'>삭제</button>
            <button id='modalRegisterBtn' type='button' class='btn btn-primary' data-dismiss='modal'>등록</button>            
            <button id='modalCloseBtn' type='button' class='btn btn-default' data-dismiss='modal'>닫기</button>
            
         </div>
      </div>
   </div>
</div>


<!-- 댓글 관련 함수 선언 reply.js  -->
<script src="/resources/js/reply.js"></script>	

<script type="text/javascript">
	
	// 댓글 페이지 버튼 추가
	let pageNum = 1;
	const replePageFooter = $('.panel-footer');
	
	function showReplyPage(replyCnt){
		
		let endNum = Math.ceil(pageNum / 5.0) * 5;
		let startNum = endNum - 4;
		
		let prev = startNum != 1;
		let next = false;
		
		if(endNum * 5 >= replyCnt){
			endNum = Math.ceil(replyCnt / 5.0);
		}
		
		if(endNum * 5 < replyCnt){
			next = true;
		}
		
		let str = "<ul class='pagination pull-right'>";
		if(prev){
			str += "<li class='page-item'>";
			str += "  <a class='page-link' href='" + (startNum - 1) + "'>이전</a>";
			str += "</li>";
 		}
		for(let i=startNum; i<=endNum;i++){
			let active = pageNum == i ? 'active':'';
			str += "<li class='page-item " + active + "'>";
			str += "  <a class='page-link' href='" + i + "'>" + i + "</a>";
			str += "</li>";
		}
		if(next){
			str += "<li class='page-item'>";
			str += "  <a class='page-link' href='" + (endNum + 1) + "'>다음</a>";
			str += "</li>";
 		}
		str += "</ul>";
		
		replePageFooter.html(str);
		
	}
	
	replePageFooter.on('click', 'li a', function(e){
		e.preventDefault();
		
		pageNum = $(this).attr('href');;
		showList(pageNum);
	})
	
	
	//게시물 번호 가져오기 
	const bnoValue = '<c:out value="${board.bno}" />';
	
	// 댓글 추가 모달창 띄우기
	const modal = $('#myModal');
	
	const modalInputReply = modal.find('input[name="reply"]');
	const modalInputReplyer = modal.find('input[name="replyer"]');
	const modalInputReplyDate = modal.find('input[name="replyDate"]');
	
	const modalModBtn = $('#modalModBtn');
	const modalRemoveBtn = $('#modalRemoveBtn');
	const modalRegisterBtn = $('#modalRegisterBtn');
	
	
	modalRemoveBtn.on('click', function(){
		const rno = modal.data('rno');
		
		replyService.remove(rno,
				function(result){

					//modal.modal('hide');
					alert(result);
					
					modal.find('input').val('');
					showList(pageNum);
		})
	})
	
	modalModBtn.on('click', function(){
		const rno = modal.data('rno')
		
		let reply = {
			rno: rno,
			reply: modalInputReply.val()
		}
		
		replyService.modify(reply, 
				function(result){
					//modal.modal('hide');
					alert(result);
					
					modal.find('input').val('');
					showList(pageNum);
			
				}
		)
		
	})
	
	// 댓글달기 버튼 클릭(필요한 버튼, 내용 셋팅후 모달창 띄우기)
	$('#addReplyBtn').on('click', function(){
		modalInputReplyDate.closest('div').hide();		// closest('div') : 인접한 부모태그중에 가까운 div찾기
		
		modal.find('button[id != "modalCloseBtn"]').hide();
		modal.find('button[class="close"]').show();
		modalRegisterBtn.show();

		modal.find('input').val('');
		
		modal.modal('show');
	})	
	
	modalRegisterBtn.on('click', function(){
		let reply = {
				bno : bnoValue,
				reply : modalInputReply.val(),
				replyer : modalInputReplyer.val()
		}
		//console.log(reply);
		
		replyService.add(reply, 
				function(result) {
					alert(result);
					modal.find('input').val('');
					showList(-1);
				}
		);
	})
	
	// li 댓글들은 동적으로 추가된 html이므로 가져올수 없으므로 부모(ul)에 클릭이벤트를 주고 li로 위임시켜줌
	$('.chat').on('click', 'li', function(){
		const rno = $(this).data('rno');	// li 태그에 dataset이 설정되어있음!!
		// console.log(rno);
		
		// 수정, 삭제 할때 rno가 필요하므로 추가함(input hidden으로도 할수 있음)
		// 모달에 데이터셋 추가 할 수 있음
		modal.data('rno', rno);
		// console.log(modal.data('rno'));
		
		replyService.get(rno,
				function(result){
					modalInputReply.val(result.reply);
					modalInputReplyer.val(result.replyer);
					modalInputReplyDate.val(replyService.displayTime(result.replyDate));
					
					modalInputReplyDate.attr('readonly', 'readonly');
					
					modal.find('button[id != "modalCloseBtn"]').hide();
					modal.find('button[class="close"]').show();
					modalModBtn.show();
					modalRemoveBtn.show();
					
					modalInputReplyDate.closest('div').show();
					
					modal.modal('show');
				}		
		)
	})
	
	
	const replyUL = $('.chat');
	
	showList(pageNum);
	
	// 댓글관련 작업 추가(reply.js)
	function showList(page){
		
		// 서버에서 데이터 가져옴
		replyService.getList(
				{bno:bnoValue, page:page || 1},
				
				function(replyCnt, list){
					
					// console.log(list);
					
					// page -1이면 마지막 페이지로 이동시켜줌(추가했을때...)
					if(page == -1){
						pageNum = Math.ceil(replyCnt / 5.0);
						showList(pageNum);
						return;
					}
					
					// 화면출력
					let str = '';	// 태그 저장용
					
					if(list == null || list.length == 0){
						replyUL.html('');	// UL 태그 비움
						return;
					}
					
					for(let i=0; i<list.length; i++){
						str += "<li class='left clearfix' data-rno='" + list[i].rno + "'>";
						str += "  <div>";
						str += "    <div class='header'>";
						str += "      <strong class='primary-font'>" + list[i].replyer + "</strong>";
						str += "      <small class='pull-right text-muted'>" + replyService.displayTime(list[i].replyDate) + "</small>";
						str += "    </div>";
						str += "	<p>" + list[i].reply + "</p>";
						str += " </div>";
						str += "</li>";							
					}
					replyUL.html(str);
					
					// page
					showReplyPage(replyCnt);
				}
		)
	}
	
	const actionForm = $('#actionForm');

	$('button[data-oper="modify"]').on('click', function() {
		actionForm.attr('action', '/board/modify').submit();
	});

	$('button[data-oper="list"]').on('click', function() {
		actionForm.attr('action', '/board/list').submit();
	});
</script>

<%@ include file="../includes/footer.jsp"%>