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
			
			String atDelete=multiRequest.getParameter("atDelete");
			if("atDelete".equals(atDelete)) {
				new File(savePath+multiRequest.getParameter("originFileName")).delete();
			}
			
			if(multiRequest.getParameter("originFileName")!=null) {
				//????????? ????????? ?????? ???
				b.setAttachmentPath("resources/board_upfiles/");
				b.setAttachmentName(multiRequest.getFilesystemName("reUpfile"));
				
				if(multiRequest.getOriginalFileName("reUpfile")!=null) {
					//?????? ????????? ????????? ???
					new File(savePath+multiRequest.getParameter("originFileName")).delete();
					//?????? ??????
				}
			}
			
			int result=new BoardService().updateBoard(b);
			
			if(result>0) {
				//??????=>/jsp/list.bo??? ??????(????????????????????? ???????????????)
				request.getSession().setAttribute("alertMsg", "??????????????? ???????????? ?????????????????????");
				response.sendRedirect(request.getContextPath()+"/detail.bo?bno="+boardNo+"&currentPage=1");
			}else {
				//??????=>?????????????????? ?????????

				request.setAttribute("errorMsg", "????????? ?????? ??????");
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
