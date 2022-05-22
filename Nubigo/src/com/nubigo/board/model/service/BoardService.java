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
}
