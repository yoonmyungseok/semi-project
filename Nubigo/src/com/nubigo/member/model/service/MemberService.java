package com.nubigo.member.model.service;

import static com.nubigo.common.JDBCTemplate.close;
import static com.nubigo.common.JDBCTemplate.getConnection;

import java.sql.Connection;

import com.nubigo.member.model.dao.MemberDao;
import com.nubigo.member.model.vo.Member;

public class MemberService {
	
	public Member loginMember(String memberId, String memberPwd) {
		
		Connection conn = getConnection();
		
		Member m = new MemberDao().loginMember(conn, memberId, memberPwd);
		
		close(conn);
		
		return m;
		
	}
	
	
	
}
