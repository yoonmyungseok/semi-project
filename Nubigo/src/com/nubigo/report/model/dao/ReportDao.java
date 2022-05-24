package com.nubigo.report.model.dao;
import static com.nubigo.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.nubigo.report.model.vo.Report;

public class ReportDao {
	private Properties prop=new Properties();
	public ReportDao() {
		//나중에 배포될 classes 폴더 기준으로 xml 파일의 경로 잡기
		String fileName=ReportDao.class.getResource("/sql/report/report-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int boardReport(Connection conn, Report r) {
		int result=0;
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("boardReport");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, r.getBoardNo());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	public int boardReportContent(Connection conn, Report r) {
		int result=0;
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("boardReportContent");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, r.getReportType());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int boardReplyReport(Connection conn, Report r) {
		int result=0;
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("boardReplyReport");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, r.getBoardNo());
			pstmt.setInt(2, r.getReplyNo());
			pstmt.setString(3, r.getReportContent());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
}
