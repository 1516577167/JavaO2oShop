package com.o2o.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.o2o.dao.PersoninfoDao;
import com.o2o.entity.PersonInfo;
import com.o2o.service.PerInfoService;

@Service
public class PerInfoServiceImpl implements PerInfoService{

	@Autowired
	private PersoninfoDao personInfoDao;
	
	@Override
	public PersonInfo queryPersonInfo(Long userId) {
		// TODO Auto-generated method stub
		return personInfoDao.queryPersonInfo(userId);
	}


}
