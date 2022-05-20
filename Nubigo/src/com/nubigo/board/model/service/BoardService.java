package com.nubigo.board.model.service;

import static com.nubigo.common.JDBCTemplate.close;
import static com.nubigo.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.nubigo.board.model.dao.BoardDao;
import com.nubigo.board.model.vo.Board;

public class BoardService {
	public ArrayList<Board> selectBoardList(){
		Connection conn=getConnection();
		ArrayList<Board> list=new BoardDao().selectBoardList(conn);
		close(conn);
		return list;
	}
	
	public Board selectBoard(int boardNo) {
		Connection conn=getConnection();
		Board b=new BoardDao().selectBoard(conn, boardNo);
		close(conn);
		return b;
	}
}
