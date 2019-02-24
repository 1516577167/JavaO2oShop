package com.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.o2o.BaseTest;
import com.o2o.dto.ImageHolder;
import com.o2o.dto.ShopExecution;
import com.o2o.entity.Area;
import com.o2o.entity.PersonInfo;
import com.o2o.entity.Shop;
import com.o2o.entity.ShopCategory;
import com.o2o.enums.ShopStateEnum;
import com.o2o.exceptions.ShopOperationException;

public class ShopServiceTest extends BaseTest{
	@Autowired
	private ShopService shopservice;
	
	@Test
	@Ignore
	public void testAddShop() throws FileNotFoundException {
		Shop shop = new Shop();
//		shop.setShopId(1L);
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory= new ShopCategory();
		owner.setUserId(1L);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(1L);
		shop.setShopName("测试Controll");
		shop.setShopDesc("te");
		shop.setShopAddr("te"); 
		shop.setPhone("12345");
//		shop.setShopImg("te");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		shop.setOwner(owner);
		shop.setShopCategory(shopCategory);
		shop.setArea(area);
		File imgFile = new File("C:/Users/Administrator/Desktop/1.JPG");
		InputStream is = new FileInputStream(imgFile);
		ImageHolder imageHolder = new ImageHolder("dabai.jpg", is);
		ShopExecution se = shopservice.addShop(shop,imageHolder);
		assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
	}
	
	@Test
	@Ignore
	public void testUpdateShop() throws ShopOperationException,FileNotFoundException{
		Shop shop = shopservice.queryByShopId(1L);
		shop.setShopName("修改后的店铺名称");
		File imgFile = new File("C:/Users/Administrator/Desktop/桌面/1.jpg");
		InputStream is = new FileInputStream(imgFile);
		ImageHolder imageHolder = new ImageHolder("dabai.jpg", is);
		ShopExecution shopExecution = shopservice.updateShop(shop, imageHolder);
		System.out.println(shop.getEnableStatus());
		System.out.println(shop.getEnaleStatus());
	}
	
	@Test
	public void testQueryShopListAndCount(){
		Shop shopCondition = new Shop();
//		PersonInfo owner = new PersonInfo();
//		owner.setUserId(1L);
//		shopCondition.setOwner(owner);
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setShopCategoryId(1L);
		shopCondition.setShopCategory(shopCategory);
		ShopExecution se = shopservice.getShopList(shopCondition, 1, 2);
		System.out.println("店鋪大小" + se.getCount());
		System.out.println("店鋪大小" + se.getShopList().size());
	}
	
}
