package com.nubigo.list.model.service;

import static com.nubigo.common.JDBCTemplate.close;
import static com.nubigo.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.nubigo.list.model.dao.ListDao;
import com.nubigo.list.model.vo.List;
import com.nubigo.list.model.vo.ListPlaces;
import com.nubigo.place.model.vo.Place;

public class ListService {
	
	public ArrayList<List> selectMyList() {
		
		Connection conn = getConnection();
		
		ArrayList<List> list = new ListDao().selectMyList(conn);
		
		close(conn);
		
		return list;
	}
	
	public ArrayList<Place> findTravel(String keyword) {
		
		Connection conn = getConnection();
		
		ArrayList<Place> list = new ListDao().findTravel(conn, keyword);
		
		close(conn);
	
		return list;
	}
	
	public ArrayList<ListPlaces> selectMyPlace(int listNo) {
		
		Connection conn = getConnection();
		
		ArrayList<ListPlaces> list = new ListDao().selectMyPlace(conn, listNo);
		
		close(conn);
		
		return list;
	}
	
	public ArrayList<List> selectOneList(int listNo) {
		
		Connection conn = getConnection();
		
		ArrayList<List> list = new ListDao().selectOneList(conn, listNo);
		
		close(conn);
		
		return list;
	}

}
