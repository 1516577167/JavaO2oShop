package com.o2o.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.o2o.BaseTest;
import com.o2o.entity.Area;
import com.o2o.service.impl.AreaServiceImpl;

public class AreaServiceTest extends BaseTest {
	@Autowired
	private AreaService areaService;
	@Test
	public void testGetAreaList() {
		List<Area> listArea = areaService.queryArea();
		assertEquals("南区", listArea.get(0).getAreaName());
	}
}
