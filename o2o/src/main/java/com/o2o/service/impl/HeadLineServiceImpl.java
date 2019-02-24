package com.o2o.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.o2o.cache.JedisUtil;
import com.o2o.dao.HeadLineDao;
import com.o2o.entity.Area;
import com.o2o.entity.HeadLine;
import com.o2o.exceptions.AreaOperationException;
import com.o2o.exceptions.HeadLineOperationException;
import com.o2o.service.HeadLineService;

@Service
public class HeadLineServiceImpl implements HeadLineService{

	@Autowired
	private HeadLineDao headLineDao;
	@Autowired
	private JedisUtil.Keys Keys;
	@Autowired
	private JedisUtil.Strings Strings;
	
	private static Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);

	@Override
	public List<HeadLine> queryHeadLineList(HeadLine headLine) throws IOException {
		String key = HEADLINELIST;
		List<HeadLine> headLineList = null;
		// 定义jackson数据转换操作类
		ObjectMapper mapper = new ObjectMapper();
		// 拼接出redis的key
		if (headLine != null && headLine.getEnableStatus() != null) {
			key = key + "_" + headLine.getEnableStatus();
		}
		if (!Keys.exists(key)) {
			// 若不存在，则从数据库里面取出相应数据
			headLineList = headLineDao.queryHeadLineList(headLine);
			// 将相关的实体类集合转换成string,存入redis里面对应的key中
			String jsonString;
			try {
				jsonString = mapper.writeValueAsString(headLineList);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				throw new HeadLineOperationException(e.getMessage());
			}
			Strings.set(key, jsonString);
		}else {
			String jsonStr = Strings.get(key);
			JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, HeadLine.class); 
			try {
				headLineList = mapper.readValue(jsonStr, javaType);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new AreaOperationException(e.getMessage());
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new AreaOperationException(e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new AreaOperationException(e.getMessage());
			}
		}
		return headLineList;
	}
}
