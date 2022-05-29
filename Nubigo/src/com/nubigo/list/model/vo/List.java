package com.nubigo.list.model.vo;

import java.util.Date;

public class List {
	
	// 필드부
	private int listNo;			// LIST_NO NUMBER PRIMARY KEY,
	private String listTitle;	// LIST_TITLE VARCHAR2(30) NOT NULL,
	private Date startDate;		// START_DATE DATE NOT NULL,
	private Date endDate;		// END_DATE DATE NOT NULL,
	private int memberNo;		// MEMBER_NO NUMBER NOT NULL,
	
	// 생성자부
	public List() {
		super();
	}

	public List(int listNo, String listTitle, Date startDate, Date endDate, int memberNo) {
		super();
		this.listNo = listNo;
		this.listTitle = listTitle;
		this.startDate = startDate;
		this.endDate = endDate;
		this.memberNo = memberNo;
	}

	// 메소드부
	public int getListNo() {
		return listNo;
	}

	public void setListNo(int listNo) {
		this.listNo = listNo;
	}

	public String getListTitle() {
		return listTitle;
	}

	public void setListTitle(String listTitle) {
		this.listTitle = listTitle;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	@Override
	public String toString() {
		return "List [listNo=" + listNo + ", listTitle=" + listTitle + ", startDate=" + startDate + ", endDate="
				+ endDate + ", memberNo=" + memberNo + "]";
	}
}
