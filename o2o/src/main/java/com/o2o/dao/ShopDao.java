package com.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.o2o.entity.Shop;

//新增店铺
public interface ShopDao {
	List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition,
			@Param("rowIndex") int rowIndex,@Param("pageSize") int pageSize);
	int queryShopCount(@Param("shopCondition") Shop shopCondition);
	Shop queryByShopId(Long shopId);
	int insertShop(Shop shop);
	int updateShop(Shop shop);
}
