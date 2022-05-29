<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여행기 남기기</title>
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
            width : 1200px;
            height : 100%;
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
            width : 60%;
        }
        .body3{
            width:24%;
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
            font-weight : bold;
            color : black;
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

        #selected:hover {
            color : white;
        }

        /*파일 업로드*/
        .filebox label {
  display: inline-block;
  padding: .4em .4em;
  font-size: inherit;
  line-height: normal;
  vertical-align: middle;
  cursor: pointer;
  border-radius: .25em;
  color: #fff;
  background-color: #00b8ff;
  border-color: #00b8ff;
}

.filebox input[type="file"] {  /* 파일 필드 숨기기 */
  position: absolute;
  width: 1px;
  height: 1px;
  padding: 0;
  margin: -1px;
  overflow: hidden;
  clip:rect(0,0,0,0);
  border: 0;
}
#uploadContent{
    border: 1px solid black;
    height: 100px;
}

/*여행지 검색*/
.outer {
        width: 1200px;
        margin: auto;
        margin-top: 30px;
    }

    a { cursor: pointer; }

    .find-travel .list-group-item img {
      width: 50px; 
      height: 50px; 
      margin: 5px;
    }
    
   #findKeyword {
      width: 75%; 
      float: left; 
      margin-bottom: 15px;
    }
    
    .result-div 
    
    #findTravelResult::-webkit-scrollbar {
       width: 10px;
   }
     
   #findTravelResult::-webkit-scrollbar-thumb {
       background-color: steelblue;
       border-radius: 10px;
       background-clip: padding-box;
       border: 2px solid transparent;
    }
  
    #findTravelResult::-webkit-scrollbar-track {
       background-color: lightsteelblue;
       border-radius: 10px;
       box-shadow: inset 0px 0px 5px white;
    }
    #accordion {
      -ms-overflow-style: none;
   }
   #accordion::-webkit-scrollbar {
     display:none;
   }
   table {
      table-layout: fixed;
   }
   
   .btn-make {
        background-color: #FFCA29; 
        color: white;
    }
    .body1::after {
  display: block;
  content: '';
  clear: both;
}
</style>
</head>
<body>
    <%@ include file="../common/menubar.jsp" %>

    <div class="outer" align="center">
        <div class="body">
            <div class="body1">
                <div class="review"><a href="<%= contextPath %>/reviewMain.re" class="menus" id="selected">여행 후기</a></div>
                <div><a href="<%= contextPath %>/sightsReview.re" class="menus">관광지 후기</a></div>
                <hr class="menu-divide">
                <div><a href="<%= contextPath %>/foodsReview.re" class="menus">맛집 후기</a></div>
                <hr class="menu-divide">
                <div><a href="<%= contextPath %>/myReview.re?currentPage=1" class="menus">나의 여행 이야기</a></div>
            </div>
            <h2 class="d-flex mb-3" style="padding:0 20px;">
                <div class="col">
                    <p id="title">여행기 남기기</p>
                </div>
            </h2>
            <form action="<%=contextPath%>/insert.re" method="post" enctype="multipart/form-data" class="row">
                <input type="hidden" name="memberNo" value="<%=loginUser.getMemberNo()%>">
                <div class="body2">
                    <div class="d-flex row mb-3">
                        <div class="col-3">
                            <select name="score" style="width: 100%;" required>
                                <option value="">별점</option>
                                <option value="1">⭐️</option>
                                <option value="2">⭐️⭐️</option>
                                <option value="3">⭐️⭐️⭐️</option>
                                <option value="4">⭐️⭐️⭐️⭐️</option>
                                <option value="5">⭐️⭐️⭐️⭐️⭐️</option>
                            </select>
                        </div>
                        <div class="col-9">
                            <input type="text" name="title" class="w-100" placeholder="제목을 입력해주세요" required>
                        </div>
                    </div>
                    <div class="d-flex row mb-3">
                        <div class="col-4">
                            <select name="age" id="" style="width: 100%;" required>
                                <option value="">연령대</option>
                                <option value="1">10~20대</option>
                                <option value="2">30~40대</option>
                                <option value="3">50대 이상</option>
                            </select>
                        </div>
                        <div class="col-4">
                            <select name="season" id="" style="width: 100%;" required>
                                <option value="">계절</option>
                                <option value="1">봄</option>
                                <option value="2">여름</option>
                                <option value="3">가을</option>
                                <option value="4">겨울</option>
                            </select>
                        </div>
                        <div class="col-4">
                            <select name="groupWith" id="" style="width: 100%;" required>
                                <option value="">구성원</option>
                                <option value="1">혼자</option>
                                <option value="2">커플</option>
                                <option value="3">친구</option>
                                <option value="4">가족</option>
                            </select>
                        </div>
                    </div>
                    <div class="d-flex row mb-3">
                        <div class="col">
                            <textarea name="content" id="" rows="10" style="width: 100%; resize: none;" placeholder="내용을 입력해주세요" required></textarea>
                        </div>
                    </div>
                    <div class="d-flex flex-row align-items-center">
                        <div class="col-2">
                            <p class="float-left">첨부파일</p>
                        </div>
                        <div class="d-flex">
                            <div class="filebox mr-2">
                                <label for="ex_file1">업로드</label>
                                <input type="file" id="ex_file1" name="file1">
                            </div>
                            <div class="filebox mr-2">
                                <label for="ex_file2">업로드</label>
                                <input type="file" id="ex_file2" name="file2">
                            </div>
                            <div class="filebox mr-2">
                                <label for="ex_file3">업로드</label>
                                <input type="file" id="ex_file3" name="file3">
                            </div>
                            <div class="filebox">
                                <label for="ex_file4">업로드</label>
                                <input type="file" id="ex_file4" name="file4">
                            </div>
                        </div>
                    </div>
                    <div class="d-flex row mb-3">
                        <div class="col">
                            <div id="uploadContent">

                            </div>
                        </div>
                    </div>
                </div>
                <script>
                    $(function () {
                            $("input[type='file']").on('change', function () {
                                $("#uploadContent").append('<div>'+event.target.files[0].name+'</div>');
                            });
                        })
                </script>
                <div class="body3">
                    <div class="findTravelForm" style="width: 100%; height: 300px;">
                        <h4 class="text-left">여행지 찾아보기</h4>
                        <input type="text" class="form-control form-control-sm" id="findKeyword">
                        <button id="btn-search" class="btn btn-nubigoMain btn-sm" style="margin-left: 10px;"
                            onclick="findTravel();">검색</button>
                        <div class="result-div">
                            <ul class="list-group list-group-flush" style="width: 80%; height: 300px; overflow-y : auto"
                                id="findTravelResult">
                            </ul>
                        </div>
                    </div>
                    
                    <script>
                        function findTravel() {
                            $.ajax({
                                url: "findTravel.li",
                                type: "POST",
                                data: { "keyword": $("#findKeyword").val() },
                                success: function (result) {

                                    var str = "";

                                    for (var i = 0; i < result.length; i++) {
                                        str += "<li class='list-group-item' style='padding: 0px;''>" +
                                            "<img src='" + result[i].thumbnailPath + result[i].thumbnailName + "' class='img-thumbnail' style='width: 50px; height: 50px'>" +
                                            result[i].placeName + " / " +
                                            result[i].placeContent +
                                            "</li>"
                                    }

                                    $(".findTravelForm #findTravelResult").html(str);

                                },
                                error: function () {
                                    console.log("실패");
                                }
                            });

                        }
                    </script>
                    </div>
                    <div class="d-flex" style="padding:0 20px;">
                        <div class="col text-left">
                            <button class="btn btn-outline-nubigoSub" onclick="history.back();">취소</button>
                            <button class="btn btn-nubigoMain" type="submit">작성완료</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>