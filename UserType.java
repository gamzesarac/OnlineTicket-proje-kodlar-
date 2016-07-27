package com.mybiletix.enumaration;

/**
 * All user Types are defined
 */
public enum UserType {

	CUSTOMER(1), ADMIN(2), ORGINIZER(3);

	private int value;

	private UserType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static String findNameByValue(Integer type) {
		if (type == null) {
			return null;
		}
		for (UserType userType : values()) {
			if (userType.value == type) {
				return userType.name();
			}
		}
		return null;
	}

}
