package com.nubigo.board.model.vo;

public class Board {
	private int boardNo; //BOARD_NO NUMBER PRIMARY KEY,
    private String boardTitle;//BOARD_TITLE VARCHAR2(90) NOT NULL,
    private String boardContent;//BOARD_CONTENT VARCHAR2(3000) NOT NULL,
    private String boardDate;//BOARD_DATE DATE DEFAULT SYSDATE NOT NULL,
    private String deleteStatus;//DELETE_STATUS VARCHAR2(1) DEFAULT 'N' CHECK (DELETE_STATUS IN ('Y', 'N')),
    private String attachmentPath;//ATTACHMENT_PATH VARCHAR2(1000),
    private String attachmentName;//ATTACHMENT_NAME VARCHAR2(200),
    private String memberId;
    private int count;
    private int replyCount;
    private int rNum;
    
	public Board() {
		super();
	}

	public Board(int boardNo, String boardTitle, String boardContent, String boardDate, String deleteStatus,
			String attachmentPath, String attachmentName, String memberId,int count, int replyCount, int rNum) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardDate = boardDate;
		this.deleteStatus = deleteStatus;
		this.attachmentPath = attachmentPath;
		this.attachmentName = attachmentName;
		this.memberId=memberId;
		this.count=count;
		this.replyCount=replyCount;
		this.rNum=rNum;
	}
	
	//게시글 전체 조회용
	public Board(int boardNo, String boardTitle, String boardDate, String memberId, int count, int replyCount, int rNum) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardDate = boardDate;
		this.memberId = memberId;
		this.count=count;
		this.replyCount=replyCount;
		this.rNum=rNum;
	}

	//게시글 상세 조회용
	public Board(int boardNo, String boardTitle, String boardContent, String boardDate, String memberId, String attachmentPath, String attachmentName, int count, int replyCount) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardDate = boardDate;
		this.memberId = memberId;
		this.attachmentPath=attachmentPath;
		this.attachmentName=attachmentName;
		this.count=count;
		this.replyCount=replyCount;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}

	public String getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public String getAttachmentPath() {
		return attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public int getrNum() {
		return rNum;
	}

	public void setrNum(int rNum) {
		this.rNum = rNum;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardDate=" + boardDate + ", deleteStatus=" + deleteStatus + ", attachmentPath=" + attachmentPath
				+ ", attachmentName=" + attachmentName + ", memberId=" + memberId + ", count=" + count + ", replyCount="
				+ replyCount + ", rNum=" + rNum + "]\n";
	}

}
