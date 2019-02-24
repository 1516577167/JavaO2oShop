package com.o2o.service.impl;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.o2o.dao.ShopDao;
import com.o2o.dto.ImageHolder;
import com.o2o.dto.ShopExecution;
import com.o2o.entity.Shop;
import com.o2o.enums.ShopStateEnum;
import com.o2o.exceptions.ShopOperationException;
import com.o2o.service.ShopService;
import com.o2o.util.ImageUtil;
import com.o2o.util.PageCalculator;
import com.o2o.util.PathUtil;

@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopDao shopdao;

	@Override
	@Transactional
	public ShopExecution addShop(Shop shop, ImageHolder image) {
		// TODO Auto-generated method stub
		if (shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		try {
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			int effectedNum = shopdao.insertShop(shop);
			if (effectedNum <= 0) {
				throw new ShopOperationException("店鋪創建失敗！");
			} else {
				if (image != null) {
					try {
						addShopimg(shop, image);
					} catch (Exception e) {
						// TODO: handle exception
						throw new ShopOperationException("addShopImgerror:" + e.getMessage());
					}
					effectedNum = shopdao.updateShop(shop);
					if (effectedNum <= 0) {
						throw new ShopOperationException("图片更新失敗！");
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new ShopOperationException("addShop error:" + e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK, shop);
	}

	private void addShopimg(Shop shop,ImageHolder image) {
//		获取shop图片的相对值路径
		String dest = PathUtil.getShopImagePath(shop.getShopId());
//		System.out.println(dest);
//		System.out.println(fileName);
		String shopImgAddr = ImageUtil.generateThumbnail(image.getImage(), image.getImageName(), dest);
		System.out.println(shopImgAddr);
		shop.setShopImg(shopImgAddr);
		
	}

	@Override
	public Shop queryByShopId(Long shopId) {
		return shopdao.queryByShopId(shopId);
	}

	@Override
	public ShopExecution updateShop(Shop shop,ImageHolder image)
			throws ShopOperationException {
		if (shop == null || shop.getShopId() == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		} else {
			try {
				if (image.getImage() != null && image.getImageName() != null) {
					Shop tempShop = shopdao.queryByShopId(shop.getShopId());
					if (tempShop.getShopImg() != null) {
						ImageUtil.deleteFileOrPath(tempShop.getShopImg());
					}
					addShopimg(shop, image);
//					System.out.println(1);
				}
				shop.setLastEditTime(new Date());
				int effectedNum = shopdao.updateShop(shop);
				if (effectedNum <= 0) {
					return new ShopExecution(ShopStateEnum.INNER_ERROR);
				} else {
					shop = shopdao.queryByShopId(shop.getShopId());
					return new ShopExecution(ShopStateEnum.SUCCESS);
				}
			} catch (Exception e) {
				throw new ShopOperationException("updateShopError:"+e.getMessage());
			}
		}
	}

	@Override
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Shop> shopList = shopdao.queryShopList(shopCondition, rowIndex, pageSize);
		int count = shopdao.queryShopCount(shopCondition);
		ShopExecution se = new ShopExecution();
		if (shopList != null) {
			se.setShopList(shopList);
			se.setCount(count);
		}else {
			se.setState(ShopStateEnum.INNER_ERROR.getState());
		}
		return se;
	}
}
