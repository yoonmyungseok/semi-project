package com.nubigo.review.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nubigo.common.model.vo.PageInfo;
import com.nubigo.member.model.vo.Member;
import com.nubigo.review.model.service.ReviewService;
import com.nubigo.review.model.vo.Review;

/**
 * Servlet implementation class ReviewSearchController
 */
@WebServlet("/reviewSearch.re")
public class ReviewSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewSearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String keyword = request.getParameter("keyword");
		String season = request.getParameter("season");
		String age = request.getParameter("age");
		String group = request.getParameter("groupWith");
		// ----- 페이징 처리 -----
		// 기본적으로 구해야 하는 변수들
		int listCount; // 현재 총 게시글의 개수
		int currentPage; // 현재 보여질 페이지 (사용자가 요청한 페이지)
		int pageLimit; // 페이지 하단에 보여질 페이징바의 페이지 최대 개수
		int boardLimit; // 한 페이지에 보여질 게시글의 최대 개수 (한 페이지당 게시글이 몇 개 단위씩 보여질 것인지)
		
		// 위의 변수들을 이용해 계산
		int maxPage; // 총 페이지 수
		int startPage; // 페이지 하단에 보여질 페이징바의 시작수
		int endPage; // 페이지 하단에 보여질 페이징바의 끝수
		
		ArrayList<Review> rlist = new ReviewService().searchReview(keyword, season, age, group);
		
		listCount = rlist.size();
		Member loginUser = (Member)request.getSession().getAttribute("loginUser");
		
		// * currentPage : 현재페이지 (사용자가 요청한 페이지)
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		// * pageLimit : 페이지 하단에 보여질 페이징바의 페이지 최대 갯수
		//               (페이지 목록들이 몇 개 단위씩 보여질 지 지정)
		pageLimit = 5;
		
		// * boardLimit : 한 페이지에 보여질 게시글의 최대 갯수
		//                 ( 게시글이 몇 개 단위로 보여질 지 지정)
		boardLimit = 4;
		
		// * maxPage : 가장 마지막 페이지가 몇 번째 페이지인지
		maxPage = (int)(Math.ceil((double)listCount / boardLimit));
		
		// * startPage : 페이지 하단에 보여질 페이징바의 시작수
		startPage = ((currentPage - 1) - (currentPage - 1) % 10) + 1;
		
		// * endPage : 페이지 하단에 보여질 페이징바의 끝수
		endPage = startPage + 4;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// 페이지 정보들로 VO 클래스 가공
		// 일반게시판 뿐 아니라 공지사항이니ㅏ 사진 게시판에서도 쓸 수 있도록 common 패키지에 생성
		
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		ArrayList<Review> list = new ReviewService().searchReviewList(pi, keyword, season, age, group);
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		request.setAttribute("keyword", keyword);
		request.getRequestDispatcher("views/review/reviewSearchList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
