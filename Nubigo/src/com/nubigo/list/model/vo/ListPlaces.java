package com.nubigo.list.model.vo;

public class ListPlaces {
	
	// 필드부
	private String listNo;	// LIST_NO NUMBER NOT NULL,
    private String placeNo;	// PLACE_NO NUMBER NOT NULL,
    private String startTime;	// START_TIME DATE
    private String startDate;		// START_DATE DATE NOT NULL,
	private String endDate;		// END_DATE DATE NOT NULL,
	
	// 생성자부
	public ListPlaces() {
		super();
	}

	public ListPlaces(String listNo, String placeNo, String startTime, String startDate, String endDate) {
		super();
		this.listNo = listNo;
		this.placeNo = placeNo;
		this.startTime = startTime;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	// 메소드부
	public String getListNo() {
		return listNo;
	}

	public void setListNo(String listNo) {
		this.listNo = listNo;
	}

	public String getPlaceNo() {
		return placeNo;
	}

	public void setPlaceNo(String placeNo) {
		this.placeNo = placeNo;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
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

	@Override
	public String toString() {
		return "ListPlaces [listNo=" + listNo + ", placeNo=" + placeNo + ", startTime=" + startTime + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}
}