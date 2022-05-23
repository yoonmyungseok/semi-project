package com.nubigo.board.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.nubigo.board.model.service.BoardService;
import com.nubigo.board.model.vo.Board;
import com.nubigo.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class BoardUpdateController
 */
@WebServlet("/update.bo")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize=30*1024*1024;
			String savePath=request.getSession().getServletContext().getRealPath("/resources/board_upfiles/");
			
			MultipartRequest multiRequest=new MultipartRequest(request, savePath,maxSize,"utf-8",new MyFileRenamePolicy());
			
			int boardNo=Integer.parseInt(multiRequest.getParameter("bno"));
			String memberNo=multiRequest.getParameter("memberNo");
			String boardTitle=multiRequest.getParameter("title");
			String boardContent=multiRequest.getParameter("content");
			
			Board b=new Board();
			b.setBoardNo(boardNo);
			b.setMemberId(memberNo);
			b.setBoardTitle(boardTitle);
			b.setBoardContent(boardContent);
			
			if(multiRequest.getFilesystemName("reUpfile")!=null) {
				b.setAttachmentName(multiRequest.getFilesystemName("reUpfile"));
				b.setAttachmentPath("resources/board_upfiles/");
				
				if(b.getAttachmentName()!=null) {
					//첨부파일이 있었을 경우
					//삭제시키고자하는 파일 객체 생성=>delete 메소드 (해당 파일을 삭제시켜주는 역할)
					new File(savePath+multiRequest.getParameter("reUpfile")).delete();
				}
			}
			
			int result=new BoardService().updateBoard(b);
			
			if(result>0) {
				//성공=>/jsp/list.bo로 요청(리스트페이지가 보여지도록)
				request.getSession().setAttribute("alertMsg", "성공적으로 게시글이 수정되었습니다");
				response.sendRedirect(request.getContextPath()+"/detail.bo?bno="+boardNo);
			}else {
				//실패=>에러페이지로 포워딩

				request.setAttribute("errorMsg", "게시글 수정 실패");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
