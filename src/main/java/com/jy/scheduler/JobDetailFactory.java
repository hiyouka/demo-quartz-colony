//package com.jy.scheduler;
//
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.scheduling.quartz.QuartzJobBean;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//
///**
// * 〈〉
// *
// * @author jianglei
// * @create 2018/6/28
// * @since 1.0.0
// */
//public class JobDetailFactory extends QuartzJobBean {
//
//
//    //类
//    private String targetName;
//
//    //需要执行定时任务的bean名称
//    private String methodName;
//
//    private String args;
//
//    private ApplicationContext applicationContext;
//
//    @Override
//    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
//
//        Object target = applicationContext.getBean(targetName);
//        Method method = null;
//        try{
//            method = target.getClass().getMethod(methodName);
//            method.invoke(target);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//    }
//
//}