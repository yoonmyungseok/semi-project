package com.nubigo.review.model.vo;

import java.sql.Date;

public class Review {
	/*
		REVIEW_NO NUMBER PRIMARY KEY,
	    SCORE NUMBER NOT NULL,
	    REVIEW_TITLE VARCHAR2(90) NOT NULL,
	    REVIEW_CONTENT VARCHAR2(4000) NOT NULL,
	    REVIEW_DATE DATE DEFAULT SYSDATE NOT NULL,
	    DELETE_STATUS VARCHAR2(1) DEFAULT 'N' CHECK (DELETE_STATUS IN ('Y', 'N')),
	    AGE VARCHAR2(1) CHECK (AGE IN ('1', '2', '3')),
	    SEASON VARCHAR2(1) CHECK (SEASON IN ('1', '2', '3', '4')),
	    GROUP_WITH VARCHAR2(1) CHECK (GROUP_WITH IN ('1', '2', '3', '4')),
	    PLACE_NO NUMBER NOT NULL,
	 */
	
	private int reviewNo;
	private int score;
	private String reviewTitle;
	private String reviewContent;
	private Date reviewDate;
	private String deleteStatus;
	private String age;
	private String season;
	private String groupWith;
	private int placeNo;
	private String placeName;
	private String memberId;
	private int likes;
	public Review() {
		super();
	}
	
	public Review(int reviewNo, int score, String reviewTitle, String reviewContent, Date reviewDate, int placeNo, String placeName,
			String memberId, int likes) {
		super();
		this.reviewNo = reviewNo;
		this.score = score;
		this.reviewTitle = reviewTitle;
		this.reviewContent = reviewContent;
		this.reviewDate = reviewDate;
		this.placeNo = placeNo;
		this.placeName = placeName;
		this.memberId = memberId;
		this.likes = likes;
	}

	public Review(int reviewNo, int score, String reviewTitle, String reviewContent, Date reviewDate,
			String deleteStatus, String age, String season, String groupWith, int placeNo, String memberId, int likes) {
		super();
		this.reviewNo = reviewNo;
		this.score = score;
		this.reviewTitle = reviewTitle;
		this.reviewContent = reviewContent;
		this.reviewDate = reviewDate;
		this.deleteStatus = deleteStatus;
		this.age = age;
		this.season = season;
		this.groupWith = groupWith;
		this.placeNo = placeNo;
		this.memberId = memberId;
		this.likes = likes;
	}
	
	public Review(int reviewNo, int score, String reviewTitle, String reviewContent, Date reviewDate,
			String deleteStatus, String age, String season, String groupWith, String placeName, String memberId, int likes) {
		super();
		this.reviewNo = reviewNo;
		this.score = score;
		this.reviewTitle = reviewTitle;
		this.reviewContent = reviewContent;
		this.reviewDate = reviewDate;
		this.deleteStatus = deleteStatus;
		this.age = age;
		this.season = season;
		this.groupWith = groupWith;
		this.placeName = placeName;
		this.memberId = memberId;
		this.likes = likes;
	}
	
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getReviewTitle() {
		return reviewTitle;
	}
	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public Date getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
	public String getDeleteStatus() {
		return deleteStatus;
	}
	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public String getGroupWith() {
		return groupWith;
	}
	public void setGroupWith(String groupWith) {
		this.groupWith = groupWith;
	}
	public int getPlaceNo() {
		return placeNo;
	}
	public void setPlaceNo(int placeNo) {
		this.placeNo = placeNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	@Override
	public String toString() {
		return "Review [reviewNo=" + reviewNo + ", score=" + score + ", reviewTitle=" + reviewTitle + ", reviewContent="
				+ reviewContent + ", reviewDate=" + reviewDate + ", deleteStatus=" + deleteStatus + ", age=" + age
				+ ", season=" + season + ", groupWith=" + groupWith + ", placeNo=" + placeNo + ", memberId=" + memberId + ", likes=" + likes + "]";
	}
	
	
}
