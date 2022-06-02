package com.nubigo.inquiry.model.service;

import java.sql.Connection;

import com.nubigo.inquiry.model.dao.InquiryDao;
import static com.nubigo.common.JDBCTemplate.*;

public class InquiryService {
	public int insertInquiry(int memberNo, String qTitle, String qContent) {
		Connection conn = getConnection();
		
		int result = new InquiryDao().insertInquiry(conn, memberNo, qTitle, qContent);
		
		if(result > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		
		return result;
	}
}
