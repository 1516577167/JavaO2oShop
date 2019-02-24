package com.o2o.service;

import com.o2o.dto.WechatAuthExecution;
import com.o2o.entity.PersonInfo;
import com.o2o.entity.WechatAuth;
import com.o2o.exceptions.WechatAuthOperationException;

public interface WechatAuthService {
	WechatAuth queryWechatAuthByOpenId(String openId);

	WechatAuthExecution insertWechatAuth(WechatAuth wechatAuth) throws WechatAuthOperationException;
}
