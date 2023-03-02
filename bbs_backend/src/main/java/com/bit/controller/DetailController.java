package com.bit.controller;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bit.model.BbsDao;
import com.bit.model.BbsDto;

@WebServlet("/bbs/*")
public class DetailController extends HttpServlet {
	Logger log = Logger.getLogger(this.getClass().getCanonicalName());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Credentials", "true");
		resp.setHeader("Access-Control-Allow-Methods", "*");
		resp.setContentType("application/json; charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		BbsDto bbs = new BbsDto();
		String[] uris = req.getRequestURI().split("/");
		if(uris[uris.length-1].equals("search")) {
			Map<String, String> qsMap = new HashMap<>();
			String qS = req.getQueryString();
			String[] parsedQs = qS.split("\\$");
			for(String raw:parsedQs) {
				qsMap.put(raw.split("=")[0], URLDecoder.decode(raw.split("=")[1], "utf-8"));
			}
			
			try(PrintWriter out = resp.getWriter();) {
				BbsDao dao = new BbsDao();
				List<BbsDto> list = dao.search(qsMap);
				
				int idx = 0;
				
				out.print("{\"result\":[");
				for(BbsDto bean:list) {
					out.print("{\"num\":"+bean.getNum()
							+ ", \"viewcnt\":"+bean.getViewcnt()
							+ ", \"title\":\""+bean.getTitle()+"\""
							+ ", \"author\":\""+bean.getAuthor()+"\""
							+ ", \"writeDate\":\""+bean.getWriteDate().toLocalDate()+"\""
							+ ",\"total\":"+bean.getTotal());
					if(idx++ != list.size()-1) out.print("},");
					else out.print("}");
				}
				out.print("]}");
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
				
		}else {
			String[] ends = uris[uris.length-1].split(":");
			
			int bbsno = Integer.parseInt(ends[ends.length-1]);
			
			try(PrintWriter out = resp.getWriter();) {
				
				BbsDao dao = new BbsDao();
				bbs = dao.getOne(bbsno);
				out.print("{\"bbs\":{");
				out.print("\"num\":" + bbs.getNum()
						+ ", \"title\":\""+bbs.getTitle()+"\""
						+ ", \"author\":\""+bbs.getAuthor()+"\""
						+ ", \"writeDate\":\""+bbs.getWriteDate().toLocalDate()+"\""
						+ ", \"content\":\""+bbs.getContent()+"\""
						+ ", \"viewcnt\":"+bbs.getViewcnt());
				out.print("}}");
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "*");
		log.info("delete 요청 들어옴");
		String[] uris = req.getRequestURI().split("/");
		String[] ends = uris[uris.length-1].split(":");
		int bbsNo = Integer.parseInt(ends[1]);
		
		try {
			BbsDao dao = new BbsDao();
			if(dao.deleteOne(bbsNo) > 0) {
				resp.setStatus(resp.SC_OK);
			}else {
				resp.setStatus(resp.SC_BAD_REQUEST);
			}
		} catch (NamingException e) {
			e.printStackTrace();
			resp.setStatus(resp.SC_INTERNAL_SERVER_ERROR);
		} catch (SQLException e) {
			e.printStackTrace();
			resp.setStatus(resp.SC_INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
		resp.setHeader("Access-Control-Allow-Methods", "*");
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> res = new HashMap<>();
		req.setCharacterEncoding("utf-8");
		String[] uris = req.getRequestURI().split("/");
		String[] ends = uris[uris.length-1].split(":");
		int bbsNo = Integer.parseInt(ends[ends.length-1]);
		
		BbsDto bbs = new BbsDto();
		
		try(
			InputStream is = req.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
		){
			
			String msg = "";
			String temp = "";
			while(true) {
				temp = br.readLine();
				if(temp == null) break;
				msg += temp;
			}
			
			String[] datas = msg.split("&");
			
			for(String str:datas) {
				String[] parsed = str.split("=");
				res.put(parsed[0], URLDecoder.decode(parsed[1], "utf-8"));
			}

			bbs.setNum(bbsNo);
			bbs.setTitle(res.get("title"));
			bbs.setAuthor(res.get("author"));
			bbs.setPwd(res.get("pwd"));
			bbs.setContent(res.get("content"));
			
			try {
				BbsDao dao = new BbsDao();
				if(dao.updateOne(bbs) > 0) {
					resp.setStatus(resp.SC_OK);
				};
			} catch (NamingException e) {
				resp.setStatus(resp.SC_INTERNAL_SERVER_ERROR);
				e.printStackTrace();
			} catch (SQLException e) {
				resp.setStatus(resp.SC_BAD_REQUEST);
				e.printStackTrace();
			}
		}
	}
}
