package com.o2o.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.o2o.entity.LocalAuth;

public interface LocalAuthDao {
//	根据账号密码查找用户
	LocalAuth queryLocalByUserNameAndPwd(@Param("username") String username, @Param("password") String password);
//根据用户id查找密码
	LocalAuth queryLocalByUserId(@Param("userId") long userId);

	int insertLocalAuth(LocalAuth localAuth);
	
	int updateLocalAuth(@Param("userId") Long userId, @Param("username") String username,
			@Param("password") String password, @Param("newPassword") String newPassword,
			@Param("lastEditTime") Date lastEditTime);
}
