package com.nubigo.place.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nubigo.place.model.service.PlaceService;

/**
 * Servlet implementation class PlaceDeleteController
 */
@WebServlet("/delete.pl")
public class PlaceDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 글 번호 뽑기
		int placeNo = Integer.parseInt(request.getParameter("pno"));
		
		int result = new PlaceService().deletePlace(placeNo);
		
		if(result > 0) { // 성공
			
			request.getSession().setAttribute("alertMsg", "성공적으로 여행지가 삭제되었습니다.");
			response.sendRedirect("/nbg/adminlist.pl?currentPage=1");
			
		} else { // 실패
			
			request.getSession().setAttribute("alertMsg", "여행지 삭제에 실패했습니다.");
			response.sendRedirect("/nbg/adminlist.pl?currentPage=1");
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
