package com.o2o.dao;

import com.o2o.entity.WechatAuth;

public interface WechatAuthDao {
 
	WechatAuth queryWechatAuthByOpenId(String openId);
	
	int insertWechatAuth(WechatAuth wechatAuth);
}
