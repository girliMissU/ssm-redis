package com.amaan.controller;

import com.amaan.pojo.Account;
import com.amaan.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.List;

/**
 * 佛祖保佑，永无BUG
 *
 * @author AMAAN
 * SSMR
 * 2020-08-30 12:20
 */
@RestController
@RequestMapping("/ssmr")
public class AccountController {

    @Autowired
    AccountService as;

    @RequestMapping(value="/selectById", method = RequestMethod.GET)
    public Account findById(@RequestParam("id") Integer id){
        return as.findAccountById(id);
    }
//    public ModelAndView findById(@RequestParam("id") Integer id){
//        Account account = as.findAccountById(id);
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("account",account);
//        mv.setView(new MappingJackson2JsonView());
//        return mv;
//    }

}
