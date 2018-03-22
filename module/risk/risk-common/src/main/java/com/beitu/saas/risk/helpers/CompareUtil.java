package com.beitu.saas.risk.helpers;

import org.apache.commons.lang3.StringUtils;

public class CompareUtil {

	public static boolean equals(String left, String right) {
		if (StringUtils.isBlank(left)) {
			return StringUtils.isBlank(right);
		} else {
			return left.equalsIgnoreCase(right);
		}
	}

	public static boolean equals(float left, float right) {
		return Float.compare(left, right) == 0;
	}

	public static boolean equals(Float left, Float right) {
		if (left == null) {
			return right == null;
		}

		if (right == null)
			return false;

		return left.compareTo(right) == 0;
	}

}
