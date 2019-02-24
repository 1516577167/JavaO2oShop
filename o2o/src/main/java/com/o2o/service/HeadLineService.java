package com.o2o.service;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.o2o.dto.ProductCategoryExecution;
import com.o2o.entity.HeadLine;
import com.o2o.entity.ProductCategory;
import com.o2o.exceptions.ProductCategoryOperationException;

public interface HeadLineService {
	public static String HEADLINELIST = "listHeadLine";
	
	List<HeadLine> queryHeadLineList(HeadLine headLine) throws IOException;
}
