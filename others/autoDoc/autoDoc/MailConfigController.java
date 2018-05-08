package com.susu.util;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 01369674 on 2018/3/14
 * Description:邮件通知配置管理
 */

@Controller
@RequestMapping("mailcfg")
public class MailConfigController{

    @ResponseBody
    @RequestMapping(value = "getById", method = RequestMethod.GET)
    public Integer getById(Integer id) {
        return id;
    }

    @ResponseBody
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public Map<String, Object> getSelective(Integer page, Integer rows) {
        Map<String, Object> result = new HashMap<>();
        return result;
    }

}
