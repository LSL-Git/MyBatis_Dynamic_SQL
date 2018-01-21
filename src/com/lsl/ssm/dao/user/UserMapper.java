package com.lsl.ssm.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lsl.ssm.pojo.User;

public interface UserMapper {
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	public int modify(User user);

	/**
	 * 查询用户列表
	 * @param userName
	 * @param roleId
	 * @return
	 */
	public List<User> getUserList(@Param("userName")String userName, @Param("userRole")Integer roleId);
}
