<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.nubigo.review.model.vo.Review" %>
<%
	ArrayList<Review> list = (ArrayList<Review>)request.getAttribute("list");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관광지 후기</title>
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
		border-color : rgb(136, 136, 136);
		height : 40px;
	}

	table, table>tr, table>tr>td {
		padding : 0px;
		margin : 0px;
	}

	#search-btn {
		background-color : #00b8ff;
		width : 80px;
		height : 90px;
		border-radius : 8px;
		color : white;
		font-size : 17px;
		line-height : 88px;
		text-align : center;
		border : none;
	}

	#search-btn-cell {
		width : 140px;
		padding-left : 20px;
		padding-right : 20px;
	}

	#blank {
		width : 100px;
	}

	#subtitle {
		font-size : 24px;
		margin : 0px;
		padding : 0px;
		text-align :left;
		font-weight : bold;
	}

	.best-review {
		width : 100%;
		height : 150px;
	}

	.best-review:hover {
		cursor : pointer;
	}


	.review-thumbnail {
		width : 140px;
		height : 100%;
	}

	.content {
		width : 660px;
		font-size : 14px;
	}

	.review-title {
		width : 400px;
		font-weight : bold;
	}
	
	.review-writer {
		font-weight : bold;
		text-align : right;
	}

	.date {
		font-weight : bold;
		text-align : right;
	}

	hr.menu-divide {
		margin : 0px;
		height : 1.5px;
		border : 0px;
		background-color :rgb(136, 136, 136);
		width : 120px;
    }
	.score-star {
		width : 15px;
		height : 15px;
	}

	.heart {
		width : 20px;
		height : 20px;
		margin-top : -2px;
	}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>

	<div class="outer" align="center">
        <div class="body">
            <div class="body1">
                <div><a href="<%= contextPath %>/reviewMain.re" class="menus">여행후기</a></div>
                <div class="review"><a href="<%= contextPath %>/sightsReview.re" class="menus">관광지 후기</a></div>
                <div><a href="<%= contextPath %>/foodsReview.re" class="menus">맛집 후기</a></div>
				<hr class="menu-divide">
                <div><a href="<%= contextPath %>/myReview.re?currentPage=1" class="menus">나의 여행 이야기</a></div>
            </div>
            <div class="body2">
                <p id="title">관광지 후기</p>
                <br>
                <div class="search">
                	<form action="reviewSearch.re" name="reviewSearch" id="reviewSearch" method="post">
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
                                    <td rowspan="2" id="search-btn-cell">
										<input type="hidden" name="currentPage" value="1">
                                        <button id="search-btn" type="submit">검색</button>
                                    </td>
                                    <td rowspan="2" id="blank"></td>
                                </tr>
                                <tr>
                                    <td colspan="3" id="search-cell">
                                        <input type="text" name="keyword" id="search-input" class="form-control" placeholder="여행 후기를 검색해보세요.">
                                    </td>
                                </tr>
                            </form>
	                    </table>
                    </form>
                </div>
                <br><br>

                <p id="subtitle">베스트 관광지 후기</p>

                <% for(Review r : list) { %>
                    <hr>
	                <div class="best-review" onclick="location.href='<%= contextPath %>/detail.re?rNo=<%= r.getReviewNo() %>'">
	                    <table>
	                        <tr>
	                            <td rowspan="3">
	                                <div class="review-thumbnail">
	                                    <img src="">
	                                </div>
	                            </td>
	                            <td colspan="2">
									<% for(int i = 0; i < r.getScore(); i++) { %><img class="score-star" src="resources/images/score-star.png"><% } %>
								</td>
	                            <td colspan="2" class="like" align="right"><img class="heart" src="resources/images/solid-heart.png"><%= r.getLikes() %></td>
	                        </tr>
	                        <tr>
	                            <td class="review-title"><%= r.getReviewTitle() %></td>
	                            <td class="review-writer"><%= r.getMemberId() %></td>
	                            <td colspan="2" class="date"><%= r.getReviewDate() %></td>
	                        </tr>
	                        <tr>
	                            <td colspan="4"  class="content"><%= r.getReviewContent() %></td>
	                        </tr>
	                    </table>
	                </div>
	            <% } %>
            </div>
        </div>
    </div>

	<%@ include file="../common/footer.jsp" %>
</body>
</html>