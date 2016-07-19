package com.service.intf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.model.JDBCTemplate;

public interface DataLoaderServiceIntf {
	public List<ArrayList> loadData(JDBCTemplate jdbcObject);
	
}
