package com.o2o.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.o2o.cache.JedisUtil;
import com.o2o.service.CacheService;

@Service
public class CacheServiceImpl implements CacheService{
	@Autowired
	private JedisUtil.Keys Keys;

	@Override
	public void removeFromCache(String keyPrefix) {
		// TODO Auto-generated method stub
		Set<String> keySet = Keys.keys(keyPrefix + "*");
		for (String key:keySet) {
			Keys.del(key);
		}
	}
	
}
