package com.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.o2o.BaseTest;
import com.o2o.entity.Area;
import com.o2o.entity.PersonInfo;

public class PersonInfoDaoTest extends BaseTest{
	@Autowired
	private PersoninfoDao areaDao;
	
	@Test
	public void testInsertPersonInfo() {
		PersonInfo eff = areaDao.queryPersonInfo(1L);
		System.out.println(eff.getName());
	}
}
