package com.amaan.controller;

import com.amaan.service.UserRedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping(value = "/grabRedPacket")
    @ResponseBody
    public Map<String,Object> grabRedPacket(@RequestParam("redPacketId") Long redPacketId ,@RequestParam("userId") Long userId){
        int result = userRedPacketService.grabRedPacket(redPacketId,userId);
        Map<String,Object> resMap = new HashMap<>();
        boolean flag = result>0;
        resMap.put("success",flag);
        resMap.put("messages",flag?"抢红包成功":"抢红包失败");
        return resMap;
    }
}
