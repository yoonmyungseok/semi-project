<%@page import="oracle.net.aso.r"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.nubigo.board.model.vo.Board, com.nubigo.member.model.vo.Reply, java.util.ArrayList, com.nubigo.common.model.vo.PageInfo" %>
<%
	Board b=(Board)request.getAttribute("b");
    ArrayList<Reply> reply=(ArrayList<Reply>)request.getAttribute("reply");
    int prev=(int)request.getAttribute("prev");
    int next=(int)request.getAttribute("next");

    ArrayList<Board> list=(ArrayList<Board>)request.getAttribute("list");
            PageInfo pi=(PageInfo)request.getAttribute("pi");
            String search=(String)request.getAttribute("search");
            String keyword=(String)request.getAttribute("keyword");
            String options=(String)request.getAttribute("options");
            //페이징바 관련 변수
            int currentPage=pi.getCurrentPage();
            int startPage=pi.getStartPage();
            int endPage=pi.getEndPage();
            int maxPage=pi.getMaxPage();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>자유게시판</title>
<style>
    .list-area>tbody>tr {
        cursor: pointer;
    }
</style>
</head>
<body>
<%@ include file="../common/menubar.jsp" %>
    <div id="wrap">
        <h2 class="mb-4 p-2"><span onclick="location.href='<%=contextPath%>/list.bo?currentPage=1';" style="cursor: pointer;">자유게시판</span></h2>
        <hr>
        <div id="board-detail" class="mb-5">
            <div id="board-detail-title" class="d-flex justify-content-between p-2 border-bottom border-top bg-light">
                <h5 class="mb-0 font-weight-bold"><%=b.getBoardTitle() %></h5>
                <div><small><%=b.getBoardDate() %></small></div>
            </div>
            <div id="board-detail-header" class="d-flex p-2 border-bottom">
                <div class="mr-auto"><%=b.getMemberId() %></div>
                <div class="mr-1"><small>조회수 <b><%=b.getCount()%></b></small></div>
                <div><small>댓글 <b><%=b.getReplyCount() %></b></small></div>
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
                        <div class="modal-body d-none" id="boardReportContent">
                            <textarea name="boardReportContent"cols="30" rows="3" style="resize: none;"></textarea>
                        </div>
                        <div class="modal-footer">        
                            <button type="button" class="btn btn-nubigoMain" data-dismiss="modal">취소</button>
                            <button type="button" class="btn btn-nubigoSub" onclick="reportBoard();">신고</button>
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
            <% if(prev!=0){ %>
            <a class="btn btn-outline-nubigoMain btn-sm" href="<%=contextPath%>/detail.bo?bno=<%=prev%>" id="boardPrev"><i class="bi bi-chevron-left"></i>이전 글</a>
            <%}else{%>
            <a class="btn btn-outline-nubigoMain btn-sm" id="boardPrev"><i class="bi bi-chevron-left"></i>이전 글</a>
            <%}%>
            <a class="btn btn-nubigoMain btn-sm" href="<%=contextPath%>/list.bo?currentPage=1">목록으로</a>
            <% if(next!=0){%>
            <a class="btn btn-outline-nubigoMain btn-sm" href="<%=contextPath%>/detail.bo?bno=<%=next%>" id="boardNext">다음 글<i class="bi bi-chevron-right"></i></a>
            <%}else{%>
            <a class="btn btn-outline-nubigoMain btn-sm" id="boardNext">다음 글<i class="bi bi-chevron-right"></i></a>
            <%}%>
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
            <div class="board-comment border-bottom reply-board mb-5">
                <div class="p-2">댓글이 없습니다.</div>
            </div>
            <%}else{ %>
            <%for(Reply r:reply){ %>     
            <div class="board-comment border-bottom reply-board mb-5" data-reply-no="<%=r.getReplyNo()%>">
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
                <form class="d-none commentUpdateShow" method="post" action="<%=contextPath%>/rupdate.bo">
                    <input type="hidden" name="bno" value="<%=b.getBoardNo()%>">
                    <input type="hidden" name="rno" value="<%=r.getReplyNo()%>">
                    <div class="p-2">
                        <div class="d-flex">
                            <label for="commentUpdate" class="font-weight-bold flex-grow-1"><i class="bi bi-arrow-return-right"></i>댓글 수정</label>
                            <small><span class="replyUpdateBtn"><i class="bi bi-x-lg"></i>닫기</span></small>
                        </div>
                        <div class="d-flex">
                            <textarea class="form-control w-100" id="commentUpdate" rows="3" name="content" style="resize: none;" required></textarea>
                            <button type="submit" class="btn btn-nubigoSub flex-shrink-0">등록</button>
                        </div>
                    </div>
                </form>
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
                        <div class="modal-body d-none" id="boardReplyReportContent">
                            <textarea name="boardReplyReportContent" cols="30" rows="3" style="resize: none;"></textarea>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-nubigoMain" data-dismiss="modal">취소</button>
                            <button type="button" class="btn btn-nubigoSub" id="commentReportBtn">신고</button>
                        </div>
                    </div>
                </div>
            </div>  
            <div class="btn-group btn-group-toggle mb-2 btn-group-sm">
                <%if(keyword==null&&search==null){%>
                <a href="<%=contextPath%>/list.bo?options=new&keyword=&search=&currentPage=1">
                    <div class="btn btn-nubigoMain btn-sm mr-1">최신순</div>
                </a>
                <a href="<%=contextPath%>/list.bo?options=old&keyword=&search=&currentPage=1">
                    <div class="btn btn-nubigoSub btn-sm">조회순</div>
                </a>
                <%}else{%>
                <a href="<%=contextPath%>/list.bo?options=new&keyword=<%=keyword%>&search=<%=search%>&currentPage=1">
                    <div class="btn btn-nubigoMain btn-sm mr-1">최신순</div>
                </a>
                <a href="<%=contextPath%>/list.bo?options=old&keyword=<%=keyword%>&search=<%=search%>&currentPage=1">
                    <div class="btn btn-nubigoSub btn-sm">조회순</div>
                </a>
                <%}%>
                    </div>
                    <table class="table table-hover table-sm text-center list-area">
                        <thead class="thead-light">
                            <tr>
                                <th scope="col" style="width:10%;">글번호</th>
                                <th scope="col" style="width:55%;">제목</th>
                                <th scope="col" style="width:15%;">작성자</th>
                                <th scope="col" style="width:15%;">작성일</th>
                                <th scope="col" style="width:5%;">조회</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%if(list.isEmpty()){ %>
                <!-- 리스트가 비어있을 경우 -->
                <tr style="pointer-events: none;">
                    <td colspan="5">존재하는 게시글이 없습니다.</td>
                </tr>
                <%}else{ %>
                <!-- 리스트가 존재할 경우 -->
                <%for(Board l:list){%>
                <tr>
                    <td><%=l.getBoardNo()%></td>
                    <!--<td><%=b.getrNum()%></td>-->
                    <td><%=l.getBoardTitle() %>
                        <%if(l.getReplyCount()==0){ %><%}else{ %><small><b>[<%=l.getReplyCount() %>]</b></small><%} %></td>
                    <td><%=l.getMemberId() %></td>
                    <td><%=l.getBoardDate() %></td>
                    <td><%=l.getCount() %></td>
                </tr>
                <%} %>
                <%} %>
                </tbody>
                </table>
                <div class="d-flex mb-5">
                    <form class="form-inline" method="get" action="<%=contextPath %>/list.bo">
                        <input type="hidden" name="currentPage" value="<%=currentPage%>">
                        <div class="mr-sm-2">
                            <select class="custom-select" name="keyword">
                                <option value="제목내용">제목+내용</option>
                                <option value="제목">제목</option>
                                <option value="내용">내용</option>
                                <option value="작성자">작성자</option>
                            </select>
                        </div>
                        <div class="input-group">
                            <input type="search" class="form-control" placeholder="검색어를 입력해 주세요" name="search" required
                                <%if(search!=null){%> value="<%=search%>" <%}%> >
                                <div class="input-group-append">
                                    <button class="btn btn-nubigoMain" type="submit" id="button-addon2"><i class="bi bi-search"></i></button>
                                </div>
                            </div>
                        </form>
                        <%if(loginUser!=null){ %> <a class="btn btn-nubigoMain ml-auto" type="button"
                                href="<%=contextPath%>/enrollForm.bo">글쓰기</a>
                            <%} %>
                        </div>
                        <!--페이징바-->
                        <nav>
                            <ul class="pagination justify-content-center">
                                <%if(currentPage!=1){ %>
                                <li class="page-item">
                                    <button class="page-link"
                                        onclick="location.href='<%=contextPath%>/list.bo?currentPage=<%=currentPage-1%>&keyword=&search=&options=<%=options%>';">
                                        <span aria-hidden="true">&laquo;</span>
                                    </button>
                                </li>
                                <%}%>
                            <%for(int p=startPage;p<=endPage;p++){ %>
                                <% if(p!=currentPage){ %>
                                <li class="page-item"><button class="page-link"
                                        onclick="location.href='<%=contextPath%>/list.bo?currentPage=<%=p%>&keyword=&search=&options=<%=options%>';"><%=p %></button>
                                </li>
                                <%}else{ %>
                                <li class="page-item active"><button class="page-link"><%=p %></button></li>
                                <%} %>
                                <%} %>
                                <%if(currentPage!=maxPage){ %>
                                <li class="page-item">
                                    <button class="page-link"
                                        onclick="location.href='<%=contextPath%>/list.bo?currentPage=<%=currentPage+1%>&keyword=&search=&options=<%=options%>';">
                                        <span aria-hidden="true">&raquo;</span>
                                    </button>
                                </li>
                                <%}%>
                        </ul>
                    </nav>            
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
                    if($("#commentRadio4:checked").val() && $("textarea[name=boardReplyReportContent]").val() == ""){
                        alert('신고 사유를 작성해주세요');
                    }else{
                        $.ajax({
                        type: "POST",
                        url: "rreport.bo",
                        traditional: true,
                        data: {
                            bno: '<%=b.getBoardNo()%>',
                            rno: $(".replyReportBtn").data("replyNo"),
                            report: $('input[name=commentReportRadio]:checked').val(),
                            content: $("textarea[name=boardReplyReportContent]").val()
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

        //게시글 신고
        function reportBoard(){
            if (!$('input[name=boardReport]:checked').val()) {
                alert('항목을 선택해주세요.');
                return false;
            }else{
                if($("#boardRadio4:checked").val() && $("textarea[name=boardReportContent]").val() == ""){
                    alert('신고 사유를 작성해주세요');
                }else{
                    $.ajax({
                        url: "report.bo",
                        data: {
                            bno: '<%=b.getBoardNo()%>',
                            report: $('input[name=boardReport]:checked').val(),
                            content: $("textarea[name=boardReportContent]").val()
                        },
                        success: function (result) {
                            if (result > 0) {
                                alert("게시글 신고 성공");
                                location.href = "<%=contextPath%>/list.bo?currentPage=1";
                            } else {
                                alert("게시글 신고 실패");
                            }
                        },
                        error: function () {
                            console.log("게시글 신고 ajax 통신 실패");
                        }
                    })
                }
                
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

        //신고 기타 작성 창
        $("input[name='boardReport']").click(function(){
            if($(this).val()==$("#boardRadio4").val()){
                $("#boardReportContent").removeClass("d-none");
            }else{
                $("#boardReportContent").addClass("d-none");
            }
        })
        //댓글 신고 기타 작성 창
        $("input[name='commentReportRadio']").click(function () {
                if ($(this).val() == $("#commentRadio4").val()) {
                    $("#boardReplyReportContent").removeClass("d-none");
                } else {
                    $("#boardReplyReportContent").addClass("d-none");
                }
        })
        //이전글 클릭
        $("#boardPrev").click(function(){
            if('<%=prev%>'==0){
                alert('첫 번째 게시글입니다.');
            }
        })
        //다음글 클릭
        $("#boardNext").click(function () {
            if ('<%=next%>' == 0) {
                alert('마지막 게시글입니다.');
            }
        })

        //밑에 게시글 리스트
        $(function () {
                $(".list-area>tbody>tr").click(function () {
                    location.href = "<%=contextPath%>/detail.bo?bno=" + $(this).children().eq(0).text() + "&currentPage=<%=currentPage%>";
                });

            })
            if ('<%=keyword%>' != "") {
                for (var i = 0; i < 4; i++) {
                    if ($(".custom-select option").eq(i).val() == '<%=keyword%>') {
                        $(".custom-select option").eq(i).attr('selected', true);
                    }
                }
            }
    </script>
</body>
</html>