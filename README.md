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
3、 动态条件更新数据-if-set
```
<!-- 修改用户信息-if-set -->
<update id="modify" parameterType="User">
	update smbms_user
	<set>
		<if test="userCode != null">userCode=#{userCode},</if>
		<if test="userName != null">userName=#{userName},</if>
		<if test="userPassword != null">userPassword=#{userPassword},</if>
		<if test="gender != null">gender=#{gender},</if>
		<if test="birthday != null">birthday=#{birthday},</if>
		<if test="phone != null">phone=#{phone},</if>
		<if test="address != null">address=#{address},</if>
		<if test="userRole != null">userRole=#{userRole},</if>
		<if test="modifyBy != null">modifyBy=#{modifyBy},</if>
		<if test="modifyDate != null">modifyDate=#{modifyDate}</if>
	</set>
	where id=#{id}
</update>
```
4、 动态条件查找数据-if-trim-prefix-prefixOverrides
```
<!-- 查询用户列表 动态条件查询-if-trim-prefix-prefixOverrides -->
<select id="getUserList" resultMap="userList">
	select * from smbms_user
	<trim prefix="where" prefixOverrides="and|or">
		<!-- 
			如果满足条件，下面的语句会连接到上面的语句，trim会将第一个连上来的语句的and或or替换成where
			prefix:前缀  prefixOverrides：被替换的词，用 ’|‘分隔，不能有空格（我的测试是这样）
		 -->
		<if test="userName != null and userName != ''">
			and userName like CONCAT ('%',#{userName},'%')
		</if>
		<if test="userRole != null">
			and userRole = #{userRole}
		</if>
	</trim>
</select>
```

   

@Author: 瞌睡虫   
@mybatis-3.2.2   
@Database: mysql 5.7.15   
@Tool: MyEclipse