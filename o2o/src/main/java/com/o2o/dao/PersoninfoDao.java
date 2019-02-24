package com.o2o.dao;

import com.o2o.entity.PersonInfo;

public interface PersoninfoDao {

	PersonInfo queryPersonInfo(Long userId);
	
	int insertPersonInfo(PersonInfo personInfo);
}
