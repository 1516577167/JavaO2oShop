package com.o2o.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.o2o.BaseTest;
import com.o2o.entity.Product;
import com.o2o.entity.ProductCategory;
import com.o2o.entity.Shop;

public class ProduceDaoTest extends BaseTest {
	@Autowired
	private ProductDao productDao;
	
	@Test
	@Ignore
	public void testInsertProduceCate() {
		Product product = new Product();
		product.setCreateTime(new Date());
		product.setEnableStatus(1);
		product.setImgAddr("12332231");
		product.setLastEditTime(new Date());
		product.setNormalPrice("200");
		product.setPriority(200);
		ProductCategory productCategory =new ProductCategory();
		productCategory.setProduceCategoryId(4L);
		Shop shop = new Shop();
		shop.setShopId(1L);
		product.setProductCategory(productCategory);
		product.setShop(shop);
		product.setProductDesc("testes");
		product.setProductName("testname");
		product.setPromotionPrice("500");
		int e = productDao.insertProduct(product);
		System.out.println(e);
	}
	
	@Test
	@Ignore
	public void testUpdateProduce() {
		Product product = new Product();
		product.setCreateTime(new Date());
		product.setEnableStatus(1);
		product.setImgAddr("12332231");
		product.setLastEditTime(new Date());
		product.setNormalPrice("200");
		product.setPriority(200);
		ProductCategory productCategory =new ProductCategory();
		productCategory.setProduceCategoryId(4L);
		Shop shop = new Shop();
		shop.setShopId(1L);
		product.setProductCategory(productCategory);
		product.setShop(shop);
		product.setProductDesc("testes");
		product.setProductName("testupdate");
		product.setPromotionPrice("500");
		product.setProductId(4L);
		int e = productDao.updateProduct(product);
		System.out.println(e);
	}
	
	@Test
	@Ignore
	public void testQueryProduce1() {
		Product p = productDao.queryByProductId(10L);
		System.out.println(p.getProductImgList().get(0).getImgAddr());
		System.out.println(p.getProductImgList().get(1).getImgAddr());
		System.out.println(p.getProductName());
	}
	
	@Test
	@Ignore
	public void queryProduceList() {
		Product product = new Product();
//		ProductCategory pc = new ProductCategory();
//		pc.setProduceCategoryId(4L);
//		product.setProductCategory(pc);
		int e = productDao.queryProductCount(product);
		System.out.println(e);
	}
	
	@Test
	@Ignore
	public void testQueryProduce() {
		Product p = productDao.queryByProductId(10L);
		System.out.println(p.getProductImgList().get(0).getImgAddr());
		System.out.println(p.getProductImgList().get(1).getImgAddr());
		System.out.println(p.getProductName());
	}
//	
	@Test
//	@Ignore
	public void testDeleteProduceCateList() {
		int produceCateList = productDao.updateProductCategory(4L);
		System.out.println(produceCateList);
	}
}
