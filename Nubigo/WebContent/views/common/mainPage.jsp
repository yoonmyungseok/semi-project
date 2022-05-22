<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- <link href="resources/css/footer-style.css" rel="stylesheet"> -->
    <title>index</title>
</head>
<body>

	<%@ include file="menubar.jsp" %>

    <!-- 이미지 슬라이드 -->
    <div class="main-imageslide-wrap">
        <div class="main-imageslide" style="position:relative;">
            <div id="main-imageslide-text" style="position:absolute;">YOUR WORLD OF JOY</div>
            <div id="main-imageslide-text2" style="position:absolute;">누비고와 함께 세상의 모든 즐거움을 찾아보세요</div>
            <div>
                <form class="example" action="/action_page.php" style="margin:auto;max-width:300px">
                    <input type="text" placeholder="여행지를 검색해보세요." name="search2">
                    <button type="submit"><i class="fa fa-search"></i></button>
                </form>
            </div>
            <div id="demo" class="carousel slide" data-ride="carousel">
				<!-- 메인 이미지 슬라이드 -->
				<div class="carousel-inner">
					<div class="carousel-item active">
				    	<img src="resources/images/main-images-1.jpg">
					</div>
				  	<div class="carousel-item">
				    	<img src="resources/images/main-images-2.jpg">
				  	</div>
				  	<div class="carousel-item">
					    <img src="resources/images/main-images-3.jpg">
				  	</div>
				</div>
				
				<!-- 왼쪽/오른쪽 화살표 -->
		        <a class="carousel-control-prev" href="#demo" data-slide="prev">
		          	<span class="carousel-control-prev-icon"></span>
		        </a>
		        <a class="carousel-control-next" href="#demo" data-slide="next">
		      	    <span class="carousel-control-next-icon"></span>
		        </a>
			</div>
		</div>
	</div>

       <!-- 여행지 리스트 슬라이드 -->

	<%@ include file="footer.jsp" %>

    
</body>
</html>