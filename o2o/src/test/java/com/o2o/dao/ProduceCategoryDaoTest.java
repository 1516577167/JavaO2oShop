package com.o2o.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.o2o.BaseTest;
import com.o2o.entity.ProductCategory;

public class ProduceCategoryDaoTest extends BaseTest {
	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	@Test
	@Ignore
	public void testInsertProduceCate() {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setCreateTime(new Date());
		productCategory.setPriority(50);
		productCategory.setShopId(1L);
		productCategory.setProductCategoryName("123");
		ProductCategory productCategory2 = new ProductCategory();
		productCategory2.setCreateTime(new Date());
		productCategory2.setPriority(5);
		productCategory2.setShopId(1L);
		productCategory2.setProductCategoryName("321");
		List<ProductCategory> productCateList = new ArrayList<ProductCategory>();
		productCateList.add(productCategory);
		productCateList.add(productCategory2);
		int e = productCategoryDao.insertProduceCategory(productCateList);
		System.out.println(e);
	}
	
	@Test
	@Ignore
	public void testQueryProduceCateList() {
		List<ProductCategory> produceCateList = productCategoryDao.queryProductCategory(2L);
		System.out.println(produceCateList.size());
	}
	
	@Test
	public void testDeleteProduceCateList() {
		int produceCateList = productCategoryDao.deleteProductCategory(12L, 1L);
		System.out.println(produceCateList);
	}
}
