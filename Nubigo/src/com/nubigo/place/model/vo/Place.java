package com.nubigo.place.model.vo;

public class Place {
	
	// 필드부
	private int placeNo;			// PLACE_NO NUMBER PRIMARY KEY,
	private String placeCategory;	// PLACE_CATEGORY VARCHAR2(1) NOT NULL CHECK (PLACE_CATEGORY IN('1', '2')),
    private String placeName;		// PLACE_NAME VARCHAR2(60) NOT NULL UNIQUE,
    private int latitude;			// LATITUDE NUMBER NOT NULL,
    private int longitude;			// LONGITUDE NUMBER NOT NULL,
    private String placeContent;	// PLACE_CONTENT VARCHAR2(3000) NOT NULL,
    private String deleteStatus; 	// DELETE_STATUS VARCHAR2(1) DEFAULT 'N' CHECK (DELETE_STATUS IN ('Y', 'N')),
    private String thumbnailPath;	// THUMBNAIL_PATH VARCHAR2(1000),
    private String thumbnailName;	// THUMBNAIL_NAME VARCHAR2(200),
    private String localNo;			// LOCAL_NO NUMBER NOT NULL
	
    
    // 생성자부
    public Place() {
		super();
	}

	public Place(int placeNo, String placeCategory, String placeName, int latitude, int longitude, String placeContent,
			String deleteStatus, String thumbnailPath, String thumbnailName, String localNo) {
		super();
		this.placeNo = placeNo;
		this.placeCategory = placeCategory;
		this.placeName = placeName;
		this.latitude = latitude;
		this.longitude = longitude;
		this.placeContent = placeContent;
		this.deleteStatus = deleteStatus;
		this.thumbnailPath = thumbnailPath;
		this.thumbnailName = thumbnailName;
		this.localNo = localNo;
	}

	// 관리자 페이지 > 여행지 관리 리스트
	public Place(int placeNo, String placeCategory, String placeName, int latitude, int longitude, String deleteStatus,
			String localNo) {
		super();
		this.placeNo = placeNo;
		this.placeCategory = placeCategory;
		this.placeName = placeName;
		this.latitude = latitude;
		this.longitude = longitude;
		this.deleteStatus = deleteStatus;
		this.localNo = localNo;
	}

	// 여행지 리스트 페이지 > 여행지 찾아보기
	public Place(String placeName, String placeContent, String thumbnailPath, String thumbnailName) {
		super();
		this.placeName = placeName;
		this.placeContent = placeContent;
		this.thumbnailPath = thumbnailPath;
		this.thumbnailName = thumbnailName;
	}

	// 메소드부
	public int getPlaceNo() {
		return placeNo;
	}

	public void setPlaceNo(int placeNo) {
		this.placeNo = placeNo;
	}

	public String getPlaceCategory() {
		return placeCategory;
	}

	public void setPlaceCategory(String placeCategory) {
		this.placeCategory = placeCategory;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public int getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	public int getLongitude() {
		return longitude;
	}

	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}

	public String getPlaceContent() {
		return placeContent;
	}

	public void setPlaceContent(String placeContent) {
		this.placeContent = placeContent;
	}

	public String getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public String getThumbnailPath() {
		return thumbnailPath;
	}

	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}

	public String getThumbnailName() {
		return thumbnailName;
	}

	public void setThumbnailName(String thumbnailName) {
		this.thumbnailName = thumbnailName;
	}

	public String getLocalNo() {
		return localNo;
	}

	public void setLocalNo(String localNo) {
		this.localNo = localNo;
	}

	@Override
	public String toString() {
		return "Place [placeNo=" + placeNo + ", placeCategory=" + placeCategory + ", placeName=" + placeName
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", placeContent=" + placeContent
				+ ", deleteStatus=" + deleteStatus + ", thumbnailPath=" + thumbnailPath + ", thumbnailName="
				+ thumbnailName + ", localNo=" + localNo + "]";
	}
}
