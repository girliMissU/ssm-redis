package com.amaan.controller;

import com.amaan.service.UserRedPacketService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 佛祖保佑，永无BUG
 *
 * @author AMAAN
 * SSMR
 * 2020-08-30 20:55
 */
@Controller
@RequestMapping("/userRedPacket")
public class UserRedPacketController {

    @Autowired
    private UserRedPacketService userRedPacketService;

    @RequestMapping(value = "/grabRedPacket", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> grabRedPacket(@Param("redPacketId") Long redPacketId ,@Param("userId") Long userId){
        System.out.println(redPacketId+"||"+userId);
        int result = userRedPacketService.grabRedPacket(redPacketId,userId);
        Map<String,Object> resMap = new HashMap<>(2);
        boolean flag = result>0;
        resMap.put("success",flag);
        resMap.put("messages",flag?"抢红包成功":"抢红包失败");
        return resMap;
    }
}
