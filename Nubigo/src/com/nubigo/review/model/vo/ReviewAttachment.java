package com.nubigo.review.model.vo;

public class ReviewAttachment {
	private String filePath;
	private int fileNo;
	private String fileName;
	private int reviewNo;
	private int fileLevel;
	
	public ReviewAttachment() {
		super();
	}
	
	
	public ReviewAttachment(String filePath, int fileNo, String fileName, int reviewNo) {
		super();
		this.filePath = filePath;
		this.fileNo = fileNo;
		this.fileName = fileName;
		this.reviewNo = reviewNo;
	}


	public ReviewAttachment(String filePath, int fileNo, String fileName, int reviewNo, int fileLevel) {
		super();
		this.filePath = filePath;
		this.fileNo = fileNo;
		this.fileName = fileName;
		this.reviewNo = reviewNo;
		this.fileLevel = fileLevel;
	}


	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}


	public int getFileLevel() {
		return fileLevel;
	}


	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}


	@Override
	public String toString() {
		return "ReviewAttachment [filePath=" + filePath + ", fileNo=" + fileNo + ", fileName=" + fileName
				+ ", reviewNo=" + reviewNo + ", fileLevel=" + fileLevel + "]";
	}
	
	
}
