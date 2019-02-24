package com.o2o.web.shopadmin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.o2o.dto.ProductCategoryExecution;
import com.o2o.entity.ProductCategory;
import com.o2o.entity.Shop;
import com.o2o.entity.ShopCategory;
import com.o2o.enums.ProductCategoryStateEnum;
import com.o2o.service.ProductCategoryService;
import com.o2o.util.HttpServletRequestUtil;

@Controller
@RequestMapping(value = "/shopadmin")
public class ProductCategoryManagementController {
	@Autowired
	private ProductCategoryService productCategoryService;

	@RequestMapping(value = "/getproductcategorylist", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> queryProductCategory(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Shop shop = (Shop) request.getSession().getAttribute("currentShop");
		long shopId;
		if (shop != null && shop.getShopId() >= 0) {
			shopId = shop.getShopId();
		} else {
			shopId = 1L;
		}
		List<ProductCategory> productCategories = productCategoryService.queryProductCategory(shopId);
		modelMap.put("success", true);
		modelMap.put("data", productCategories);
		return modelMap;

	}

	@RequestMapping(value = "/addproductcategorys", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addProductCategorys(@RequestBody List<ProductCategory> productCategoryList,
			HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Shop shop = (Shop) request.getSession().getAttribute("currentShop");
		long shopId;
		if (shop != null && shop.getShopId() >= 0) {
			shopId = shop.getShopId();
		} else {
			shopId = 1L;
		}
		for (ProductCategory pc : productCategoryList) {
			pc.setShopId(shop.getShopId());
			pc.setCreateTime(new Date());
		}
		if (productCategoryList != null && productCategoryList.size() > 0) {
			try {
				ProductCategoryExecution pe = productCategoryService.insertProduceCategory(productCategoryList);
				if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					System.out.println(pe.getState());
					modelMap.put("errMsg", pe.getStateInfo());
				}
			} catch (Exception e) {
				// TODO: handle exception
				modelMap.put("success", true);
				modelMap.put("errMsg", e.toString());
				System.out.println(e.getMessage());
				return modelMap;
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请至少输入一个商品类别");
		}
		return modelMap;
	}

	@RequestMapping(value = "/removeproductcategory", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> removeProductCategory(Long productCategoryId, HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (productCategoryId != null && productCategoryId > 0) {
			Shop shop = (Shop) request.getSession().getAttribute("currentShop");
			try {
				ProductCategoryExecution pe = productCategoryService.deleteProductCategory(productCategoryId, shop.getShopId());
				if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					System.out.println(pe.getState());
				}
			} catch (Exception e) {
				// TODO: handle exception
				modelMap.put("success", true);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "未獲取商品類別Id");
		}
		return modelMap;
	}
}
