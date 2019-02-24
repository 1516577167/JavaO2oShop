package com.o2o.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="frontend")
public class FrontendController {
	@RequestMapping(value="/index")
	public String ShopOperation() {
		return "frontend/index";
	}
	
	@RequestMapping(value="/shoplist")
	public String ShopList() {
		return "frontend/shoplist";
	}
	
	@RequestMapping(value="/shopdetail",method = RequestMethod.GET)
	public String ShopDetail() {
		return "frontend/shopdetail";
	}
	
	@RequestMapping(value="/productdetail",method = RequestMethod.GET)
	public String ProductDetail() {
		return "frontend/productdetail";
	}
}
