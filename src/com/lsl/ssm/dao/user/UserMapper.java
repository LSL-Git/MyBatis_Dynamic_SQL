package com.lsl.ssm.dao.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lsl.ssm.pojo.User;

public interface UserMapper {

	/**
	 * 查询用户信息（choose）
	 * @param userName
	 * @param userCode
	 * @param userRole
	 * @param creationDate
	 * @return
	 */
	public List<User> getUserList_choose(@Param("userName") String userName,
			@Param("userCode") String userCode,
			@Param("userRole") Integer userRole,
			@Param("creationDate") Date creationDate);

	/**
	 * 根据用户角色列表，获取该角色列表下用户列表信息-foreach_map
	 * 
	 * @param roleIds
	 * @return
	 */
	public List<User> getUserByRoleId_foreach_map(Map<String, Object> roleIdMap);

	/**
	 * 根据用户角色列表，获取该角色列表下用户列表信息-foreach_list
	 * 
	 * @param roleIds
	 * @return
	 */
	public List<User> getUserByRoleId_foreach_list(List<Integer> roleIdList);

	/**
	 * 根据用户角色列表，获取该角色列表下用户列表信息-foreach_array
	 * 
	 * @param roleIds
	 * @return
	 */
	public List<User> getUserByRoleId_foreach_array(Integer[] roleIds);

	/**
	 * 修改用户信息
	 * 
	 * @param user
	 * @return
	 */
	public int modify(User user);

	/**
	 * 查询用户列表
	 * 
	 * @param userName
	 * @param roleId
	 * @return
	 */
	public List<User> getUserList(@Param("userName") String userName,
			@Param("userRole") Integer roleId);
}
