package com.service.impl;



import java.sql.ResultSet;

import java.sql.ResultSetMetaData;

import java.sql.SQLException;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Iterator;

import java.util.List;
import java.util.Map;
import java.util.Set;



import org.neo4j.cypher.internal.compatibility.exceptionHandlerFor2_2;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.RowMapper;



import com.Util.Utility;

import com.model.ClientJdbcDaoSupport;
import com.model.DatabaseMapper;
import com.model.DisputeByGroup;
import com.model.JDBCTemplate;

import com.service.intf.DataLoaderServiceIntf;



import redis.clients.jedis.Jedis;

import org.codehaus.jackson.*;

import redis.clients.jedis.*;



import argo.jdom.*;

import argo.jdom.JsonNode;

import redis.clients.jedis.Jedis;

import redis.clients.jedis.JedisPool;

import redis.clients.jedis.JedisPoolConfig;

import redis.clients.jedis.Protocol;



public class DataLoaderServiceImpl implements RowMapper {



	@Autowired

	protected JdbcTemplate jdbc;

	protected ClientJdbcDaoSupport dao = new com.model.ClientJdbcDaoSupport();

	/// static Jedis jedis = new Jedis("localhost");

	static String metaDataLoader = "";

	static String SQLQuery = "";

	static String objectName = "";



	public List loadData(JDBCTemplate jdbcObject) {

		System.out.print("We are in the load data method");

		jdbc = getJDBCConnection(jdbcObject);

		System.out.println("jdbc:postgresql://" + jdbcObject.getHost() + ":" + jdbcObject.getPort() + "/ "

				+ jdbcObject.getDatabase() + " " + jdbcObject.getUsername() + " " + jdbcObject.getPassword());

		getMetaDataLoader(jdbcObject.getUsername());

		objectName = jdbcObject.getDatabase() + "_" + jdbcObject.getUsername();

		SQLQuery = jdbcObject.getSql();

		
		
	///	SQLQuery= "select '1' rownum, testtable.customer_name , testtable.customer_Region from public.\"testtable\" where 1=1 ";

				

				System.out.println("SQLQuery ---- " + SQLQuery);

		// String inIds =

		// StringUtils.arrayToCommaDelimitedString(ObjectUtils.toObjectArray(ids));

		/// return jdbc.query("SELECT * FROM sb_user", userMapper);

		return jdbc.query(SQLQuery, dataInfoMapper);

	}



	private static final RowMapper dataInfoMapper = new RowMapper() {

		public HashMap<String, HashMap<String, HashMap<String, Object>>> mapRow(ResultSet rs, int rowNum)

				throws SQLException {



		/*	///ResultSet rs1= rs;

			System.out.println("Fetch Size  rs1 :  Before " + rs.getFetchSize());

			System.out.println("Just Before RS next ");

			while(rs.next())

			{

				

				System.out.println("values : " + rs.getString("customer_name") + "\t" +  rs.getString("customer_Region")
						);

			}
*/
			

			HashMap<String, DatabaseMapper> map = new HashMap<String, DatabaseMapper>();

			HashMap<String, Object> mapper = new HashMap<String, Object>();

			HashMap<String, HashMap<String, HashMap<String, Object>>> lstMapper1 = new HashMap<String, HashMap<String, HashMap<String, Object>>>();

			HashMap<String, HashMap<String, Object>> lstMapper = new HashMap<String, HashMap<String, Object>>();

			// user.alias = rs.getString("alias");

			//System.out.println("Fetch Size  rs1 :  Before " + rs1.getFetchSize());

			

			ResultSetMetaData rsmd = rs.getMetaData();
			System.out.println("getMetaData ---- ");
		  int numberOfColumns = rsmd.getColumnCount();
		  System.out.println("numberOfColumns ---- >>>> " + numberOfColumns);
		  String columnNameArr[] = new String[numberOfColumns-1];
			int j = 0;
			//// System.out.println("metaDataLoader" + metaDataLoader);
			HashMap<String, HashMap<String, String>> mapOutput = Utility.getColumnDataTypeFinal(metaDataLoader,
					SQLQuery);
			HashMap<String, String> transMap = new HashMap<String, String>();
			for (int i = 2; i <= numberOfColumns; i++) {

				System.out.println(" rsmd.getColumnName(i) "+  rsmd.getColumnName(i));
				
				columnNameArr[j++] = rsmd.getColumnName(i);
				//// System.out.println("rsmd.getColumnName(i) " +
				//// rsmd.getColumnName(i));
			}

			  System.out.println("columnNameArr ---- (1) " + columnNameArr.length);
			Set set1 = mapOutput.keySet();

			Iterator irr1 = set1.iterator();



			while (irr1.hasNext()) { // System.out.println(irr1.next());

				HashMap map1 = mapOutput.get(irr1.next());
				//
				/*
				 * HashMap map1 = (HashMap) irr1.next();
				 */ Set set2 = map1.keySet();
				Iterator irr2 = set2.iterator();
				while (irr2.hasNext()) {

					String key = irr2.next().toString();

					String value = map1.get(key).toString();

					transMap.put(key, value);

					 System.out.println( "(transMap)   Key " + key + "Values " + value);

				}

			}
			  System.out.println(" After transMap ---- ");
			j = 0;

			int rownum = 0;

			// mapOutput.get(columnName.toUpperCase()).toString();

			System.out.println("Fetch Size :  " + rs.getFetchSize());

			

			while (rs.next()) {



				System.out.println("Result set Found some values");

				

			///	System.out.println("Fetch Size  rs1 :  After " + rs1.getFetchSize());

				System.out.println("Fetch Size  rs1 :  After " + rs.getFetchSize());

				

				

				mapper = new HashMap<String, Object>();

				// rownum=rs.getString("rownum");

				rownum++;

				 System.out.println("columnNameArr" + columnNameArr.length);



				/// System.out.println("columnName " + columnName);

				//columnNameArr[0]="Sachin";

				for (int k = 0; k < columnNameArr.length; k++) {



					String columnName = columnNameArr[k];

					if (transMap.containsKey(columnName)) {

						String dataType = transMap.get(columnName).toString();

						System.out.println("dataType " + dataType + " columnName " + columnName);

						switch (dataType) {

						case "integer":

							// System.out.println("integer");

							int ans = rs.getInt(columnName);

							System.out.println("columnName " + columnName + " Integer " + ans);

							mapper.put(columnName, ans);

							break;

						case "numeric":

							// System.out.println("numeric");

							double tempd = rs.getDouble(columnName);

							System.out.println("columnName " + columnName + " Double  " + tempd);

							mapper.put(columnName, tempd);

							break;

						case "character varying":

							// System.out.println("character varying");

							String tmpString = rs.getString(columnName);

							System.out.println("columnName " + columnName + " TempString  " + tmpString);

							mapper.put(columnName, tmpString);

							break;

						case "date":

							// System.out.println("Date");

							mapper.put(columnName, rs.getDate(columnName));

							break;



						}

					}

					lstMapper.put(Integer.toString(rownum), mapper);

				}



				// map.put(, databaseMapper);

				// databaseMapper.getMapper().put("rownum",rs.getString("rownum"));



			//	 map.add 



			}

			System.out.println("objectName in DataLoader will be " + objectName);

			lstMapper1.put(objectName, lstMapper);

			return lstMapper1;



		}



	};



