package com.nubigo.faq.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.nubigo.faq.model.dao.FaqDao;
import com.nubigo.faq.model.vo.Faq;

import static com.nubigo.common.JDBCTemplate.*;

public class FaqService {
	public ArrayList<Faq> getFaqList() {
		Connection conn = getConnection();
		
		ArrayList<Faq> list = new FaqDao().getFaqList(conn);
		
		close(conn);
		
		return list;
	}
}
