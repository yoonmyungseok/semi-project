<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.nubigo.place.model.vo.Place" %>
<%
	Place p = (Place)request.getAttribute("p");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>

<!-- Popper JS -->

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

<style>
    .outer {
        width: 800px;
        margin: auto;
        margin-top: 50px;
    }

    table th {
        width: 110px;
        padding: 10px;
        vertical-align: top;
    }

    #insertPlace-form>table {
        margin: auto;
    }
    
    .form-control[name="deleteStatus"] {
    	width: 85px;
    }
</style>
</head>
<body>
	<div class="outer">
        
        <h3>여행지 관리</h3>

        <br>

        <form id="updatePlace-form" action="/nbg/update.pl" method="post" enctype="multipart/form-data">
        	<input type="hidden" name="pno" value="<%= p.getPlaceNo() %>">
            
            <table align="center">
                <tr>
                    <th>지역명</th>
                    <td>
	                   <select class="form-control" name="localNo">
	                       <option value="" disabled selected hidden>지역명</option>
	                       <option value="1">강릉</option>
                           <option value="2">속초</option>
                           <option value="3" selected>부산</option>
                           <option value="4">경주</option>
                           <option value="5">전주</option>
                           <option value="6">여수</option>
                           <option value="7">대전</option>
                           <option value="8">청주</option>
                           <option value="9">강화도</option>
                           <option value="10">가평</option>
                           <option value="11">제주시</option>
                           <option value="12">서귀포시</option>
                    	</select>

                        <script>

                            $(function() {
                                $("#updatePlace-form [name=localNo] option").each(function() {

                                    if($(this).text() == "<%= p.getLocalNo() %>") {
                                        $(this).attr("selected", true);
                                    }
                                });
                            });

                        </script>
                        
                    </td>
                </tr>
                <tr>
                    <th>여행지명</th>
                    <td><input type="text" name="placeName" class="form-control" value="<%= p.getPlaceName() %>" required></td>
                </tr>
                <tr>
                    <th>카테고리</th>
                    <td>
                    	<select class="form-control" name="category">
                    		<option value="" disabled selected hidden>카테고리</option>
	                        <option value="1" selected>관광지</option>
	                        <option value="2" >맛집</option>
                    	</select>

                        <script>
                            $(function() {
                                $("#updatePlace-form [name=category] option").each(function() {

                                    if($(this).val() == "<%= p.getPlaceCategory() %>") {
                                        $(this).attr("selected", true);
                                    }
                                });
                            });
                        </script>
                    </td>
                </tr>
                <tr>
                    <th>위도</th>
                    <td><input type="text" name="latitude" class="form-control" value="<%= p.getLatitude() %>" required></td>
                </tr>
                <tr>
                    <th>경도</th>
                    <td><input type="text" name="longitude" class="form-control" value="<%= p.getLongitude() %>" required></td>
                </tr>
                <tr>
                    <th>여행지 설명</th>
                    <td>
                        <textarea name="placeContent" class="form-control" rows="5" style="resize: none;" required><%= p.getPlaceContent()%></textarea>
                    </td>
                </tr>
                <tr>
                    <th>기존 썸네일</th>
                    <td><%= p.getThumbnailName() %></td>
                    <input type="hidden" name="placeThumbnailPath" value="<%= p.getThumbnailPath() %>">
                    <input type="hidden" name="placeThumbnail" value="<%= p.getThumbnailName() %>">
                </tr>
                <tr>
                	<th>변경 썸네일</th>
                	<td><input type="file" name="reUpfile"></td>
                </tr>
                <tr>
                    <th>삭제 여부</th>
                    <td>
                    	<select class="form-control" name="deleteStatus">
	                        <option value="Y">Y</option>
	                        <option value="N">N</option>
                    	</select>
                        <script>
                            $(function() {
                                $("#updatePlace-form [name=deleteStatus] option").each(function() {

                                    if($(this).val() == "<%= p.getDeleteStatus() %>") {
                                        $(this).attr("selected", true);
                                    }
                                });
                            });
                        </script>
                    	
                    </td>
                </tr>
            </table>
            
            <br>

            <div align="center">
                <button type="submit" class="btn btn-primary">수정하기</button>
                <a href="/nbg/placeList.ad?currentPage=1" class="btn btn-danger">취소하기</a>
            </div>
        </form>
	</div>

</body>
</html>