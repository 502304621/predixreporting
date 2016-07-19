package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseMapper {

	List<HashMap<String,Object>> mapper = new ArrayList<HashMap<String,Object>>();

	public List<HashMap<String, Object>> getMapper() {
		return mapper;
	}

	public void setMapper(List<HashMap<String, Object>> mapper) {
		this.mapper = mapper;
	}

	
	
	
}
