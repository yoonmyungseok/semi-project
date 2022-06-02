package com.nubigo.list.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.nubigo.list.model.service.ListService;
import com.nubigo.list.model.vo.ListPlaces;
import com.nubigo.place.model.vo.Place;

/**
 * Servlet implementation class travelListFormController
 */
@WebServlet("/listForm.li")
public class travelListFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public travelListFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int listNo = Integer.parseInt(request.getParameter("listNo"));
		
		ArrayList<ListPlaces> placeList = new ListService().selectMyPlace(listNo);
		System.out.println(placeList);
		System.out.println(placeList.get(0).getStartDate());
		
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(placeList, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
