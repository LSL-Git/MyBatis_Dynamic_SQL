package com.lsl.ssm.utils;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtils {

	private static SqlSessionFactory factory;
	
	static {
		System.out.println("create sqlsession factory=================");
		try {
			InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
			factory = new SqlSessionFactoryBuilder().build(is);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * 返回openSession
	 * @return
	 */
	public static SqlSession createSqlSession() {
		return factory.openSession(false); // true 为自动提交事务
	}
	
	/**
	 * 关闭 sqlSession
	 * @param sqlSession
	 */
	public static void closeSqlSession(SqlSession sqlSession) {
		if (null != sqlSession) {
			sqlSession.close();
		}
	}
}
