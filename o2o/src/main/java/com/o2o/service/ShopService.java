package com.o2o.service;

import java.io.File;
import java.io.InputStream;

import com.o2o.dto.ImageHolder;
import com.o2o.dto.ShopExecution;
import com.o2o.entity.Shop;
import com.o2o.exceptions.ShopOperationException;

public interface ShopService {
	ShopExecution addShop(Shop shop,ImageHolder image) throws ShopOperationException;
	Shop queryByShopId(Long shopId);
	ShopExecution updateShop(Shop shop,ImageHolder image) throws ShopOperationException;
	ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);
}
