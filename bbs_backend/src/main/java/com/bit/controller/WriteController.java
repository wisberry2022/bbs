package com.bit.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bit.model.BbsDao;
import com.bit.model.BbsDto;

@WebServlet("/bbs")
public class WriteController extends HttpServlet {
	Logger log = Logger.getLogger(this.getClass().getCanonicalName());
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		BbsDto bean = new BbsDto();
		String author = req.getParameter("author");
		String pwd = req.getParameter("pwd");
		String content = req.getParameter("content");
		String title = req.getParameter("title");

		bean.setTitle(title);
		bean.setAuthor(author);
		bean.setPwd(pwd);
		bean.setContent(content);
		
		log.info(bean.toString());
		
		try {
			BbsDao dao = new BbsDao();
			if(dao.insertOne(bean) > 0) {
				log.info("추가 성공!");
				resp.setStatus(200);
			}else {
				log.info("추가 실패!");
				resp.setStatus(404);
			}
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
