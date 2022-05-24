package com.nubigo.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nubigo.board.model.service.BoardService;
import com.nubigo.board.model.vo.Board;
import com.nubigo.common.model.vo.PageInfo;

/**
 * Servlet implementation class BoardSearchController
 */
@WebServlet("/boardSearch.bo")
public class BoardSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardSearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search=request.getParameter("boardSearch");
		String keyword=request.getParameter("keyword");
		
		//-----페이징 처리-----//
				//기본적으로 구해야 하는 변수들
				int listCount; //현재 총 게시글 갯수
				int currentPage; //현재 보여질 페이지(즉, 사용자가 요청한 페이지)
				int pageLimit; //페이지 하단에 보여질 페이징바의 페이지 최대 갯수
				int boardLimit; //한 페이지에 보여질 게시글의 최대 갯수(한 페이지당 게시글이 몇개 단위씩 보여질건지)
				
				//위의 변수들을 이용해서 계산해서 구해야 하는 변수들
				int maxPage; //가장 마지막 페이지가 몇번 페이지인지(총 페이지수)
				int startPage; //페이지 하단에 보여질 페이징바의 시작수
				int endPage; //페이지 하단에 보여질 페이징바의 끝수
				
				//*listCount: 현재 총 게시글 갯수(단, STATUS 값이 'Y'일 경우)
				listCount=new BoardService().selectListCount();
				//System.out.println(listCount);
				
				//*currentpage: 현재페이지(즉, 사용자가 요청한 페이지)
				currentPage=Integer.parseInt(request.getParameter("currentPage"));
				
				//*pageLimit: 페이지 하단에 보여질 페이징바의 페이지 최대 갯수(페이지목록들이 몇개 단위씩 보여질건지 지정)
				pageLimit=10;
				
				//*boardLimit: 한 페이지에 보여질 게시글의 최대갯수(게시글을 몇개 단위씩 보여질건지 지정)
				boardLimit=10;
				maxPage=(int)(Math.ceil((double)listCount/boardLimit));
				startPage=(currentPage-1)/pageLimit*pageLimit+1;
				endPage=startPage+pageLimit-1;
				if(endPage>maxPage) {
					endPage=maxPage;
				}
				PageInfo pi=new PageInfo(listCount,currentPage,pageLimit,boardLimit,maxPage,startPage,endPage);
		
		ArrayList<Board> list=new BoardService().boardSearch(search,keyword,pi);
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		request.setAttribute("search", search);
		request.setAttribute("keyword", keyword);

		//System.out.println(list);
		request.getRequestDispatcher("views/board/boardSearchList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
