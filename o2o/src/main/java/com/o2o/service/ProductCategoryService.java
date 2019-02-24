package com.o2o.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.o2o.dto.ProductCategoryExecution;
import com.o2o.entity.ProductCategory;
import com.o2o.exceptions.ProductCategoryOperationException;

public interface ProductCategoryService {
	ProductCategoryExecution insertProduceCategory(List<ProductCategory> produceCategory)
			throws ProductCategoryOperationException;
	List<ProductCategory> queryProductCategory(Long shopId);
	ProductCategoryExecution deleteProductCategory( long productCategoryId, long shopId)
	throws ProductCategoryOperationException;
}
