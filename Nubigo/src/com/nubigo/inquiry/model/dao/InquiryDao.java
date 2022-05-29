package com.nubigo.inquiry.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import static com.nubigo.common.JDBCTemplate.*;


import com.nubigo.member.model.dao.MemberDao;

public class InquiryDao {
	Properties prop = new Properties();
	
	public InquiryDao() {

		try {
			prop.loadFromXML(
					new FileInputStream(MemberDao.class.getResource("/sql/inquiry/inquiry-mapper.xml").getPath()));
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public int insertInquiry(Connection conn, int memberNo, String qTitle, String qContent) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertInquiry");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, qTitle);
			pstmt.setString(2, qContent);
			pstmt.setInt(3, memberNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
		
		
	}
}
