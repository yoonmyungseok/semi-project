package com.nubigo.member.model.dao;

import static com.nubigo.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import com.nubigo.member.model.vo.Member;
import com.nubigo.review.model.dao.ReviewDao;

public class MemberDao {
private Properties prop = new Properties();
	
	public MemberDao() {

		try {
			prop.loadFromXML(new FileInputStream(MemberDao.class.getResource("/sql/member/member-mapper.xml").getPath()));
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public Member loginMember(Connection conn, String memberId, String memberPwd) {
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("loginMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPwd);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Member(rset.getInt("MEMBER_NO")
						     , rset.getString("MEMBER_ID")
						     , rset.getString("MEMBER_PWD")
						     , rset.getString("MEMBER_NAME")
						     , rset.getString("GENDER")
						     , rset.getDate("BIRTHDATE")
						     , rset.getString("EMAIL")
						     , rset.getString("PHONE")
						     , rset.getString("WITHDRAW_STATUS")
						     , rset.getString("AUTHORITY_WRITE")
						     , rset.getDate("ENROLLDATE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return m;
		
	}
}
