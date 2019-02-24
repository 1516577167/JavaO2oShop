package com.o2o.util;

public class PathUtil {
//	获取分隔符
	private static String seperator = System.getProperty("file.separator");
	public static String getImgBasePath() {
//		获取操作系统名字
		String os = System.getProperty("os.name");
		String basePath = "";
		if(os.toLowerCase().startsWith("win")) {
			basePath = "D://projiectdev/image/";
		}else {
			basePath = "/home/xiangbin/image/";
		}
//		可以保证路径有效
		basePath = basePath.replace("/", seperator);
		return basePath;
	}
	
	public static String getShopImagePath(long shopId) {
		String imagePath = "/upload/item/shop/" + shopId + "/";
		return imagePath.replace("/", seperator);
	} 
}
