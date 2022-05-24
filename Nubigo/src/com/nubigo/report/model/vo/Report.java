package com.nubigo.report.model.vo;

import java.sql.Date;

public class Report {
	private int reportNo;//REPORT_NO
	private Date reportDate;//REPORT_DATE
	private String responseStatus;//RESPONSE_STATUS
	private int boardNo;//BOARD_NO
	private int replyNo;//COMMENT_NO
	private int reviewNo;//REVIEW_NO
	private String reportType;
	private String reportContent;
	
	public Report() {
		super();
	}
	public Report(int reportNo, Date reportDate, String responseStatus, int boardNo, int replyNo, int reviewNo, String reportType, String reportContent) {
		super();
		this.reportNo = reportNo;
		this.reportDate = reportDate;
		this.responseStatus = responseStatus;
		this.boardNo = boardNo;
		this.replyNo = replyNo;
		this.reviewNo = reviewNo;
		this.reportType=reportType;
		this.reportContent=reportContent;
	}
	public int getReportNo() {
		return reportNo;
	}
	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	public String getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	
	public String getReportContent() {
		return reportContent;
	}
	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}
	@Override
	public String toString() {
		return "Report [reportNo=" + reportNo + ", reportDate=" + reportDate + ", responseStatus=" + responseStatus
				+ ", boardNo=" + boardNo + ", replyNo=" + replyNo + ", reviewNo=" + reviewNo + ", reportType="
				+ reportType + ", reportContent=" + reportContent + "]";
	}

}
