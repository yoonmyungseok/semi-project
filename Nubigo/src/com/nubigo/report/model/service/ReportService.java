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
		int result1=new ReportDao().boardReport(conn, r);
		int result2=new ReportDao().boardReportContent(conn, r);
		if(result1>0&&result2>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result1*result2;
	}
}
