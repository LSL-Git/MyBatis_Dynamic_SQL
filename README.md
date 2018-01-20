# MyBatis_Dynamic_SQL

**SSM 框架学习**

二、MyBatis  动态SQL使用  
   
*在上一个项目的基础上修改，这里主要展示修改部分的内容*   

1、用户表查询操作-if
```
<!-- 查询用户列表 动态条件查询-if 
	 如果不使用if，当roleName为空或userName为空时会出现sql异常
	 查看日志可看到异常：select * from db where and userName=?
-->
<select id="getUserList" resultMap="userList">
	select u.*, r.roleName from smbms_user u, smbms_role r
	where u.userRole = r.id
	<if test="userRole != null">
		and u.userRole = #{userRole}
	</if>
	<if test="userName != null and userName != ''">
		and u.userName like CONCAT ('%',#{userName},'%')
	</if>
</select>
```   
2、 动态条件查询-if-where
```
<!-- 查询用户列表 动态条件查询-if-where-->
<select id="getUserList" resultMap="userList">
	select u.*, r.roleName from smbms_user u, smbms_role r
	<where>
		u.userRole = r.id
		<if test="userName != null and userName != ''" >
			and u.userName like CONCAT ('%',#{userName},'%')
		</if>
		<if test="userRole != null">
			and r.userRole = #{userRole}
		</if>
	</where>
</select>
```

