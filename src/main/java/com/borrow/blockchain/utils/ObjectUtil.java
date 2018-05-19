package com.borrow.blockchain.utils;

import java.util.List;

public class ObjectUtil {
	
	public static Object[] convertListObjectToArray(List<Object> listObject) {
		if (listObject != null) {
			Object[] objParams = new Object[listObject.size()];
			return listObject.toArray(objParams);
		}
		else {
			return null;
		}
	}
	
}
