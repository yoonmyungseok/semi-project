<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 전체 조회</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    
    <!-- jQuery library -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    
    <!-- Popper JS -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    
    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    <!--아이콘-->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <!--customCSS-->
    <link rel="stylesheet" href="../../resources/css/style.css">
</head>
<body>
    <div id="wrap" class="container">
        <div class="row">
            <div class="col-3 bg-light vh-100">
                <ol class="list-group list-group-flush">
                    <li class="list-group-item bg-transparent">
                        1. 회원 관리
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item">- 회원 리스트 조회</li>
                            <li class="list-group-item">- 회원 정보 수정</li>
                            <li class="list-group-item">- 회원 탈퇴 관리</li>
                        </ul>
                    </li>
                    <li class="list-group-item bg-transparent">
                        2. 게시글 관리
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item">-게시글 전체 조회</li>
                            <li class="list-group-item">-게시글 신고 관리</li>
                            <li class="list-group-item">-게시글 수정/삭제/복구</li>
                        </ul>
                    </li>
                    <li class="list-group-item bg-transparent">
                        3. 리뷰 관리
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item">-리뷰 전체 조회</li>
                            <li class="list-group-item">-리뷰 신고 관리</li>
                            <li class="list-group-item">-리뷰 수정/삭제/복구</li>
                        </ul>
                    </li>
                    <li class="list-group-item bg-transparent">
                        4. 여행지 관리
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item">-여행지 추가/수정/삭제</li>
                        </ul>
                    </li>
                    <li class="list-group-item bg-transparent">
                        5. 통계 관리
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item">-게시글 신고 현황</li>
                            <li class="list-group-item">-평점 관리</li>
                        </ul>
                    </li>
                    <li class="list-group-item bg-transparent">
                        6. 문의 관리
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item">-문의 전체 조회</li>
                            <li class="list-group-item">- 답변 작성/수정/삭제</li>
                        </ul>
                    </li>
                    <li class="list-group-item bg-transparent">
                        7. FAQ 관리
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item">- FAQ 작성/수정/삭제</li>
                        </ul>
                    </li>
                </ol>
            </div>
            <div class="col-9">
                <h2 class="mb-4 p-2">게시글 전체 조회</h2>
                <hr>
                <div class="mb-4">
                    <form class="form-group row">
                        <div class="col-3">
                            <select class="custom-select">
                                <option selected>제목+내용</option>
                                <option value="1">제목</option>
                                <option value="2">내용</option>
                                <option value="3">작성자</option>
                            </select>
                        </div>
                        <div class="input-group col-9">
                            <input type="search" class="form-control" placeholder="검색어를 입력해 주세요" aria-label="search"
                                aria-describedby="button-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-nubigoMain" type="button" id="button-addon2"><i class="bi bi-search"></i></button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="d-flex">
                    <label class="ml-auto">게시글</label><label><b>2</b></label>
                </div>
                
                <table class="table table-hover table-sm text-center table-bordered">
                    <thead class="thead-light">
                        <tr>
                            <th scope="col" style="width:5%;"><input type="checkbox" id="allCheck"></th>
                            <th scope="col" style="width:10%;">번호</th>
                            <th scope="col" style="width:40%;">제목</th>
                            <th scope="col" style="width:20%;">작성자</th>
                            <th scope="col" style="width:15%;">작성일</th>
                            <th scope="col" style="width:10%;">조회</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><input type="checkbox" name="chk"></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="chk"></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="chk"></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="chk"></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="chk"></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="chk"></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="chk"></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="chk"></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="chk"></td>
                            <td>2</td>
                            <td>반갑습니다</td>
                            <td>마시멜로우</td>
                            <td>2022-05-03</td>
                            <td>50</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="chk"></td>
                            <td>1</td>
                            <td>안녕하세요</td>
                            <td>호랑이와사자</td>
                            <td>2022-05-02</td>
                            <td>10</td>
                        </tr>
                    </tbody>
                </table>
                <div class="d-flex">
                    <button class="btn btn-nubigoMain ml-auto mb-3" id="deleteBoard" disabled>삭제</button>
                </div>
                <nav aria-label="Page navigation example">
                    <ul class="pagination justify-content-center myPagination">
                        <li class="page-item disabled">
                            <a class="page-link" href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <li class="page-item active"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item"><a class="page-link" href="#">4</a></li>
                        <li class="page-item"><a class="page-link" href="#">5</a></li>
                        <li class="page-item">
                            <a class="page-link" href="#" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
    <script>
        $(function () {
            $("#allCheck").click(function () {
                if ($("#allCheck").is(":checked")){
                    $("input[name=chk]").prop("checked", true);
                    $("#deleteBoard").prop("disabled", false);
                }
                else {
                    $("input[name=chk]").prop("checked", false);
                    $("#deleteBoard").prop("disabled", true);
                }
            });

            $("input[name=chk]").click(function () {
                var total = $("input[name=chk]").length;
                var checked = $("input[name=chk]:checked").length;

                if (total != checked) $("#allCheck").prop("checked", false);
                else $("#allCheck").prop("checked", true);

                if ($("input[name=chk]").is(":checked")) {
                    $("#deleteBoard").prop("disabled", false);
                } else {
                    $("#deleteBoard").prop("disabled", true);
                }
            });
            $("#deleteBoard").click(function(){
                if (confirm("게시글을 삭제하시겠습니까?") == true) {    //확인
                    alert('삭제가 완료되었습니다.');
                } else {   //취소
                    return false;
                }
            })
        }); 
    </script>
</body>
</html>