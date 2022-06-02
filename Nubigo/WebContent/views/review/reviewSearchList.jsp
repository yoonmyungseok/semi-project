<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.ArrayList, com.nubigo.review.model.vo.Review, com.nubigo.common.model.vo.PageInfo"%>
<%
	ArrayList<Review> list = (ArrayList<Review>)request.getAttribute("list");
    String keyword = (String)request.getAttribute("keyword");
    PageInfo pi = (PageInfo)request.getAttribute("pi");
    int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여행기 검색 결과</title>
<style>
.outer {
            width : 1200px;
            height : 1000px;
            margin : auto;
            margin-top : 50px;
            margin-bottom : 50px;
            border : 1px solid black;
            box-sizing : border-box;
        }

        .body {
            width : 1000px;
            height : 100%;
            margin : 0px 100px;
            border : 1px solid red;
            box-sizing : border-box;
        }

        .body>div {
            float : left;
            box-sizing : border-box;
            height : 100%;
        }

        .body1 {
            width : 16%;
            border : 1px solid blue;
            padding-top : 10px;
        }

        .body2 {
            width : 84%;
            border : 1px solid green;
        }

        .body1>div {
            height : 45px;
        }

        .review {
            width : 100%;
            height : 45px;
            line-height : 45px;
            font-size : 16px;
            color : white;
            background-color : #00b8ff;
            border-radius : 8px;
        }

        a.menus {
            display : block;
            width : 85%;
            height : 50px;
            line-height : 48px;
            color : black;
            text-decoration : none;
            font-size : 16px;
        }

        .menus:hover {
            text-decoration : none;
            color : #ffca29
        }

        .review {
            width : 100%;
            height : 45px;
            line-height : 45px;
            background-color : #00b8ff;
            border-radius : 8px;
        }

        .review>a {
            font-size : 16px;
            color : white;
            text-decoration : none;
        }

        .body2 {
            padding-left : 20px;
            padding-right : 20px;
            padding-top : 5px;
        }

        #title {
            font-size : 35px;
            margin : 0px;
            padding : 0px;
            text-align :left;
            font-weight : bold;
        }

        .body2>div {
            width : 100%;
            box-sizing : border-box;
        }
        
        .dropdown {
            display : inline-block;  
        }

        .dropdown>select {
            width : 200px;
            height : 45px;
        }

        #search-cell {
            width : 500px;
            height : 40px;
            padding : 0px;
            margin : 0px;
        }

#search-input {
	border-color: rgb(136, 136, 136);
	height: 40px;
}

table, table>tr, table>tr>td {
	padding: 0px;
	margin: 0px;
}

#search-btn {
	background-color: #00b8ff;
	width: 80px;
	height: 90px;
	border-radius: 8px;
	color: white;
	font-family: "Noto Sans KR", sans-serif;
	font-size: 17px;
	line-height: 88px;
	text-align: center;
	border: none;
}

#search-btn-cell {
	width: 140px;
	padding-left: 20px;
	padding-right: 20px;
}

#blank {
	width: 100px;
}

#filters {
	font-size: 14px;
	margin: 0px;
	padding: 0px;
	text-align: center;
	font-family: "Noto Sans KR", sans-serif;
}

.filter-each1 {
	width: 80px;
}

.filter-each2 {
	width: 110px;
}

.filter-each1>a, .filter-each2>a {
	color: rgb(136, 136, 136);
	font-weight: 400;
}

.filter-each1>a:hover, .filter-each2>a:hover {
	color: #FFCA29;
	font-weight: 900;
}

.best-review {
	width: 100%;
	height: 150px;
}

.best-review:hover {
	cursor: pointer;
}

.review-thumbnail {
	width: 140px;
	height: 100%;
}

.content {
	width: 660px;
	font-size: 14px;
}

.review-title {
	width: 400px;
	font-weight: bold;
}

.review-writer {
	font-weight: bold;
	text-align: right;
}

.date {
	font-weight: bold;
	text-align: right;
}

#keyword {
	background: linear-gradient(to top, #FFCA29 50%, transparent 50%);
}

.score-star {
	width: 15px;
	height: 15px;
}

.heart {
	width: 20px;
	height: 20px;
	margin-top: -2px;
}

hr.menu-divide {
	margin: 0px;
	height: 1px;
	border: 0px;
	background-color: rgb(136, 136, 136);
	width: 120px;
}

#pagingbutton {
	background-color: gray;
	color: white;
	border-radius: 5px;
	border-color: white; /*버튼테두리색*/
	border-width: 0px; /*버튼테두리두깨*/
	width: 30px;
	height: 30px;
}

