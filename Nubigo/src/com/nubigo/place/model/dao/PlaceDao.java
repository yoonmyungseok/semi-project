package com.nubigo.place.model.dao;

import static com.nubigo.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.nubigo.common.model.vo.PageInfo;
import com.nubigo.place.model.vo.Place;

public class PlaceDao {
	
	private Properties prop = new Properties();
	
	public PlaceDao() {
		try {
			prop.loadFromXML(new FileInputStream(PlaceDao.class.getResource("/sql/place/place-mapper.xml").getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int selectListCount(Connection conn) {
		
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	
	public ArrayList<Place> selectList(Connection conn, PageInfo pi) {
		
		ArrayList<Place> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Place(rset.getInt("PLACE_NO"),
								   rset.getString("PLACE_CATEGORY"),
								   rset.getString("PLACE_NAME"),
								   rset.getInt("LATITUDE"),
								   rset.getInt("LONGITUDE"),
								   rset.getString("DELETE_STATUS"),
						           rset.getString("LOCAL_NAME")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		System.out.println(list);
		return list;
	}
	
	public int insertPlace(Connection conn, Place p) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertPlace");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, p.getPlaceCategory());
			pstmt.setString(2, p.getPlaceName());
			pstmt.setInt(3, p.getLatitude());
			pstmt.setInt(4, p.getLongitude());
			pstmt.setString(5, p.getPlaceContent());
			pstmt.setString(6, p.getThumbnailPath());
			pstmt.setString(7, p.getThumbnailName());
			pstmt.setString(8, p.getLocalNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;
	}
	
	public Place selectPlace(Connection conn, int placeNo) {
		
		Place p = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectPlace");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, placeNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				p = new Place();
				
				p.setPlaceNo(rset.getInt("PLACE_NO"));
				p.setLocalNo(rset.getString("LOCAL_NAME"));
				p.setPlaceName(rset.getString("PLACE_NAME"));
				p.setPlaceCategory(rset.getString("PLACE_CATEGORY"));
				p.setLatitude(rset.getInt("LATITUDE"));
				p.setLongitude(rset.getInt("LONGITUDE"));
				p.setPlaceContent(rset.getString("PLACE_CONTENT"));
				p.setThumbnailPath(rset.getString("THUMBNAIL_PATH"));
				p.setThumbnailName(rset.getString("THUMBNAIL_NAME"));
				p.setDeleteStatus(rset.getString("DELETE_STATUS"));
			}
			
			System.out.println(p);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return p;
	}
	
	public int updatePlace(Connection conn, int placeNo, Place p) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updatePlace");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, p.getPlaceCategory());
			pstmt.setString(2, p.getPlaceName());
			pstmt.setInt(3, p.getLatitude());
			pstmt.setInt(4, p.getLongitude());
			pstmt.setString(5, p.getPlaceContent());
			pstmt.setString(6, p.getDeleteStatus());
			pstmt.setString(7, p.getThumbnailPath());
			pstmt.setString(8, p.getThumbnailName());
			pstmt.setInt(9, Integer.parseInt(p.getLocalNo()));
			
			pstmt.setInt(10, placeNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;
	}
	
	public int deletePlace(Connection conn, int placeNo) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deletePlace");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, placeNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;
	}
}
