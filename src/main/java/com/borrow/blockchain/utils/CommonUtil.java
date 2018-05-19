package com.borrow.blockchain.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class CommonUtil {
	private final static java.util.regex.Pattern LTRIM = java.util.regex.Pattern.compile("^\\s+");
	private final static java.util.regex.Pattern RTRIM = java.util.regex.Pattern.compile("\\s+$");

	public static boolean checkStringIsNull(String token) {
		return null == token || "".equalsIgnoreCase(token);
	}

	public static String replaceParamInMessage(String message, Object[] params) {

		String matchExp = null;

		if (params != null && params.length > 0) {

			for (int i = 0; i < params.length; i++) {
				matchExp = "{" + i + "}";
				if (params[i] != null) {
					message = StringUtils.replace(message, matchExp, params[i].toString());
				}
			}
		}
		return message;
	}

	public static String padding(String inputStr, int maxLength) {

		String outputStr = "";
		if (inputStr == null) {

			return outputStr;
		}

		outputStr = paddingZero(maxLength - inputStr.length()) + inputStr;
		return outputStr;
	}

	public static String paddingZero(int lenPadding) {

		String outputZero = "";
		for (int i = 0; i < lenPadding; i++) {
			outputZero += "0";
		}

		return outputZero;
	}

	public static String ltrim(String s) {
		return LTRIM.matcher(s).replaceAll("");
	}

	public static String rtrim(String s) {
		return RTRIM.matcher(s).replaceAll("");
	}

	public static boolean checkListIsNull(List<?> lists) {
		return lists == null || lists.isEmpty();
	}

	public static boolean isOnlyOneValue(String... values) {
		boolean result = true;
		if (values != null && values.length > 0) {
			int count = 0;
			for (String value : values) {
				if (!checkStringIsNull(value)) {
					count++;
					if (count > 1) {
						break;
					}
				}
			}

			if (count != 1) {
				result = false;
			}
		} else {
			result = false;
		}
		return result;
	}

	public static String addSeparatorEndPath(String path) {

		if (StringUtils.isBlank(path))
			return path;

		String lastIndex = StringUtils.substring(path, path.length() - 1, path.length());

		if (!lastIndex.equals("/") && !lastIndex.equals("\\")) {
			path = path + "/";
		}

		return path;

	}

	public static String markupATMCard(String atmCard) {
		String markup = "";
		if (atmCard != null && atmCard.length() > 0) {
			for (int i = 0; i < atmCard.length(); i++) {
				if (i == 4 || i == 8 || i == 12) {
					markup += " ";
				}
				markup += atmCard.substring(i, i + 1);
			}
		}
		return markup;
	}

	public static int stringToInt(int defaultValue, String str) {
		int answer = defaultValue;

		if (StringUtils.isNotBlank(str) && StringUtils.isNumeric(str)) {
			answer = Integer.parseInt(str);
		}

		return answer;
	}

	public static Integer generateLuhnAlgorithm(String walletNoWithoutLuhn) throws Exception {
		Integer result = null;
		if (!checkStringIsNull(walletNoWithoutLuhn)) {
			String digit;
			/* convert to array of int for simplicity */
			int[] digits = new int[walletNoWithoutLuhn.length()];
			for (int i = 0; i < walletNoWithoutLuhn.length(); i++) {
				digits[i] = Character.getNumericValue(walletNoWithoutLuhn.charAt(i));
			}

			/* double every other starting from right - jumping from 2 in 2 */
			for (int i = digits.length - 1; i >= 0; i -= 2) {
				digits[i] += digits[i];

				/*
				 * taking the sum of digits grater than 10 - simple trick by substract 9
				 */
				if (digits[i] >= 10) {
					digits[i] = digits[i] - 9;
				}
			}
			int sum = 0;
			for (int i = 0; i < digits.length; i++) {
				sum += digits[i];
			}
			/* multiply by 9 step */
			sum = sum * 9;

			/* convert to string to be easier to take the last digit */
			digit = sum + "";
			result = Integer.valueOf(digit.substring(digit.length() - 1));
		}
		return result;
	}

	public static String genSpace(int length) {
		String result = "";
		return StringUtils.leftPad(result, length, " ");
	}

	public static <T extends Serializable> T deepClone(T object) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (T) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String nonNullString(String str) {
		return !checkStringIsNull(str) ? str : "";
	}

	public static boolean checkAllStringIsNull(String... tests) {
		boolean result = true;
		if (tests != null && tests.length > 0) {
			for (String test : tests) {
				result &= checkStringIsNull(test);
			}
		}
		return result;
	}

	public static String coalesce(String... ts) {
		return Arrays.asList(ts).stream().filter(t -> !checkStringIsNull(t)).findFirst().get();
	}

}
