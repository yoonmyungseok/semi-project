package com.nubigo.review.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nubigo.member.model.vo.Member;
import com.nubigo.place.model.service.PlaceService;
import com.nubigo.place.model.vo.Place;
import com.nubigo.review.model.service.ReviewService;
import com.nubigo.review.model.vo.Review;
import com.nubigo.review.model.vo.ReviewAttachment;

/**
 * Servlet implementation class ReviewDetailPageController
 */
@WebServlet("/detail.re")
public class ReviewDetailPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewDetailPageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reviewNo = Integer.parseInt(request.getParameter("rNo"));
		
		Review r = new ReviewService().selectReview(reviewNo);
		
		request.setAttribute("r", r);
		
		Place p = new PlaceService().selectPlace(r.getPlaceNo());
		
		request.setAttribute("p", p);
		
		ArrayList<ReviewAttachment> atList = new ReviewService().selectReviewAttachment(reviewNo);
		
		request.setAttribute("atList", atList);
		
		int liked = 0;
		if((Member)request.getSession().getAttribute("loginUser") != null) {
			liked = new ReviewService().isLiked(reviewNo, ((Member)request.getSession().getAttribute("loginUser")).getMemberNo());
		}
		request.setAttribute("liked", liked);
		request.getRequestDispatcher("views/review/reviewDetailView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
