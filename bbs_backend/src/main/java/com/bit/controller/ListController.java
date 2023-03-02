package com.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bit.model.BbsDao;
import com.bit.model.BbsDto;

@WebServlet("/list/*")
public class ListController extends HttpServlet {
	Logger log = Logger.getLogger(this.getClass().getCanonicalName());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json; charset=UTF-8");
		log.info("get 요청 들어옴");
		String[] uri = req.getRequestURI().split("/");
		if(uri[uri.length-1].equals("all")) {
			resp.setCharacterEncoding("utf-8");
			doAll(req, resp);
		}else {
//			resp.setCharacterEncoding("utf-8");
			resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
			resp.setHeader("Access-Control-Allow-Origin", "*");			
			int cnt = Integer.parseInt(uri[uri.length-1]);
			doCnt(req, resp, cnt);
		}	
	}
	
	private void doAll(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub

	}
	
	private void doCnt(HttpServletRequest req, HttpServletResponse resp, int cnt) throws IOException {
		resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
		resp.setHeader("Access-Control-Allow-Origin", "*");
		try(
			PrintWriter out = resp.getWriter();
		) {
			BbsDao dao = new BbsDao();
			List<BbsDto> list = dao.getList(cnt);
			int idx = 0;
			
			out.print("{\"lists\":[");
			for(BbsDto bean:list) {
				out.print("{\"num\":"+bean.getNum()
						+ ", \"title\":\""+bean.getTitle()+"\""
						+ ", \"author\":\""+bean.getAuthor()+"\""
						+ ", \"date\":\""+bean.getWriteDate().toLocalDate()+"\""
						+ ", \"view\":"+bean.getViewcnt()
						+ ", \"total\":"+bean.getTotal());
				if(idx++ == list.size()-1 ) out.print("}");
				else out.print("},");
			}
			out.print("]}");
			
			
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}
}
