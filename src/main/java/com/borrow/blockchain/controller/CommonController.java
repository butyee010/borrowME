package com.borrow.blockchain.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
@RequestMapping("/test")
public class CommonController {
	//private Logger logger = LogManager.getLogger(CommonController.class);

	@RequestMapping(path = "reqMsg", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String clear(@RequestBody(required = false) String a) {
		String result = "SUCCESS";
		try {
			Gson gson = new Gson();
		} catch (Throwable ex) {
			result = "FAILED";
		}
		return result;
	}



}