	public void saveRecord(JDBCTemplate jdbcObject) {

		String objecName = jdbcObject.getDatabase() + jdbcObject.getUsername() + jdbcObject.gerReportName();

		try {

			System.out.println("saveRecord ---------> ");

			jdbc = getJDBCConnection(jdbcObject);

			Object[] params = new Object[] { objecName, jdbcObject.getSql().getBytes() };

			int[] types = new int[] { Types.VARCHAR, Types.LONGVARBINARY };

			int row = jdbc.update(" insert into public.querytable  (POST_DB_QRY_OBJ_NM, POST_DB_QUERY_STRNG)  values   (?,?) ", params, types);



			System.out.println("saveRecord: " + row + " row inserted.");



		} catch (Exception e) {

			e.printStackTrace();

		}



	}



	static String getMetaDataLoader(String redisKey) {

		try {

			metaDataLoader = getFromRedis(redisKey);
			System.out.println("metaDataLoader String in Redis is " + metaDataLoader);
			return metaDataLoader;

		} catch (Exception e) {

			e.printStackTrace();

			return null;

		}



	}



	JdbcTemplate getJDBCConnection(JDBCTemplate jdbcObject) {

		try {

			JdbcTemplate jdbc = dao.getJdbcTemplate("jdbc:postgresql://" + jdbcObject.getHost() + ":"

					+ jdbcObject.getPort() + "/" + jdbcObject.getDatabase(), jdbcObject.getUsername(),

					jdbcObject.getPassword());

			return jdbc;

		} catch (Exception e) {

			return null;

		}



	}



	public void callRedis(String key, String value) throws Exception {

		try {

			/* String value = ""; */

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

			System.out.println("Successfully GET  String in Redis");

		} catch (Exception ex) {

			ex.printStackTrace();

			// vcap_services could not be parsed.

			System.out.println("Failed to Set String in Redis");

		}

	}



	public static String getFromRedis(String key) throws Exception {

		String value = "";

		try {

			/* String value = ""; */

			/// Jedis jedis = new Jedis("localhost");

			key = "ue618148f48394ed3aaa1d238496f70f9";

			System.out.println("Call  GET Redissss " + key);

			String vcap_services = System.getenv("VCAP_SERVICES");

			if (vcap_services != null && vcap_services.length() > 0) {

				// parsing rediscloud credentials

				JsonRootNode root = new JdomParser().parse(vcap_services);

				JsonNode rediscloudNode = root.getNode("redis-1");

				JsonNode credentials = rediscloudNode.getNode(0).getNode("credentials");



				JedisPool pool = new JedisPool(new JedisPoolConfig(), credentials.getStringValue("host"),

						Integer.parseInt(credentials.getNumberValue("port")), Protocol.DEFAULT_TIMEOUT,

						credentials.getStringValue("password"));



				/*

				 * Jedis jedis = pool.getResource(); jedis.set(key, value);

				 */

				Jedis jedis = pool.getResource();

				value = jedis.get(key);



				// return the instance to the pool when you're done

				pool.returnResource(jedis);



			}

			System.out.println("Successfully getFromRedis String in Redis "   +  value);

			return value;

		} catch (Exception ex) {

			ex.printStackTrace();

			// vcap_services could not be parsed.

			System.out.println("Failed to Set String in Redis");

			return value;

		}

	}

	

	

