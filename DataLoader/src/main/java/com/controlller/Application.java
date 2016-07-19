package com.controlller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Set;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;

@EnableAutoConfiguration
@Configuration
@ComponentScan
public class Application {

	public static void main(String[] args) throws Throwable {
		SpringApplication app = new SpringApplication(Application.class);
		app.setShowBanner(false);
		app.run(args);

		// getJSONString();

	}

	static void getJSONString() {

		try {
			String jsonString = "";
			try {

				ObjectMapper mapper = new ObjectMapper();
				try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\502304621\\input.txt"))) {

					jsonString = br.readLine();
				}
				System.out.println(jsonString);
			} catch (Exception e) {
				e.printStackTrace();
			}

			JSONArray parentArray = new JSONArray(jsonString);
			JSONObject obj = new JSONObject(jsonString.substring(1, jsonString.length() - 1));
			// JSONArray paramsArr = obj.getJSONArray("ObjectName");

			JSONObject param1 = obj.getJSONObject("ObjectName");
			System.out.println("param1     " + param1.toString());
			Iterator itrr = param1.keys();

			while (itrr.hasNext()) {
				String keys = itrr.next().toString();

				String value = param1.get(keys).toString();
				System.out.println(value);

			}

		} catch (Exception e) {

		}

	}
}
