package com.nubigo.faq.model.dao;

import static com.nubigo.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.nubigo.board.model.dao.BoardDao;
import com.nubigo.faq.model.vo.Faq;

public class FaqDao {
	
	private Properties prop=new Properties();
			
	public FaqDao() {
		String fileName=BoardDao.class.getResource("/sql/faq/faq-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Faq> getFaqList(Connection conn) {
		ArrayList<Faq> list = new ArrayList<Faq>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Faq(rset.getString("FAQ_TITLE"), rset.getString("FAQ_CONTENT")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return list;
		
		
	}
}
