package com.nubigo.list.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nubigo.list.model.service.ListService;

/**
 * Servlet implementation class MyListDelete
 */
@WebServlet("/mylistviewdelete.me")
public class MyListDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyListDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int listNo = Integer.parseInt(request.getParameter("lno"));
		
		int result = new ListService().deleteMyList(listNo);
		
		if(result > 0) {
			
			request.getSession().setAttribute("alertMsg", "리스트가 삭제되었습니다.");
			response.sendRedirect(request.getContextPath() + "/listview.me?currentPage=1");
		}
		else {
			
			request.setAttribute("errorMgs", "리스트 삭제 실패");
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
