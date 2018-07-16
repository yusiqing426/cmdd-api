package cn.com.cmdd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.cmdd.dao.IPDao;
import cn.com.cmdd.domain.IP;


@Controller
public class IPController {

	@Autowired
	private IPDao ipDao;
	
    @RequestMapping("/ip/ship/{id}")
    @ResponseBody
    public IP get(@PathVariable("id")Integer shopId) {

        return ipDao.select(shopId) ;

    }

}

