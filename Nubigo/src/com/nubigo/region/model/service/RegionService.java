package com.nubigo.region.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.nubigo.region.model.dao.RegionDao;
import com.nubigo.region.model.vo.Region;
import static com.nubigo.common.JDBCTemplate.*;

public class RegionService {
	public ArrayList<Region> selectAll() {
		Connection conn = getConnection();
		
		ArrayList<Region> list = new RegionDao().selectAll(conn);
		
		close(conn);
		
		return list;
	}
}
