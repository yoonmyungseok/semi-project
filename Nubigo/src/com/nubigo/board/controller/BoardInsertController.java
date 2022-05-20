package com.nubigo.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nubigo.board.model.service.BoardService;
import com.nubigo.board.model.vo.Board;

/**
 * Servlet implementation class BoardInsertController
 */
@WebServlet("/insert.bo")
public class BoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String memberNo=request.getParameter("memberNo");
		String boardTitle=request.getParameter("title");
		String boardContent=request.getParameter("content");
		
		Board b=new Board();
		b.setMemberId(memberNo);
		b.setBoardTitle(boardTitle);
		b.setBoardContent(boardContent);
		
		int result=new BoardService().insertBoard(b);
		
		if(result>0) {
			//성공=>/jsp/list.bo로 요청(리스트페이지가 보여지도록)
			request.getSession().setAttribute("alertMsg", "성공적으로 공지사항이 등록되었습니다");
			response.sendRedirect(request.getContextPath()+"/list.bo");
		}else {
			//실패=>에러페이지로 포워딩
			request.setAttribute("errorMsg", "공지사항 등록 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
