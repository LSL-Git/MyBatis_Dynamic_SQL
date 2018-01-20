package com.lsl.ssm.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.lsl.ssm.dao.user.UserMapper;
import com.lsl.ssm.pojo.User;
import com.lsl.ssm.utils.MyBatisUtils;


public class UserMapperTest {

	private Logger logger = Logger.getLogger(UserMapper.class);
	
	@Before
	public void setUp() throws Exception {
//		super.setUp();
	}

	@Test
	public void test() {
		System.out.println("test running...");
	}
	
	@Test
	public void testGetUserList() {
		SqlSession sqlSession = null;
		List<User> userList = new ArrayList<User>();
		try {
			sqlSession = MyBatisUtils.createSqlSession();
			String userName = "赵";
//			Integer roleId = 3;
			Integer roleId = null;
			userList = sqlSession.getMapper(UserMapper.class).getUserList(userName, roleId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			MyBatisUtils.closeSqlSession(sqlSession);
		}
		
		logger.debug("userlist.size ----> " + userList.size());
		for(User user: userList){
			logger.debug("testGetUserList=======> id: " + user.getId() +
						" and userCode: " + user.getUserCode() + 
						" and userName: " + user.getUserName() + 
						" and userRole: " + user.getUserRole() + 
						" and userRoleName: " + user.getUserRoleName() +
						" and age: " + user.getAge() +
						" and phone: " + user.getPhone() +
						" and gender: " + user.getGender());
		}
	}
}
