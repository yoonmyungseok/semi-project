<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.nubigo.list.model.vo.List" %>
<%
   ArrayList<List> list = (ArrayList<List>)request.getAttribute("list");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>

<!-- Popper JS -->

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

<script src="https://kit.fontawesome.com/b0a310c3fc.js" crossorigin="anonymous"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=af6fb2aac338f7115679e5a16652062f"></script>

<style>
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
</style>
</head>
<body>
   <div class="outer">
        <div class="left" style="width: 300px; float: left;">
            
            <div class="travel-list" style="width: 100%; height: 350px;">
                <h4>내 여행 리스트</h4>
                <div id="accordion" style="width: 75%; height: 300px; overflow-y: auto">
                   <% for(int i = 0; i < list.size(); i++) { %>
                   <div class="card">
                      <div class="card-header" style="background-color: white;">
                          <table>
                             <tr class="listSchedule">
                               <td width="120" class="listName">
                                 <a class="card-link" data-toggle="collapse" href="#<%= list.get(i).getListTitle() %>" style="color: black;"><%= list.get(i).getListTitle() %></a>
                                 <input type="hidden" name="listNo" value="<%= list.get(i).getListNo() %>">
                               </td>
                               
                               <td width="30" align="right">
                                 <a><i class="fa-solid fa-pen"></i></a>
                               </td>
                               <td width="30" align="right">
                                 <a><i class="fa-solid fa-trash-can"></i></a>
                               </td>
                            </tr>
                          </table>
                      </div>
                  
                      <div id="<%= list.get(i).getListTitle() %>" class="collapse" data-parent="#accordion">
                        <div class="card-body" style="padding: 0%;">
                            <div class="list-group list-group-flush listPlaces">
                            </div>
                        </div>
                      </div>
                    </div>
                   
                    <% } %>
                    <script>
                      $(function() {
                          $(".listName").click(function() {
                             
                              var listNo = $(this).children().eq(1).val();

                              $.ajax({
                              url : "listForm.li",
                              type : "POST",
                              data : {"listNo" : listNo},
                              success : function(result) {
                                 
                                 var places = "";
                                 var schedule = "";
                                 
                                 var startDate = new Date(result[0].startDate);
                                 var endDate = new Date(result[0].endDate);
                                 
                                 var travelTerm = ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24)) + 1;
                                 
                                 var startTime = "";
                                 
                                 for(var k = 0; k < result.length; k++) {
                                    startTime = new Date(result[k].startTime).getHours();
                                    
                                    console.log(startTime);
                                 }
                                 
                                 console.log(startDate);
                                 console.log(endDate);
                                 console.log(travelTerm);
                                 console.log(result[0].startTime);
                                 
                                 var schedule = "";
                                 
                                 if(result.length > 0) {
                                    
                                    for(var i = 0; i < result.length; i++) {
                                       
                                       places += "<a class='list-group-item list-group-item-action'>" +
                                                    result[i].placeNo +
                                                 "</a>"
                                          
                                    }
                                    
                                 } else {
                                    places += "<a class='list-group-item list-group-item-action'>빈 리스트 입니다</a>"
                                 }
                                 
                                 schedule += "<thead align='center'>" +
                                             "<tr><td></td>" 
                                             for(var j = 1; j <= travelTerm; j++) {
                                                schedule += "<td>DAY " + j + "</td>"
                                             }
                                 schedule += "</tr></thead>"
                                 
                                 var dayNo = 0;
                                 for(var i = 6; i < 25; i++) {
                                    schedule += "<tr>" +
                                     "<td width='50' height='40'>" + i + ":00</td>"
                                     for(var j = 0; j < travelTerm; j++) {
                                        
                                        schedule += "<td draggable='true' ondragstart='drag(event)' ondrop='drop(event)' ondragover='dragEnter(event)' height='40' width='300' id='schedule-" + j + "-" + i + "' data-toggle='modal' data-target='#modifySchedule'>"
                                        
                                        for(var a = 0; a < result.length; a++) {
                                           var travelDate = new Date(result[a].startTime);
                                           
                                           var travelDay = Math.floor((travelDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24));
                                           
                                           console.log("travelDay" + travelDay);
                                           
                                           if(travelDay == j) {
                                              if(new Date(result[a].startTime).getHours() == i) {
                                                 schedule += result[a].placeNo;
                                              } else {
                                                 schedule += "";
                                              }
                                           } else {
                                             schedule += "";
                                           }
                                           
                                        }
                                        
                                     }
                                     schedule += "</td></tr>"
                                    
                                 }
                                 
                                  $(".listPlaces").html(places);
                                  $(".travelSchedule table tbody").html(schedule);
                                    
                              },
                              error : function() {
                                 console.log("실패");
                                 
                              } 
                           });   
                              
                          });
                      });
                  </script>
                  </div>    
            </div>
            <div class="findTravelForm" style="width: 100%; height: 300px;">
                <h4>여행지 찾아보기</h4>
                <input type="text" class="form-control form-control-sm" id="findKeyword">
                <button id="btn-search" type="submit" class="btn btn-success btn-sm" style="margin-left: 10px;" onclick="findTravel();">검색</button>
                <div class="result-div">
                  <ul class="list-group list-group-flush" style="width: 80%; height: 300px; overflow-y : auto" id="findTravelResult">
                  
                  </ul>
                </div>
            </div>
            
            <script>
               function findTravel() {
                  $.ajax({
                     url : "findTravel.li",
                     type : "POST",
                     data : {"keyword" : $("#findKeyword").val()},
                     success : function(result) {
                        
                        var str = "";
                        
                        for(var i = 0; i < result.length; i++) {
                           str += "<li class='list-group-item' style='padding: 0px;''>" +
                                    "<img src='" + result[i].thumbnailPath + result[i].thumbnailName +"' class='img-thumbnail' style='width: 50px; height: 50px'>"+
                                       result[i].placeName + " / " +
                                       result[i].placeContent +
                                  "</li>" 
                        }
                        
                        $(".findTravelForm #findTravelResult").html(str);
                        
                     },
                     error : function() {
                        console.log("실패");
                     }
                  });
                  
               }
            </script>
        </div>


        <div class="right" style="width: 900px; float: left;">
            <div class="map" style="width: 100%; height: 500px">
                <div id="map" align="center" style="width: 100%; height: 100%">
                      <!-- 지도 -->
                </div>
                <br>
            </div>
            <div class="travelSchedule" style="overflow-x:auto; width: 900px; border: 1px solid black; display: block;">
               <div class="schedule">
                  <table border="1" style="table-layout:fixed;">
                     <tbody>
                        <% for(int i = 6; i < 25; i++) { %>
                        <tr>
                           <td width="50" height="40"><%= i %>:00</td>
                           <td height="40" width="300px"></td>
                           <td height="40" width="300px"></td>
                           <td height="40" width="300px"></td>
                        </tr>
                        <% } %>
                     </tbody>
                  </table>
                  
               </div>
                  <div align="right">
                    <button type="reset" class="btn btn-secondary">초기화</button>
                    <button type="submit" class="btn btn-warning">저장</button>
                  </div>
            </div>
        </div>
    </div>
    
    <script>
    var travelId = "";
    function drag(ev) {
    	travelId = $(this).id();
        ev.dataTransfer.setData("text", ev.target.innerText);
        ev.dataTrasfer.clearData();
    }
    
    function drop(ev) {
        ev.preventDefault();
        ev.target.append(ev.dataTransfer.getData("text"));
    }
    
    function dragEnter(ev) {
      ev.preventDefault();
    }
  </script>
  
  
    <!-- The Modal -->
    <div class="modal" id="modifySchedule">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title" align="center">날짜 수정</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                
                <form action="" method="">
                    <!-- Modal body -->
                    <div class="modal-body">
                        <div>
                            <div class="input-div" style="width: 70%; float: left; padding: 5px;">
                                <div class="form-group">
                                    <select class="form-control">
                                        <option hidden>날짜</option>
                                        <option value="10">혼자</option>
                                        <option value="20">가족</option>
                                        <option value="30">친구</option>
                                        <option value="40">커플</option>
                                    </select>
                                </div>
                            </div>

                            <div class="input-div" style="width: 30%; float: left; padding: 5px;">
                                <div class="form-group">
                                    <select class="form-control">
                                        <option hidden>시간</option>
                                        <% for(int i = 6; i < 25; i++) { %>
                                        	<option value="<%= i %>"><%= i %> : 00</option>
                                        <% } %>
                                    </select>
                                </div>
                            </div>

                            <div>
                                <button type="submit" class="btn btn-make" style="width: 100%;">수정하기</button>
                            </div>

                        </div>
                    </div>
                </form>

            </div>
        </div>
    </div>
</body>
</html>