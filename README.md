# MyBatis_Dynamic_SQL

**SSM 框架学习 (MyBatis篇)**

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
5、 动态条件修改数据-if-trim-prefix-suffixOverrides-suffix
```
<!-- 修改用户信息-if-trim-suffix-suffixOverrides -->
<update id="modify" parameterType="User">
	update smbms_user
	<trim prefix="set" suffixOverrides="," suffix="where id = #{id}">
		<!-- prefix:前缀，在将下面的句子连接前添加，这里添加为’set‘
			suffixOverrides：后缀覆盖，即将下面的句子连接后的最后一个’，‘,替换成’where id=#{id}‘
			suffix：要替换成的句子
			这里连接的句子为:update smbms_user set userCode = ?,...,modifyDate = ? where id = ?
		-->
		<if test="userCode != null">userCode=#{userCode},</if>
		<if test="userName != null">userName=#{userName},</if>
		<if test="userPassword != null">userPassword=#{userPassword},</if>
		<if test="gender != null">gender=#{gender},</if>
		<if test="birthday != null">birthday=#{birthday},</if>
		<if test="phone != null">phone=#{phone},</if>
		<if test="address != null">address=#{address},</if>
		<if test="userRole != null">userRole=#{userRole},</if>
		<if test="modifyBy != null">modifyBy=#{modifyBy},</if>
		<if test="modifyDate != null">modifyDate=#{modifyDate},</if>
	</trim>
</update>
```
6、 动态条件查找数据-foreach-array
```
<!-- 根据用户角色列表，获取该角色列表下用户列表信息-foreach_array -->	
<select id="getUserByRoleId_foreach_array" resultMap="userMapByRole">
	select * from smbms_user where userRole in
	<!-- 
		foreach：迭代一个集合，通常用于in查询
		collection：array|list|map-key（必须指定）
		item:迭代子项，与#{roleIds}对应，
		open：句子组合开始符
		separator：句子组合分隔符
		close：句子组合结束符
	 -->
	<foreach collection="array" item="roleIds" open="(" separator="," close=")">
		#{roleIds}
	</foreach>
</select>
```
7、 动态条件查找数据-foreach-list
```
<!-- 根据用户角色列表，获取该角色列表下用户列表信息-foreach_list -->	
<select id="getUserByRoleId_foreach_list" resultMap="userMapByRole">
	select * from smbms_user where userRole in
	<!-- 
		foreach：迭代一个集合，通常用于in查询
		collection：array|list|map-key（必须指定）
		item:迭代子项，与#{roleIds}对应，
		open：句子组合开始符
		separator：句子组合分隔符
		close：句子组合结束符
	 -->
	<foreach collection="list" item="roleIdList" open="(" separator="," close=")">
		#{roleIdList}
	</foreach>
</select>
```
8、 动态条件查找数据-foreach-map
```
<!-- 根据用户角色列表，获取该角色列表下用户列表信息-foreach_map -->	
<select id="getUserByRoleId_foreach_map" resultMap="userMapByRole">
	select * from smbms_user where userRole in
	<!-- 
		foreach：迭代一个集合，通常用于in查询
		collection：array|list|map-key（必须指定,map-key对应传入map的key值）
		item:迭代子项，与#{roleIds}对应，
		open：句子组合开始符
		separator：句子组合分隔符
		close：句子组合结束符
	 -->
	<foreach collection="mKey" item="roleIdMap" open="(" separator="," close=")">
		#{roleIdMap}
	</foreach>
</select>
```
9、 动态条件查找数据-trim-choose
```
<!-- 查询用户列表(choose) -->
<select id="getUserList_choose" resultType="User">
	select * from smbms_user
	<trim prefix="where" prefixOverrides="and|or">
		<!-- choose类似于switch，从上往下，当满足条件时break(最多连接一条语句)，如果全都不满足条件则otherwise -->
		<choose>
			<when test="userName != null and userName != ''">
				and userName like CONCAT ('%',#{userName},'%')
			</when>
			<when test="userCode != null and userCode != ''">
				and userCode like CONCAT ('%',#{userCode},'%')
			</when>
			<when test="userRole != null">
				and userRole = #{userRole}
			</when>
			<otherwise>
				<!-- and YEAR(creationDate = YEAR(NOW()) -->
				and YEAR(creationDate) = YEAR(#{creationDate})
			</otherwise>
		</choose>
	</trim>		
</select>
```
10、动态条件查询用户列表-分页查询
```
<!-- 查询用户信息（分页显示） -->
<select id="getUserListByPaging" resultType="User">
	select u.*, r.roleName from smbms_user u, smbms_role r
	where u.userRole = r.id
	<if test="userRole != null">
		and u.userRole = #{userRole}
	</if>
	<if test="userName != null and userName != ''">
		and u.userName like CONCAT ('%',#{userName},'%')
	</if>
	<!-- 
		limit x,y
		y:显示条数，即每页显示条数
		x:显示起始位置
		如:limit 0,5 则从第0条开始显示接下来5条记录
		  limit 5,5 则从第5条开始，显示接下来的5条记录
		  ...
	 -->
	order by u.creationDate DESC limit #{from}, #{pageSize}
</select>
```
   
   
    
@Author: 瞌睡虫   
@mybatis-3.2.2   
@Database: mysql 5.7.15   
@Tool: MyEclipse