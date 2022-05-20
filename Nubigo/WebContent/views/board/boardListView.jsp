<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.nubigo.board.model.vo.Board" %>
<%
	ArrayList<Board> list=(ArrayList<Board>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>자유게시판</title>
</head>
<body>
    <%@ include file="../common/header.jsp" %>
    <div id="wrap">
        <h2 class="mb-4 p-2">자유게시판</h2>
        <hr>
        <div class="btn-group btn-group-toggle mb-2 btn-group-sm" data-toggle="buttons">
            <label class="btn btn-outline-nubigoMain active">
                <input type="radio" name="options" id="option1" checked>최신순
            </label>
            <label class="btn btn-outline-nubigoMain">
                <input type="radio" name="options" id="option2">조회순
            </label>
        </div>
        <table class="table table-hover table-sm text-center list-area">
            <thead class="thead-light">
                <tr>
                    <th scope="col" style="width:10%;">번호</th>
                    <th scope="col" style="width:55%;">제목</th>
                    <th scope="col" style="width:20%;">작성자</th>
                    <th scope="col" style="width:15%;">작성일</th>
                </tr>
            </thead>
            <tbody>
                <%if(list.isEmpty()){ %>
                <!-- 리스트가 비어있을 경우 -->
                    <tr>
                        <td colspan="4">존재하는 게시글이 없습니다.</td>
                    </tr>
                <%}else{ %>
                <!-- 리스트가 존재할 경우 -->
                <%for(Board b:list){%>
                        <tr>
                            <td><%=b.getBoardNo()%></td>
                            <td><%=b.getBoardTitle() %></td>
                            <td><%=b.getMemberId() %></td>
                            <td><%=b.getBoardDate() %></td>
                        </tr>
                    <%} %>
                <%} %>
            </tbody>
        </table>
        <div class="d-flex mb-5">
            <form class="form-inline">
                <div class="mr-sm-2">
                    <select class="custom-select">
                        <option selected>제목+내용</option>
                        <option value="1">제목</option>
                        <option value="2">내용</option>
                        <option value="3">작성자</option>
                    </select>
                </div>
                <div class="input-group">
                    <input type="search" class="form-control" placeholder="검색어를 입력해 주세요" aria-label="search"
                        aria-describedby="button-addon2">
                    <div class="input-group-append">
                        <button class="btn btn-nubigoMain" type="button" id="button-addon2"><i class="bi bi-search"></i></button>
                    </div>
                </div>
            </form>
            <a class="btn btn-nubigoMain ml-auto" type="button" href="boardEnrollForm.jsp">글쓰기</a>
        </div>
        <nav>
            <ul class="pagination justify-content-center">
                <li class="page-item disabled">
                    <button class="page-link" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </button>
                </li>
                <li class="page-item active"><button class="page-link">1</button></li>
                <li class="page-item"><button class="page-link" >2</button></li>
                <li class="page-item"><button class="page-link" >3</button></li>
                <li class="page-item"><button class="page-link" >4</button></li>
                <li class="page-item"><button class="page-link" >5</button></li>
                <li class="page-item">
                    <button class="page-link" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </button>
                </li>
            </ul>
        </nav>
    </div>
    <%@ include file="../common/footer.jsp" %>
    <script>
        $(function () {
            $(".list-area>tbody>tr").click(function () {
                location.href = "<%=contextPath%>/detail.bo?bno=" + $(this).children().eq(0).text();
            })
        })
    </script>
</body>
</html>