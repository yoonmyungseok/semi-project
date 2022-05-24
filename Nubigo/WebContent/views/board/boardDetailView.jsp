<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.nubigo.board.model.vo.Board, com.nubigo.member.model.vo.Reply, java.util.ArrayList" %>
<%
	Board b=(Board)request.getAttribute("b");
    ArrayList<Reply> reply=(ArrayList<Reply>)request.getAttribute("reply");
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
                <div class="mr-1">조회수 <b><%=b.getCount()%></b></div>
                <div>댓글 <b><%=b.getReplyCount() %></b></div>
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
                <%}else{ %>
                <button type="button" class="btn btn-nubigoSub btn-sm" data-toggle="modal" data-target="#board-report">신고</button>
                <%}%>
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
            <a class="btn btn-nubigoMain btn-sm" href="<%=contextPath%>/list.bo?currentPage=1">목록으로</a>
            <a class="btn btn-outline-nubigoMain btn-sm">다음 글<i class="bi bi-chevron-right"></i></a>
        </div>
        <div class="board-comments mb-5">
            <form class="border rounded-lg p-3 mb-4" action="<%=contextPath%>/rinsert.bo" method="post">
                <div class="">
                    <label class="font-weight-bold">댓글 쓰기</label>
                    <%if(loginUser!=null){ %>
                    <div class="d-flex">
                        <textarea class="form-control w-100" id="replyContent" rows="3" style="resize: none;" name="content" required></textarea>
                        <input type="hidden" name="bno" value="<%=b.getBoardNo()%>">
                        <!--<button class="btn btn-nubigoSub flex-shrink-0" onclick="insertReply();">등록</button>-->
                        <button class="btn btn-nubigoSub flex-shrink-0" type="submit">등록</button>
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
                댓글 <b><%=b.getReplyCount() %></b>
            </div>
            <%if(reply.isEmpty()){ %>
            <div class="board-comment border-bottom reply-board">
                <div class="p-2">댓글이 없습니다.</div>
            </div>
            <%}else{ %>
            <%for(Reply r:reply){ %>     
            <div class="board-comment border-bottom reply-board" data-reply-no="<%=r.getReplyNo()%>">
                <div>
                    <div class="d-flex">
                        <div class="p-2 font-weight-bold"><%=r.getMemberId() %></div>
                        <div class="p-2 flex-grow-1 text-muted"><small><%=r.getReplyDate()%></small></div>
                        <%if(loginUser!=null){ %>
                        <div class="p-2">
                            <small>
                                <% if(loginUser.getMemberId().equals(r.getMemberId())){%>
                                <span class="replyUpdateBtn" data-reply-member="<%=r.getMemberId()%>"><i class="bi bi-pencil"></i>수정</span>
                                <span class="replyDeleteBtn" data-reply-no="<%=r.getReplyNo()%>" data-reply-member="<%=r.getMemberId()%>"><i class="bi bi-trash ml-2"></i>삭제</span>
                                <%}else{%>
                                <span class="replyReportBtn" data-toggle="modal" data-target="#comment-report" data-reply-no="<%=r.getReplyNo()%>" data-reply-member="<%=r.getMemberId()%>"><i class="bi bi-megaphone ml-2"></i>신고</span>
                                <%}%>
                            </small>
                        </div>
                        <%}%>
                    </div>
                    <div>
                        <div class="p-2"><%=r.getReplyContent()%></div>
                    </div> 
                </div> 
                <div class="d-none commentUpdateShow">
                    <div class="p-2">
                        <div class="d-flex">
                            <label for="commentUpdate" class="font-weight-bold flex-grow-1"><i class="bi bi-arrow-return-right"></i>댓글 수정</label>
                            <small><span class="replyUpdateBtn"><i class="bi bi-x-lg"></i>닫기</span></small>
                        </div>
                        <div class="d-flex">
                            <textarea class="form-control w-100" id="commentUpdate" rows="3" style="resize: none;" required></textarea>
                            <button class="btn btn-nubigoSub flex-shrink-0" onclick="boardReplyUpdate();">등록</button>
                        </div>
                    </div>
                </div>
            </div>
                <%} %>
            <%} %>
            <div class="modal fade" id="comment-report" tabindex="-1" aria-hidden="true">
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
                                <input type="radio" id="commentRadio1" name="commentReportRadio" class="custom-control-input" value="욕설">
                                <label class="custom-control-label" for="commentRadio1">욕설</label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input type="radio" id="commentRadio2" name="commentReportRadio" class="custom-control-input" value="음란">
                                <label class="custom-control-label" for="commentRadio2">음란</label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input type="radio" id="commentRadio3" name="commentReportRadio" class="custom-control-input" value="홍보 및 반복글">
                                <label class="custom-control-label" for="commentRadio3">홍보 및 반복글</label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input type="radio" id="commentRadio4" name="commentReportRadio" class="custom-control-input" value="기타">
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
            //댓글 수정 폼 토글
            $(".replyUpdateBtn").click(function () {
                var idx = $(this).closest(".reply-board").children(".commentUpdateShow");
                $(idx).toggleClass("d-none");
                $('.commentUpdateShow').not($(idx)).addClass('d-none');
            })

            //댓글 신고
            $('#commentReportBtn').click(function () {
                if (!$('input[name=commentReportRadio]:checked').val()) {
                    alert('항목을 선택해주세요.');
                    return false;
                } else {
                    $.ajax({
                        type: "POST",
                        url: "rreport.bo",
                        traditional: true,
                        data: {
                            bno: '<%=b.getBoardNo()%>',
                            rno: $(".replyReportBtn").data("replyNo"),
                            report: $('input[name=commentReportRadio]:checked').val()
                        },
                        success: function (result) {
                            if (result > 0) {
                                alert('댓글 신고 성공');
                                location.reload();
                            } else {
                                alert('댓글 신고 실패');
                            }
                        },
                        error: function () {
                            console.log('댓글 신고 통신 실패');
                        }
                    });
                }
            });
            
            //댓글 삭제
            $(".replyDeleteBtn").click(function () {
                if (confirm("댓글을 삭제하시겠습니까?") == true) {    //확인
                    $.ajax({
                        type: "POST",
                        url: "rdelete.bo",
                        traditional: true,
                        data: {
                            bno: '<%=b.getBoardNo()%>',
                            rno: $(this).data("replyNo"),
                        },
                        success: function (result) {
                            if (result > 0) {
                                alert('댓글 삭제 성공');
                                location.reload();
                            } else {
                                alert('댓글 삭제 실패');
                            }
                        },
                        error: function () {
                            console.log('댓글 삭제 통신 실패');
                        }
                    });
                    //alert('삭제가 완료되었습니다.');
                } else {   //취소
                    return false;
                }
            });
        });

        //댓글 수정
        function boardReplyUpdate(){
            if($("#commentUpdate").val()==''){
                alert('안돼');
            }else{
            $.ajax({
                type: "POST",
                url: "rupdate.bo",
                traditional: true,
                data: {
                    bno: '<%=b.getBoardNo()%>',
                    rno: $(".reply-board").data("replyNo"),
                    content: $('#commentUpdate').val()
                },
                success: function (result) {
                    if (result > 0) {
                        alert('댓글 수정 성공');
                        location.reload();
                    } else {
                        alert('댓글 수정 실패');
                    }
                },
                error: function () {
                    console.log('댓글 신고 통신 실패');
                }
            });
            }
        }

        //게시글 신고
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
        
        //게시글 삭제
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
        
    </script>
</body>
</html>