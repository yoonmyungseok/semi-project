package com.nubigo.region.model.vo;

public class Region {
	private int localNo;
	private String localName;
	private String region;
	private String thumbnailPath;
	private String thumbnailName;
	public Region() {
		super();
	}
	public Region(int localNo, String localName, String region, String thumbnailPath, String thumbnailName) {
		super();
		this.localNo = localNo;
		this.localName = localName;
		this.region = region;
		this.thumbnailPath = thumbnailPath;
		this.thumbnailName = thumbnailName;
	}
	public int getLocalNo() {
		return localNo;
	}
	public void setLocalNo(int localNo) {
		this.localNo = localNo;
	}
	public String getLocalName() {
		return localName;
	}
	public void setLocalName(String localName) {
		this.localName = localName;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
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
	@Override
	public String toString() {
		return "Region [localNo=" + localNo + ", localName=" + localName + ", region=" + region + ", thumbnailPath="
				+ thumbnailPath + ", thumbnailName=" + thumbnailName + "]";
	}
	
	
}
