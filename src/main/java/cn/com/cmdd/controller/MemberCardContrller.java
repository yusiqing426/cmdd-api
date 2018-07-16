package cn.com.cmdd.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.cmdd.service.MemberCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import cn.com.cmdd.dao.MemberCardDao;
import cn.com.cmdd.domain.MemberCard;
import cn.com.cmdd.util.ResponseObject;

import java.util.HashMap;

@Controller
public class MemberCardContrller {

    @Autowired
    private MemberCardService memberCardService;

    @Autowired
    private MemberCardDao memberCardDao;

    @RequestMapping(value="/member_card/shop/{id}/isUpload/{sync_status}",method=RequestMethod.GET)
    @ResponseBody
    public ResponseObject getIsUpload(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable("sync_status")int sync_status,
            @PathVariable("id")Integer shop_id)
    {
        ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);

        try {

            responseObject.msg=memberCardDao.selectByIsUpload(shop_id,sync_status);

        } catch (Exception e) {

            responseObject.code=ResponseObject.serverError;
            responseObject.msg=e.getLocalizedMessage();

            e.printStackTrace();
        }
        return responseObject;
    }

    @RequestMapping(value = "/member_card/id",method=RequestMethod.POST)
    @ResponseBody
    public ResponseObject insertById(@RequestBody MemberCard memberCard) {

        ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);

        try {

            memberCardDao.insertById(memberCard);

        } catch (Exception e) {

            responseObject.code = ResponseObject.serverError;
            responseObject.msg = e.getLocalizedMessage();
        }

        return responseObject;

    }
        //@GetMapping(value = "/{id}")
    @RequestMapping(value="/member/member_card/{id}",method=RequestMethod.GET)
    @ResponseBody
    public ResponseObject get(@PathVariable("id")Long id){
        ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);

        try{

        	 responseObject.msg = memberCardDao.select(id);

        }catch (Exception e){

            responseObject.code = ResponseObject.serverError;
            responseObject.msg = e.getLocalizedMessage();
        }

        return responseObject;
    }

    @RequestMapping(value="/member/member_card/shop_id/{shop_id}",method=RequestMethod.GET)
    @ResponseBody
    public ResponseObject getList(@PathVariable("shop_id")Integer shopId){
        ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);

        try{

            responseObject.msg = memberCardDao.selectList(shopId);

        }catch (Exception e){

            responseObject.code = ResponseObject.serverError;
            responseObject.msg = e.getLocalizedMessage();
        }

        return responseObject;
    }

    @RequestMapping(value = "/member/member_card",method=RequestMethod.POST)
    @ResponseBody
    public ResponseObject add(@RequestBody MemberCard memberCard){

        ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);

        try{

            memberCardService.insert(memberCard);
            HashMap<String, Object> resultMap = new HashMap<String,Object>();
            resultMap.put("memberCard_id", memberCard.getId().toString());
            responseObject.msg = resultMap;

        }catch (Exception e){

            responseObject.code = ResponseObject.serverError;
            responseObject.msg = e.getLocalizedMessage();
        }

        return responseObject;

    }

    @RequestMapping(value = "/member/member_card",method=RequestMethod.PUT)
    @ResponseBody
    public ResponseObject modify(@RequestBody MemberCard memberCard){

        ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);

        try {

            responseObject.msg = memberCardService.update(memberCard);

        }catch (Exception e){

            responseObject.code = ResponseObject.serverError;
            responseObject.msg = e.getLocalizedMessage();

        }

        return responseObject;

    }

}
