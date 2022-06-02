package com.nubigo.list.model.vo;

public class Lists {
	

    private int listNo;// LIST_NO NUMBER PRIMARY KEY,
    private String listTitle;// LIST_TITLE VARCHAR2(30) NOT NULL,
    private String listContent;
    private String startDate;// START_DATE DATE NOT NULL,
    private String endDate;// END_DATE DATE NOT NULL,
    private int memberNo;// MEMBER_NO NUMBER NOT NULL,
	
    public Lists() {
		super();
	}
    

	public Lists(int listNo, String listTitle, String startDate, String endDate, int memberNo) {
		super();
		this.listNo = listNo;
		this.listTitle = listTitle;
		this.startDate = startDate;
		this.endDate = endDate;
		this.memberNo = memberNo;
	}


	public Lists(int listNo, String listTitle, String listContent, String startDate, String endDate, int memberNo) {
		super();
		this.listNo = listNo;
		this.listTitle = listTitle;
		this.listContent = listContent;
		this.startDate = startDate;
		this.endDate = endDate;
		this.memberNo = memberNo;
	}
	
	// 내 리스트 조회용 생성자
	public Lists(String listTitle, String listContent, String startDate, String endDate, int memberNo) {
		super();
		this.listTitle = listTitle;
		this.listContent = listContent;
		this.startDate = startDate;
		this.endDate = endDate;
		this.memberNo = memberNo;
	}
	
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
	
	public String getListContent() {
		return listContent;
	}
	
	public void setListContent(String listContent) {
		this.listContent = listContent;
	}
	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
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
		return "Lists [listNo=" + listNo + ", listTitle=" + listTitle + ", startDate=" + startDate + ", endDate="
				+ endDate + ", memberNo=" + memberNo + "]";
	} 
}