	public void insertRecord(JDBCTemplate jdbcObject)

	{

		System.out.println("Inside Insert Record ----------------- ");

		

	try

	{

		jdbc = getJDBCConnection(jdbcObject);

		

		System.out.println("jdbc:postgresql://" + jdbcObject.getHost() + ":" + jdbcObject.getPort() + "/ "

				+ jdbcObject.getDatabase() + " " + jdbcObject.getUsername() + " " + jdbcObject.getPassword());

		Object[] params = new Object[] {"British Airways", "APAC","CFM56", 1000, 2016 };

		int[] types = new int[] { Types.LONGNVARCHAR,Types.LONGNVARCHAR,Types.LONGNVARCHAR, Types.INTEGER, Types.INTEGER };

		int row = jdbc.update("  insert into public.testtable (customer_name, customer_region,engine_model, true_cost,year) values   (?,?,?,?,?)",

				params, types);

		

		System.out.println("saveRecord: " + 0 + " row inserted.");



	} catch (Exception e) {

		e.printStackTrace();

	}

	}



	/* (non-Javadoc)

	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)

	 */

	@Override

	public Object mapRow(ResultSet arg0, int arg1) throws SQLException {

		// TODO Auto-generated method stub

		return null;

	}
	
	/*
	 * temppppppppppppppppp
	 */
public DisputeByGroup getDisputeData(JDBCTemplate jdbcObject){
		
		System.out.println("Inside # getDisputeData Service IMPL ");
		Map<String, Map<String, Integer>> finalMap = getDisputeGroupData(jdbcObject);
		List<String> categoryList = new ArrayList<String>();
		List<String> itemList = getItemList(jdbcObject);
		Map<String, List<Integer>> itemValueMap = new HashMap<String, List<Integer>>();
		for(String item : itemList){
			List<Integer> valueList = new ArrayList<Integer>();
			itemValueMap.put(item, valueList);
			for(String catagory : finalMap.keySet()){
				if(!categoryList.contains(catagory)){
					categoryList.add(catagory);
				}
				Integer val = finalMap.get(catagory).get(item);
				itemValueMap.get(item).add(val == null?0:val);
			}
		}
		DisputeByGroup dispute = new DisputeByGroup();
		dispute.setCatagoryList(categoryList);
		dispute.setItemValueMap(itemValueMap);
		return dispute;
	}


public Map<String, Map<String, Integer>> getDisputeGroupData(JDBCTemplate jdbcObject) {
	
	try
	{System.out.println("Inside # getDisputeGroupData DAO IMPL222");	
	ClientJdbcDaoSupport dao = new ClientJdbcDaoSupport();
	JdbcTemplate jdbc;
	jdbc = getJDBCConnection(jdbcObject);


	System.out.println(jdbcObject.getHost() + ":"
			+ jdbcObject.getPort() + "/postgres" +  "\t" + jdbcObject.getUsername()+"  \t"+
			jdbcObject.getPassword());
	List<Map<String, Map<String, Integer>>> itemList = jdbc.query("select CATAGORY,ITEM, SUM(VALUE) as VAL from  public.dispute  GROUP BY ITEM,CATAGORY" 
			, new RowMapper<Map<String, Map<String, Integer>>>() {
		Map<String, Map<String, Integer>> finalMap = new HashMap<String, Map<String, Integer>>();
		public Map<String, Map<String, Integer>> mapRow(ResultSet resultSet, int i) throws SQLException {
			if(finalMap.get(resultSet.getString("CATAGORY")) != null){
				finalMap.get(resultSet.getString("CATAGORY")).put(resultSet.getString("ITEM"),resultSet.getInt("VAL"));
			} else {
				Map<String, Integer> innerMap = new HashMap<String, Integer>();
				innerMap.put(resultSet.getString("ITEM"),resultSet.getInt("VAL"));
				finalMap.put(resultSet.getString("CATAGORY"),innerMap);
			}
			return finalMap;
		}
	});
	return itemList.get(0);
	}
	catch(Exception e)
	{
		e.printStackTrace();
		return null;
	}
}

public List<String> getItemList(JDBCTemplate jdbcObject) {
	
///	DatabaseDefinition databaseDefinition = new DatabaseDefinition() ;
	ClientJdbcDaoSupport dao = new ClientJdbcDaoSupport();
	JdbcTemplate jdbc;
	jdbc = getJDBCConnection(jdbcObject);

	
	List<String> itemList = jdbc.query("select distinct(ITEM) from public.dispute ", new RowMapper<String>(){
		public String mapRow(ResultSet resultSet, int i) throws SQLException {
			return resultSet.getString("ITEM");
		}
	});

	return itemList;
}

}

