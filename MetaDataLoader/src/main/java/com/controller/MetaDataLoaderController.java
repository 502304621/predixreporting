package com.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.DatabaseDefinition;
import com.service.impl.MetaDataLoaderServiceImpl;
import redis.clients.jedis.Jedis;

@RestController
public class MetaDataLoaderController {

	/// @Autowired
	MetaDataLoaderServiceImpl dataLoaderService = new MetaDataLoaderServiceImpl();
	ObjectMapper mapper = new ObjectMapper();
	//// Jedis jedis = new Jedis("localhost");

	@CrossOrigin(origins = "https://predix-reporting.run.aws-usw02-pr.ice.predix.io")
	@RequestMapping(value = "/LoadMetaData", method = RequestMethod.POST, produces = "application/json")
	public /* HashMap<String,HashMap<String, HashMap<String,String>>> */HashMap<String, Object> getTableDetails(
			@RequestBody DatabaseDefinition jdbcObject) {
		String jsonInString = "";
		System.out.println("LoadMetaData");
		HashMap<String, Object> returnObject = new HashMap<String, Object>();
		try {
			// returnObject =serviceObject.getSchemaTabDetails(jdbcObject);
			returnObject = dataLoaderService.getSchemaTabDetails(jdbcObject);

			jsonInString = mapper.writeValueAsString(returnObject);
			System.out.println(jsonInString);
			dataLoaderService.callRedis(jdbcObject.getUsername(), jsonInString);
			/// jedis.set(jdbcObject.getUsername(), jsonInString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnObject;
	}

	@RequestMapping(value = "/dummy", method = RequestMethod.GET)
	public /* HashMap<String,HashMap<String, HashMap<String,String>>> */String getDummy() {
		return "Hello World!!";
	}
}
