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
                 <input class="form-control" name="bno" readonly="readonly" value="${board.bno}">
              </div>
            
              <div class="form-group">
                 <label>Title</label>
                 <input class="form-control" name="title" readonly="readonly" value="${board.title}">
              </div>
              
              <div class="form-group">
                 <label>Text Area</label>
                 <textarea class="form-control" rows="3" name="content" readonly="readonly">${board.content}</textarea>
              </div>
              
              <div class="form-group">
                 <label>Writer</label>
                 <input class="form-control" name="writer" readonly="readonly" value="${board.writer}">
              </div>
              
              <button class="btn btn-default" onclick="location.href='/board/modify?bno=${board.bno}'">게시글 수정</button>
              <button class="btn btn-default" onclick="location.href='/board/list'">목록</button>

         </div>
      </div>
   </div>
</div>

<%@ include file="../includes/footer.jsp"%>