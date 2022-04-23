package com.khoaluantotnghiep.entity;

import java.util.Map;

public class ReportColumn {
	private int Value;
	private String time;
	private Map<Integer, Integer> mapValue;

	public int getValue() {
		return Value;
	}

	public void setMapValue(Map<Integer, Integer> value) {
		this.mapValue = value;
	}
	public Map<Integer, Integer> getMapValue() {
		return mapValue;
	}

	public void setValue(int value) {
		Value = value;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}