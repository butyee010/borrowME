package com.borrow.blockchain.utils;

import java.io.IOException;
import java.io.Serializable;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;


public class JsonUtility implements Serializable{
	

	private static final long serialVersionUID = -3081386751734102711L;

	public static <T> T jsonToObject(String jsonFormat,Class<T> classOfJson) throws IOException{
		T object = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			object = mapper.readValue(jsonFormat, classOfJson);
		} catch (IOException e) {
			throw e;
		}
		return object;
	}
	
	public static String objectToJson(Object object) throws IOException {
		String jsonFormat = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			jsonFormat = mapper.writeValueAsString(object);
		} catch (IOException e) {
			throw e;
		}
		return jsonFormat;
	}
	
	public static String objectToJsonInclude(Object object) throws IOException {
		String jsonFormat = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Inclusion.NON_NULL);
			jsonFormat = mapper.writeValueAsString(object);
		} catch (IOException e) {
			throw e;
		}
		return jsonFormat;
	}
	
	public static String objectToPrettyJson(Object object) throws IOException {
		String jsonFormat = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			jsonFormat = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		} catch (IOException e) {
			throw e;
		}
		return jsonFormat;
	}
	
	public static String objectToPrettyJsonInclude(Object object) throws IOException {
		String jsonFormat = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Inclusion.NON_NULL);
			jsonFormat = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		} catch (IOException e) {
			throw e;
		}
		return jsonFormat;
	}
	

}
