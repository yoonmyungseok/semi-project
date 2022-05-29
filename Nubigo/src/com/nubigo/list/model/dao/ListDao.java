package com.nubigo.list.model.dao;

import static com.nubigo.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.nubigo.list.model.vo.List;
import com.nubigo.list.model.vo.ListPlaces;
import com.nubigo.place.model.vo.Place;

public class ListDao {
	
	private Properties prop = new Properties();
	
	public ListDao() {
		
		try {
			prop.loadFromXML(new FileInputStream(ListDao.class.getResource("/sql/list/list-mapper.xml").getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<List> selectMyList(Connection conn) {
		
		ArrayList<List> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMyList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new List(rset.getInt("LIST_NO"),
						          rset.getString("LIST_TITLE"),
						          rset.getDate("START_DATE"),
						          rset.getDate("END_DATE"),
						          rset.getInt("MEMBER_NO")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public ArrayList<Place> findTravel(Connection conn, String keyword) {
		
		ArrayList<Place> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("findTravel");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, keyword);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Place(rset.getString("PLACE_NAME"),
								   rset.getString("PLACE_CONTENT"),
								   rset.getString("THUMBNAIL_PATH"),
								   rset.getString("THUMBNAIL_NAME")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public ArrayList<ListPlaces> selectMyPlace(Connection conn, int listNo) {
		
		ArrayList<ListPlaces> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMyPlace");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, listNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new ListPlaces(rset.getString("LIST_TITLE"),
								        rset.getString("PLACE_NAME"),
								        rset.getString("START_TIME"),
								        rset.getString("START_DATE"),
								        rset.getString("END_DATE")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public ArrayList<List> selectOneList(Connection conn, int listNo) {
		
		ArrayList<List> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectOneList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, listNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new List(rset.getInt("LIST_NO"),
						          rset.getString("LIST_TITLE"),
						          rset.getDate("START_DATE"),
						          rset.getDate("END_DATE"),
						          rset.getInt("MEMBER_NO")));
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
