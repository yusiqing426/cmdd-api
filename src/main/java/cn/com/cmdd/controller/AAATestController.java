package cn.com.cmdd.controller;

import cn.com.cmdd.domain.AAATest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import cn.com.cmdd.dao.AAATestDao;

@Controller
public class AAATestController {

	private final static Logger LOGGER = LoggerFactory.getLogger(AAATestController.class);
	
	
    @RequestMapping("/test")
    @ResponseBody
    public String test() {

    	return "5aa";
    }

    @RequestMapping("/testSave")
    @ResponseBody
    public String save() {
		return null;
    }

    public static void main(String[] args) throws Exception {
    	
    }
}

