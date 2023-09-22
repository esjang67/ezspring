<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../includes/header.jsp"%>

<div class="row">
   <div class="col-lg-12">
      <h1 class="page-header">Tables</h1>
   </div>
   <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
   <div class="col-lg-12">
      <div class="panel panel-default">
         <div class="panel-heading">
         	Board List Page
			<a href="/board/add" class="btn btn-primary pull-right btn-xs">글작성</a>
			<button id="addBtn" class="btn btn-danger pull-right btn-xs">글작성</button>
         </div>
         
         <div class="panel-body">
            <table class="table table-striped table-bordered table-hover">
               <thead>
                  <tr>
                     <th>번호</th>
                     <th>제목</th>
                     <th>작성자</th>
                     <th>작성일</th>
                     <th>수정일</th>
                  </tr>
               </thead>
               
               <c:forEach items="${list}" var="board">
               		<tr>
	               		<td>${board.bno}</td>
	               		<td>
	               			<a href="get?bno=${board.bno}">${board.title}</a>
	               		</td>
	               		<td>${board.writer}</td>
	               		<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regdate}"/></td>
	               		<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.updatedate}"/></td>
               		</tr>
               </c:forEach>

            </table>
            
            <div id="myModal" class="modal fade" tabindex="-1" area-hidden="true" role="dialog">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h5 class="modal-title">Modal title</h5>
                  </div>
                  <div class="modal-body">
                    <p>Modal body text goes here.</p>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <!-- <button type="button" class="btn btn-primary">Save changes</button> -->
                  </div>
                </div>
              </div>
            </div>            


			<div class="pull-right">
			    <ul class="pagination">
				
				<c:if test="${pageInfo.prev}">
			         <li class="page-item">
			            <a class="page-link" href="${pageInfo.startPage - 1}">Previous</a>
			         </li>
			        </c:if>
			         
		    	<c:forEach begin="${pageInfo.startPage}" end="${pageInfo.endPage}" var="num">
			         <li class="page-item ${pageInfo.paging.pageNum==num ? 'active' : '' }">
			            <a class="page-link" href="${num}">${num}</a>
			         </li>
				</c:forEach>
				
				<c:if test="${pageInfo.next}">
			         <li class="page-item">
			            <a class="page-link" href="${pageInfo.endPage + 1}">Next</a>
			         </li>
				</c:if>
			    </ul>
			</div>
         
         </div>
      </div>
   </div>
</div>

<!-- 데이터 저장용 -->
<form id="actionForm" action="/board/list" method="get">
	<input type="hidden" name="pageNum" value="${pageInfo.paging.pageNum}">
	<input type="hidden" name="amount" value="${pageInfo.paging.amount}">
</form>

<script>

	// 페이지작업
	const actionForm = $('#actionForm')
	$('.page-link').on('click', function(e){
		e.preventDefault();
		
		// 선택한 페이지버튼의 번호(href)를 보여줘
		console.log($(this).attr('href'));
		
		actionForm.find('input[name="pageNum"]').val($(this).attr('href'));
		actionForm.submit();
		
	})
	//  $('.page-item > .page-link')
	

	// 글작성후 bno가져오기
	$(document).ready(()=> {
		const result = '<c:out value="${result}" />';
		console.log("result : " + result);
		
		if(result != ''){ 
			if(isNaN(result)){  // 숫자일 경우 false, 숫자가 아닐경우 true
				$(".modal-body").html(result);
			} else {
				$(".modal-body").html(result + "번 게시글 등록 완료");
			}
			$("#myModal").modal("show");
			
		}
	})
	
	$('#addBtn').on('click', ()=> {
		self.location = '/board/add';
	});

</script>

<%@ include file="../includes/footer.jsp"%>