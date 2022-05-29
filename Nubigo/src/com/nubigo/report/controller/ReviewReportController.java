package com.nubigo.report.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nubigo.report.model.service.ReportService;
import com.nubigo.report.model.vo.Report;

/**
 * Servlet implementation class ReviewReportController
 */
@WebServlet("/report.re")
public class ReviewReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewReportController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rno=Integer.parseInt(request.getParameter("rno"));
		String report=request.getParameter("report");
		String content=request.getParameter("content");
		
		Report r=new Report();
		r.setReviewNo(rno);
		if(report.equals("기타")) {
			r.setReportContent(content);
		}else {
			r.setReportContent(report);
		}
		int result=new ReportService().reviewReport(r);
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
