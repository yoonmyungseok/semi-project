package com.nubigo.place.model.service;

import static com.nubigo.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.nubigo.common.model.vo.PageInfo;
import com.nubigo.place.model.dao.PlaceDao;
import com.nubigo.place.model.vo.Place;

public class PlaceService {
	
	public int selectListCount() {
		
		Connection conn = getConnection();
		
		int listCount = new PlaceDao().selectListCount(conn);
		
		close(conn);
		
		return listCount;
	}
	
	public ArrayList<Place> selectList(PageInfo pi) {
		
		Connection conn = getConnection();
		
		ArrayList<Place> list = new PlaceDao().selectList(conn, pi);
		
		close(conn);
		
		return list;
	}
	
	public int insertPlace(Place p) {
		
		Connection conn = getConnection();
		
		int result = new PlaceDao().insertPlace(conn, p);
		
		if(result > 0) {
			
			commit(conn);
		
		} else {
			
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	public Place selectPlace(int placeNo) {
		
		Connection conn = getConnection();
		
		Place p = new PlaceDao().selectPlace(conn, placeNo);
		
		close(conn);
		
		return p;
	}

	public int updatePlace(int placeNo, Place p) {
		
		Connection conn = getConnection();
		
		int result = new PlaceDao().updatePlace(conn, placeNo, p);
		
		if(result > 0) {
			
			commit(conn);
			
		} else {
			
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	public int deletePlace(int placeNo) {
		
		Connection conn = getConnection();
		
		int result = new PlaceDao().deletePlace(conn,placeNo);
		
		if(result > 0) {
			
			commit(conn);
		
		} else {
		
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
}
