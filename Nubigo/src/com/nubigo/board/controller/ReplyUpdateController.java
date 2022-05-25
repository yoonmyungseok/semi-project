package com.nubigo.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nubigo.board.model.service.BoardService;
import com.nubigo.member.model.vo.Reply;

/**
 * Servlet implementation class ReplyUpdateController
 */
@WebServlet("/rupdate.bo")
public class ReplyUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int boardNo=Integer.parseInt(request.getParameter("bno"));
		int replyNo=Integer.parseInt(request.getParameter("rno"));
		String content=request.getParameter("content");
		
		Reply r=new Reply();
		r.setBoardNo(boardNo);
		r.setReplyNo(replyNo);
		r.setReplyContent(content);
		
		int result=new BoardService().updateReply(r);
		
		if(result>0) {
			request.getSession().setAttribute("alertMsg", "성공적으로 댓글이 수정되었습니다");
			response.sendRedirect(request.getContextPath()+"/detail.bo?bno="+boardNo);
		}else {
			request.setAttribute("errorMsg", "댓글 수정 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		
		//System.out.println(boardNo);
		//System.out.println(replyNo);
		//System.out.println(content);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
