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
import com.o2o.entity.productImg;

public class ProduceImgTest extends BaseTest {
	@Autowired
	private ProductImgDao productImgDao;
	
	@Test
	@Ignore
	public void testInsertProduceCate() {
		productImg p1 = new productImg();
		p1.setCreateTime(new Date());
		p1.setImgAddr("p1addr");
		p1.setImgDesc("desc");
		p1.setPriority(20);
		p1.setProductId(4L);
		productImg p2 = new productImg();
		p2.setCreateTime(new Date());
		p2.setImgAddr("p2addr");
		p2.setImgDesc("desc");
		p2.setPriority(20);
		p2.setProductId(4L);
		List<productImg> productImgList = new ArrayList<productImg>();
		productImgList.add(p2);
		productImgList.add(p1);
		int e = productImgDao.insertproductImg(productImgList);
		System.out.println(e);
	}
	
//	@Test
//	@Ignore
//	public void testQueryProduceCateList() {
//		List<ProductCategory> produceCateList = productCategoryDao.queryProductCategory(2L);
//		System.out.println(produceCateList.size());
//	}
//	
	@Test
	public void testDeleteProduceImgList() {
		int produceCateList = productImgDao.deleteImgByProductId(4L);
		System.out.println(produceCateList);
	}
}
