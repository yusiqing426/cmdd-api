package cn.com.cmdd.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import cn.com.cmdd.dao.MemberIntegralDao;
import cn.com.cmdd.domain.MemberIntegral;
import cn.com.cmdd.util.ResponseObject;

import java.util.HashMap;

@Controller
public class MemberIntegralController {

    @Autowired
    MemberIntegralDao memberIntegralDao;

    @RequestMapping(value="/member_integral",method=RequestMethod.POST)
    @ResponseBody
    public ResponseObject insert(@RequestBody MemberIntegral memberIntegral){

        ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);

        try{

            memberIntegralDao.insert(memberIntegral);
            HashMap<String, Object> resultMap = new HashMap<String,Object>();
            resultMap.put("memberIntegral_id", memberIntegral.getId());
            responseObject.msg = resultMap;


        }catch (Exception e){

            responseObject.code = ResponseObject.serverError;
            responseObject.msg = e.getLocalizedMessage();
        }

        return responseObject;
    }


	@RequestMapping(value="/member_integral/shop/{id}/isUpload/{sync_status}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getIsUpload(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("sync_status")int sync_status,
			@PathVariable("id")Integer shop_id)
	{
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);

		try {

			responseObject.msg=memberIntegralDao.selectByIsUpload(shop_id,sync_status);

		} catch (Exception e) {

			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();

			e.printStackTrace();
		}
		return responseObject;
	}


    @RequestMapping(value="/member_integral/id",method=RequestMethod.POST)
    @ResponseBody
    public ResponseObject insertById(@RequestBody MemberIntegral memberIntegral){

        ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);

        try{

            memberIntegralDao.insertById(memberIntegral);

        }catch (Exception e){

            responseObject.code = ResponseObject.serverError;
            responseObject.msg = e.getLocalizedMessage();
        }

        return responseObject;
    }


    @RequestMapping(value="/member/member_integral/shop_id/{shop_id}",method=RequestMethod.GET)
    @ResponseBody
    public ResponseObject get(@PathVariable("shop_id")Integer shopId){

        ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);

        try{

            responseObject.msg = memberIntegralDao.select(shopId);

        }catch (Exception e){

            responseObject.code = ResponseObject.serverError;
            responseObject.msg = e.getLocalizedMessage();
        }

        return responseObject;
    }
}
