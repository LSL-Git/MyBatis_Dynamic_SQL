package com.lsl.ssm.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lsl.ssm.pojo.User;

public interface UserMapper {
	
	
	/**
	 * 根据用户角色列表，获取该角色列表下用户列表信息-foreach_array
	 * @param roleIds
	 * @return
	 */
	public List<User> getUserByRoleId_foreach_array(Integer[] roleIds);
	
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
