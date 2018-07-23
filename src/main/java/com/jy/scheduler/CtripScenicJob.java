package com.jy.scheduler;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 〈〉
 *
 * @author jianglei
 * @create 2018/6/28
 * @since 1.0.0
 */
@Component
public class CtripScenicJob implements Job {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        JobDataMap map = context.getJobDetail().getJobDataMap();
        String url = (String) map.get("url");
        System.out.println("Start Invoke url[{"+url+"}] !");
        String result = restTemplate.getForObject(url, String.class);
        System.out.println("Invoke url[{"+url+"}] result is {"+result+"}");
    }
}