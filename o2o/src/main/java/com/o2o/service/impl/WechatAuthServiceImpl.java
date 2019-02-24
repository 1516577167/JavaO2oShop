package com.o2o.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.o2o.dao.PersoninfoDao;
import com.o2o.dao.WechatAuthDao;
import com.o2o.dto.WechatAuthExecution;
import com.o2o.entity.PersonInfo;
import com.o2o.entity.WechatAuth;
import com.o2o.enums.WechatAuthStateEnum;
import com.o2o.exceptions.WechatAuthOperationException;
import com.o2o.service.WechatAuthService;

@Service
public class WechatAuthServiceImpl implements WechatAuthService{
	@Autowired
	private WechatAuthDao wechatAuthDao;
	@Autowired
	private PersoninfoDao personInfoDao;
	
	
	@Override
	public WechatAuth queryWechatAuthByOpenId(String openId) {
		// TODO Auto-generated method stub
		return wechatAuthDao.queryWechatAuthByOpenId(openId);
	}

	@Override
	public WechatAuthExecution insertWechatAuth(WechatAuth wechatAuth) throws WechatAuthOperationException {
		// TODO Auto-generated method stub
		if(wechatAuth == null || wechatAuth.getPersonInfo() == null) {
			return new WechatAuthExecution(WechatAuthStateEnum.NULL_AUTH_INFO);
			}
		try {
			wechatAuth.setCreateTime(new Date());
			if (wechatAuth != null && wechatAuth.getPersonInfo() != null) {
				try {
					wechatAuth.getPersonInfo().setCreateTime(new Date());
					wechatAuth.getPersonInfo().setEnableStatus(1);
					PersonInfo p = wechatAuth.getPersonInfo();
					int eff = personInfoDao.insertPersonInfo(p);
					wechatAuth.setPersonInfo(p);
					if(eff <= 0) {
						throw new WechatAuthOperationException("添加用户信息失败！");
					}
				} catch (Exception e) {
					throw new WechatAuthOperationException("insert PersonInfo:"+e.getMessage());
				}
			}
			int effectedNum = wechatAuthDao.insertWechatAuth(wechatAuth);
			if (effectedNum <= 0) {
				throw new WechatAuthOperationException("添加weauth用户失败");
			}else {
				return new WechatAuthExecution(WechatAuthStateEnum.SUCCESS);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new WechatAuthOperationException("创建用户失败：:"+e.getMessage());
		}
	}
	
}
