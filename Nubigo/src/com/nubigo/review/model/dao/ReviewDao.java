package com.nubigo.review.model.dao;

import static com.nubigo.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import com.nubigo.common.model.vo.PageInfo;
import com.nubigo.review.model.vo.Review;
import com.nubigo.review.model.vo.ReviewAttachment;

public class ReviewDao {
	private Properties prop = new Properties();
	
	public ReviewDao() {

		try {
			prop.loadFromXML(new FileInputStream(ReviewDao.class.getResource("/sql/review/review-mapper.xml").getPath()));
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public ArrayList<Review> searchReview(Connection conn, String keyword, String season, String age, String group) {
		
		ArrayList<Review> list = new ArrayList<Review>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "";
		
		try {
			if(!season.equals("null")) {
				if(!age.equals("null")) {
					if(!group.equals("null")) {
						sql = prop.getProperty("searchReview123");
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(3, season);
						pstmt.setString(4, age);
						pstmt.setString(5, group);						
					}
					else {
						sql = prop.getProperty("searchReview12");
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(3, season);
						pstmt.setString(4, age);
					}
				}
				else {
					if(!group.equals("null")) {
						sql = prop.getProperty("searchReview13");
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(3, season);
						pstmt.setString(4, group);
					}
					else {
						sql = prop.getProperty("searchReview1");
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(3, season);
					}
				}
			}
			else {
				if(!age.equals("null")) {
					if(!group.equals("null")) {
						sql = prop.getProperty("searchReview23");
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(3, age);
						pstmt.setString(4, group);
					}
					else {
						sql = prop.getProperty("searchReview2");
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(3, age);
					}
				}
				else {
					if(!group.equals("null")) {
						sql = prop.getProperty("searchReview3");
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(3, group);
					}
					else {
						sql = prop.getProperty("searchReview");
						pstmt = conn.prepareStatement(sql);
					}
				}
			}
			
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");

			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Review r = new Review(rset.getInt("REVIEW_NO")
						            , rset.getInt("SCORE")
						            , rset.getString("REVIEW_TITLE")
						            , rset.getString("REVIEW_CONTENT")
						            , rset.getDate("REVIEW_DATE")
						            , rset.getString("DELETE_STATUS")
						            , rset.getString("AGE")
						            , rset.getString("SEASON")
						            , rset.getString("GROUP_WITH")
						            , rset.getString("PLACE_NAME")
						            , rset.getString("MEMBER_ID")
						            , rset.getInt("LIKES"));
				
				list.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
		
		
	}
	
	public ArrayList<Review> searchReviewList(Connection conn, PageInfo pi, String keyword, String season, String age, String group) {
		ArrayList<Review> list = new ArrayList<Review>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "";
		
		try {
			if(!season.equals("null")) {
				if(!age.equals("null")) {
					if(!group.equals("null")) {
						sql = prop.getProperty("searchReviewList123");
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(3, season);
						pstmt.setString(4, age);
						pstmt.setString(5, group);
						pstmt.setInt(6, ((pi.getCurrentPage() - 1) * pi.getBoardLimit()) + 1);
						pstmt.setInt(7, pi.getCurrentPage() * pi.getBoardLimit());
					}
					else {
						sql = prop.getProperty("searchReviewList12");
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(3, season);
						pstmt.setString(4, age);
						pstmt.setInt(5, ((pi.getCurrentPage() - 1) * pi.getBoardLimit()) + 1);
						pstmt.setInt(6, pi.getCurrentPage() * pi.getBoardLimit());
					}
				}
				else {
					if(!group.equals("null")) {
						sql = prop.getProperty("searchReviewList13");
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(3, season);
						pstmt.setString(4, group);
						pstmt.setInt(5, ((pi.getCurrentPage() - 1) * pi.getBoardLimit()) + 1);
						pstmt.setInt(6, pi.getCurrentPage() * pi.getBoardLimit());
					}
					else {
						sql = prop.getProperty("searchReviewList1");
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(3, season);
						pstmt.setInt(4, ((pi.getCurrentPage() - 1) * pi.getBoardLimit()) + 1);
						pstmt.setInt(5, pi.getCurrentPage() * pi.getBoardLimit());
					}
				}
			}
			else {
				if(!age.equals("null")) {
					if(!group.equals("null")) {
						sql = prop.getProperty("searchReviewList23");
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(3, age);
						pstmt.setString(4, group);
						pstmt.setInt(5, ((pi.getCurrentPage() - 1) * pi.getBoardLimit()) + 1);
						pstmt.setInt(6, pi.getCurrentPage() * pi.getBoardLimit());
					}
					else {
						sql = prop.getProperty("searchReviewList2");
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(3, age);
						pstmt.setInt(4, ((pi.getCurrentPage() - 1) * pi.getBoardLimit()) + 1);
						pstmt.setInt(5, pi.getCurrentPage() * pi.getBoardLimit());
					}
				}
				else {
					if(!group.equals("null")) {
						sql = prop.getProperty("searchReviewList3");
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(3, group);
						pstmt.setInt(4, ((pi.getCurrentPage() - 1) * pi.getBoardLimit()) + 1);
						pstmt.setInt(5, pi.getCurrentPage() * pi.getBoardLimit());
					}
					else {
						sql = prop.getProperty("searchReviewList");
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(3, ((pi.getCurrentPage() - 1) * pi.getBoardLimit()) + 1);
						pstmt.setInt(4, pi.getCurrentPage() * pi.getBoardLimit());
					}
				}
			}
			
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");

			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Review r = new Review(rset.getInt("REVIEW_NO")
						            , rset.getInt("SCORE")
						            , rset.getString("REVIEW_TITLE")
						            , rset.getString("REVIEW_CONTENT")
						            , rset.getDate("REVIEW_DATE")
						            , rset.getString("DELETE_STATUS")
						            , rset.getString("AGE")
						            , rset.getString("SEASON")
						            , rset.getString("GROUP_WITH")
						            , rset.getString("PLACE_NAME")
						            , rset.getString("MEMBER_ID")
						            , rset.getInt("LIKES"));
				
				list.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public Review selectReview(Connection conn, int reviewNo) {
		
		Review r = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectReview");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, reviewNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				r = new Review(rset.getInt("REVIEW_NO")
						     , rset.getInt("SCORE")
						     , rset.getString("REVIEW_TITLE")
						     , rset.getString("REVIEW_CONTENT")
						     , rset.getDate("REVIEW_DATE")
						     , rset.getString("DELETE_STATUS")
						     , rset.getString("AGE")
						     , rset.getString("SEASON")
						     , rset.getString("GROUP_WITH")
						     , rset.getInt("PLACE_NO")
						     , rset.getString("MEMBER_ID")
						     , rset.getInt("LIKES"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return r;
		
	}
	
	public ArrayList<ReviewAttachment> selectReviewAttachment(Connection conn, int reviewNo) {
		
		ArrayList<ReviewAttachment> list = new ArrayList<ReviewAttachment>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectReviewAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, reviewNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new ReviewAttachment(rset.getString("FILE_PATH")
						                    , rset.getInt("FILE_NO")
						                    , rset.getString("FILE_NAME")
						                    , rset.getInt("REVIEW_NO")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
		
	}
	
	public ArrayList<Review> selectBestReviews(Connection conn) {
		
		ArrayList<Review> list = new ArrayList<Review>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectBestReviews");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			int[] arr = new int[3];
			

			if(rset.next()) {
				arr[0] = rset.getInt("REVIEW_NO");
			}
			if(rset.next()) {
				arr[1] = rset.getInt("REVIEW_NO");
			}
			if(rset.next()) {
				arr[2] = rset.getInt("REVIEW_NO");
			}
						
			for(int i : arr) {
				sql = prop.getProperty("selectReview");
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, i);
				rset = pstmt.executeQuery();
				if(rset.next()) {
					list.add(new Review(rset.getInt("REVIEW_NO")
							          , rset.getInt("SCORE")
							          , rset.getString("REVIEW_TITLE")
							          , rset.getString("REVIEW_CONTENT")
							          , rset.getDate("REVIEW_DATE")
							          , rset.getInt("PLACE_NO")
							          , rset.getString("PLACE_NAME")
							          , rset.getString("MEMBER_ID")
							          , rset.getInt("LIKES")));
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
		
	}
	
	public ArrayList<Review> selectBestCategoryReviews(Connection conn, String category) {
		ArrayList<Review> list = new ArrayList<Review>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectBestCategoryReviews");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			rset = pstmt.executeQuery();
			
			int[] arr = new int[3];
			
			if(rset.next()) {
				arr[0] = rset.getInt("REVIEW_NO");
			}
			if(rset.next()) {
				arr[1] = rset.getInt("REVIEW_NO");
			}
			if(rset.next()) {
				arr[2] = rset.getInt("REVIEW_NO");
			}
						
			for(int i : arr) {
				sql = prop.getProperty("selectReview");
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, i);
				rset = pstmt.executeQuery();
				if(rset.next()) {
					list.add(new Review(rset.getInt("REVIEW_NO")
							          , rset.getInt("SCORE")
							          , rset.getString("REVIEW_TITLE")
							          , rset.getString("REVIEW_CONTENT")
							          , rset.getDate("REVIEW_DATE")
							          , rset.getString("DELETE_STATUS")
							          , rset.getString("AGE")
							          , rset.getString("SEASON")
							          , rset.getString("GROUP_WITH")
							          , rset.getInt("PLACE_NO")
							          , rset.getString("MEMBER_ID")
							          , rset.getInt("LIKES")));
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
		
	}
	
	public ArrayList<Review> showMyReviews(Connection conn, int memberNo) {
		ArrayList<Review> list = new ArrayList<Review>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("showMyReviews");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Review(rset.getInt("REVIEW_NO")
						          , rset.getInt("SCORE")
						          , rset.getString("REVIEW_TITLE")
						          , rset.getString("REVIEW_CONTENT")
						          , rset.getDate("REVIEW_DATE")
						          , "N"
						          , rset.getString("AGE")
						          , rset.getString("SEASON")
						          , rset.getString("GROUP_WITH")
						          , rset.getString("PLACE_NAME")
						          , rset.getString("MEMBER_ID")
						          , rset.getInt("LIKES")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
		
	}
	
	public ArrayList<Review> myReviewList(Connection conn, PageInfo pi, int memberNo) {
		ArrayList<Review> list = new ArrayList<Review>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("myReviewList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, ((pi.getCurrentPage() - 1) * pi.getBoardLimit()) + 1);
			pstmt.setInt(3, pi.getCurrentPage() * pi.getBoardLimit());
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Review(rset.getInt("REVIEW_NO")
				          , rset.getInt("SCORE")
				          , rset.getString("REVIEW_TITLE")
				          , rset.getString("REVIEW_CONTENT")
				          , rset.getDate("REVIEW_DATE")
				          , "N"
				          , rset.getString("AGE")
				          , rset.getString("SEASON")
				          , rset.getString("GROUP_WITH")
				          , rset.getString("PLACE_NAME")
				          , rset.getString("MEMBER_ID")
				          , rset.getInt("LIKES")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
		
	}
	
	public int isLiked(Connection conn, int reviewNo, int memberNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("isLiked");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewNo);
			pstmt.setInt(2, memberNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}
	
	public int likeReview(Connection conn, int memberNo, int reviewNo) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("isLiked");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, reviewNo);
			pstmt.setInt(2, memberNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				sql = prop.getProperty("deleteLike");
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, reviewNo);
				pstmt.setInt(2, memberNo);
				result= pstmt.executeUpdate()-1;
			}
			else {
				sql = prop.getProperty("likeReview");
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, memberNo);
				pstmt.setInt(2, reviewNo);
				result=pstmt.executeUpdate()+1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}
	
	public ArrayList<Review> myReviewSearchList(Connection conn, PageInfo pi, int memberNo, String keyword) {
		
		ArrayList<Review> list = new ArrayList<Review>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("myReviewSearchList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setString(3, "%" + keyword + "%");
			pstmt.setInt(4, ((pi.getCurrentPage() - 1) * pi.getBoardLimit()) + 1);
			pstmt.setInt(5, pi.getCurrentPage() * pi.getBoardLimit());
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Review(rset.getInt("REVIEW_NO")
				          , rset.getInt("SCORE")
				          , rset.getString("REVIEW_TITLE")
				          , rset.getString("REVIEW_CONTENT")
				          , rset.getDate("REVIEW_DATE")
				          , "N"
				          , rset.getString("AGE")
				          , rset.getString("SEASON")
				          , rset.getString("GROUP_WITH")
				          , rset.getString("PLACE_NAME")
				          , rset.getString("MEMBER_ID")
				          , rset.getInt("LIKES")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;		
		
	}
	
	public int insertReview(Connection conn, Review r) {
		int result=0;
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("insertReview");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, r.getScore());
			pstmt.setString(2, r.getReviewTitle());
			pstmt.setString(3, r.getReviewContent());
			pstmt.setString(4, r.getAge());
			pstmt.setString(5, r.getSeason());
			pstmt.setString(6, r.getGroupWith());
			pstmt.setString(7, r.getMemberId());
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int insertReviewAttachment(Connection conn, ArrayList<ReviewAttachment> ra) {
		int result=1;
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("insertReviewAttachment");
		
		try {
			for(ReviewAttachment ralist:ra) {
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, ralist.getFilePath());
				pstmt.setString(2, ralist.getFileName());
				pstmt.setInt(3, ralist.getFileLevel());
				result*=pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
}
