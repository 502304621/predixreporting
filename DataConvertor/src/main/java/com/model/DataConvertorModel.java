package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataConvertorModel {

	List<HashMap> mainJSON = new ArrayList<HashMap>();
	ArrayList<DrillDownModel>  drillDownModel = 
			new ArrayList<DrillDownModel> ();
	public List<HashMap> getMainJSON() {
		return mainJSON;
	}
	public void setMainJSON(List<HashMap> mainJSON) {
		this.mainJSON = mainJSON;
	}
	public ArrayList<DrillDownModel> getDrillDownModel() {
		return drillDownModel;
	}
	public void setDrillDownModel(ArrayList<DrillDownModel> drillDownModel) {
		this.drillDownModel = drillDownModel;
	}
	
	

}
