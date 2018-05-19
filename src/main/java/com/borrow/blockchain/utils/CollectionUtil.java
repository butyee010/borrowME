package com.borrow.blockchain.utils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectionUtil {

	public static Boolean isEmpty(List<?> list) {
		return list.isEmpty();
	}

	public static Boolean isNotEmpty(List<?> list) {
		return !list.isEmpty();
	}

	public static <K extends Comparable<? super K>, V> Map<K, V> sortByKey(Map<K, V> map) {
		map = map.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey,
				Map.Entry::getValue, (oldValue, newValue) -> newValue, LinkedHashMap::new));
		return map;
	}
}
