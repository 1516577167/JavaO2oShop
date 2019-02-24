package com.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.o2o.entity.ProductCategory;

public interface ProductCategoryDao {
	int insertProduceCategory(List<ProductCategory> produceCategory);
	List<ProductCategory> queryProductCategory(@Param("shopId") Long shopId);
	int deleteProductCategory(@Param("productCategoryId") long productCategoryId,@Param("shopId") long shopId);
}
