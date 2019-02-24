package com.o2o.service;

import java.util.List;

import com.o2o.entity.Area;

public interface AreaService {
	public static String AREALIST = "arealist";
	
	List<Area> queryArea();
}
