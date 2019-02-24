package com.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.o2o.BaseTest;
import com.o2o.entity.Area;
import com.o2o.entity.ShopCategory;

public class ShopCategoryDaoTest extends BaseTest{
	@Autowired
	private ShopCategoryDao categoryDao;
	
	@Test
	@Ignore
	public void testQueryArea() {
		List<ShopCategory> areaList = categoryDao.queryShopCategory(null);
		System.out.println(areaList.get(0).getShopCategoryName());
		assertEquals(2,areaList.size());
		ShopCategory testCategory = new ShopCategory();
		ShopCategory parentCategory = new ShopCategory();
		parentCategory.setShopCategoryId(1L);
		testCategory.setParent(parentCategory); 
		System.out.println(testCategory.getParent().getShopCategoryId());
		areaList = categoryDao.queryShopCategory(testCategory);
		assertEquals(1,areaList.size());
		System.out.println(areaList.get(0).getShopCategoryName());
	}
	
	@Test
	public void queryCateInfo() {
		List<ShopCategory> i = categoryDao.queryShopCategory(new ShopCategory());
		System.out.println(i.size());
	}
}
