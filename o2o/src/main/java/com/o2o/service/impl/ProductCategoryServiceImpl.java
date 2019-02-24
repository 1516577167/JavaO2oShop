package com.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.o2o.dao.ProductCategoryDao;
import com.o2o.dao.ProductDao;
import com.o2o.dto.ProductCategoryExecution;
import com.o2o.entity.ProductCategory;
import com.o2o.enums.ProductCategoryStateEnum;
import com.o2o.exceptions.ProductCategoryOperationException;
import com.o2o.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryDao productCategoryDao;
	@Autowired
	private ProductDao productDao;
	
	@Override
	public ProductCategoryExecution insertProduceCategory(List<ProductCategory> produceCategory)
			throws ProductCategoryOperationException {
		if (produceCategory != null && produceCategory.size() > 0) {
			int effectedNum = productCategoryDao.insertProduceCategory(produceCategory);
			try {
				if (effectedNum < 0) {
					throw new ProductCategoryOperationException("店鋪类别创建失败！");
				} else {
					return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
				}
			} catch (Exception e) {
				// TODO: handle exception
				throw new ProductCategoryOperationException("添加商品类别失败：" + e.getMessage());
			}
		} else {
			return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
		}
	}

	@Override
	public List<ProductCategory> queryProductCategory(Long shopId) {
		// TODO Auto-generated method stub
		return productCategoryDao.queryProductCategory(shopId);
	}

	@Override
	@Transactional
	public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
			throws ProductCategoryOperationException {
		// TODO 要将此商品类别下的商品类别Id置空
		try {
			int eff = productDao.updateProductCategory(productCategoryId);
			if(eff < 0) {
				throw new RuntimeException("商品類別更新失敗");
			}
		} catch (Exception e) {
			throw new RuntimeException("deleteProductCategory:"+e.getMessage());
		}
		try {
			int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
			if (effectedNum < 0) {
				throw new ProductCategoryOperationException("店鋪类别创建失败！");
			} else {
				return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new ProductCategoryOperationException("刪除商品类别失败：" + e.getMessage());
		}
	}
}
