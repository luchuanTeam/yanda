package com.yanda.util;

public class SortUtil {

	public static final String mvSort(String sort, String order) {
		if (sort == null) {
			return null;
		}
		String sortWithOrder = "";
		if (sort.equalsIgnoreCase("name")) {
			sortWithOrder = "mv_name";
		} else if (sort.equalsIgnoreCase("default") || sort.equalsIgnoreCase("time")) {
			return "update_time";
		} else if (sort.equalsIgnoreCase("classify")) {
			sortWithOrder = "classify_id";
		}
		if (order == null) {
			return sortWithOrder;
		}

		if (order.equalsIgnoreCase("asc")) {
			sortWithOrder += " ASC";
		} else if (order.equalsIgnoreCase("desc")) {
			sortWithOrder += " DESC";
		}

		return sortWithOrder;
	}
}
