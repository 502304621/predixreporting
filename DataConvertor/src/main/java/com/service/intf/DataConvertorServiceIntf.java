package com.service.intf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;

import com.model.DrillDownModel;

public interface DataConvertorServiceIntf {

	 List<HashMap> aggregateBarChartData(String jsonString, String XAxisVariable,
			String YAxisVariable);
		public ArrayList<DrillDownModel> getDrillDownJSON(String jsonInString, String jsonXaxis,
				String jsonYaxis,  String drilldown)throws JSONException;
}
