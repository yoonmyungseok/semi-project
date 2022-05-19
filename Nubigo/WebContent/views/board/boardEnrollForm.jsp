<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>자유게시판</title>
</head>
<body>
<%@ include file="../common/header.jsp" %>
    <div id="wrap">
        <h2 class="mb-4 p-2">자유게시판</h2>
        <hr>
        <form action="" method="post" id="board-Enroll">
            <div class="form-group row">
                <label class="col-2 col-form-label font-weight-bold">제목</label>
                <div class="col-10">
                    <input type="text" class="form-control" id="inputTitle" placeholder="제목을 입력해주세요." required>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-2 col-form-label font-weight-bold">내용</label>
                <div class="col-10">
                    <textarea class="form-control" id="boardTextarea" placeholder="내용을 입력해주세요." rows="10" required style="resize:none;"></textarea>
                </div>
            </div>
            <!--
            <div class="form-group row">
                <label class="col-2 col-form-label font-weight-bold">첨부파일</label>
                <div class="input-group col-10">
                    <div class="custom-file">
                        <input type="file" class="custom-file-input" id="inputGroupFile01" aria-describedby="inputGroupFileAddon01">
                        <label class="custom-file-label" for="inputGroupFile01" data-browse="파일 첨부">파일 용량 제한: 30MB</label>
                    </div>
                </div>
            </div>
            -->
        </form>
        <hr>
        <div class="float-right">
            <button class="btn btn-outline-nubigoSub" onclick="location.href='boardList.html';">취소</button>
            <button class="btn btn-nubigoMain" onclick="insertBoard();">작성완료</button>
        </div>
    </div>
    <script>
        /*
        $("input[type='file']").on('change',function(){
            $(this).next('.custom-file-label').html(event.target.files[0].name);
        });
        */
        function insertBoard(){
            alert('게시글이 등록되었습니다.');
            location.href="boardList.html";
        }
    </script>
</body>
</html>