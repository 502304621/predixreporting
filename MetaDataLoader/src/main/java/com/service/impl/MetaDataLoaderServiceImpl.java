package com.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.constants.SQLQuery;
import com.model.ClientJdbcDaoSupport;
import com.model.DatabaseDefinition;
import com.service.intf.MetaDataLoaderServiceIntf;

import org.codehaus.jackson.*;
import redis.clients.jedis.*;

import argo.jdom.*;
import argo.jdom.JsonNode;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

@Repository
public class MetaDataLoaderServiceImpl implements MetaDataLoaderServiceIntf {

	@Autowired
	JdbcTemplate jdbcTemplate;

	/*
	 * getSchemaTabDetailsBck23 : Back up of the code as on May 23:
	 */

	public HashMap<String, HashMap<String, HashMap<String, String>>> getSchemaTabDetailsBck23(
			DatabaseDefinition databaseDefinition) {

		ClientJdbcDaoSupport dao = new ClientJdbcDaoSupport();
		JdbcTemplate jdbc;

		HashMap<String, HashMap<String, HashMap<String, String>>> schemaMap = new HashMap<String, HashMap<String, HashMap<String, String>>>();
		/*
		 * HashMap<String,ArrayList<String>> tableMap = new
		 * HashMap<String,ArrayList<String>>();
		 */

		/*
		 * HashMap<String, ArrayList<String>> tempMap= new HashMap<String,
		 * ArrayList<String>>();
		 */
		String tableName = "";
		try {
			jdbc = dao.getJdbcTemplate("jdbc:postgresql://" + databaseDefinition.getHost() + ":"
					+ databaseDefinition.getPort() + "/postgres", databaseDefinition.getUsername(),
					databaseDefinition.getPassword());

			String stringQuery = SQLQuery.GET_DETAILS_FOR_SCHEMA;

			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

			result = jdbc.queryForList(stringQuery);
			ArrayList<HashMap<String, String>> colList = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> colMap = new HashMap<String, String>();
			for (int i = 0; i < result.size(); i++) {

				Map<String, Object> tempObject = result.get(i);
				String schema = (String) tempObject.get("object_schema");
				HashMap<String, HashMap<String, String>> tempMap = new HashMap<String, HashMap<String, String>>();

				if (schemaMap.containsKey(schema)) {
					tempMap = schemaMap.get(schema);

					tableName = (String) tempObject.get("table_name");

					if (tempMap.get(tableName) == null) {
						tempMap.put(tableName, new HashMap<String, String>());
						colMap = new HashMap<String, String>();
					}
					// tempMap.get(tableName).add((String)
					// tempObject.get("column_name")+":"+
					// tempObject.get("data_type"));
					/// colMap.put(tempObject.get("column_name").toString(),
					// tempObject.get("data_type").toString());
					colMap.put(tempObject.get("column_name").toString(), tempObject.get("data_type").toString());
					colList.add(colMap);
					tempMap.put(tableName, colMap);
					schemaMap.put(schema, tempMap);

				} else {
					tableName = (String) tempObject.get("table_name");
					if (tempMap.get(tableName) == null) {
						tempMap.put(tableName, new HashMap<String, String>());
						colMap = new HashMap<String, String>();
					}
					colMap.put(tempObject.get("column_name").toString(), tempObject.get("data_type").toString());
					colList.add(colMap);
					tempMap.put(tableName, colMap);
					schemaMap.put(schema, tempMap);
					//// schemaMap.put(schema,tempMap);
				}

			}

			/*
			 * String strQry = ""; String tableName; List<Map<String, Object>>
			 * result = jdbcTemplate.queryForList(strQry); HashMap<String,
			 * List<String>> tableCols = new HashMap<String, List<String>>();
			 * for (Map<String, Object> row : result) { tableName = (String)
			 * row.get("table_name"); if (tableCols.get(tableName) == null) {
			 * tableCols.put(tableName, new ArrayList<String>()); }
			 * tableCols.get(tableName).add((String) row.get("column_name")); }
			 */
			return schemaMap;
		} catch (Exception e) {
			return null;
		}

	}

