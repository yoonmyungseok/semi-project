package com.nubigo.place.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.nubigo.common.MyFileRenamePolicy;
import com.nubigo.place.model.service.PlaceService;
import com.nubigo.place.model.vo.Place;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class PlaceUpdateController
 */
@WebServlet("/update.pl")
public class PlaceUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 인코딩 세팅
		request.setCharacterEncoding("UTF-8");
		
		// enctype이 multipart/form-data로 잘 전송되었을 경우를 먼저 검사
		if(ServletFileUpload.isMultipartContent(request)) {
			
			// 전송 파일의 용량 제한 (byte 단위)
			int maxSize = 10 * 1024 * 1024;
			
			// 전달된 파일을 저장할 서버의 실제 경로
			String savePath = request.getSession().getServletContext().getRealPath("/resources/place_upfiles/");
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			// DB에 기록할 데이터 추출
			int placeNo = Integer.parseInt(multiRequest.getParameter("pno"));
			
			String localNo = multiRequest.getParameter("localNo");
			String placeName = multiRequest.getParameter("placeName");
			String placeCategory = multiRequest.getParameter("category");
			int latitude = Integer.parseInt(multiRequest.getParameter("latitude"));
			int longitude = Integer.parseInt(multiRequest.getParameter("longitude"));
			String placeContent = multiRequest.getParameter("placeContent");
			String deleteStatus = multiRequest.getParameter("deleteStatus");
			
			Place p = new Place();
			
			p.setLocalNo(localNo);
			p.setPlaceName(placeName);
			p.setPlaceCategory(placeCategory);
			p.setLatitude(latitude);
			p.setLongitude(longitude);
			p.setPlaceContent(placeContent);
			p.setDeleteStatus(deleteStatus);
			p.setThumbnailPath("resources/place_upfiles/");

			if(multiRequest.getOriginalFileName("reUpfile") != null) { // 썸네일을 수정한 경우
				
				p.setThumbnailName(multiRequest.getFilesystemName("reUpfile")); // 수정된 썸네일
				
			} else {
				
				p.setThumbnailName(multiRequest.getParameter("placeThumbnail")); // 기존 썸네일	
			}
			System.out.println(p);
			
			int result = new PlaceService().updatePlace(placeNo, p);
			
			if(result > 0) { // 성공
				
				request.getSession().setAttribute("alertMsg", "성공적으로 여행지를 수정했습니다.");
				response.sendRedirect("/nbg/adminlist.pl?currentPage=1");
				
			} else { // 실패
				
				request.getSession().setAttribute("alertMsg", "여행지 수정에 실패했습니다.");
				response.sendRedirect("/nbg/adminlist.pl?currentPage=1");
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
