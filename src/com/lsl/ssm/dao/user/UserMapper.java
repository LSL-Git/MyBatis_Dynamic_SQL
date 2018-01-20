package com.lsl.ssm.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lsl.ssm.pojo.User;

public interface UserMapper {

	/**
	 * 查询用户列表
	 * @param userName
	 * @param roleId
	 * @return
	 */
	public List<User> getUserList(@Param("userName")String userName, @Param("userRole")Integer roleId);
}
