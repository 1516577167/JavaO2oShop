package com.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.o2o.entity.HeadLine;

//新增店铺
public interface HeadLineDao {
	List<HeadLine> queryHeadLineList(@Param("headLineCondition") HeadLine headLineCondition);
//	int queryHeadLine(@Param("headLineCondition") HeadLine headLineCondition);
//	Shop queryByShopId(Long shopId);
//	int insertShop(Shop shop);
//	int updateShop(Shop shop);
}
