package com.nubigo.place.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nubigo.common.model.vo.PageInfo;
import com.nubigo.place.model.service.PlaceService;
import com.nubigo.place.model.vo.Place;

/**
 * Servlet implementation class AdminPlaceListController
 */
@WebServlet("/adminlist.pl")
public class AdminPlaceListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPlaceListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 페이징 처리
		int listCount;		// 현재 총 여행지 개수
		int currentPage;	// 현재 보여질 페이지
		int pageLimit;		// 하단 페이징바의 최대 개수
		int boardLimit;		// 한 페이지 당 여행지 개수
		
		int maxPage;	// 총 페이지 수
		int startPage;	// 페이징바의 시작수
		int endPage;	// 페이징바의 끝수
		
		// 총 여행지 개수
		listCount = new PlaceService().selectListCount();
		
		// 현재 페이지
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		// 페이징바의 최대 개수
		pageLimit = 10;
		
		// 한 페이지 당 여행지 개수
		boardLimit = 10;
		
		// 페이징바의 가장 마지막 페이지
		maxPage = (int)Math.ceil((double)listCount / boardLimit);
		
		// 페이징바의 시작수
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		
		// 페이징바의 끝수
		endPage = startPage + pageLimit - 1;
		
		// 페이징바의 끝수가 페이징바의 가장 마지막 수보다 클 경우
		// 페이징바의 끝수를 페이징바의 가장 마지막 수로 진행
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		
		// 페이징 바 생성에 필요한 객체 세팅
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit,
				                   maxPage, startPage, endPage);
		
		// 현재 페이지에 보여질 여행지 리스트 요청
		ArrayList<Place> list = new PlaceService().selectList(pi);
		
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		
		request.getRequestDispatcher("views/place/adminPlaceView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
