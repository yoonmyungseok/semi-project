package com.nubigo.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nubigo.member.model.service.MemberService;
import com.nubigo.member.model.vo.Member;

/**
 * Servlet implementation class AdminMemberUpdateController
 */
@WebServlet("/update.ad")
public class AdminMemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) 인코딩 설정(Post 방식일 때만)
				request.setCharacterEncoding("UTF-8");
				
				// 2) 요청 시 전달값들을 뽑아서 변수에 기록 및 VO객체로 가공 (아이디, 이름, 성별, 생년월일, 이메일, 폰, 탈퇴여부, 글작성권한 여부, 가입일)
				String memberId = request.getParameter("memberId"); 
				String memberName = request.getParameter("memberName"); 
				String gender = request.getParameter("gender");
				String birthday = request.getParameter("birthday");
				String email = request.getParameter("email"); 
				String phone = request.getParameter("phone"); 
				String WithdrawStatus = request.getParameter("WithdrawStatus");
				String AuthorityStatus = request.getParameter("AuthorityStatus");
				
				// 매개변수 생성자를 이용해서 Member 객체에 담기
				Member m = new Member(memberId, memberName, gender, birthday, email, phone, WithdrawStatus, AuthorityStatus);
			
				// 3) MemberService 클래스의 어떤 메소드를 호출하여 요청 처리(전달값을 넘기고 결과 받기)
				Member updateMem = new MemberService().updateMember(m);
				
					
				// 4_2, 4_3) 포워딩
				request.getRequestDispatcher("views/member/MemberUpdateForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
