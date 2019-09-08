package com.jlink.util;

public class PathUtil {

	//判断当前系统用的是斜杠还是反斜杠ls
	private static String seperator = System.getProperty("file.separator");
	
	public static String getImgBasePath(){
		String oString = System.getProperty("os.name");
		String basePath = "";
		if(oString.toLowerCase().startsWith("win")){
			basePath = "D:/projectdev/image";
		}else if (oString.toLowerCase().startsWith("mac")) {
			basePath = "/Users/binzhang/Documents/shopImages";
		}else{
			basePath = "/root/shopImages";
		}
		basePath = basePath.replace("/", seperator);
		return basePath;
	}
	
	public static String getShopImagePath(long shopId){
		String imagePath = "/upload/item/shop/" + shopId + "/";
		return imagePath.replace("/", seperator);
	}
	
}

