package com.jy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 〈〉
 *
 * @author jianglei
 * @create 2018/6/29
 * @since 1.0.0
 */
@RestController
public class JobController {

    private Logger logger = LoggerFactory.getLogger(JobController.class);

    @RequestMapping(value = "/v1/job",method = RequestMethod.GET)
    public String simpleJob(){
        logger.info("{} job start",new Exception().getStackTrace()[0].getMethodName());
        return "ok";
    }

}