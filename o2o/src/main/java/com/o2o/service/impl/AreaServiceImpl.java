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
import com.o2o.dao.AreaDao;
import com.o2o.entity.Area;
import com.o2o.exceptions.AreaOperationException;
import com.o2o.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService{

	@Autowired
	private AreaDao areaDao;
	@Autowired
	private JedisUtil.Keys Keys;
	@Autowired
	private JedisUtil.Strings Strings;
	
	private static Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);
	@Override
	public List<Area> queryArea() {
		// TODO Auto-generated method stub
		String key = AREALIST;
		List<Area> listArea = null;
		ObjectMapper mapper = new ObjectMapper();
		if(!Keys.exists(key)) {
			listArea = areaDao.queryArea();
			String jsonStr;
			try {
				jsonStr = mapper.writeValueAsString(listArea);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e.getMessage());
				throw new AreaOperationException(e.getMessage());
			}
			Strings.set(key, jsonStr);
		}else {
			String jsonStr = Strings.get(key);
			JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Area.class); 
			try {
				listArea = mapper.readValue(jsonStr, javaType);
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
		
		return listArea;
	}
}
