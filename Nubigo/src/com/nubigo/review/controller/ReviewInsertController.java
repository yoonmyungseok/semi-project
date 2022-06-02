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
			
			//넘어올 수 있는 file의 갯수는
			//최소 1개~최대 4개
			//각 file의 key 값은
			//file1~file4까지
			for(int i=1; i<=4; i++) {
				String key="file"+i;
				//file1, file2, file3, file4
				//원본명=>multiRequest.getOriginalFileName(key)
				//수정명=>mulitRequest.getFilesystemName(key)
				
				//첨부파일이 존재할 경우
				if(multiRequest.getOriginalFileName(key)!=null) {
					//Attachment 객체 생성
					//=>원본명, 수정명, 저장경로, 파일레벨 필드 가공
					ReviewAttachment ra=new ReviewAttachment();
					ra.setFileName(multiRequest.getFilesystemName(key));
					ra.setFilePath("resources/review_upfiles/");
					
					if(i==1) {//대표이미지일 경우
						ra.setFileLevel(1);
					}else {//상세이미지일 경우
						ra.setFileLevel(2);
					}
					list.add(ra);//첨부파일의 개수만큼 담김
				}
			}
			int result=new ReviewService().insertReview(r,list);
			
			if(result>0) {
				request.getSession().setAttribute("alertMsg", "리뷰 작성 성공");
				response.sendRedirect(request.getContextPath()+"/myReview.re?currentPage=1");
			}else {
				request.setAttribute("errorMsg", "리뷰 작성 실패");
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
