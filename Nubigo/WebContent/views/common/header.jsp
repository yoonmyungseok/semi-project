<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://kit.fontawesome.com/c8ea39d107.js" crossorigin="anonymous"></script>
    <!--아이콘-->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <!--customCSS-->
    <link rel="stylesheet" href="/nbg/resources/css/style.css">
    <link rel="stylesheet" href="/nbg/resources/css/header-style.css" >
    <title>header</title>
</head>
<body>
    <div class="header-wrap">
        <div id="header">
            <div id="header_1" style="margin-top:10px"><a href=""><img src="/nbg/resources/images/Nubigo_logo_1.png" class="logo"></a></div> <!-- 로고 이미지 파일(클릭 시 홈 화면 이동 구현 할거면 contextPath 설정하세요) -->
            <div id="header_2">
                <div class="search">
                <form action="" style="position:relative;">
                    <button class="btn_search" style="position:relative;"></button>
                    <input class="btn_text" type="text" placeholder="여행지를 검색해보세요." style="border:0 solid black; border-radius: 30px; padding-left: 25px;">
                </form>
                </div>
            </div>
            <div id="header_3">
                <ul class="nav nav-pills" style="margin-top: 3px; float: right;">
                    <li class="nav-item" id="nav-1">
                        <a class="nav-link" href="#" style="color: gray;">고객센터</a>
                    </li>
                    <li class="nav-item" id="nav-2">
                        <b><a class="nav-link" href="#" style="color: black;">회원가입</a></b>
                    </li>
                    <li class="nav-item">
                        <b><a class="nav-link active" id="nav-login" style="border-radius: 20px; background-color: #00b8ff;" href="#">로그인</a></b>
                    </li>
                    <!-- 맨 오른쪽 사용자 로고는 이미지를 사용하거나 아이콘 사용해서 a태그로 마이페이지 링크 걸어서 클릭시 이동 시키고 c:if 써서 비 로그인 시에는 로그인 화면으로 보내는게 괜찮을듯?-->
                    <!-- 사용자 로고 자리-->
                </ul>
            </div>
        </div>
        <hr style="border: solid lightgrey; border-width: 1px 0 0; margin: 5px; padding: 5px; margin-top: 15px;">
        <div id="navigator">
            <li class="menu">
                <a href="">여행지역 둘러보기</a>
            </li>
            <li class="menu">
                <a href="">리뷰 페이지</a>
            </li>
            <li class="menu">
                <a href="">나만의 여행리스트</a>
            </li>
            <li class="menu">
                <a href="/nbg/views/board/boardList.jsp">자유게시판</a>
            </li>
        </div>
    </div>
</body>
</html>