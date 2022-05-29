package com.nubigo.review.model.service;

import static com.nubigo.common.JDBCTemplate.close;
import static com.nubigo.common.JDBCTemplate.commit;
import static com.nubigo.common.JDBCTemplate.getConnection;
import static com.nubigo.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.nubigo.common.model.vo.PageInfo;
import com.nubigo.review.model.dao.ReviewDao;
import com.nubigo.review.model.vo.Review;
import com.nubigo.review.model.vo.ReviewAttachment;

public class ReviewService {
	public ArrayList<Review> searchReview(String keyword, String season, String age, String group) {
		Connection conn = getConnection();
		
		ArrayList<Review> list = new ReviewDao().searchReview(conn, keyword, season, age, group);
		
		close(conn);
		
		return list;
	}
	
	public ArrayList<Review> searchReviewList(PageInfo pi, String keyword, String season, String age, String group) {
		Connection conn = getConnection();
		
		ArrayList<Review> list = new ReviewDao().searchReviewList(conn, pi, keyword, season, age, group);
		
		close(conn);
		
		return list;
	}
	
	public Review selectReview(int reviewNo) {
		Connection conn = getConnection();
		
		Review r = new ReviewDao().selectReview(conn, reviewNo);
		
		close(conn);
		
		return r;
		
	}
	
	public ArrayList<ReviewAttachment> selectReviewAttachment(int reviewNo) {
		
		Connection conn = getConnection();
		
		ArrayList<ReviewAttachment> list = new ReviewDao().selectReviewAttachment(conn, reviewNo);
		
		close(conn);
		
		return list;
		
	}
	
	
	public ArrayList<Review> selectBestReviews() {
		
		Connection conn = getConnection();
		
		ArrayList<Review> list = new ReviewDao().selectBestReviews(conn);
		
		close(conn);
		
		return list;
		
	}
	
	public ArrayList<Review> selectBestCategoryReviews(String category) {
		Connection conn = getConnection();
		ArrayList<Review> list = new ReviewDao().selectBestCategoryReviews(conn, category);
		close(conn);
		return list;
	}
	public ArrayList<Review> showMyReviews(int memberNo) {
		Connection conn = getConnection();
		
		ArrayList<Review> list = new ReviewDao().showMyReviews(conn, memberNo);
		
		close(conn);
		
		return list;
	}
	
	public ArrayList<Review> myReviewList(PageInfo pi, int memberNo) {
		Connection conn = getConnection();
		
		ArrayList<Review> list = new ReviewDao().myReviewList(conn, pi, memberNo);
		
		close(conn);
		
		return list;
	}
	
	public int isLiked(int reviewNo, int memberNo) {
		
		Connection conn = getConnection();
		
		int result = new ReviewDao().isLiked(conn, reviewNo, memberNo);
		
		close(conn);
		
		return result;
		
	}
	
	public int likeReview(int memberNo, int reviewNo) {
		Connection conn = getConnection();
		
		int result = new ReviewDao().likeReview(conn, memberNo, reviewNo);
		
		if(result > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	public ArrayList<Review> myReviewSearchList(PageInfo pi, int memberNo, String keyword) {
		Connection conn = getConnection();
		
		ArrayList<Review> list = new ReviewDao().myReviewSearchList(conn, pi, memberNo, keyword);
		
		close(conn);
		
		return list;
	}
	
	public int insertReview(Review r, ArrayList<ReviewAttachment> ra) {
		Connection conn=getConnection();
		int result1=new ReviewDao().insertReview(conn,r);
		int result2=new ReviewDao().insertReviewAttachment(conn, ra);
		if(result1>0&&result2>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result1*result2;
	}
	
}
