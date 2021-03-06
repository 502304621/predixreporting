package com.service.impl;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.DataConvertorModel;
import com.model.DrillDownModel;
import com.service.intf.DataConvertorServiceIntf;

public class DataConvertorServiceImpl implements DataConvertorServiceIntf {

	
	
	public List<HashMap> aggregateBarChartData(String jsonString, String XAxisVariable,
			String YAxisVariable) {
		try {
			try {
				/// System.out.println(jsonString);
				//// List<HashMap> outputMap = new ArrayList();
				// System.out.println(jsonString);
			///	System.out.println(jsonString);
				JSONArray parentArray = new JSONArray(jsonString);
				JSONObject parentObjects = parentArray.getJSONObject(0);
/*				System.out.println("Parent Array " + 
						parentObjects.toString());*/
				parentArray = new JSONArray("["+parentObjects.toString()+"]");
				
				JSONObject obj = new JSONObject(jsonString.substring(1,jsonString.length()-1));
			 //   JSONArray paramsArr = obj.getJSONArray("ObjectName");


			    JSONObject param1 = obj.getJSONObject(/*"ObjectName"*/"d8a2439d7a719459cb3acaeace2f6d93f_ue618148f48394ed3aaa1d238496f70f9");
			   System.out.println("param1     "+ param1.toString());
			    
				ObjectMapper mapperObj = new ObjectMapper();
				HashMap<String, String> map = new HashMap<String, String>();
				HashMap<String, Object> totalMap = new HashMap<>();
				double sumYAxis = 0.0;
				DecimalFormat df = new DecimalFormat("0.00");
				String xAxisVar = "";
				ArrayList<HashMap> lstOfMap = new ArrayList<HashMap>();
				System.out.println(
						"Start For Loop inside aggregate method " + new Timestamp(new java.util.Date().getTime()));
				parentArray = new JSONArray("["+ param1.toString() + "]");
				
			/*	parentObjects = parentArray.getJSONObject(0);
				parentArray=new JSONArray("["+ parentObjects.toString() + "]");*/
			System.out.println("parentArray.length() " + parentArray.length());
				Iterator itrr = param1.keys();
		 	    
		 	    while (itrr.hasNext()) {
					
					//// JSONObject obj = new JSONObject(jsonString.substring(1,
					//// jsonString.length() - 1));

					//System.out.println("Inside Parent Array " + parentObjects.toString());


					String keys= itrr.next().toString();
		 	    	
		 	    	String stringJSON = param1.get(keys).toString();
		 	    	System.out.println("stringJSON " + stringJSON);
					/*
					 * JSONObject obj1 = new
					 * JSONObject(parentArray.getString(Integer.toString(m +
					 * 1))); xAxisVar = obj1.getString(XAxisVariable);
					 */

					// JSONObject obj1 = new
					// JSONObject(parentArray.getString(Integer.toString(m +
					// 1)));
					xAxisVar = new JSONObject(stringJSON).getString(XAxisVariable);
					if (totalMap.containsKey(xAxisVar)) {
						sumYAxis = new Double(totalMap.get(xAxisVar).toString()).doubleValue();
						sumYAxis = sumYAxis + new Double(
								new JSONObject(stringJSON).getString(YAxisVariable))
										.doubleValue();
						sumYAxis = new Double(df.format(sumYAxis)).doubleValue();
						totalMap.put(xAxisVar, sumYAxis);
						 System.out.println("Match Found " + " " + xAxisVar +
						" " + sumYAxis);
					} else {
					System.out.println( "YAxisVariable   " + YAxisVariable );
						sumYAxis = new Double(
								new JSONObject(stringJSON).getString(YAxisVariable))
										.doubleValue();
						sumYAxis = new Double(df.format(sumYAxis)).doubleValue();

						totalMap.put(xAxisVar, sumYAxis);
						/// totalMap.put(XAxisVariable, xAxisVar);
						 System.out.println("Match Not Found " + " " +
						 xAxisVar + " " + sumYAxis);
					}
				}
				System.out.println(
						"End For Loop inside aggregate method " + new Timestamp(new java.util.Date().getTime()));
				for (Map.Entry<String, Object> entry1 : totalMap.entrySet()) {
					HashMap<String, Object> tempMap = new HashMap<String, Object>();
					tempMap.put("name", entry1.getKey());
					tempMap.put("y", entry1.getValue());
					tempMap.put("drilldown", entry1.getKey());
					lstOfMap.add(tempMap);

				///	System.out.println("entry1 = " + entry1.getKey() + ", Value = " + entry1.getValue());
				}
				String jsonResp = mapperObj.writeValueAsString(lstOfMap);
				/////System.out.println(jsonResp);
				/// outputMap.add(totalMap);

				return lstOfMap;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	public ArrayList<DrillDownModel> getDrillDownJSON(String jsonInString, String jsonXaxis,
			String jsonYaxis,  String drilldown)
			throws JSONException {

		// JSONParser parser = new JSONParser();
		DrillDownModel graphDataObject;
		Map<String, Map<String, Float>> hm2 = new HashMap<String, Map<String, Float>>();
		ArrayList<DrillDownModel> listOfCustomersWithDrillDown = new ArrayList<DrillDownModel>();
		///String s = "[{\"1\":{\"customerId\":0,\"customerName\":\"Luftansa\",\"customerRegion\":\"CA\",\"engineModel\":\"CF34\",\"shopVistDate\":null,\"trueCost\":70.0,\"year\":2010}},{\"2\":{\"customerId\":0,\"customerName\":\"Luftansa\",\"customerRegion\":\"CA\",\"engineModel\":\"CF34\",\"shopVistDate\":null,\"trueCost\":75.0,\"year\":2011}},{\"3\":{\"customerId\":0,\"customerName\":\"Air China\",\"customerRegion\":\"CB\",\"engineModel\":\"GE90\",\"shopVistDate\":null,\"trueCost\":90.0,\"year\":2010}},{\"4\":{\"customerId\":0,\"customerName\":\"Air China\",\"customerRegion\":\"CA\",\"engineModel\":\"GE90\",\"shopVistDate\":null,\"trueCost\":190.0,\"year\":2010}},{\"5\":{\"customerId\":0,\"customerName\":\"Luftansa\",\"customerRegion\":\"CB\",\"engineModel\":\"CF34\",\"shopVistDate\":null,\"trueCost\":70.0,\"year\":2010}}]";
	//	System.out.println(jsonInString);
		int index = 0;		
		
		// Object obj = parser.parse(s);
		
		JSONArray parentArray = new JSONArray(jsonInString);
		JSONObject parentObjects = parentArray.getJSONObject(0);
	/*	System.out.println("Parent Array " + 
				parentObjects.toString());*/
		parentArray = new JSONArray("["+parentObjects.toString()+"]");
		
		JSONObject obj = new JSONObject(jsonInString.substring(1,jsonInString.length()-1));
	 //   JSONArray paramsArr = obj.getJSONArray("ObjectName");


	    JSONObject param1 = obj.getJSONObject(/*"ObjectName"*/"d8a2439d7a719459cb3acaeace2f6d93f_ue618148f48394ed3aaa1d238496f70f9");
	 ///   System.out.println("param1     "+ param1.toString());
		
		JSONArray array = new JSONArray("[" + param1.toString() +"]");
		 Iterator itrr = param1.keys();
	////	JSONObject obj2 = (JSONObject) array.get(1);

		Map<String, Float> hm = null;
		Map<String, Float> temp;

		JSONObject obj4;
		JSONObject obj5;

		String attributeField = drilldown;
		float cost, test1, test2;
		String customerName;
		ArrayList<Object> al = null;
		String graphXAxis = null, graphYAxis = null, graphDrillDown = null;
		boolean hasNext = false;
 	    while (itrr.hasNext()) {
 	    	
 	    	hasNext=true;
 	    	String keys= itrr.next().toString();
 	    	
 	    	String value = param1.get(keys).toString();

			obj4 = new JSONObject(value);
		 	if(hasNext)
 	    	{
			//keys= itrr.next().toString();
 	    	
 	    	///value = param1.get(keys).toString();
			obj5 =  new JSONObject(value);

			graphXAxis = obj5.get(jsonXaxis).toString();
			graphYAxis = obj5.get(jsonYaxis).toString();
			graphDrillDown = obj5.get(attributeField).toString();

			temp = hm2.get(graphXAxis);

			if (hm2.get(graphXAxis) != null) {
				hm = temp;
				hm2.put(graphXAxis, hm);
			} else {

				temp = new HashMap<String, Float>();
				hm = temp;
				hm2.put(graphXAxis, hm);
			}

			test1 = Float.parseFloat(graphYAxis);

			if (hm.get(graphDrillDown) != null) {
				test2 = hm.get(graphDrillDown);
				hm.put(graphDrillDown, test1 + test2);
			} else
				hm.put(graphDrillDown, test1);

			index++;
 	    }
		}

		Iterator iterator = hm2.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry pair = (Map.Entry) iterator.next();
			graphDataObject = new DrillDownModel();

			graphDataObject.setId(pair.getKey().toString());
			graphDataObject.setNames((String) pair.getKey());

			HashMap<String, Float> toIterate;
			toIterate = (HashMap<String, Float>) pair.getValue();
			Iterator iterator2 = toIterate.entrySet().iterator();

			while (iterator2.hasNext()) {
				Map.Entry pair2 = (Map.Entry) iterator2.next();
				al = new ArrayList<Object>();
				al.add(pair2.getKey());
				al.add(pair2.getValue());
				graphDataObject.getData().add(al);
			}
			listOfCustomersWithDrillDown.add(graphDataObject);
		}

		return listOfCustomersWithDrillDown;
	}

}
