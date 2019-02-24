package com.o2o.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.o2o.BaseTest;
import com.o2o.dto.ProductCategoryExecution;
import com.o2o.entity.ProductCategory;

public class ProductCategoryTest extends BaseTest {

	@Autowired
	private ProductCategoryService productCategoryService;
	
	@Test
	@Ignore
	public void testInsertProductCategory() {
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
		ProductCategoryExecution e = productCategoryService.insertProduceCategory(productCateList);
		System.out.println(e);
	}
	
	@Test
	public void testdeleteProductCategory() {
		ProductCategoryExecution e = productCategoryService.deleteProductCategory(16L, 1L);
		System.out.println(e.getState());
		System.out.println(e.getStateInfo());
		
	}
	
}
