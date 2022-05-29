package com.nubigo.place.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nubigo.place.model.service.PlaceService;
import com.nubigo.place.model.vo.Place;

/**
 * Servlet implementation class PlaceUpdateFormController
 */
@WebServlet("/updateForm.pl")
public class PlaceUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceUpdateFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int placeNo = Integer.parseInt(request.getParameter("pno"));
		System.out.println(placeNo);
	
		Place p = new PlaceService().selectPlace(placeNo);
		
		request.setAttribute("p", p);
		
		// 여행지 수정 폼 포워딩
		request.getRequestDispatcher("views/place/placeUpdateForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
