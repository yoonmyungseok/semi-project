package com.nubigo.member.model.vo;

import java.sql.Date;

public class Reply {
	private int replyNo;//COMMENT_NO
	private String replyContent;//COMMENT_CONTENT
	private Date replyDate;//COMMENT_DATE
	private String status;//STATUS
	private String memberId;//MEMBER_ID
	private int boardNo;//BOARD_NO
	private int memberNo;
	
	public Reply(int replyNo, String replyContent, Date replyDate, String status, String memberId, int boardNo, int memberNo) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.replyDate = replyDate;
		this.status = status;
		this.memberId = memberId;
		this.boardNo = boardNo;
		this.memberNo=memberNo;
	}
	
	public Reply(int replyNo, String replyContent, Date replyDate, String memberId) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.replyDate = replyDate;
		this.memberId = memberId;
	}

	public Reply() {
		super();
	}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	

	public int getMemberNo() {
		return memberNo;
	}


	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	@Override
	public String toString() {
		return "Reply [replyNo=" + replyNo + ", replyContent=" + replyContent + ", replyDate=" + replyDate + ", status="
				+ status + ", memberId=" + memberId + ", boardNo=" + boardNo + ", memberNo=" + memberNo + "]";
	}


	
}

