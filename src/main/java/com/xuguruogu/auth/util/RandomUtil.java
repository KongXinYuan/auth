package com.xuguruogu.auth.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RandomUtil {

	/**
	 * 获取随机字母数字组合
	 *
	 * @param length
	 *            字符串长度
	 * @return
	 */
	public static String getRandomCharAndNumr(Integer length) {
		String str = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {

			// 0-9数字
			// 10-35大写字母
			// 36-61小写字母
			int value = random.nextInt(62);
			if (value <= 9) {
				str += String.valueOf(value);
			} else if (value <= 35) {
				str += (char) (65 + value - 10);
			} else {
				str += (char) (97 + value - 36);
			}
		}
		return str;
	}

	/**
	 * 验证随机字母数字组合是否纯数字与纯字母
	 *
	 * @param str
	 * @return true 是 ， false 否
	 */
	public static boolean isRandomUsable(String str) {
		// String regExp =
		// "^[A-Za-z]+(([0-9]+[A-Za-z0-9]+)|([A-Za-z0-9]+[0-9]+))|[0-9]+(([A-Za-z]+[A-Za-z0-9]+)|([A-Za-z0-9]+[A-Za-z]+))$";
		String regExp = "^([0-9]+)|([A-Za-z]+)$";
		Pattern pat = Pattern.compile(regExp);
		Matcher mat = pat.matcher(str);
		return mat.matches();
	}
}
