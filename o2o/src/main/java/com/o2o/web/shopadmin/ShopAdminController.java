package com.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="shopadmin",method=RequestMethod.GET)
public class ShopAdminController {
	@RequestMapping(value="/shopoperation")
	public String ShopOperation() {
		return "shop/shopreg";
	}
	
	@RequestMapping(value="/shoplist")
	public String ShopList() {
		return "shop/shoplist";
	}
	
	@RequestMapping(value="/shopmanagement")
	public String ShopManagement() {
		return "shop/shopmanagement";
	}
	
	
	@RequestMapping(value="/productcategorymanagement")
	public String ProductCategoryManagement() {
		return "shop/productcategorymanagement";
	}
	
	@RequestMapping(value="/productoperation")
	public String AddProductgoryManagement() {
		return "shop/productoperation";
	}
	@RequestMapping(value="/productmanagement")
	public String productManagement() {
		return "shop/productmanagement";
	}
}
