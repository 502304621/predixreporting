package com.controlller;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.impl.DataLoaderServiceImpl;
import com.service.intf.DataLoaderServiceIntf;

import argo.jdom.JdomParser;
import argo.jdom.JsonNode;
import argo.jdom.JsonRootNode;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;


import com.model.DisputeByGroup;
import com.model.JDBCTemplate;
@RestController
//@RequestMapping("DataLoader")
public class DataLoaderController {

	
	private DataLoaderServiceImpl loadService = new DataLoaderServiceImpl();
	
	String jsonInString = "";
	ObjectMapper mapper = new ObjectMapper();
	///Jedis jedis = new Jedis("localhost");
	@RequestMapping(value = "/DataLoader",  method = RequestMethod.POST, produces = "application/json")
	public String getDataLoad(@RequestBody JDBCTemplate jdbcObject) {
		// log.info("Get users");
		///jedis.expire("jsonString", 1);
		try {
			// System.out.println("Data Loader Starts " + new Timestamp(new
			// java.util.Date().getTime()));
			// System.out.println(users.getAllUsers().size());
			///jsonInString = mapper.writeValueAsString(loadService.loadData(jdbcObject));
			////System.out.println(jsonInString);
			/// System.out.println("Got the JSON " + new Timestamp(new
			/// java.util.Date().getTime()));
			// System.out.println("jsonInString " + jsonInString);
			///jedis.set("jsonString", jsonInString);
			// System.out.println("Setting in Redis Complete " + new
			// Timestamp(new java.util.Date().getTime()));
			//String dataLoadeded = mapper.writeValueAsString(loadService.loadData(jdbcObject));
			///System.out.println("s " +dataLoadeded);

			loadService.saveRecord(jdbcObject);
			loadService.insertRecord(jdbcObject);
			String loadString =
					 mapper.writeValueAsString(loadService.loadData(jdbcObject));
			
			///jedis.set(jdbcObject.getUsername()+ jdbcObject.gerReportName(), loadString);
			
			loadService.callRedis("ue618148f48394ed3aaa1d238496f70f9_report", loadString);
			
			System.out.println("Key ------> ue618148f48394ed3aaa1d238496f70f9_report   " +
					"String Loaded in Redis " + loadString);
				
			return "Data Loaded Successfully # Redis Key: ";   
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
	
	
	
/*
 *Temmmmmmmmmmmp
 */
	
	@RequestMapping(value = "/reportingDisputeByGrp", method = RequestMethod.GET, produces = "application/json")
	public DisputeByGroup getReportDisputeByGrp() {
		Jedis jedis;
		JDBCTemplate jdbcObject =new JDBCTemplate();
		jdbcObject.setDatabase("d8a2439d7a719459cb3acaeace2f6d93f");
		jdbcObject.setHost("10.72.6.143");
		jdbcObject.setPort("5432");
		jdbcObject.setUsername("ue618148f48394ed3aaa1d238496f70f9");
		jdbcObject.setPassword("736127db60254a6cacfca404e4a5e7a1");
		System.out.println("reportingDisputeByGrp ------->");
		//  jedis =new Jedis("localhost");
		jedis =getJedisPool().getResource();
		Long start = System.currentTimeMillis();

		/*
		 * List catagoryList = jedis.hmget("reportingDisputeByGrp",
		 * "catagoryList"); List itemValueMap =
		 * jedis.hmget("reportingDisputeByGrp", "itemValueMap");
		 */		
		List itemValueMap = null;
		List catagoryList = null;		
		if (!jedis.exists("reportingDisputeByGrp")) {
			DisputeByGroup dispute = loadService.getDisputeData( jdbcObject);
			String itemValueMapjSOn = convertMapToString(dispute.getItemValueMap());
			String catagoryListjSon = convertListToString(dispute.getCatagoryList());
			//log.info("itemValueMapjSOn >>" + itemValueMapjSOn + ">>>");
			//log.info("catagoryListjSon >>" + catagoryListjSon + ">>>");
			HashMap<String, String> hashq = new HashMap<String, String>();
			hashq.put("catagoryList", catagoryListjSon);
			hashq.put("itemValueMap", itemValueMapjSOn);
			jedis.hmset("reportingDisputeByGrp", hashq);
			catagoryList = jedis.hmget("reportingDisputeByGrp", "catagoryList");
			itemValueMap = jedis.hmget("reportingDisputeByGrp", "itemValueMap");
		} else {
			catagoryList = jedis.hmget("reportingDisputeByGrp", "catagoryList");
			itemValueMap = jedis.hmget("reportingDisputeByGrp", "itemValueMap");
		}
		//log.info("catagoryList >>" + catagoryList + ">>>");
		DisputeByGroup disputeOutPut = new DisputeByGroup();
		disputeOutPut.getCatagoryList().addAll(convertStringToList(catagoryList.get(0)));
		disputeOutPut.setItemValueMap(convertStringToMap(itemValueMap.get(0)));
		//log.info("Time taken : " + (start - System.currentTimeMillis()));
		close();
		jedis.close();
		return disputeOutPut;
	}

	private JedisPool getJedisPool() {
		try {
			String vcap_services = System.getenv("VCAP_SERVICES");
			JedisPool pool=null;
			if (vcap_services != null && vcap_services.length() > 0) {

				// parsing rediscloud credentials

				JsonRootNode root = new JdomParser().parse(vcap_services);

				JsonNode rediscloudNode = root.getNode("redis-1");

				JsonNode credentials = rediscloudNode.getNode(0).getNode("credentials");
				
				pool = new JedisPool(new JedisPoolConfig(), credentials.getStringValue("host"),

						Integer.parseInt(credentials.getNumberValue("port")), Protocol.DEFAULT_TIMEOUT,

						credentials.getStringValue("password"));

			}
			return pool;
		} catch (Exception e) {
			return null;
		}
	}


	private String convertListToString(List<String> catagoryList) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < catagoryList.size(); i++) {
			if (i > 0) {
				sb.append(",");
			}
			sb.append(catagoryList.get(i));
		}
		return sb.toString();
	}