#pagingbutton1 {
	background-color: skyblue;
	color: white;
	border-radius: 5px;
	border-color: white; /*버튼테두리색*/
	border-width: 0px; /*버튼테두리두깨*/
	width: 30px;
	height: 30px;
}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp"%>

	<div class="outer" align="center">
		<div class="body">
			<div class="body1">
                <div class="review"><a href="<%= contextPath %>/reviewMain.re" class="menus">여행 후기</a></div>
                <div><a href="<%= contextPath %>/sightsReview.re" class="menus">관광지 후기</a></div>
                <hr class="menu-divide">
                <div><a href="<%= contextPath %>/foodsReview.re" class="menus">맛집 후기</a></div>
                <hr class="menu-divide">
                <div><a href="<%= contextPath %>/myReview.re?currentPage=1" class="menus">나의 여행 이야기</a></div>
			</div>
			<div class="body2">
				<p id="title">
					'<span id="keyword"><%= keyword %></span>'에 대한
					<%= list.size() %>건의 검색 결과
				</p>
				<br>
				<div class="search">
					<form action="reviewSearch.re" name="reviewSearch"
						id="reviewSearch" method="post">
						<table>
							<form action="reviewSearch.re" method="post">
								<tr>
									<td>
										<div class="dropdown">
											<select name="season">
												<option value='null' selected>계절</option>
												<option value='1'>봄</option>
												<option value='2'>여름</option>
												<option value='3'>가을</option>
												<option value='4'>겨울</option>
											</select>
										</div>
									</td>
									<td>
										<div class="dropdown">
											<select name="groupWith">
												<option value='null' selected>구성원</option>
												<option value='1'>혼자서</option>
												<option value='2'>커플로</option>
												<option value='3'>친구와</option>
												<option value='4'>가족과</option>
											</select>
										</div>
									</td>
									<td>
										<div class="dropdown">
											<select name="age">
												<option value='null'>연령대</option>
												<option value='1'>10-20</option>
												<option value='2'>30-40</option>
												<option value='3'>50-</option>
											</select>
										</div>
									</td>
									<td rowspan="2" id="search-btn-cell"><input type="hidden"
										name="currentPage" value="1">
										<button id="search-btn" type="submit">검색</button></td>
									<td rowspan="2" id="blank"></td>
								</tr>
								<tr>
									<td colspan="3" id="search-cell"><input type="text"
										name="keyword" id="search-input" class="form-control"
										placeholder="여행 후기를 검색해보세요."></td>
								</tr>
							</form>
						</table>
					</form>
				</div>
				<br>
				<br>

				<div id="filters">
					<table>
						<td class="filter-each1"><a href="">최신순</a></td>
						<td class="filter-each1"><a href="">추천순</a></td>
						<td class="filter-each2"><a href="">별점높은순</a></td>
						<td class="filter-each2"><a href="">별점낮은순</a></td>
					</table>
				</div>
				<br>
				<% if(list.size() > 0) { %>
					<% for(Review r : list) { %>
					<hr>
					<div class="best-review"
						onclick="location.href='<%= contextPath %>/detail.re?rNo=<%= r.getReviewNo() %>'">
						<table>
							<tr>
								<td rowspan="3">
									<div class="review-thumbnail">
										<img src="">
									</div>
								</td>
								<td><%= r.getPlaceName() %></td>
								<td>
									<% for(int i = 0; i < r.getScore(); i++) { %><img
									class="score-star" src="resources/images/score-star.png">
									<% } %>
								</td>
								<td colspan="2" class="like" align="right"><img
									class="heart" src="resources/images/solid-heart.png"><%= r.getLikes() %></td>
							</tr>
							<tr>
								<td class="review-title"><%= r.getReviewTitle() %></td>
								<td class="review-writer" colspan="2"><%= r.getMemberId() %></td>
								<td class="date"><%= r.getReviewDate() %></td>
							</tr>
							<tr>
								<td colspan="4" class="content"><%= r.getReviewContent() %></td>
							</tr>
						</table>
					</div>
					<% } %>
	
					<br>
	
					<div align="center" class="paging-area">
	
						<% if(currentPage != 1) { %>
						<button id="pagingbutton"
							onclick="location.href='<%= contextPath %>/reviewSearch.re?currentPage=<%= currentPage - 1 %>';">&lt;</button>
						<% } %>
	
						<% for(int p = startPage; p <= endPage; p++) { %>
						<% if( p!= currentPage) { %>
						<button id="pagingbutton"
							onclick="location.href='<%= contextPath %>/reviewSearch.re?currentPage=<%= p %>';"><%= p %></button>
						<% } else { %>
						<!-- 현재 내가 보고있는 페이지일 경우는 클릭 안되게끔 -->
						<button id="pagingbutton1" disabled><%= p %></button>
						<% } %>
						<% } %>
	
						<% if(currentPage != maxPage) { %>
						<button id="pagingbutton"
							onclick="location.href='<%= contextPath %>/reviewSearch.re?currentPage=<%= currentPage + 1 %>';">&gt;</button>
						<% } %>
					</div>
				<% } else { %>
					<hr>
                    <div>조건에 맞는 검색 결과가 없습니다.</div>
				<% } %>
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>