package com.nubigo.member.model.vo;

import java.sql.Date;

public class Member {

	/*
		MEMBER_NO NUMBER PRIMARY KEY,
	    MEMBER_ID VARCHAR2(15) NOT NULL UNIQUE,
	    MEMBER_PWD VARCHAR2(20) NOT NULL,
	    MEMBER_NAME VARCHAR2(15),
	    GENDER CHAR(1),
	    BIRTHDATE DATE,
	    EMAIL VARCHAR2(40) NOT NULL UNIQUE,
	    PHONE VARCHAR2(20) UNIQUE,
	    WITHDRAW_STATUS CHAR(1) DEFAULT 'N' CHECK (WITHDRAW_STATUS IN ('Y', 'N')),
	    AUTHORITY_WRITE CHAR(1) DEFAULT 'Y' CHECK (AUTHORITY_WRITE IN ('Y', 'N')),
	    ENROLLDATE DATE DEFAULT SYSDATE NOT NULL
	 */
	
	private int memberNo;
	private String memberId;
	private String memberPwd;
	private String memberName;
	private String gender;
	private Date birthdate;
	private String email;
	private String phone;
	private String withdrawStatus;
	private String authorityWrite;
	private Date enrollDate;
	public Member() {
		super();
	}
	public Member(int memberNo, String memberId, String memberPwd, String memberName, String gender, Date birthdate,
			String email, String phone, String withdrawStatus, String authorityWrite, Date enrollDate) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.gender = gender;
		this.birthdate = birthdate;
		this.email = email;
		this.phone = phone;
		this.withdrawStatus = withdrawStatus;
		this.authorityWrite = authorityWrite;
		this.enrollDate = enrollDate;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPwd() {
		return memberPwd;
	}
	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getWithdrawStatus() {
		return withdrawStatus;
	}
	public void setWithdrawStatus(String withdrawStatus) {
		this.withdrawStatus = withdrawStatus;
	}
	public String getAuthorityWrite() {
		return authorityWrite;
	}
	public void setAuthorityWrite(String authorityWrite) {
		this.authorityWrite = authorityWrite;
	}
	public Date getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPwd=" + memberPwd + ", memberName="
				+ memberName + ", gender=" + gender + ", birthdate=" + birthdate + ", email=" + email + ", phone="
				+ phone + ", withdrawStatus=" + withdrawStatus + ", authorityWrite=" + authorityWrite + ", enrollDate="
				+ enrollDate + "]";
	}
	
}
