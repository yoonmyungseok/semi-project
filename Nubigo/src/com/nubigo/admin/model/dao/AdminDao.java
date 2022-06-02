package com.nubigo.admin.model.dao;

import static com.nubigo.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import com.nubigo.member.model.vo.Member;
import com.nubigo.review.model.dao.ReviewDao;

public class AdminDao {
	private Properties prop = new Properties();
	
	public AdminDao() {

		try {
			prop.loadFromXML(new FileInputStream(ReviewDao.class.getResource("/sql/admin/admin-mapper.xml").getPath()));
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public ArrayList<Member> selectAll(Connection conn) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Member> list = new ArrayList<Member>();
		
		String sql = prop.getProperty("selectAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Member(rset.getInt("MEMBER_NO")
						          , rset.getString("MEMBER_ID")
						          , rset.getString("MEMBER_PWD")
						          , rset.getString("MEMBER_NAME")
						          , rset.getString("GENDER")
						          , rset.getString("BIRTHDATE")
						          , rset.getString("EMAIL")
						          , rset.getString("PHONE")
						          , rset.getString("WITHDRAW_STATUS")
						          , rset.getString("AUTHORITY_WRITE")
						          , rset.getDate("ENROLLDATE")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
}	
