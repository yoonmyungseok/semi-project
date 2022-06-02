package com.nubigo.region.model.dao;

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

import com.nubigo.region.model.vo.Region;
import com.nubigo.review.model.dao.ReviewDao;
import static com.nubigo.common.JDBCTemplate.*;

public class RegionDao {
private Properties prop = new Properties();
	
	public RegionDao() {

		try {
			prop.loadFromXML(new FileInputStream(ReviewDao.class.getResource("/sql/region/region-mapper.xml").getPath()));
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public ArrayList<Region> selectAll(Connection conn) {
		ArrayList<Region> list = new ArrayList<Region>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new Region(rset.getInt("LOCAL_NO")
						          , rset.getString("LOCAL_NAME")
						          , rset.getString("REGION")
						          , rset.getString("THUMBNAIL_PATH")
						          , rset.getString("THUMBNAIL_NAME")));
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
