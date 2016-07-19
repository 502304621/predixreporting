package com.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class DrillDownModel {

	String names;
	String id;
	ArrayList<ArrayList<Object>> data=new ArrayList<ArrayList<Object>>();

	
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ArrayList<ArrayList<Object>> getData() {
		return data;
	}
	public void setAl(ArrayList<ArrayList<Object>> data) {
		this.data = data;
	}
	
}
