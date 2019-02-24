package com.o2o.dao;

import java.util.List;


import com.o2o.entity.productImg;


public interface ProductImgDao {
	int insertproductImg(List<productImg> productImgList);
	int deleteImgByProductId(long productId);
	List <productImg> queryByProductIdList(Long productId);
}
