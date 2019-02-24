package com.o2o.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.o2o.dao.ProductDao;
import com.o2o.dao.ProductImgDao;
import com.o2o.dto.ImageHolder;
import com.o2o.dto.ProductCategoryExecution;
import com.o2o.dto.ProductExecution;
import com.o2o.dto.ShopExecution;
import com.o2o.entity.Product;
import com.o2o.entity.Shop;
import com.o2o.entity.productImg;
import com.o2o.enums.ProductCategoryStateEnum;
import com.o2o.enums.ProductStateEnum;
import com.o2o.enums.ShopStateEnum;
import com.o2o.exceptions.ProductCategoryOperationException;
import com.o2o.exceptions.ProductOperationException;
import com.o2o.service.ProductService;
import com.o2o.util.ImageUtil;
import com.o2o.util.PageCalculator;
import com.o2o.util.PathUtil;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgDao productImgDao;

	@Override
	@Transactional
	public ProductExecution insertProduct(Product product, ImageHolder image, List<ImageHolder> imageList)
			throws ProductOperationException {
		if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			product.setCreateTime(new Date());
			product.setLastEditTime(new Date());
			product.setEnableStatus(1);
			if (image != null) {
				// 添加商品缩略图
				addProductImg(product, image);
			}
			try {
				int eff = productDao.insertProduct(product);
				if (eff <= 0) {
					throw new ProductOperationException("创建商品失败！");
				}
			} catch (Exception e) {
				// TODO: handle exception
				throw new ProductOperationException("创建商品失败！:" + e.getMessage());
			}
			if (imageList != null && imageList.size() > 0) {
				// 添加商品详情图
				addProductImgList(product, imageList);
			}
			return new ProductExecution(ProductStateEnum.SUCCESS, product);
		} else {
			throw new ProductOperationException("创建商品失败！参数为空");
		}
	}

	private void addProductImgList(Product product, List<ImageHolder> imageList) {
		// TODO Auto-generated method stub
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		List<productImg> productImgList = new ArrayList<productImg>();
		for(ImageHolder productImgHold : imageList) {
			String imageAddr = ImageUtil.generateNormalImg(productImgHold.getImage(), productImgHold.getImageName(), dest);	
			productImg productImg = new productImg();
			productImg.setCreateTime(new Date());
			productImg.setImgAddr(imageAddr);
			productImg.setProductId(product.getProductId());
			productImgList.add(productImg);
		}
		if(productImgList.size() > 0) {
			try {
				int eff = productImgDao.insertproductImg(productImgList);
				if(eff <= 0) {
					throw new ProductOperationException("创建商品详情图失败！");
				}
			} catch (Exception e) {
				throw new ProductOperationException("创建商品详情图失败！:"+e.toString());
			}
		}
	}

	private void addProductImg(Product product, ImageHolder image) {
		// TODO Auto-generated method stub
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		String imageAddr = ImageUtil.generateThumbnail(image.getImage(), image.getImageName(), dest);
		product.setImgAddr(imageAddr);
	}

	@Override
	public Product queryByProductId(Long productId) {
		// TODO Auto-generated method stub
		return productDao.queryByProductId(productId);
	}

	@Override
	public ProductExecution updateProduct(Product product, ImageHolder image, List<ImageHolder> imageList)
			throws ProductOperationException {
		if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			product.setLastEditTime(new Date());
			if(image!=null) {
				Product productTemp = productDao.queryByProductId(product.getProductId());
				if(productTemp.getImgAddr()!=null) {
					ImageUtil.deleteFileOrPath(productTemp.getImgAddr());
				}
				addProductImg(product, image);
			}
			if (imageList != null && imageList.size() > 0) {
				// 添加商品详情图
				deleteProductImgList(product.getProductId());
				addProductImgList(product, imageList);
			}
			try {
				int eff = productDao.updateProduct(product);
				if (eff <= 0) {
					throw new ProductOperationException("修改商品失败！");
				}
			} catch (Exception e) {
				// TODO: handle exception
				throw new ProductOperationException("修改商品失败！:" + e.getMessage());
			}
			return new ProductExecution(ProductStateEnum.SUCCESS, product);
		} else {
			throw new ProductOperationException("创建商品失败！参数为空");
		}
	}

	private void deleteProductImgList(Long productId) {
		// TODO Auto-generated method stub
		List <productImg> productImgList = productImgDao.queryByProductIdList(productId);
		for(productImg pImg:productImgList ) {
			ImageUtil.deleteFileOrPath(pImg.getImgAddr());
		}
		
		productImgDao.deleteImgByProductId(productId);
	}

	@Override
	public ProductExecution queryByProductList(Product productCondition, int pageIndex, int pageSize)
			 throws ProductOperationException{
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Product> productList = productDao.queryByProductList(productCondition, rowIndex, pageSize);
		int count = productDao.queryProductCount(productCondition);
		ProductExecution pe = new ProductExecution();
		if (productList != null) {
			pe.setProductList(productList);
			pe.setCount(count);
		}else {
			pe.setState(ShopStateEnum.INNER_ERROR.getState());
		}
		return pe;
	}

	@Override
	public int queryProductCount(Product productCondition) {
		// TODO Auto-generated method stub
		return productDao.queryProductCount(productCondition);
	}
}
