<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        <table class="table table-hover table-sm text-center">
            <thead class="thead-light">
                <tr>
                    <th scope="col" style="width:10%;">번호</th>
                    <th scope="col" style="width:55%;">제목</th>
                    <th scope="col" style="width:15%;">작성자</th>
                    <th scope="col" style="width:10%;">작성일</th>
                    <th scope="col" style="width:10%;">조회</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>&nbsp;</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>반갑습니다</td>
                    <td>마시***</td>
                    <td>2022-05-03</td>
                    <td>50</td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>안녕하세요</td>
                    <td>호랑이***</td>
                    <td>2022-05-02</td>
                    <td>10</td>
                </tr>
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
        $(function(){
            $('tr').click(function(){
                location.href="boardDetailView.jsp";
            })
        })
    </script>
</body>
</html>