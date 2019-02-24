package com.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.o2o.entity.Product;
import com.o2o.entity.Shop;

public interface ProductDao {
	int insertProduct(Product product);
	
	Product queryByProductId(Long productId);
	
	int updateProductCategory(Long productCategoryId);
	
	int updateProduct(Product product);
	
	List<Product> queryByProductList(@Param("productCondition") Product productCondition,
			@Param("rowIndex") int rowIndex,@Param("pageSize") int pageSize);

	int queryProductCount(@Param("productCondition") Product productCondition);
}
