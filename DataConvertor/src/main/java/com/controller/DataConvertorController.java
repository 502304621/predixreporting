package com.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.model.DataConvertorModel;
import com.service.impl.DataConvertorServiceImpl;

import redis.clients.jedis.Jedis;
import java.sql.Timestamp;
import redis.clients.jedis.Jedis;

import org.codehaus.jackson.*;

import redis.clients.jedis.*;



import argo.jdom.*;

import argo.jdom.JsonNode;

import redis.clients.jedis.Jedis;

import redis.clients.jedis.JedisPool;

import redis.clients.jedis.JedisPoolConfig;

import redis.clients.jedis.Protocol;


@RestController
public class DataConvertorController {
	private DataConvertorServiceImpl convertService = new DataConvertorServiceImpl();
	@CrossOrigin(origins = "https://predix-reporting.run.aws-usw02-pr.ice.predix.io")
	@RequestMapping(value = "/dataConvetor", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody DataConvertorModel getBartChartOutputTuesday(@RequestParam("xaxis") String XAxisVariable,
			@RequestParam("yaxis") String YAxisVariable,@RequestParam("drilldown") String drilldown) {
		DataConvertorModel outputObject = new DataConvertorModel();		
		String nextString = "";
		try {
			// System.out.println(jedis.get("jsonString"));
			// jsonInString = mapper.writeValueAsString(users.getAllUsers());
			System.out.println("Start get from Redis " + new Timestamp(new java.util.Date().getTime()));
			///jsonInString = jedis.get("jsonString");
			/*
			 * Temporary Code to read from Files.
			 * try {
				
				ObjectMapper mapper = new ObjectMapper();
				try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\502304621\\input.txt"))) {

					nextString = br.readLine();
			}
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			System.out.println(nextString);
			System.out.println("End get from Redis " + new Timestamp(new java.util.Date().getTime()));
			
			String redisDataLoadString = getFromRedis(/*jdbcObject.getUsername()+ jdbcObject.gerReportName()*/"ue618148f48394ed3aaa1d238496f70f9_report");
		//	String redisDataLoadString = jedis.get("postgresReport1");
			System.out.println("getFromRedis -----> " + redisDataLoadString);
			outputObject.setMainJSON(convertService.aggregateBarChartData(redisDataLoadString/* passJsonString() */, XAxisVariable, YAxisVariable));
			outputObject.setDrillDownModel(convertService.getDrillDownJSON(redisDataLoadString, XAxisVariable,
					YAxisVariable, drilldown));
			return outputObject;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	public static String getFromRedis(String key) throws Exception {

		String value = "";

		try {

			/* String value = ""; */

			/// Jedis jedis = new Jedis("localhost");

			///key = "ue618148f48394ed3aaa1d238496f70f9";

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


}
