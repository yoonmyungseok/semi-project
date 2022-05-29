package com.nubigo.inquiry.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nubigo.inquiry.model.service.InquiryService;

/**
 * Servlet implementation class InquiryInsertController
 */
@WebServlet("/insertInquiry.in")
public class InquiryInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String qTitle = request.getParameter("title");
		String qContent = request.getParameter("content");
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		
		int result = new InquiryService().insertInquiry(memberNo, qTitle, qContent);
		
		if(result > 0) {
			request.getSession().setAttribute("alertMsg", "문의내용이 성공적으로 전달되었습니다.");
			response.sendRedirect(request.getContextPath() + "/answerlistview.me");
		}
		else {
			request.setAttribute("errorMsg", "게시글 등록 실패");
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
