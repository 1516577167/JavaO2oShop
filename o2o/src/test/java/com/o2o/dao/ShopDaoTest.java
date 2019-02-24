package com.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.security.acl.Owner;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.o2o.BaseTest;
import com.o2o.entity.Area;
import com.o2o.entity.PersonInfo;
import com.o2o.entity.Shop;
import com.o2o.entity.ShopCategory;

public class ShopDaoTest extends BaseTest{
@Autowired
private ShopDao shopDao;
@Test
@Ignore
public void testInsertShop() {
	Shop shop = new Shop();
	PersonInfo owner = new PersonInfo();
	Area area = new Area();
	ShopCategory shopCategory= new ShopCategory();
	owner.setUserId(1L);
	area.setAreaId(2);
	shopCategory.setShopCategoryId(1L);
	shop.setShopName("测试店铺");
	shop.setShopDesc("test");
	shop.setShopAddr("test");
	shop.setPhone("1234567");
	shop.setShopImg("test");
	shop.setCreateTime(new Date());
	shop.setEnableStatus(1);
	shop.setAdvice("审核中");
	shop.setOwner(owner);
	shop.setShopCategory(shopCategory);
	shop.setArea(area);
	int effectedNum = shopDao.insertShop(shop);
	assertEquals(1,effectedNum);
}

@Test
public void testQueryShopListAndCount() {
	Shop shopCondition = new Shop();
//	PersonInfo owner = new PersonInfo();
//	owner.setUserId(1L);
//	shopCondition.setOwner(owner);
	ShopCategory shopCategory = new ShopCategory();
	shopCategory.setShopCategoryId(2L);
	shopCondition.setShopCategory(shopCategory);
	Area a = new Area();
	a.setAreaId(1);
	shopCondition.setArea(a);
	int count = shopDao.queryShopCount(shopCondition);
	List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 5);
	System.out.println("店鋪大小" + shopList.size());
	System.out.println(count); 
}

@Test
@Ignore
public void testQuerybyShopId() {
	long shopId = 1;
	Shop shop = shopDao.queryByShopId(shopId);
	System.out.println(shop.getShopName());
	System.out.println(shop.getShopId());
	System.out.println(shop.getShopCategory().getShopCategoryId());
}

@Test
@Ignore
public void updateShop() {
	Shop shop = new Shop();
	shop.setShopId(1L);
	PersonInfo owner = new PersonInfo();
	Area area = new Area();
	ShopCategory shopCategory= new ShopCategory();
	owner.setUserId(1L);
	area.setAreaId(2);
	shopCategory.setShopCategoryId(1L);
	shop.setShopName("测试");
	shop.setShopDesc("te");
	shop.setShopAddr("te");
	shop.setPhone("12345");
	shop.setShopImg("te");
	shop.setCreateTime(new Date());
	shop.setEnableStatus(1);
	shop.setAdvice("审核中");
	shop.setOwner(owner);
	shop.setShopCategory(shopCategory);
	shop.setArea(area);
	int effectedNum = shopDao.updateShop(shop);
	assertEquals(1,effectedNum);
}
}
