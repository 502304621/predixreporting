package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScattredPlot {
	private List<String> catagoryList;
	private Map<String, List<List<Double>>> itemValueMap;

	
	public List<String> getCatagoryList() {
		if(catagoryList == null){
			catagoryList = new ArrayList<String>();
		}
		return catagoryList;
	}

	public void setCatagoryList(List<String> catagoryList) {
		this.catagoryList = catagoryList;
	}

	public Map<String, List<List<Double>>> getItemValueMap() {
		if(itemValueMap == null){
			itemValueMap = new HashMap<String, List<List<Double>>>();
		}
		return itemValueMap;
	}

	public void setItemValueMap(Map<String, List<List<Double>>> itemValueMap) {
		this.itemValueMap = itemValueMap;
	}
}
