package com.nubigo.review.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.nubigo.common.MyFileRenamePolicy;
import com.nubigo.review.model.service.ReviewService;
import com.nubigo.review.model.vo.Review;
import com.nubigo.review.model.vo.ReviewAttachment;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ReviewInsertController
 */
@WebServlet("/insert.re")
public class ReviewInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewInsertController() {
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
			String savePath=request.getSession().getServletContext().getRealPath("/resources/review_upfiles/");
			
			MultipartRequest multiRequest=new MultipartRequest(request, savePath,maxSize,"utf-8",new MyFileRenamePolicy());
			String memberNo=multiRequest.getParameter("memberNo");
			String title=multiRequest.getParameter("title");
			String content=multiRequest.getParameter("content");
			int score=Integer.parseInt(multiRequest.getParameter("score"));
			String age=multiRequest.getParameter("age");
			String season=multiRequest.getParameter("season");
			String groupWith=multiRequest.getParameter("groupWith");
			
			Review r=new Review();
			r.setMemberId(memberNo);
			r.setReviewTitle(title);
			r.setReviewContent(content);
			r.setScore(score);
			r.setAge(age);
			r.setSeason(season);
			r.setGroupWith(groupWith);
			
			ArrayList<ReviewAttachment> list=new ArrayList<>();
			
			//????????? ??? ?????? file??? ?????????
			//?????? 1???~?????? 4???
			//??? file??? key ??????
			//file1~file4??????
			for(int i=1; i<=4; i++) {
				String key="file"+i;
				//file1, file2, file3, file4
				//?????????=>multiRequest.getOriginalFileName(key)
				//?????????=>mulitRequest.getFilesystemName(key)
				
				//??????????????? ????????? ??????
				if(multiRequest.getOriginalFileName(key)!=null) {
					//Attachment ?????? ??????
					//=>?????????, ?????????, ????????????, ???????????? ?????? ??????
					ReviewAttachment ra=new ReviewAttachment();
					ra.setFileName(multiRequest.getFilesystemName(key));
					ra.setFilePath("resources/review_upfiles/");
					
					if(i==1) {//?????????????????? ??????
						ra.setFileLevel(1);
					}else {//?????????????????? ??????
						ra.setFileLevel(2);
					}
					list.add(ra);//??????????????? ???????????? ??????
				}
			}
			int result=new ReviewService().insertReview(r,list);
			
			if(result>0) {
				request.getSession().setAttribute("alertMsg", "?????? ?????? ??????");
				response.sendRedirect(request.getContextPath()+"/myReview.re?currentPage=1");
			}else {
				request.setAttribute("errorMsg", "?????? ?????? ??????");
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