	/*
	 * May23: Added this code to get object details along with other details of
	 * the schma.
	 */
	public HashMap<String, Object> getSchemaTabDetails(DatabaseDefinition databaseDefinition) {

		System.out.println("Inside getSchemaTabDetails");
		ClientJdbcDaoSupport dao = new ClientJdbcDaoSupport();
		JdbcTemplate jdbc;

		/// HashMap<String, Object> schemaMap = new HashMap<String, Object>();
		HashMap<String, HashMap<String, HashMap<String, String>>> schemaMap = new HashMap<String, HashMap<String, HashMap<String, String>>>();
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		/*
		 * HashMap<String,ArrayList<String>> tableMap = new
		 * HashMap<String,ArrayList<String>>();
		 */

		/*
		 * HashMap<String, ArrayList<String>> tempMap= new HashMap<String,
		 * ArrayList<String>>();
		 */
		String tableName = "";
		try {
			jdbc = dao.getJdbcTemplate("jdbc:postgresql://" + databaseDefinition.getHost() + ":"
					+ databaseDefinition.getPort() + "/d8a2439d7a719459cb3acaeace2f6d93f", databaseDefinition.getUsername(),
					databaseDefinition.getPassword());

			System.out.println("jdbc:postgresql://" + databaseDefinition.getHost()  + " \t" +  databaseDefinition.getPort() + "/d8a2439d7a719459cb3acaeace2f6d93f"   +" " +
			databaseDefinition.getUsername() + " " +  databaseDefinition.getPassword());
			String objectDtlQuery = SQLQuery.GET_OBECT_DETAILS;

			String metaDataQuery = SQLQuery.GET_DETAILS_FOR_SCHEMA;

			String schema = "";

			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

			result = jdbc.queryForList(metaDataQuery);
			ArrayList<HashMap<String, String>> colList = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> colMap = new HashMap<String, String>();
			for (int i = 0; i < result.size(); i++) {

				Map<String, Object> tempObject = result.get(i);
				schema = (String) tempObject.get("object_schema");
				HashMap<String, HashMap<String, String>> tempMap = new HashMap<String, HashMap<String, String>>();

				if (schemaMap.containsKey(schema)) {
					tempMap = (HashMap<String, HashMap<String, String>>) schemaMap.get(schema);

					tableName = (String) tempObject.get("table_name");

					if (tempMap.get(tableName) == null) {
						tempMap.put(tableName, new HashMap<String, String>());
						colMap = new HashMap<String, String>();
					}
					// tempMap.get(tableName).add((String)
					// tempObject.get("column_name")+":"+
					// tempObject.get("data_type"));
					/// colMap.put(tempObject.get("column_name").toString(),
					// tempObject.get("data_type").toString());
					colMap.put(tempObject.get("column_name").toString(), tempObject.get("data_type").toString());
					colList.add(colMap);
					tempMap.put(tableName, colMap);
					schemaMap.put(schema, tempMap);

				} else {
					tableName = (String) tempObject.get("table_name");
					if (tempMap.get(tableName) == null) {
						tempMap.put(tableName, new HashMap<String, String>());
						colMap = new HashMap<String, String>();
					}
					colMap.put(tempObject.get("column_name").toString(), tempObject.get("data_type").toString());
					colList.add(colMap);
					tempMap.put(tableName, colMap);
					schemaMap.put(schema, tempMap);
					//// schemaMap.put(schema,tempMap);
				}

			}
			returnMap.put("MetaData", schemaMap);
			result = jdbc.queryForList("SELECT POST_DB_QUERY_STRNG   , POST_DB_QRY_OBJ_NM    FROM public.\"POST_DB_SQL_QRY2\"");

			for (int i = 0; i < result.size(); i++) {

				Map<String, Object> tempObject = result.get(i);
				Map<String, String> returnObject = new HashMap<String, String>();
				Set set1 = tempObject.keySet();
				Iterator itr1 = set1.iterator();
				Object value = "";
				String addKey = "";
				while (itr1.hasNext()) {
					String key = itr1.next().toString();
					if (key.equalsIgnoreCase("post_db_query_strng")) {
						byte[] imgBytes = (byte[]) tempObject.get(key);
						value = new String(imgBytes);

					} else
						addKey = tempObject.get(key).toString();
					returnObject.put(addKey, value.toString());
					/// System.out.println(key + " " + value);
				}

				returnMap.put("ObjectDetails", returnObject);
			}

			/*
			 * String strQry = ""; String tableName; List<Map<String, Object>>
			 * result = jdbcTemplate.queryForList(strQry); HashMap<String,
			 * List<String>> tableCols = new HashMap<String, List<String>>();
			 * for (Map<String, Object> row : result) { tableName = (String)
			 * row.get("table_name"); if (tableCols.get(tableName) == null) {
			 * tableCols.put(tableName, new ArrayList<String>()); }
			 * tableCols.get(tableName).add((String) row.get("column_name")); }
			 */
			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public void callRedis(String key,String value) throws Exception {
		try {
		/*	String value = "";*/
			/// Jedis jedis = new Jedis("localhost");
			
			System.out.println("Call Redissss " + value + " key  " + key);
			String vcap_services = System.getenv("VCAP_SERVICES");
			if (vcap_services != null && vcap_services.length() > 0) {
				// parsing rediscloud credentials
				JsonRootNode root = new JdomParser().parse(vcap_services);
				JsonNode rediscloudNode = root.getNode("redis-1");
				JsonNode credentials = rediscloudNode.getNode(0).getNode("credentials");

				JedisPool pool = new JedisPool(new JedisPoolConfig(), credentials.getStringValue("host"),
						Integer.parseInt(credentials.getNumberValue("port")), Protocol.DEFAULT_TIMEOUT,
						credentials.getStringValue("password"));

				Jedis jedis = pool.getResource();
				jedis.set(key, value);
				value = jedis.get(key);

				// return the instance to the pool when you're done
				pool.returnResource(jedis);

			}
			System.out.println("Successfully Set String in Redis");
		} catch (Exception ex) {
			ex.printStackTrace();
			// vcap_services could not be parsed.
			System.out.println("Failed to Set String in Redis");
		}
	}

}
