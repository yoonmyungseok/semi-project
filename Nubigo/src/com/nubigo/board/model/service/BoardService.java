package com.nubigo.board.model.service;

import static com.nubigo.common.JDBCTemplate.close;
import static com.nubigo.common.JDBCTemplate.commit;
import static com.nubigo.common.JDBCTemplate.getConnection;
import static com.nubigo.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.nubigo.board.model.dao.BoardDao;
import com.nubigo.board.model.vo.Board;
import com.nubigo.common.model.vo.PageInfo;
import com.nubigo.member.model.vo.Reply;

public class BoardService {
	public ArrayList<Board> selectBoardList(PageInfo pi){
		Connection conn=getConnection();
		ArrayList<Board> list=new BoardDao().selectBoardList(conn, pi);
		close(conn);
		return list;
	}
	
	public Board selectBoard(int boardNo) {
		Connection conn=getConnection();
		Board b=new BoardDao().selectBoard(conn, boardNo);
		close(conn);
		return b;
	}
	
	public int selectListCount() {
		Connection conn=getConnection();
		int result=new BoardDao().selectListCount(conn);
		close(conn);
		return result;
	}
	
	public int insertBoard(Board b) {
		Connection conn=getConnection();
		int result=new BoardDao().insertBoard(conn,b);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int insertReply(Reply r) {
		Connection conn=getConnection();
		int result=new BoardDao().insertReply(conn, r);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public ArrayList<Reply> selectReplyList(int boardNo){
		Connection conn=getConnection();
		ArrayList<Reply> list=new BoardDao().selectReplyList(conn, boardNo);
		close(conn);
		return list;
	} 
	
	public int deleteBoard(int boardNo) {
		Connection conn=getConnection();
		int result=new BoardDao().deleteBoard(conn,boardNo);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int updateBoard(Board b) {
		Connection conn=getConnection();
		int result=new BoardDao().updateBoard(conn,b);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public ArrayList<Board> boardSearch(String search, String keyword){
		Connection conn=getConnection();
		ArrayList<Board> list=new BoardDao().boardSearch(conn, search, keyword);
		close(conn);
		return list;
	}
}
