<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.nubigo.review.model.vo.ReviewAttachment, com.nubigo.review.model.vo.Review, com.nubigo.place.model.vo.Place" %>
<%
	ArrayList<ReviewAttachment> atList = (ArrayList<ReviewAttachment>)request.getAttribute("atList");
	Review r = (Review)request.getAttribute("r");
    Place p = (Place)request.getAttribute("p");
    int liked = (int)request.getAttribute("liked");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여행기 상세보기</title>
	<!-- 카카오 관련 -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=21bc5aad720887b9a9d2b3a63297c46d"></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=APIKEY&libraries=LIBRARY"></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=APIKEY&libraries=services"></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=APIKEY&libraries=services,clusterer,drawing"></script>
<style>
        .outer {
            width : 1200px;
            height : 1000px;
            margin : auto;
            margin-top : 50px;
            margin-bottom : 50px;
            box-sizing : border-box;
        }

        .body {
            width : 1000px;
            height : 100%;
            margin : 0px 100px;
            box-sizing : border-box;
        }

        .body>div {
            float : left;
            box-sizing : border-box;
            height : 100%;
        }

        .body1 {
            width : 16%;
            padding-top : 10px;
        }

        .body2 {
            width : 84%;
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
            line-height : 50px;
            color : black;
            text-decoration : none;
            font-size : 16px;
        }

        .menus:hover {
            text-decoration : none;
            color : #ffca29
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

        .body2>table {
            width : 800px;
        }

        #title {
            font-size : 25px;
            margin : 0px;
            padding : 0px;
            text-align :left;
            font-weight : bold;
            font-family : "Noto Sans KR", sans-serif;
        }

        .review-content {
            width : 100%;
            box-sizing : border-box;
        }

        .btn-end {
            width : 80px;
            height : 40px;
            float : right;
        }

        #kakao {
            width : 300px;
            height : 150px;
        }

        #map {
            width : 300px;
            height : 150px;
        }

        .button {
            background-color : #FFCA29;
            width : 100px;
            height : 40px;
            border : 0px;
            border-radius : 3px;
            color : black;
        }
        
        .review-detail-heart {
            width : 30px;
            height : 30px;
        }

        .review-detail-heart:hover {
            cursor : pointer;
        }

        hr.menu-divide {
            margin : 0px;
            height : 1.5px;
            border : 0px;
            background-color :rgb(136, 136, 136);
            width : 120px;
        }
        .review-imageslide-wrap {
            margin: auto;
            width: 100%;
            height : 400px;
        }

        .review-imageslide {
            height: 400px;
            overflow : hidden;
            margin : 0 auto;
        }

        #carousel-item {
            height : 400px;
            width : auto;
        }

        .review-content {
            text-align : left;
            padding-top : 10px;
        }

        #prev {
            top : 330px;
            left : 10px;
        }

        #next {
            top : 330px;
            left : 500px;
        }
        .report-icon {
            width : 30px;
            height : 30px;
        }
        .heart {
            width : 20px;
            height : 20px;
            margin-top : -2px;
        }
        .score-star {
        	width : 15px;
        	height : 15px;
        }
    </style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
    <div class="outer" align="center">
        <div class="body">
            <div class="body1">
                <div class="review"><a href="<%= contextPath %>/reviewMain.re" class="menus">여행 후기</a></div>
                <div><a href="<%= contextPath %>/sightsReview.re" class="menus">관광지 후기</a></div>
                <hr class="menu-divide">
                <div><a href="<%= contextPath %>/foodsReview.re" class="menus">맛집 후기</a></div>
                <hr class="menu-divide">
                <div><a href="<%= contextPath %>/myReview.re" class="menus">나의 여행 이야기</a></div>
            </div>
            <div class="body2">
                <table border="1">
                    <tr>
                        <td colspan="4" id="title">
                        <p><%= r.getReviewTitle() %></p>
                        <span><small><img class="heart" src="resources/images/solid-heart.png"><%=r.getLikes() %></small></span>
                        <span><small><% for(int i = 0; i < r.getScore(); i++) { %><img class="score-star" src="resources/images/score-star.png"><% } %><%=r.getScore() %></small></span>
                        </td>
                        <td rowspan="3" id="kakao"><div id="map"></div></td>
                    </tr>
                    <tr>
                        <td colspan="2"><%= p.getPlaceName() %></td>
                        <td><%= r.getMemberId() %></td>
                        <td><%= r.getReviewDate() %></td>
                    </tr>
                    <tr>
                        <td><button type="button" class="button" disabled><% if(r.getAge().equals("1")) { %>10-20<% } else if(r.getAge().equals("2")) { %>30-40<% } else { %>50-<% } %></button></td>
                        <td><button type="button" class="button" disabled><% if(r.getSeason().equals("1")) { %>봄<% } else if(r.getSeason().equals("2")) { %>여름<% } else if(r.getSeason().equals("3")) { %>가을<% } else { %>겨울<% } %></button></td>
                        <td><button type="button" class="button" disabled><% if(r.getGroupWith().equals("1")) { %>혼자서<% } else if(r.getGroupWith().equals("2")) { %>커플로<% } else if(r.getGroupWith().equals("3")) { %>친구와<% } else { %>가족과<% } %></button></td>
                    </tr>
                </table>
                <script>

                    var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
                    var options = { //지도를 생성할 때 필요한 기본 옵션
                        center: new kakao.maps.LatLng(<%= p.getLatitude() %>, <%= p.getLongitude() %>), //지도의 중심좌표.
                        level: 6 //지도의 레벨(확대, 축소 정도)
                    };

                    var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
                    
                    var imageSrc = 'resources/images/map-marker.png', // 마커이미지의 주소입니다    
                    imageSize = new kakao.maps.Size(20, 27), // 마커이미지의 크기입니다
                    imageOption = {offset: new kakao.maps.Point(15, 15)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
                      
	                // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
	                var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
	                    markerPosition = new kakao.maps.LatLng(<%= p.getLatitude() %>, <%= p.getLongitude() %>); // 마커가 표시될 위치입니다
	
	                // 마커를 생성합니다
	                var marker = new kakao.maps.Marker({
	                    position: markerPosition, 
	                    image: markerImage // 마커이미지 설정 
	                });
	
	                // 마커가 지도 위에 표시되도록 설정합니다
	                marker.setMap(map);  

                </script>

                <br>

                <% if(atList != null) { %>
                    <div class="review-imageslide-wrap">
                        <div class="review-imageslide" style="position: relative;">
                            <div id="demo" class="carousel slide" data-ride="carousel">
                                <div class="carousel-inner">
                                <% for(ReviewAttachment rAt : atList) { %>
                                <!-- 메인 이미지 슬라이드 -->
                                    
                                    <% if(rAt.equals(atList.get(0))) { %>
                                        <div class="carousel-item active" id="carousel-item">
                                            <img
                                                src="<%= rAt.getFilePath() %><%= rAt.getFileName() %>">
                                        </div>
                                    <% } else { %>
                                        <div class="carousel-item" id="carousel-item">
                                            <img
                                                src="<%= rAt.getFilePath() %><%= rAt.getFileName() %>">
                                        </div>
                                    <% } %>
                                    
                                <% } %>
                                </div>
                                <!-- 왼쪽/오른쪽 화살표 -->
                                <a class="carousel-control-prev" id="prev" href="#demo" data-slide="prev">
                                    <span class="carousel-control-prev-icon"></span>
                                </a> <a class="carousel-control-next" id="next" href="#demo" data-slide="next">
                                    <span class="carousel-control-next-icon"></span>
                                </a>
                                
                            </div>
                        </div>
                    </div>

                    <script>
                        // $(".carousel").carousel({ interval:2500 });
                    </script>
                
                <% } %>

                <div class="review-content">
                    <p width="750px" height="800px">
                       <%= r.getReviewContent() %>
                    </p>
                </div>
                <br>
                <hr>
                <table style="width : 100%;">
                    
                    <td id="review-heart">
                        <% if(loginUser != null) { %>
                            <% if (liked == 0) { %>
                                <img class="review-detail-heart" id="heart-empty" src="resources/images/empty-heart.png">
                            <% } else { %>
                                <img class="review-detail-heart" id="heart-full" src="resources/images/solid-heart.png">
                            <% } %>
                        <% } else { %>
                            <img class="review-detail-heart" id="heart-empty" src="resources/images/empty-heart.png">
                        <% } %>
                        <script>
                            $(document).on('click',".review-detail-heart",function(){
                                $.ajax({
                                    type: "post",
                                    url: "like.re",
                                    data: {
                                        memberNo: <%=loginUser.getMemberNo()%>,
                                        reviewNo: <%=r.getReviewNo()%>
                                    },
                                    success: function (response) {
                                        if(response<=0){
                                            $("#review-heart").html('<img class="review-detail-heart" id="heart-empty" src="resources/images/empty-heart.png">');
                                        }else{
                                            $("#review-heart").html('<img class="review-detail-heart" id="heart-full" src="resources/images/solid-heart.png">');
                                        }
                                    },
                                    error:function(){
                                        alert('실패');
                                    }
                                });
                            })
                        
                        </script>
                    </td>
                    <td><a type="button" data-toggle="modal" data-target="#review-report"><img class="report-icon" src="resources/images/report.png"></a></td>
                    <!-- Modal -->
            <div class="modal fade" id="review-report" tabindex="-1" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">신고 사유를 선택해주세요.</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body d-flex justify-content-between">
                            <div class="custom-control custom-radio">
                                <input type="radio" id="reviewRadio1" name="reviewReport" class="custom-control-input" value="욕설">
                                <label class="custom-control-label" for="reviewRadio1">욕설</label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input type="radio" id="reviewRadio2" name="reviewReport" class="custom-control-input" value="음란">
                                <label class="custom-control-label" for="reviewRadio2">음란</label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input type="radio" id="reviewRadio3" name="reviewReport" class="custom-control-input" value="홍보 및 반복글">
                                <label class="custom-control-label" for="reviewRadio3">홍보 및 반복글</label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input type="radio" id="reviewRadio4" name="reviewReport" class="custom-control-input" value="기타">
                                <label class="custom-control-label" for="reviewRadio4">기타</label>
                            </div>
                        </div>
                        <div class="modal-body d-none" id="reviewReportContent">
                            <textarea name="reviewReportContent"cols="30" rows="3" style="resize: none;"></textarea>
                        </div>
                        <div class="modal-footer">        
                            <button type="button" class="btn btn-nubigoMain" data-dismiss="modal">취소</button>
                            <button type="button" class="btn btn-nubigoSub" onclick="reportReview();">신고</button>
                        </div>
                    </div>
                </div>
            </div>
            <script>
            //리뷰 신고
        function reportReview(){
            if (!$('input[name=reviewReport]:checked').val()) {
                alert('항목을 선택해주세요.');
                return false;
            }else{
                if($("#reviewRadio4:checked").val() && $("textarea[name=reviewReportContent]").val() == ""){
                    alert('신고 사유를 작성해주세요');
                }else{
                    $.ajax({
                        url: "report.re",
                        data: {
                            rno: '<%=r.getReviewNo()%>',
                            report: $('input[name=reviewReport]:checked').val(),
                            content: $("textarea[name=reviewReportContent]").val()
                        },
                        success: function (result) {
                            if (result > 0) {
                                alert("리뷰 신고 성공");
                                location.href = "<%=contextPath%>/reviewMain.re";
                            } else {
                                alert("리뷰 신고 실패");
                            }
                        },
                        error: function () {
                            console.log("리뷰 신고 ajax 통신 실패");
                        }
                    })
                }
                
            }
        }
        //신고 기타 작성 창
        $("input[name='reviewReport']").click(function(){
            if($(this).val()==$("#reviewRadio4").val()){
                $("#reviewReportContent").removeClass("d-none");
            }else{
                $("#reviewReportContent").addClass("d-none");
            }
        })
            </script>

                    <% if(loginUser != null && loginUser.getMemberId().equals(r.getMemberId())) { %>
                        <td width="500px"></td>
                        <td><button class="btn btn-end" style="background-color : #FFCA29;">수정</button></td>
                        <td><button class="btn btn-end" style="background-color : rgb(210, 210, 210)">삭제</button></td>
                    <% } else { %>
                        <td width="700px"></td>
                    <% } %>

                </table>
            </div>
        </div>
    </div>
    <%@ include file="../common/footer.jsp" %>
</body>
</html>