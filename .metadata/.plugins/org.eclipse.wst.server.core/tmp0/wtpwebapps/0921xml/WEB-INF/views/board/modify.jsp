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
              
              <button data-oper="modify" class="btn btn-primary">수정</button>
              <button data-oper="remove" class="btn btn-danger">삭제</button>
              <button data-oper="list" class="btn btn-default">목록</button>
			</form>
         </div>
      </div>
   </div>
</div>

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
		} else {
			 location.href='/board/list';
			 return;
		}
		
		formTag.submit();
		
	})

</script>

<%@ include file="../includes/footer.jsp"%>