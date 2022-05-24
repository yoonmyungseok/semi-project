<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.nubigo.board.model.vo.Board, com.nubigo.common.model.vo.PageInfo" %>
<%
    ArrayList<Board> list=(ArrayList<Board>)request.getAttribute("list");
PageInfo pi=(PageInfo)request.getAttribute("pi");
//페이징바 관련 변수
int currentPage=pi.getCurrentPage();
int startPage=pi.getStartPage();
int endPage=pi.getEndPage();
int maxPage=pi.getMaxPage();

String keyword=String.valueOf(request.getAttribute("keyword"));
String search=String.valueOf(request.getAttribute("search"));

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
    <%@ include file="../common/menubar.jsp" %>
    <div id="wrap">
        <h2 class="mb-4 p-2">자유게시판</h2>
        <hr>
        <div class="btn-group btn-group-toggle mb-2 btn-group-sm" data-toggle="buttons">
            <button class="btn btn-outline-nubigoMain active" type="radio" name="options" id="option1" value="new">최신순</button>
            <button class="btn btn-outline-nubigoMain" type="radio" name="options" id="option2" value="old">오래된순</button>
        </div>
        <table class="table table-hover table-sm text-center list-area">
            <thead class="thead-light">
                <tr>
                    <th scope="col" style="width:10%;">번호</th>
                    <th scope="col" style="width:55%;">제목</th>
                    <th scope="col" style="width:15%;">작성자</th>
                    <th scope="col" style="width:15%;">작성일</th>
                    <th scope="col" style="width:5%;">조회</th>
                </tr>
            </thead>
            <tbody>
                <%if(list.isEmpty()){ %>
                <!-- 리스트가 비어있을 경우 -->
                    <tr style="pointer-events: none;">
                        <td colspan="5">존재하는 게시글이 없습니다.</td>
                    </tr>
                <%}else{ %>
                <!-- 리스트가 존재할 경우 -->
                <%for(Board b:list){%>
                        <tr>
                            <td><%=b.getBoardNo()%></td>
                            <td><%=b.getBoardTitle() %></td>
                            <td><%=b.getMemberId() %></td>
                            <td><%=b.getBoardDate() %></td>
                            <td><%=b.getCount() %></td>
                        </tr>
                    <%} %>
                <%} %>
            </tbody>
        </table>
        <div class="d-flex mb-5">
            <form class="form-inline" method="get" action="<%=contextPath %>/boardSearch.bo">
            <input type="hidden" name="currentPage" value="1">
                <div class="mr-sm-2">
                    <select class="custom-select" name="keyword">
                        <option value="제목내용" selected>제목+내용</option>
                        <option value="제목">제목</option>
                        <option value="내용">내용</option>
                        <option value="작성자">작성자</option>
                    </select>
                </div>
                <div class="input-group">
                    <input type="search" class="form-control" placeholder="검색어를 입력해 주세요" name="boardSearch">
                    <div class="input-group-append">
                        <button class="btn btn-nubigoMain" type="submit" id="button-addon2"><i class="bi bi-search"></i></button>
                    </div>
                </div>
            </form>
            <%if(loginUser!=null){ %>
            <a class="btn btn-nubigoMain ml-auto" type="button" href="<%=contextPath%>/enrollForm.bo">글쓰기</a>
            <%} %>
        </div>
        <!--페이징바-->
        <nav>
            <ul class="pagination justify-content-center">
                <%if(currentPage!=1){ %>
                <li class="page-item">
                    <button class="page-link" onclick="location.href='<%=contextPath%>/list.bo?currentPage=<%=currentPage-1%>';">
                        <span aria-hidden="true">&laquo;</span>
                    </button>
                </li>
                <%}%>
                <%for(int p=startPage;p<=endPage;p++){ %>
                    <% if(p!=currentPage){ %>
                    <li class="page-item"><button class="page-link" onclick="location.href='<%=contextPath%>/list.bo?currentPage=<%=p%>';"><%=p %></button></li>
                    <%}else{ %>
                    <li class="page-item active"><button class="page-link"><%=p %></button></li>
                    <%} %>
                <%} %>
                <%if(currentPage!=maxPage){ %>
                <li class="page-item">
                    <button class="page-link" onclick="location.href='<%=contextPath%>/list.bo?currentPage=<%=currentPage+1%>';">
                        <span aria-hidden="true">&raquo;</span>
                    </button>
                </li>
                <%}%>
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