package com.focus.levelup.model;

import java.util.HashMap;
import java.util.Map;

public enum Statuses {
	INACTIVE(0),
	ACTIVE(1),
	UNCHECKED(2),
	REVIEWED(3);

	private int value;
	private static Map<Integer, Statuses> map = new HashMap<>();

	private Statuses(int value) {
		this.value = value;
	}

	// Mapping statuses
	static {
		for (Statuses status : Statuses.values() ) {
			map.put(status.value, status);
		}
	}

	// Getting value of status passed on
	public static Statuses valueOf(int status) {
		return (Statuses) map.get(status);
	}

	public int getValue() {
		return value;
	}
}