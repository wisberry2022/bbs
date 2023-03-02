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

@WebServlet("/val")
public class ValController extends HttpServlet {
	Logger log = Logger.getLogger(this.getClass().getCanonicalName());
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		int num = Integer.parseInt(req.getParameter("num"));
		String pwd = req.getParameter("pwd");
		
		try {
			BbsDao dao = new BbsDao();
			if(dao.isValid(num, pwd)) {
				resp.setStatus(resp.SC_OK);
			}else {
				resp.setStatus(resp.SC_BAD_REQUEST);
			};
		} catch (NamingException e) {
			e.printStackTrace();
			resp.setStatus(resp.SC_EXPECTATION_FAILED);
		} catch (SQLException e) {
			e.printStackTrace();
			resp.setStatus(resp.SC_INTERNAL_SERVER_ERROR);
		}
		
	}
}
