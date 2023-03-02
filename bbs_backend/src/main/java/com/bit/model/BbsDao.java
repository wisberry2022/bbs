package com.bit.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.mysql.cj.jdbc.MysqlDataSource;

public class BbsDao {
	private Logger log = Logger.getLogger(this.getClass().getCanonicalName());
	private Connection conn = null;
	
	public BbsDao() throws NamingException, SQLException {
//		Context initContext = new InitialContext();
//		Context envContext  = (Context)initContext.lookup("java:/comp/env");
//		DataSource ds = (DataSource)envContext.lookup("jdbc/bbsDB");
		MysqlDataSource ds = new MysqlDataSource();
		ds.setURL("jdbc:mysql://localhost:3306/bbsproject");
		ds.setUser("scott");
		ds.setPassword("tiger");
		conn = ds.getConnection();
	}
	
	// test용 생성자
	public BbsDao(String driver, String url, String user, String pwd) {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public String dateQuery() {
		return "writeDate between ? and ? AND ";
	}
	
	

	// 게시글 검색을 위한 메서드
	public List<BbsDto> search(Map<String, String> qsMap) throws SQLException {
		List<BbsDto> list = new ArrayList<>();
		Boolean dateFlag = false;
		
		String sql = "select num, title, author, writeDate, viewcnt from bbs where ";
		
		String sqlSuffix = "order by num desc limit ?,10";
		ResultSet rs = null;
		
		List<String> keySet = new ArrayList<>();
		Set<String> keys = qsMap.keySet();
		Iterator<String> ite = keys.iterator();
		while(ite.hasNext()) {
			String key = ite.next();
			keySet.add(key);
		}
		
//		log.info(keySet);
		
		if(keySet.contains("eDate") && keySet.contains("sDate")) {
			String sql2 = "select num, title, author, writeDate, viewcnt, ";
			String ssql = "(select count(*) from bbs where writeDate between ? and ? AND "+ qsMap.get(keySet.get(4)) +" like ?) as total ";
			sql2 += ssql + "from bbs where ";
			sql2 += dateQuery();
			sql2 += qsMap.get(keySet.get(4)) +" Like ? " + sqlSuffix;
			try(
					Connection conn = this.conn;
					PreparedStatement pstmt = conn.prepareStatement(sql2);
			){
					pstmt.setString(1, qsMap.get(keySet.get(1)));
					pstmt.setString(2, qsMap.get(keySet.get(3)));
					pstmt.setString(3, "%"+ qsMap.get(keySet.get(0)) +"%");
					pstmt.setString(4, qsMap.get(keySet.get(1)));
					pstmt.setString(5, qsMap.get(keySet.get(3)));
					pstmt.setString(6, "%" + qsMap.get(keySet.get(0)) + "%");
					pstmt.setInt(7, Integer.parseInt(qsMap.get(keySet.get(2))));
					rs = pstmt.executeQuery();
					while(rs.next()) {
						BbsDto bean = new BbsDto();
						
						bean.setNum(rs.getInt(1));
						bean.setTitle(rs.getString(2));
						bean.setAuthor(rs.getString(3));
						bean.setWriteDate(rs.getDate(4));
						bean.setViewcnt(rs.getInt(5));
						bean.setTotal(rs.getInt(6));
						
						list.add(bean);
					}
					
					return list;
			}	
		}else {
			sql += qsMap.get(keySet.get(2))+" Like ? " + sqlSuffix;
			try(
					Connection conn = this.conn;
					PreparedStatement pstmt = conn.prepareStatement(sql);
			){
				log.info(sql);
				pstmt.setString(1, "%" + qsMap.get(keySet.get(0)) + "%");
				pstmt.setInt(2, Integer.parseInt(qsMap.get(keySet.get(1))));
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					BbsDto bean = new BbsDto();
					
					bean.setNum(rs.getInt(1));
					bean.setTitle(rs.getString(2));
					bean.setAuthor(rs.getString(3));
					bean.setWriteDate(rs.getDate(4));
					bean.setViewcnt(rs.getInt(5));
					
					list.add(bean);
				}
				
				return list;		
			}
		}

		
	}
	
	
	// 게시글 삭제를 위한 메서드
	public int deleteOne(int bbsNo) throws SQLException {
		String sql = "delete from bbs where num=?";
		
		try(
				Connection conn = this.conn;
				PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			pstmt.setInt(1, bbsNo);
			return pstmt.executeUpdate();
		}
	}
	

	
	// 게시글 수정을 위한 메서드
	public int updateOne(BbsDto bean) throws SQLException {
		String sql = "update bbs set title=?, author=?, pwd=AES_ENCRYPT(?, sha2('key', 512)), writeDate=now(), content=? where num=?";
		
		try(
				Connection conn = this.conn;
				PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			pstmt.setString(1, bean.getTitle());
			pstmt.setString(2, bean.getAuthor());
			pstmt.setString(3, bean.getPwd());
			pstmt.setString(4, bean.getContent());
			pstmt.setInt(5, bean.getNum());
			return pstmt.executeUpdate();
		}
	}
	
