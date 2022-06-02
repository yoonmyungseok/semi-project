package com.nubigo.report.model.service;
import static com.nubigo.common.JDBCTemplate.close;
import static com.nubigo.common.JDBCTemplate.commit;
import static com.nubigo.common.JDBCTemplate.getConnection;
import static com.nubigo.common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.nubigo.report.model.dao.ReportDao;
import com.nubigo.report.model.vo.Report;

public class ReportService {
	public int boardReport(Report r) {
		Connection conn=getConnection();
		int result=new ReportDao().boardReport(conn, r);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int boardReplyReport(Report r) {
		Connection conn=getConnection();
		int result=new ReportDao().boardReplyReport(conn, r);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public int reviewReport(Report r) {
		Connection conn=getConnection();
		int result=new ReportDao().reviewReport(conn, r);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
}
