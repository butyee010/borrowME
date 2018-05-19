package com.borrow.blockchain.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.borrow.blockchain.entity.PropertyConfig;
import com.borrow.blockchain.repo.PropertyConfigRepository;
import com.borrow.blockchain.utils.CommonUtil;


@Component
public class PropertyConfigHelper {
	private static ConcurrentMap<String, String[]> configMap = null;

	private static boolean isCheckInstance = false;

	private static String instanceName = "hackathon";

	private static PropertyConfigRepository repo;
	
	@Autowired
	public void setPropertyConfigRepository(PropertyConfigRepository propertyConfigRepository) {
		PropertyConfigHelper.repo = propertyConfigRepository;
	}

	@PostConstruct
	public static void init() {

		if (instanceName != null && !"".equalsIgnoreCase(instanceName.trim())) {
			isCheckInstance = true;
		}

		List<PropertyConfig> propertyConfigList = repo.getPropertyConfig();

		if (!CommonUtil.checkListIsNull(propertyConfigList)) {
			configMap = propertyConfigList.stream().collect(Collectors.toConcurrentMap(PropertyConfig::getPrcfConfigKey,
					config -> getListFromFix(config.getPrcfConfigVal()), (oldVal, newVal) -> oldVal));
		}

	}

	private static String[] getListFromFix(String fix) {

		if (CommonUtil.checkStringIsNull(fix))
			return new String[] { "" };

		if (fix.startsWith("[") && fix.endsWith("]")) {
			fix = new String(fix.substring(1, fix.length() - 1));

			String tmpList[] = fix.split(",");
			List<String> respList = new ArrayList<String>();
			for (String tmp : tmpList) {
				if (CommonUtil.checkStringIsNull(tmp))
					continue;
				tmp = CommonUtil.ltrim(tmp);
				tmp = CommonUtil.rtrim(tmp);

				respList.add(tmp);
			}

			return respList.toArray(new String[respList.size()]);
		} else {
			return new String[] { fix };
		}
	}

	public static String getConfigValue(String key) {

		if (configMap == null)
			init();

		if (isCheckInstance) {
			String instanceKey = instanceName + "." + key;

			if (configMap.containsKey(instanceKey)) {
				String[] _valList = configMap.get(instanceKey);
				if (_valList == null || _valList.length == 0) {
					return getConfigNotInstants(key);
				}
				return _valList[0];
			}
		}

		if (configMap.containsKey(key)) {
			String[] _valList = configMap.get(key);
			if (_valList == null || _valList.length == 0) {
				return null;
			}
			return _valList[0];
		}

		return null;
	}

	private static String getConfigNotInstants(String key) {
		if (configMap.containsKey(key)) {
			String[] _valList = configMap.get(key);
			if (_valList == null || _valList.length == 0) {
				return null;
			}
			return _valList[0];
		}
		return null;
	}

	public static String[] getConfigValueList(String key) {

		if (configMap == null)
			init();

		if (isCheckInstance) {
			String instanceKey = instanceName + "." + key;
			if (configMap.containsKey(instanceKey)) {
				String[] _valList = configMap.get(instanceKey);
				if (_valList == null || _valList.length == 0) {
					return getConfigNotInstantList(key);
				}
				return _valList;
			}
		}

		if (configMap.containsKey(key)) {
			return configMap.get(key);
		}

		return null;
	}

	private static String[] getConfigNotInstantList(String key) {
		if (configMap.containsKey(key)) {
			String[] _valList = configMap.get(key);
			if (_valList == null || _valList.length == 0) {
				return null;
			}
			return _valList;
		}
		return null;
	}

	public static synchronized void clear() {
		configMap = null;
	}

	public static void setConfigValue(String key, String[] value) {
		if (null == configMap) {
			init();
		}
		configMap.remove(key);
		configMap.put(key, value);
	}

	public static Map<String, String[]> getAllConfig() {
		return new HashMap<>(configMap);
	}

	public static void deleteConfig(String key) {
		configMap.remove(key);
	}
}
