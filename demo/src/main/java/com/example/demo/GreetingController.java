package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	
	// value annotation
	@Value("${my.greeting}")
	private String greetingMsg;
	
	// typecheck
	@Value("${my.num}")
	private int intValue; // typecheck is taken care of @Value annotation itself
	
	// default value in case of no property to avoid error
	@Value("${app.version:latest version}")
	private String version;
	
	// Managing Lists
	@Value("${app.list.values}")
	private List<String> listValues;
	//private String[] valuesArray;
	
	// Managing Maps
	@Value("#{${dbValues}}")
	private Map<String, String> mapValues;
	
	//db setting - bad idea
//	@Value("#{${app.db.connection}}")
//	private Map<String, String> dbconnection;
//	@Value("#{${app.db.host}}")
//	private String dbhost;
//	@Value("#{${app.db.port}}")
//	private String dbport;
	
	//db setting - powerfull
	@Autowired
	private DBsetting dbSetting;
	
	// Environment Bean/object by spring to look up prop values
	@Autowired
	private Environment env;
	
	@Value("${system.env.value}")
	private String sysEnv;
	
	@Value("${dotEnv.env.value}")
	private String dotEnv;
	
	// Test
	@GetMapping("/greeting")
	public String greeting() {
		//return "Hello";
		return (greetingMsg);
	}	
	
	// Test @Value and @ConfigurationProperties
	@GetMapping("/test")
	public String test() {
		return (greetingMsg + " " + intValue + " " + version + " " + listValues + 
				" " + mapValues + " " + dbSetting.getConnection() + " " + dbSetting.getHost() + " " + dbSetting.getPort());
	}

	// Test Environment Object
	@GetMapping("/envDetails")
	public String[] envDetails() {
		return env.getActiveProfiles();
	}
	
	// Test set environment variables
	@GetMapping("/sysEnv")
	public String sysEnvTest() {
		return (sysEnv);
	}
	
	// Test set environment variables
	@GetMapping("/dotEnv")
	public String dotEnvTest() {
		return (dotEnv);
	}
	
}
