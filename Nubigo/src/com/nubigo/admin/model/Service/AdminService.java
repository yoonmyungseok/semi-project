package com.nubigo.admin.model.Service;

import static com.nubigo.common.JDBCTemplate.close;
import static com.nubigo.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.nubigo.admin.model.dao.AdminDao;
import com.nubigo.member.model.vo.Member;

public class AdminService {
	public ArrayList<Member> selectAll() {
		
		Connection conn = getConnection();
		
		ArrayList<Member> list = new AdminDao().selectAll(conn);
		
		close(conn);
		
		return list;
				
		
	}
}
