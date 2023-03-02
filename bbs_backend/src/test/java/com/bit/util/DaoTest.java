package com.bit.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bit.model.BbsDao;
import com.bit.model.BbsDto;

public class DaoTest {
	private static Logger log = Logger.getLogger(DaoTest.class.getCanonicalName());
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/bbsproject";
	private static final String USER = "scott";
	private static final String PWD = "tiger";
	private static BbsDao dao = null;
	
	public static void insertTest(BbsDto bean) {
		dao = new BbsDao(DRIVER,URL,USER,PWD);
		try {
			dao.insertOne(bean);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void getListTest(int cnt) {
		dao = new BbsDao(DRIVER, URL, USER, PWD);
		List<BbsDto> list = new ArrayList<>();
		try {
			list = dao.getList(cnt);
			log.info(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getListAllTest() {
		dao = new BbsDao(DRIVER, URL, USER, PWD);
		List<BbsDto> list = new ArrayList<>();
		try {
			list = dao.getListAll();
			log.info(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String[] test = {"Board", "list", "5"};
		System.out.println((Object)test[test.length-1] instanceof String);
	}

}
