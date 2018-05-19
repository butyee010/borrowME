package com.borrow.blockchain.config;

import com.borrow.blockchain.helper.PropertyConfigHelper;

public class ConfigConstants {

	public static String getAuthor() {
		return PropertyConfigHelper.getConfigValue("AUTHOR");
	}

}
