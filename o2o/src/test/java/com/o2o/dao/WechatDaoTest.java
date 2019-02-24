package com.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.o2o.BaseTest;
import com.o2o.entity.Area;
import com.o2o.entity.PersonInfo;
import com.o2o.entity.WechatAuth;
import com.o2o.entity.productImg;

public class WechatDaoTest extends BaseTest{
	@Autowired
	private WechatAuthDao areaDao;
	@Autowired
	private PersoninfoDao pDao;
	
	@Test
	@Ignore
	public void testInsertWechatAuth() {
		PersonInfo p = pDao.queryPersonInfo(1L);
		WechatAuth w = new WechatAuth();
		w.setPersonInfo(p);
		w.setCreateTime(new Date());
		w.setOpenId("1we");
		int eff = areaDao.insertWechatAuth(w);
		System.out.println(eff);
	}
	
	@Test
	public void testQueryWechatAuth() {
//		PersonInfo p = pDao.queryPersonInfo(1L);
//		WechatAuth w = new WechatAuth();
//		w.setPersonInfo(p);
//		w.setCreateTime(new Date());
//		w.setOpenId("1we");
		WechatAuth eff = areaDao.queryWechatAuthByOpenId("1we");
		System.out.println(eff.getWechatAuthId());
		System.out.println(eff.getPersonInfo().getName());
	}
}
