package com.mybiletix.enumaration;

public enum UserType {
	
	CUSTOMER(1),
	ADMIN(2),
	ORGINIZER(3);

	private int value;

	private UserType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
}
