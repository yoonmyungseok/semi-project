package com.nubigo.board.model.dao;

import static com.nubigo.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.nubigo.board.model.vo.Board;
import com.nubigo.common.model.vo.PageInfo;
import com.nubigo.member.model.vo.Reply;

public class BoardDao {
	//전역변수로 Properties 타입의 객체 하나 만들어두기
	private Properties prop=new Properties();
		
	//공통적인 코드를 기본생성자에 빼기
	public BoardDao() {
		//나중에 배포될 classes 폴더 기준으로 xml 파일의 경로 잡기
		String fileName=BoardDao.class.getResource("/sql/board/board-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int selectListCount(Connection conn) {
		int count=0;
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String sql=prop.getProperty("selectListCount");
		
		try {
			pstmt=conn.prepareStatement(sql);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				count=rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return count;
	}
	public int selectListCount(Connection conn,String search,String keyword) {
		int count=0;
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String sql="";
		switch(keyword) {
			case "제목내용" : sql=prop.getProperty("listCountAll"); break;
			case "제목" : sql=prop.getProperty("listCountTitle"); break;
			case "내용" : sql=prop.getProperty("listCountContent"); break;
			case "작성자" : sql=prop.getProperty("listCountId"); break;
		}
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			rset=pstmt.executeQuery();
			if(rset.next()) {
				count=rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return count;
	}
	
	public int increaseCount(Connection conn, int boardNo) {
		int result=0;
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("increaseCount");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public Board selectBoard(Connection conn, int boardNo) {
		Board b=null;
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String sql=prop.getProperty("selectBoard");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				b=new Board(
						rset.getInt("BOARD_NO"),
						rset.getString("BOARD_TITLE"),
						rset.getString("BOARD_CONTENT"),
						rset.getString(String.valueOf("BOARD_DATE")),
						rset.getNString("MEMBER_ID"), 
						rset.getString("ATTACHMENT_PATH"),
						rset.getString("ATTACHMENT_NAME"),
						rset.getInt("COUNT"),
						rset.getInt("REPLY_COUNT")
						);
		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return b;
	}
	
	public int insertBoard(Connection conn, Board b) {
		int result=0;
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("insertBoard");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setString(3, b.getAttachmentPath());
			pstmt.setString(4, b.getAttachmentName());
			pstmt.setInt(5, Integer.parseInt(b.getMemberId()));
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int insertReply(Connection conn, Reply r) {
		int result=0;
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("insertReply");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, r.getReplyContent());
			pstmt.setInt(2, r.getBoardNo());
			pstmt.setInt(3, r.getMemberNo());
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	public ArrayList<Reply> selectReplyList(Connection conn, int boardNo){
		ArrayList<Reply> list=new ArrayList<>();
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String sql=prop.getProperty("selectReplyList");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Reply(
						rset.getInt("COMMENT_NO"),
						rset.getString("COMMENT_CONTENT"),
						rset.getString("COMMENT_DATE"),
						rset.getString("MEMBER_ID")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public int deleteBoard(Connection conn, int boardNo) {
		int result=0;
		PreparedStatement pstmt=null;
		
		String sql=prop.getProperty("deleteBoard");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateBoard(Connection conn, Board b) {
		int result=0;
		PreparedStatement pstmt=null;
		
		String sql=prop.getProperty("updateBoard");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setString(3, b.getAttachmentPath());
			pstmt.setString(4, b.getAttachmentName());
			pstmt.setInt(5, b.getBoardNo());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	public ArrayList<Board> selectBoardList(Connection conn,PageInfo pi){
		ArrayList<Board> list=new ArrayList<>();
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String sql=prop.getProperty("selectBoardList");
		
		try {
			pstmt=conn.prepareStatement(sql);
			int startRow=(pi.getCurrentPage()-1)*pi.getBoardLimit()+1;
			int endRow=startRow+pi.getBoardLimit()-1;
			//int endRow=pi.getListCount()-(pi.getCurrentPage()-1)*pi.getBoardLimit();
			//int startRow=(endRow>pi.getBoardLimit())? endRow-pi.getBoardLimit()+1: endRow;
					
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				list.add(new Board(
						rset.getInt("BOARD_NO"),
						rset.getString("BOARD_TITLE"),
						rset.getString(String.valueOf("BOARD_DATE")),
						rset.getString("MEMBER_ID"),
						rset.getInt("COUNT"),
						rset.getInt("REPLY_COUNT"),
						rset.getInt("RNUM")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	public ArrayList<Board> selectBoardListCount(Connection conn,PageInfo pi){
		ArrayList<Board> list=new ArrayList<>();
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String sql=prop.getProperty("selectBoardListCount");
		
		try {
			pstmt=conn.prepareStatement(sql);
			int startRow=(pi.getCurrentPage()-1)*pi.getBoardLimit()+1;
			int endRow=startRow+pi.getBoardLimit()-1;
			//int endRow=pi.getListCount()-(pi.getCurrentPage()-1)*pi.getBoardLimit();
			//int startRow=(endRow>pi.getBoardLimit())? endRow-pi.getBoardLimit()+1: endRow;

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				list.add(new Board(
						rset.getInt("BOARD_NO"),
						rset.getString("BOARD_TITLE"),
						rset.getString(String.valueOf("BOARD_DATE")),
						rset.getString("MEMBER_ID"),
						rset.getInt("COUNT"),
						rset.getInt("REPLY_COUNT"),
						rset.getInt("RNUM")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public ArrayList<Board> selectBoardListNew(Connection conn,PageInfo pi,String search, String keyword){
		ArrayList<Board> list=new ArrayList<>();
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String sql="";
		switch(keyword) {
			case "제목내용" : sql=prop.getProperty("boardSearchAllNew"); break;
			case "제목" : sql=prop.getProperty("boardSearchTitleNew"); break;
			case "내용" : sql=prop.getProperty("boardSearchContentNew"); break;
			case "작성자" : sql=prop.getProperty("boardSearchIdNew"); break;
			default: sql=prop.getProperty("boardSearchAllNew"); break;
		}
		
		try {
			pstmt=conn.prepareStatement(sql);
			int startRow=(pi.getCurrentPage()-1)*pi.getBoardLimit()+1;
			int endRow=startRow+pi.getBoardLimit()-1;
			//int endRow=pi.getListCount()-(pi.getCurrentPage()-1)*pi.getBoardLimit();
			//int startRow=(endRow>pi.getBoardLimit())? endRow-pi.getBoardLimit()+1: endRow;

			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				list.add(new Board(
						rset.getInt("BOARD_NO"),
						rset.getString("BOARD_TITLE"),
						rset.getString(String.valueOf("BOARD_DATE")),
						rset.getString("MEMBER_ID"),
						rset.getInt("COUNT"),
						rset.getInt("REPLY_COUNT"),
						rset.getInt("RNUM")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public ArrayList<Board> selectBoardListOld(Connection conn,PageInfo pi,String search, String keyword){
		ArrayList<Board> list=new ArrayList<>();
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String sql="";
		switch(keyword) {
			case "제목내용" : sql=prop.getProperty("boardSearchAllOld"); break;
			case "제목" : sql=prop.getProperty("boardSearchTitleOld"); break;
			case "내용" : sql=prop.getProperty("boardSearchContentOld"); break;
			case "작성자" : sql=prop.getProperty("boardSearchIdOld"); break;
			default: sql=prop.getProperty("boardSearchAllOld"); break;
		}
		
		try {
			pstmt=conn.prepareStatement(sql);
			int startRow=(pi.getCurrentPage()-1)*pi.getBoardLimit()+1;
			int endRow=startRow+pi.getBoardLimit()-1;
			//int endRow=pi.getListCount()-(pi.getCurrentPage()-1)*pi.getBoardLimit();
			//int startRow=(endRow>pi.getBoardLimit())? endRow-pi.getBoardLimit()+1: endRow;

			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				list.add(new Board(
						rset.getInt("BOARD_NO"),
						rset.getString("BOARD_TITLE"),
						rset.getString(String.valueOf("BOARD_DATE")),
						rset.getString("MEMBER_ID"),
						rset.getInt("COUNT"),
						rset.getInt("REPLY_COUNT"),
						rset.getInt("RNUM")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public int deleteReply(Connection conn, Reply r) {
		int result=0;
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("deleteReply");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, r.getReplyNo());
			pstmt.setInt(2, r.getBoardNo());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateReply(Connection conn, Reply r) {
		int result=0;
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("updateReply");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, r.getReplyContent());
			pstmt.setInt(2, r.getReplyNo());
			pstmt.setInt(3, r.getBoardNo());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	//이전글
	public int boardPrev(Connection conn, int boardNo) {
		int result=0;
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String sql=prop.getProperty("boardPrev");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				result=rset.getInt("PREV");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}
	//다음글
	public int boardNext(Connection conn, int boardNo) {
		int result=0;
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String sql=prop.getProperty("boardNext");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				result=rset.getInt("NEXT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}
}
