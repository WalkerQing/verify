package com.yunyang.hnbt.applets.controller;

import com.yunyang.hnbt.applets.service.IMerchartAmountDetailService;
import com.yunyang.hnbt.applets.service.IMerchartAmountDiaryService;
import com.yunyang.hnbt.applets.task.VerifyTask;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;


@Log4j2
@RestController
@RequestMapping("/")
@Api(value ="收支统计")
public class MerchantAmountController {

    @Autowired
    VerifyTask verifyTask;


    @GetMapping("verify")
    public Map<String,Object> verify(){
        verifyTask.verify();
        Map<String,Object> map = new HashMap<>();
        map.put("status","success");
        return map;
    }

    @GetMapping("verifyall")
    public Map<String,Object> verifyAll(){
        Map<String,Object> map = new HashMap<>();
        verifyTask.verifyAll();
        map.put("status","success");
        return map;
    }



}
