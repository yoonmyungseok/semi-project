package com.nubigo.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nubigo.board.model.service.BoardService;
import com.nubigo.member.model.vo.Member;
import com.nubigo.member.model.vo.Reply;

/**
 * Servlet implementation class ReplyInsertController
 */
@WebServlet("/rinsert.bo")
public class ReplyInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String replyContent=request.getParameter("content");
		int boardNo=Integer.parseInt(request.getParameter("bno"));
		
		//추가적으로 필요한 값: 현재 로그인한 사용자의 회원번호
		//=>세션객체로부터 얻어냄
		int userNo=((Member)request.getSession().getAttribute("loginUser")).getMemberNo();
		
		Reply r=new Reply();
		r.setReplyContent(replyContent);
		r.setBoardNo(boardNo);
		r.setMemberNo(userNo);
		
		int result=new BoardService().insertReply(r);
		
		if(result>0) {
			request.getSession().setAttribute("alertMsg", "성공적으로 댓글이 등록되었습니다");
			response.sendRedirect(request.getContextPath()+"/detail.bo?bno="+boardNo);
		}else {
			request.setAttribute("errorMsg", "게시글 등록 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		/*
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().print(result);
		*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
