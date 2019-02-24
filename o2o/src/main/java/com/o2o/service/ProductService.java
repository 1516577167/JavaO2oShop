package com.o2o.service;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.o2o.dto.ImageHolder;
import com.o2o.dto.ProductExecution;
import com.o2o.entity.Product;
import com.o2o.exceptions.ProductOperationException;

public interface ProductService {
	ProductExecution insertProduct(Product product,ImageHolder image,List<ImageHolder> imageList) throws ProductOperationException ;
	Product queryByProductId(Long productId);
	ProductExecution updateProduct(Product product,ImageHolder image,List<ImageHolder> imageList) throws ProductOperationException;
	ProductExecution queryByProductList(Product productCondition, int rowIndex,int pageSize) throws ProductOperationException;
	int queryProductCount( Product productCondition);
}