	// 게시글 비밀번호 일치 여부 확인 메서드
	public boolean isValid(int num, String pwd) throws SQLException {
		boolean result;
		String sql = "select num from bbs where num = ? and pwd = AES_ENCRYPT(?, sha2('key', 512))";
		ResultSet rs = null;
		
		try(
			Connection conn = this.conn;
			PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			pstmt.setInt(1, num);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = num == rs.getInt(1) ? true : false;
			}else {
				result = false;
			}
			
			if(rs != null) rs.close();
		}	
		return result;
	}
	
	// 방문자수 증가
	public void increaseView(int bbsno) throws SQLException {
		String sql = "update bbs set viewcnt = viewcnt+1 where num=?";
		
		try(
				PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			pstmt.setInt(1, bbsno);
			pstmt.executeUpdate();
		}
	}
	
	// 게시물 번호로 게시글 데이터 받아오는 메서드
	public BbsDto getOne(int bbsno) throws SQLException {
		String sql = "select num, title, author, writeDate, content, viewcnt from bbs where num=?";
		BbsDto bbs = new BbsDto();
		ResultSet rs = null;
		
		try(
			Connection conn = this.conn;
			PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			increaseView(bbsno);
			pstmt.setInt(1, bbsno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bbs.setNum(rs.getInt(1));
				bbs.setTitle(rs.getString(2));
				bbs.setAuthor(rs.getString(3));
				bbs.setWriteDate(rs.getDate(4));
				bbs.setContent(rs.getString(5));
				bbs.setViewcnt(rs.getInt(6));
			}
		}finally {
			if(rs != null) rs.close();
		}
		return bbs;
	}
	
	public List<BbsDto> getListAll() throws SQLException {
		String sql = "select num, title, author, writeDate, viewcnt from bbs order by num desc";
		List<BbsDto> list = new ArrayList<>();
		
		try(
			Connection conn = this.conn;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
		){
			while(rs.next()) {
				BbsDto bean = new BbsDto();
				bean.setNum(rs.getInt(1));
				bean.setTitle(rs.getString(2));
				bean.setAuthor(rs.getString(3));
				bean.setWriteDate(rs.getDate(4));
				bean.setViewcnt(rs.getInt(5));
				list.add(bean);
			}
		}
		return list;
	}
	
	// 게시물 목록 불러오는 함수
	public List<BbsDto> getList(int cnt) throws SQLException {
//		select * from bbs order by num desc limit 0, 10;
//		select * from bbs order by num desc limit 10, 10;
		String sql = "select num, title, author, writeDate, viewcnt, (select count(*) from bbs) as total from bbs order by num desc limit ?, 10";
		List<BbsDto> list = new ArrayList<>();
		ResultSet rs = null;
		
		try(
				Connection conn = this.conn;
				PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			pstmt.setInt(1, cnt);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BbsDto bean = new BbsDto();
				bean.setNum(rs.getInt(1));
				bean.setTitle(rs.getString(2));
				bean.setAuthor(rs.getString(3));
				bean.setWriteDate(rs.getDate(4));
				bean.setViewcnt(rs.getInt(5));
				bean.setTotal(rs.getInt(6));
				list.add(bean);
			}
			if(rs != null) rs.close();
		}
		
		return list;
	}
	
	public int insertOne(BbsDto bean) throws SQLException {
		String sql = "insert into bbs(title, author, pwd, writeDate, content) values(?,?, AES_ENCRYPT(?, sha2('key', 512)), now(), ?)";
		try(
			Connection conn = this.conn;
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setString(1, bean.getTitle());
			pstmt.setString(2, bean.getAuthor());
			pstmt.setString(3, bean.getPwd());
			pstmt.setString(4, bean.getContent());
			return pstmt.executeUpdate();
		}
		
	}
}
