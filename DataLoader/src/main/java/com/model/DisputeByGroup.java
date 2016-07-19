package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisputeByGroup {
	private List<String> catagoryList;
	private Map<String, List<Integer>> itemValueMap;

	
	public List<String> getCatagoryList() {
		if(catagoryList == null){
			catagoryList = new ArrayList<String>();
		}
		return catagoryList;
	}

	public void setCatagoryList(List<String> catagoryList) {
		this.catagoryList = catagoryList;
	}

	public Map<String, List<Integer>> getItemValueMap() {
		if(itemValueMap == null){
			itemValueMap = new HashMap<String, List<Integer>>();
		}
		return itemValueMap;
	}

	public void setItemValueMap(Map<String, List<Integer>> itemValueMap) {
		this.itemValueMap = itemValueMap;
	}
}
