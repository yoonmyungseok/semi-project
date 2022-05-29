package com.nubigo.list.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nubigo.common.model.vo.PageInfo;
import com.nubigo.list.model.service.ListService;
import com.nubigo.list.model.vo.Lists;
import com.nubigo.member.model.service.MemberService;
import com.nubigo.member.model.vo.Member;

/**
 * Servlet implementation class MyListView
 */
@WebServlet("/listview.me")
public class MyListViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyListViewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 로그인 후 내 문의 내역 보여주게하기 
		HttpSession session = request.getSession();
				
		Member loginUser = (Member)session.getAttribute("loginUser");
				
		if(session.getAttribute("loginUser") == null) {
			session.setAttribute("alertMsg", "로그인 후 이용 가능한 서비스입니다.");
			response.sendRedirect(request.getContextPath());
		}
		else {
			
			// 페이징 처리 (기본적으로 구해야하는변수)
			int listCount; // 현재 총 게시글 갯수
			int currentPage; // 현재 보여질 페이지(즉, 사용자가 요청한 페이지)
			int pageLimit; // 페이지 하단에 보여질 페이징바의 페이지 최대 갯수
			int boardLimit; // 한페이지에 보여질 게시글의 최대 갯수(한 페이지당 게시글이 몇개 단위씩 보여질건지)
						
			int maxPage; // 가장 마지막 페이지가 몇번페이지인지(총 페이지 수)
			int startPage; // 페이지 하단에 보여질 페이싱바의 시작수
			int endPage; // 페이지 하단에 보여질 페이징바의 끝수
			
			int memberNo = loginUser.getMemberNo();
			
			// listCount : 현재 총 게시글 갯수 (단, STATUS 값이 'Y' 일 경우)
			listCount = new ListService().selectListCount(memberNo);
			
			// cuurentPage : 현재 페이지 (즉, 사용자가 요청한 페이지)
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
					
			// pageLimit : 페이지 하단에 보여질 페이징 바의 페이지의 최대 갯수
			pageLimit = 5; //(5페이지씩)
								
			// boardLimit : 한 페이지에 보여질 게시글의 최대 갯수
			boardLimit = 5; //(한 페이지당 게시글 5개씩)
						
			// 페이징 가장 마지막 페이지
			maxPage = (int)Math.ceil((double)listCount / boardLimit);
			if(maxPage == 0) {
				maxPage = 1;
			}
			// startPage : 페이지 하단에 보여질 페이징바의 시작수
			startPage = currentPage - (currentPage % pageLimit) + 1;
						
			// 페이지 하단에 보여질 페이징 바의 끝수(1 ~ 5 == 1 + 5 - 1 : 5)
			endPage = startPage + pageLimit - 1;
						
			if(endPage > maxPage) { // 마지막페이지가 end페이지보다 적으면 마지막페이지를 최대페이지로변경
				endPage = maxPage;
			}
			
			// 1. 페이징 바 만들때 필요한 객체 셋팅
			PageInfo pi = new PageInfo(listCount,currentPage,pageLimit,boardLimit,maxPage,startPage,endPage,memberNo);
			
			ArrayList<Lists> list = new ListService().selectList(pi);
			
			request.setAttribute("list", list);// list수하물 담아주기
			request.setAttribute("pi", pi); // 페이징도 같이 담아주기
			request.getRequestDispatcher("views/list/myList.jsp").forward(request, response);
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
