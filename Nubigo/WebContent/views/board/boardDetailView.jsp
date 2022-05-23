<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.nubigo.board.model.vo.Board" %>
<%
	Board b=(Board)request.getAttribute("b");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>자유게시판</title>
</head>
<body>
<%@ include file="../common/menubar.jsp" %>
    <div id="wrap">
        <h2 class="mb-4 p-2">자유게시판</h2>
        <hr>
        <div id="board-detail" class="mb-5">
            <div id="board-detail-title" class="d-flex justify-content-between p-2 border-bottom border-top bg-light">
                <h5 class="mb-0 font-weight-bold"><%=b.getBoardTitle() %></h5>
                <div><%=b.getBoardDate() %></div>
            </div>
            <div id="board-detail-header" class="d-flex p-2 border-bottom">
                <div class="mr-auto"><%=b.getMemberId() %></div>
                <div>댓글 <b>2</b></div>
            </div>
            <div id="board-detail-content" class="p-2">
                <p>
                    <%=b.getBoardContent() %>
                </p>
            </div>
            <div class="float-right p-2">
            <%if(loginUser!=null){ %>
                <% if(loginUser.getMemberId().equals(b.getMemberId())){%>
                <button type="button" class="btn btn-nubigoSub btn-sm" onclick="deleteBoard();">삭제</button>
                <a href="<%=contextPath %>/updateForm.bo?bno=<%=b.getBoardNo() %>" type="button" class="btn btn-nubigoMain btn-sm">수정</a>
                <%} %>
                <button type="button" class="btn btn-nubigoSub btn-sm" data-toggle="modal" data-target="#board-report">신고</button>
            <%} %>
            </div>
            <!-- Modal -->
            <div class="modal fade" id="board-report" tabindex="-1" aria-hidden="true">
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
                                <input type="radio" id="boardRadio1" name="boardReport" class="custom-control-input" value="욕설">
                                <label class="custom-control-label" for="boardRadio1">욕설</label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input type="radio" id="boardRadio2" name="boardReport" class="custom-control-input" value="음란">
                                <label class="custom-control-label" for="boardRadio2">음란</label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input type="radio" id="boardRadio3" name="boardReport" class="custom-control-input" value="홍보 및 반복글">
                                <label class="custom-control-label" for="boardRadio3">홍보 및 반복글</label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input type="radio" id="boardRadio4" name="boardReport" class="custom-control-input" value="기타">
                                <label class="custom-control-label" for="boardRadio4">기타</label>
                            </div>
                        </div>
                        <div class="modal-footer">        
                            <button type="button" class="btn btn-nubigoSub" onclick="reportBoard();">신고</button>
                            <button type="button" class="btn btn-nubigoMain" data-dismiss="modal" >취소</button>
                        </div>
                    </div>
                </div>
            </div>
            <br clear="both">
                
            <div class="text-muted p-2 border-bottom">
                <!--첨부파일이 없을 경우: 첨부파일이 없습니다-->
                <%if(b.getAttachmentName()==null){ %>
                첨부파일이 없습니다.
                <%}else{ %>
                <!--첨부파일이 있을 경우: 첨부파일의 원본명-->
                <a class="text-decoration-none text-muted" download="<%=b.getAttachmentName() %>" href="<%=contextPath%>/<%=b.getAttachmentPath() + b.getAttachmentName()%>">
                <span>첨부<i class="bi bi-file-earmark-arrow-down-fill"></i><%=b.getAttachmentName()%></span></a>
                <%} %>
            </div>
                
        </div>
        <div class="d-flex justify-content-between mb-3 p-2">
            <a class="btn btn-outline-nubigoMain btn-sm"><i class="bi bi-chevron-left"></i>이전 글</a>
            <a class="btn btn-nubigoMain btn-sm" href="<%=contextPath%>/list.bo?currentPage=1">목록으로</button>
            <a class="btn btn-outline-nubigoMain btn-sm">다음 글<i class="bi bi-chevron-right"></i></a>
        </div>
        <div class="board-comments mb-5">
            <form class="border rounded-lg p-3 mb-4">
                <div class="">
                    <label class="font-weight-bold">댓글 쓰기</label>
                    <%if(loginUser!=null){ %>
                    <div class="d-flex">
                        <textarea class="form-control w-100" id="replyContent" rows="3" style="resize: none;"></textarea>
                        <button class="btn btn-nubigoSub flex-shrink-0" onclick="insertReply();">등록</button>
                    </div>
                    <%}else{ %>
                    <div class="d-flex">
                        <textarea class="form-control w-100" rows="3" style="resize: none;" readonly>로그인 후 이용가능한 서비스입니다.</textarea>
                        <button class="btn btn-nubigoSub flex-shrink-0" disabled>등록</button>
                    </div>
                    <%} %>
                </div>
            </form>
        </div>
            <div class="p-2 border rounded bg-light">
                댓글<b>2</b>
            </div>
            <div id="reply-board">
	            <div class="board-comment border-bottom">
	                <div class="d-flex">
	                    <div class="p-2 font-weight-bold">마시멜로우</div>
	                    <div class="p-2 flex-grow-1 text-muted"><small>2022.04.27 15:11</small></div>
	                    <div class="p-2"><small><span onclick="commentUpdate();"><i class="bi bi-pencil"></i>수정</span><span onclick="commentDelete();"><i class="bi bi-trash ml-2"></i>삭제</span></small></div>
	                </div>
	                <div>
	                    <div class="p-2">반갑뜹니다</div>
	                </div> 
	            </div> 
	            <form class="d-none" id="commentUpdateShow">
	                <div class="p-2">
	                    <div class="d-flex">
	                        <label for="commentUpdate" class="font-weight-bold flex-grow-1"><i class="bi bi-arrow-return-right"></i>댓글 수정</label>
	                        <small><span onclick="commentUpdate();"><i class="bi bi-x-lg"></i>닫기</span></small>
	                    </div>
	                    <div class="d-flex">
	                        <textarea class="form-control w-100" id="commentUpdate" rows="3" style="resize: none;"></textarea>
	                        <button class="btn btn-nubigoSub flex-shrink-0" onclick="commentInsert();">등록</button>
	                    </div>
	                </div>
	            </form>
	        </div>
        <div class="board-comment border-bottom">
            <div class="d-flex">
                <div class="p-2 font-weight-bold">username</div>
                <div class="p-2  flex-grow-1 text-muted"><small>2022.04.26 15:11</small></div>
                <div class="p-2"><small><span data-toggle="modal" data-target="#comment-report"><i class="bi bi-megaphone"></i>신고</small></div>
            </div>
            <div>
                <div class="p-2">안녕하세요</div>
            </div>
        </div>
        <div class="modal fade" id="comment-report" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title"">신고 사유를 선택해주세요.</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body d-flex justify-content-between">
                        <div class="custom-control custom-radio">
                            <input type="radio" id="commentRadio1" name="commentReportRadio" class="custom-control-input">
                            <label class="custom-control-label" for="commentRadio1">욕설</label>
                        </div>
                        <div class="custom-control custom-radio">
                            <input type="radio" id="commentRadio2" name="commentReportRadio" class="custom-control-input">
                            <label class="custom-control-label" for="commentRadio2">음란</label>
                        </div>
                        <div class="custom-control custom-radio">
                            <input type="radio" id="commentRadio3" name="commentReportRadio" class="custom-control-input">
                            <label class="custom-control-label" for="commentRadio3">홍보 및 반복글</label>
                        </div>
                        <div class="custom-control custom-radio">
                            <input type="radio" id="commentRadio4" name="commentReportRadio" class="custom-control-input">
                            <label class="custom-control-label" for="commentRadio4">기타</label>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-nubigoSub" id="commentReportBtn">신고</button>
                        <button type="button" class="btn btn-nubigoMain" data-dismiss="modal">취소</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        $(function(){
            $('#commentReportBtn').click(function () {
                if (!$('input[name=commentReportRadio]:checked').val()) {
                    alert('항목을 선택해주세요.');
                    return false;
                } else {
                    alert('신고가 완료되었습니다.');
                    location.reload();
                }
            })

            selectReplyList();
        });
        function reportBoard(){
            if (!$('input[name=boardReport]:checked').val()) {
                alert('항목을 선택해주세요.');
                return false;
            }else{
                $.ajax({
                    url:"report.bo",
                    data:{
                        bno:'<%=b.getBoardNo()%>',
                        report:$('input[name=boardReport]:checked').val()
                    },
                    success:function(result) {
                        if(result>0){
                            alert("게시글 신고 성공");
                            location.href="<%=contextPath%>/list.bo?currentPage=1";
                        }else{
                            alert("게시글 신고 실패");
                        }
                    },
                    error:function(){
                        console.log("게시글 신고 ajax 통신 실패");
                    }
                })
            }
        }
        
        function deleteBoard(){
            if (confirm("이 게시글을 삭제하시겠습니까?") == true) {    //확인
                $.ajax({
                    url:"delete.bo",
                    data:{
                        bno:'<%=b.getBoardNo()%>'
                    },
                    type:"post",
                    success:function(result){
                        if(result>0){
                            alert('게시글을 삭제했습니다');
                            location.href="<%=contextPath%>/list.bo?currentPage=1";
                        }else{
                            alert('게시글 삭제 실패');
                        }
                    },
                    error:function(){
                        console.log("게시글 삭제 ajax 통신 실패");
                    }
                })
            } else {   //취소
                return false;
            }
        }
        function insertReply() {
            $.ajax({
                url: "rinsert.bo",
                data: {
                    content: $("#replyContent").val(),
                    bno: '<%= b.getBoardNo() %>'
                },
                type: "post",
                success: function (result) {

                    // result 가 1 이라면 성공 / 0 이라면 실패
                    if (result > 0) { // 댓글작성 성공

                        // 갱신된 댓글 리스트 조회
                        selectReplyList();

                        // textarea 초기화
                        $("#replyContent").val("");
                    }
                    else { // 댓글작성 실패
                        alert("댓글 등록에 실패했습니다.");
                    }
                },
                error: function () {
                    console.log("댓글 작성용 ajax 통신 실패!");
                }
            });
        }
        function selectReplyList(){
            $.ajax({
                url: "rlist.bo",
                data: {bno:'<%=b.getBoardNo()%>'},
                success: function (result) {
                    var resultStr="";
                    for (var i = 0; i < result.length; i++) {
                        resultStr+=
                        '<div class="board-comment border-bottom">'+
                            '<div class="d-flex">'+
                                '<div class="p-2 font-weight-bold">'+result[i].memberId+'</div>'+
                                '<div class="p-2 flex-grow-1 text-muted"><small>'+result[i].replyDate+'</small></div>'+
                            '</div>'+
                            '<div>'+
                                '<div class="p-2">'+result[i].replyContent+'</div>'+
                            '</div>'+
                        '</div>';
                    }
                    $("#reply-board").html(resultStr);
                },
                error: function () {
                    console.log("댓글리스트 조회용 ajax 통신 실패!");
                }
            });
        }
        function commentDelete() {
            if (confirm("댓글을 삭제하시겠습니까?") == true) {    //확인
                alert('삭제가 완료되었습니다.');
            } else {   //취소
                return false;
            }
        }
        function commentUpdate(){
            $("#commentUpdateShow").toggleClass("d-none");
        }
    </script>
</body>
</html>