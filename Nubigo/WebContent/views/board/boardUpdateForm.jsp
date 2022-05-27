<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.nubigo.board.model.vo.*" %>
<%
Board b=(Board)request.getAttribute("b");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>자유게시판</title>
</head>
<body>
<%@ include file="../common/menubar.jsp" %>
    <div id="wrap">
        <h2 class="mb-4 p-2">자유게시판</h2>
        <hr>
        <form action="<%=contextPath %>/update.bo" method="post" id="update-form" enctype="multipart/form-data">
            <input type="hidden" name="bno" value="<%=b.getBoardNo() %>">
            <div class="form-group row">
                <label class="col-2 col-form-label font-weight-bold">제목</label>
                <div class="col-10">
                    <input type="text" class="form-control" name="title" id="inputTitle" required value="<%=b.getBoardTitle()%>">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-2 col-form-label font-weight-bold">내용</label>
                <div class="col-10">
                    <textarea class="form-control" name="content" id="boardTextarea" rows="10" required style="resize:none;"><%=b.getBoardContent() %></textarea>
                </div>
            </div>
            
            <div class="form-group row">
                <label class="col-2 col-form-label font-weight-bold">첨부파일</label>
                <div class="input-group col-10">
                    <div class="custom-file">
                        <input type="hidden" name="originFileName" value="<%=b.getAttachmentName()%>">
                        <input type="file" class="custom-file-input" id="BoardFileUpload" name="reUpfile" >
                        <label class="custom-file-label" for="BoardFileUpload" data-browse="파일 첨부">
                        <% if(b.getAttachmentName()==null){ %>
                        파일 용량 제한: 30MB
                        <%}else{ %>
                        <%=b.getAttachmentName() %>
                        <%} %>
                        </label>
                    </div>
                </div>
            </div>
            <% if(b.getAttachmentName()!=null){ %>
            <div class="form-group row">
                <div class="col-2"></div>
                <div class="col-10">
                    <%=b.getAttachmentName() %>
                    <a type="button" onclick="atDelete();"><i class="bi bi-x-square-fill"></i></a>
                    <input type="hidden" name="atDelete" id="atDelete">
                </div>
            </div>
            <%}%>
        <hr>
        <div class="float-right">
            <button class="btn btn-outline-nubigoSub" onclick="history.back();">취소</button>
            <button class="btn btn-nubigoMain" type="submit">작성완료</button>
        </div>
        </form>
    </div>
    <script>
        $(function(){
            $("input[type='file']").on('change',function(){
                $(this).next('.custom-file-label').html(event.target.files[0].name);
            });
        })
        function atDelete(){
            $('#atDelete').attr('value','atDelete');
            $('#atDelete').parent().parent().addClass('d-none');
            $(".custom-file-label").html("파일 용량 제한: 30MB");
        }
    </script>
</body>
</html>