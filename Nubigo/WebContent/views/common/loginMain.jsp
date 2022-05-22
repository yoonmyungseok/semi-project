<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="resources/css/login-style.css" rel="stylesheet">
</head>
<body>
	<%@ include file="menubar.jsp" %>
	
	<div class="login-wrap" style="position:relative;">
        <div id="login-background">
            <!-- 새로고침 시 이미지 랜덤 -->
            <script type="text/javascript">
                var mb=new Array()
                mb[0]="<img src='resources/images/random-images-1.jpg' border='0'></a>";
                mb[1]="<img src='resources/images/random-images-2.jpg' border='0'></a>";
                mb[2]="<img src='resources/images/random-images-3.jpg' border='0'></a>";
                
                var whichquote=Math.floor(Math.random()*(mb.length))
                document.write(mb[whichquote])

                </script>
        </div>
        <!-- 로그인 폼 -->
        <div class="login-form-background" style="position:absolute;">
            <span id="login-form-login">로그인</span><br>
            <form class="login-form" action="login.me" method="post">
                <input type="text" id="login-id-input" name="userId" placeholder="아이디"><br>
                <input type="password" id="login-pwd-input" name="userPwd" placeholder="비밀번호"><br>
                <input type="checkbox" id="login-idcheckbox" name="login-idcheckbox" value="login-idcheckbox">
                <label id="login-idcheckbox-idsave" for="login-idcheckbox">아이디 저장</label>
                <span class="login-id-pwd-search"><a href="" class="login-id-pwd-search">아이디/비밀번호 찾기</a></span>
                <button type="submit" id="login-loginbutton">로그인</button><br>
                <button type="button" id="login-signupbutton">회원가입</button>
            </form>
        </div>
    </div>
	
	<%@ include file="footer.jsp" %>
</body>
</html>