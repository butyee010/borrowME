package com.borrow.blockchain.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {

	public static List<String> getListFromFix(String fix) {
		List<String> respList = new ArrayList<String>();

		if (StringUtils.isBlank(fix))
			return respList;

		if (fix.startsWith("[") && fix.endsWith("]")) {
			fix = new String(fix.substring(1, fix.length() - 1));

			String tmpList[] = fix.split(",");
			for (String tmp : tmpList) {
				if (StringUtils.isBlank(tmp))
					continue;

				tmp = tmp.trim();

				respList.add(tmp);
			}

			return respList;
		} else {
			respList.add(fix);
			return respList;
		}
	}

	public static String getPrefix(String text, int pos) {
		if (StringUtils.isNotBlank(text)) {
			text = text.substring(0, pos);
		}
		return text;
	}

	public static String getSuffix(String text, int pos) {
		if (StringUtils.isNotBlank(text)) {
			text = text.substring(text.length() - pos);
		}
		return text;
	}
	
	public static String padZero(int pos , String text) {
		return StringUtils.leftPad(text, pos, "0");
	}
}
