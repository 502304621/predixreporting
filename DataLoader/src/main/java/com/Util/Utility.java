package com.Util;

import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.apache.commons.lang.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utility {
	final static String SPACE_CHARACTER = " ";
	final static String SCHEMA_NAME = ".";
	final static String COMMA = ",";
	public final static String SQLQuery = "select row_number() over() as rownum, CSA_CUSTOMER_DTLS.customer_name, CSA_CUSTOMER_DTLS.engine_model,"
			+ " CSA_CUSTOMER_DTLS.customer_Region, CSA_CUSTOMER_DTLS.true_cost, CSA_CUSTOMER_DTLS.last_shop_visit,  CSA_CUSTOMER_DTLS.year from POC.CSA_CUSTOMER_DTLS WHERE 1=1";

	public final static String SQL_POST_DB_SQL_QRY = "INSERT INTO POST_DB_SQL_QRY (POST_DB_QRY_OBJ_NM, POST_DB_QUERY_STRNG)"
			+ " VALUES (?,?) 	";
	// final static String GET_CUSTOMER_INFO = "SELECT csa_customer_dtls.YEAR,
	// csa_customer_dtls.TRUE_COST FROM csa_customer_dtls WHERE 1=1;";

	/*public static void main1(String args[]) {
		String dataLoaderString = "";
		String SQLQuery = "select csa_customer_dtls.year, csa_customer_dtls.customer_name from poc.csa_customer_dtls where 1=1";
		String nextString = "";
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\502304621\\sampleJSON.txt"))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				dataLoaderString = sCurrentLine;
				System.out.println("dataLoaderString" + dataLoaderString);
			}

			getColumnDataTypeFinal(dataLoaderString, SQLQuery);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}*/

	public static HashMap<String, HashMap<String, String>> getColumnDataTypeFinal(String dataLoaderString,
			String SQLQuery) {
		try {
			System.out.println("dataLoaderString " + dataLoaderString);
			System.out.println("SQLQuery " + SQLQuery);
			// System.out.println("dataLoaderString" + dataLoaderString);
			JSONObject obj1 = new JSONObject(dataLoaderString);
			HashMap<String, String> tableMap = new HashMap<String, String>();
			HashMap<String, HashMap<String, String>> dataTypeMap = new HashMap<String, HashMap<String, String>>();
			Iterator irr = obj1.keys();
			ArrayList list = getTablebNameSunday(SQLQuery);
			while (irr.hasNext()) {
				String irrString = irr.next().toString();
				// System.out.println(irrString);
				JSONObject obj2 = new JSONObject(obj1.getString(irrString));
				Iterator irr2 = obj2.keys();
				//// tableMap = new HashMap<String, String>();
				if (!irrString.equalsIgnoreCase("ObjectDetails"))

				{
					while (irr2.hasNext()) {

						JSONObject objtemp = new JSONObject(obj2.getString(irr2.next().toString()));
						Iterator irrtemp = objtemp.keys();
						while (irrtemp.hasNext()) {
							String tempTableName = irrtemp.next().toString();
							for (int i = 0; i < list.size(); i++) {
								HashMap<String, String> tempMap = (HashMap<String, String>) list.get(i);
								if (tempMap.containsKey(tempTableName.toUpperCase())) {
									JSONObject obj3 = new JSONObject(objtemp.getString(tempTableName));
									Iterator irr3 = obj3.keys();
									while (irr3.hasNext()) {
										String columnName = irr3.next().toString();
										String MapColumn = tempMap.get(tempTableName.toUpperCase());
										// System.out.println("columnName "+
										// columnName);
										String dataType = obj3.getString(columnName);
										// System.out.println( "data " +
										// dataType);
										/*
										 * System.out.println("MapColumn " +
										 * MapColumn);
										 * System.out.println("columnName " +
										 * columnName);
										 * System.out.println("columnName " +
										 * columnName);
										 */
										/*System.out.println("MapColumn " + MapColumn + " columnName " + columnName
												+ " dataType " + dataType);*/
										if (MapColumn.equalsIgnoreCase(columnName)) {
											System.out.println("MapColumn " + MapColumn + " dataType " + dataType);
											tableMap.put(columnName, dataType);
										}

									}
									dataTypeMap.put(tempTableName, tableMap);
								}

							}

						}
						// String tableName = irr2.next().toString();
						for (int i = 0; i < list.size(); i++) {
							/*
							 * HashMap<String,String > tempMap =
							 * (HashMap<String, String>) list.get(i);
							 * ////System.out.println("i  " + i +" tableName " +
							 * tableName);
							 * if(tempMap.containsKey(tableName.toUpperCase()))
							 * {
							 * 
							 * 
							 * 
							 * // System.out.println(tableName); //
							 * if(tableName.equalsIgnoreCase("csa_customer_dtls"
							 * )) // { JSONObject obj3 = new
							 * JSONObject(obj2.getString(tableName)); Iterator
							 * irr3 = obj3.keys(); while (irr3.hasNext()) {
							 * String columnName = irr3.next().toString();
							 * String MapColumn =
							 * tempMap.get(tableName.toUpperCase()); //
							 * System.out.println("columnName "+ columnName);
							 * String dataType = obj3.getString(columnName); //
							 * System.out.println( "data " + dataType);
							 * System.out.println("MapColumn " + MapColumn);
							 * System.out.println("columnName " + columnName);
							 * System.out.println("columnName " + columnName);
							 * System.out.println("MapColumn " + MapColumn +
							 * " columnName " + columnName + " dataType " +
							 * dataType);
							 * if(MapColumn.equalsIgnoreCase(columnName)) {
							 * System.out.println("MapColumn " + MapColumn +
							 * " dataType " + dataType);
							 * tableMap.put(columnName, dataType); }
							 * 
							 * } /// obj3.getString(arg0); ///
							 * System.out.println(obj3.toString());
							 * 
							 * /// }
							 * 
							 * dataTypeMap.put(tableName, tableMap); } else
							 * System.out.println("List Does not contain ");
							 */}
					}
				}
			}

			/*
			 * HashMap<String,HashMap<String, String>> mapOutput = dataTypeMap;
			 * Set set1 = mapOutput.keySet(); Iterator irr1 = set1.iterator();
			 * 
			 * while (irr1.hasNext()) { /// System.out.println(irr1.next());
			 * HashMap map1 =mapOutput.get(irr1.next()); //HashMap map1 =
			 * (HashMap) irr1.next(); Set set2 = map1.keySet(); Iterator irr2 =
			 * set2.iterator(); while (irr2.hasNext()) { String key =
			 * irr2.next().toString(); String value = map1.get(key).toString();
			 * System.out.println("Key " + key + "Values  " + value); } }
			 */

			/*
			 * JSONObject obj1 = new JSONObject(nextString); JSONObject obj2 =
			 * new JSONObject(obj1.getString("public")); JSONObject obj3 = new
			 * JSONObject(obj2.getString("sb_user")); String name =
			 * obj3.getString("created_dt"); System.out.println(name);
			 */
			return dataTypeMap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static ArrayList<HashMap<String, String>> getTablebNameSunday(String SQLQuery) {
		ArrayList<HashMap<String, String>> tableList = new ArrayList<HashMap<String, String>>();
		try {
			String tableString = StringUtils.substringBetween(SQLQuery.toUpperCase(), "FROM", "WHERE");
			
			System.out.println("Table String in GET Table " + tableString);
			
			String columnString = StringUtils.substringBetween(SQLQuery.toUpperCase(), "SELECT", "FROM");
			
			System.out.println("Column String in GET Table " + columnString);
			String[] tableArray = tableString.split(",");
			String[] columnArray = columnString.split(",");
			HashMap<String, String> mapColumns = new HashMap<String, String>();
			if (columnArray.length > 0) {

				for (int i = 0; i < columnArray.length; i++) {

					if (!columnArray[i].toString().equalsIgnoreCase("row_number() over() as rownum")) {
						mapColumns = new HashMap<String, String>();

						String[] splitColumn = columnArray[i].trim().replace(SCHEMA_NAME, SPACE_CHARACTER)
								.split(SPACE_CHARACTER);
						
						 System.out.println(" splitColumn[0] " +
						 splitColumn[0]);
						 System.out.println(" splitColumn[1] " +
						 splitColumn[1]);
						
						mapColumns.put(splitColumn[0], splitColumn[1]);
						tableList.add(mapColumns);
					}
				}
			}

			/*
			 * if (tableArray.length > 0) {
			 * 
			 * for (int i = 0; i < tableArray.length; i++) { String[]
			 * tableNameArray = tableArray[i].split(SPACE_CHARACTER); String
			 * tableName = tableNameArray[1];
			 * 
			 * if (tableName.contains(SCHEMA_NAME)) { tableNameArray =
			 * tableName.replace(SCHEMA_NAME,
			 * SPACE_CHARACTER).split(SPACE_CHARACTER); tableName =
			 * tableNameArray[1]; } tableList.add(tableName); } } else
			 * tableList.add(tableString.trim());
			 */

			return tableList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
