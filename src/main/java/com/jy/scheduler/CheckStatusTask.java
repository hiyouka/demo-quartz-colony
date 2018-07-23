//package com.jy.scheduler;
//
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// * 〈〉
// *
// * @author jianglei
// * @create 2018/6/28
// * @since 1.0.0
// */
//public class CheckStatusTask implements Job {
//
////    private Logger log = Logger.getLogger(CheckStatusTask.class);
//
//    @Autowired
//    private EclickupService eclickupService;
//
//    @Override
//    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        System.out.println("===============定时刷新z_check_evaluate里的checkStatus 开始==============");
//        int result = eclickupService.editCheckStatus();
//        if (result > 0)
//            System.out.println("===============定时刷新z_check_evaluate里的checkStatus 成功==============个数："+result);
//    }
//}