	private List<String> convertStringToList(Object list) {

		String[] sArray = list.toString().split(",");

		List<String> sb = new ArrayList<String>();
		for (int i = 0; i < sArray.length; i++) {
			sb.add(sArray[i]);
		}
		return sb;
	}

	private String convertMapToString(Map<String, List<Integer>> itemMap) {
		StringBuilder sb = new StringBuilder();
		List<String> keySet = new ArrayList<String>(itemMap.keySet());
		for (int i = 0; i < itemMap.keySet().size(); i++) {
			if (i > 0) {
				sb.append("#");
			}
			sb.append(keySet.get(i));
			sb.append(":");
			sb.append(convertListIToString(itemMap.get(keySet.get(i))));
		}
		String a = "A:a,b,c,d,e#B:a,b,c,d,e";
		String b = "O&G:a-b,c-d,e-f#GEA:a-b,c-d,e-f";
		return sb.toString();
	}

	/* START : Scattered plot */
	private String convertMap1ToString(Map<String, List<List<Double>>> itemMap) {
		StringBuilder sb = new StringBuilder();
		List<String> keySet = new ArrayList<String>(itemMap.keySet());
		for (int i = 0; i < itemMap.keySet().size(); i++) {
			if (i > 0) {
				sb.append("#");
			}
			sb.append(keySet.get(i));
			sb.append(":");
			sb.append(convertList1ToString(itemMap.get(keySet.get(i))));
		}
		return sb.toString();
	}

	private String convertList1ToString(List<List<Double>> catagoryList) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < catagoryList.size(); i++) {
			if (i > 0) {
				sb.append(",");
			}
			sb.append(catagoryList.get(i).get(0));
			sb.append("-");
			sb.append(catagoryList.get(i).get(1));
		}
		return sb.toString();
	}

	private Map<String, List<List<Double>>> convertString1ToMap(Object sb) {
		Map<String, List<List<Double>>> itemMap = new HashMap<String, List<List<Double>>>();
		String[] keyValArray = sb.toString().split("#");
		for (int i = 0; i < keyValArray.length; i++) {
			String[] keyVal = keyValArray[i].split(":");
			itemMap.put(keyVal[0], convertString1ToList(keyVal[1]));
		}
		return itemMap;
	}

	private List<List<Double>> convertString1ToList(Object list) {

		String[] sArray = list.toString().split(",");

		List<List<Double>> sb = new ArrayList<List<Double>>();
		for (int i = 0; i < sArray.length; i++) {
			String[] ssArray = sArray[i].split("-");
			List<Double> ssb = new ArrayList<Double>();
			for (int j = 0; j < ssArray.length; j++) {
				ssb.add(Double.parseDouble(ssArray[j]));
			}
			sb.add(ssb);
		}
		return sb;
	}

	/* END : Scattered plot */
	private Map<String, List<Integer>> convertStringToMap(Object sb) {
		Map<String, List<Integer>> itemMap = new HashMap<String, List<Integer>>();
		String[] keyValArray = sb.toString().split("#");
		for (int i = 0; i < keyValArray.length; i++) {
			String[] keyVal = keyValArray[i].split(":");
			itemMap.put(keyVal[0], convertStringToListI(keyVal[1]));
		}
		return itemMap;
	}

	private String convertListIToString(List<Integer> catagoryList) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < catagoryList.size(); i++) {
			if (i > 0) {
				sb.append(",");
			}
			sb.append(catagoryList.get(i));
		}
		return sb.toString();
	}

	private List<Integer> convertStringToListI(Object list) {

		String[] sArray = list.toString().split(",");

		List<Integer> sb = new ArrayList<Integer>();
		for (int i = 0; i < sArray.length; i++) {
			sb.add(Integer.parseInt(sArray[i]));
		}
		return sb;
	}

	private DisputeByGroup getItemAsCatagory(List<String> catagoryList, Map<String, List<Integer>> itemMap,
			String item) {
		DisputeByGroup disputeOutput = new DisputeByGroup();
		disputeOutput.setCatagoryList(new ArrayList<String>(itemMap.keySet()));
		int i = catagoryList.indexOf(item);
		List<Integer> a = new ArrayList<Integer>();
		for (String key : itemMap.keySet()) {
			a.add(itemMap.get(key).get(i));
		}
		disputeOutput.getItemValueMap().put(item, a);
		return disputeOutput;
	}

	private void close() {
		
	}
}
