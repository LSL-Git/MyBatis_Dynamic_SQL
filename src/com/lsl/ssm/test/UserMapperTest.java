package com.lsl.ssm.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	public void testModify(){
		logger.debug("testModify !===================");
		SqlSession sqlSession = null;
		int count = 0;
		try {
			User user = new User();
			user.setId(19);
			user.setUserCode("testmodify");
			user.setUserName("测试用户修改02");
			user.setUserPassword("1234567");
			Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse("1980-10-10");
			user.setBirthday(birthday);
			user.setCreationDate(new Date());
			user.setAddress("地址测试修改");
			user.setGender(2);
			user.setPhone("13600002222");
			user.setUserRole(2);
			user.setModifyBy(1);
			user.setModifyDate(new Date());
			sqlSession = MyBatisUtils.createSqlSession();
			count = sqlSession.getMapper(UserMapper.class).modify(user);
			//模拟异常，进行回滚
			//int i = 2/0;
			sqlSession.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
			count = 0;
		}finally{
			MyBatisUtils.closeSqlSession(sqlSession);
		}
		logger.debug("testModify count: " + count);
	}
	
	@Test
	public void testGetUserList() {
		SqlSession sqlSession = null;
		List<User> userList = new ArrayList<User>();
		try {
			sqlSession = MyBatisUtils.createSqlSession();
			String userName = "赵";
			Integer roleId = 3;
//			Integer roleId = null;
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
