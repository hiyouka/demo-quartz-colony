//package com.jy.config;
//
//import org.quartz.JobDetail;
//import org.quartz.Trigger;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
//import org.springframework.scheduling.quartz.JobDetailFactoryBean;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
///**
// * @author jianglei
// * @create 2018/6/28
// * @since 1.0.0
// */
///* 支持集群能力的调度工厂 */
//public class SchedulerFactoryBeanConfig {
//
//    @Value("${quartz.scheduler.instanceName}")
//    private String quartzInstanceName;
//
//    @Value("${org.quartz.dataSource.myDS.driver}")
//    private String myDSDriver;
//
//    @Value("${org.quartz.dataSource.myDS.URL}")
//    private String myDSURL;
//
//    @Value("${org.quartz.dataSource.myDS.user}")
//    private String myDSUser;
//
//    @Value("${org.quartz.dataSource.myDS.password}")
//    private String myDSPassword;
//
//    @Value("${org.quartz.dataSource.myDS.maxConnections}")
//    private String myDSMaxConnections;
//
//    /**
//     * 定时任务集群配置
//     * 设置属性
//     *
//     * @return
//     * @throws IOException
//     */
//    private Properties quartzProperties() throws IOException {
//        Properties prop = new Properties();
//        prop.put("quartz.scheduler.instanceName", quartzInstanceName);
//        prop.put("org.quartz.scheduler.instanceId", "AUTO");
//        prop.put("org.quartz.scheduler.skipUpdateCheck", "true");
//        prop.put("org.quartz.scheduler.jmx.export", "true");
//
//        prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
//        prop.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
//        prop.put("org.quartz.jobStore.dataSource", "quartzDataSource");
//        prop.put("org.quartz.jobStore.tablePrefix", "T_B_QRTZ_");
//        prop.put("org.quartz.jobStore.isClustered", "true");
//
//        prop.put("org.quartz.jobStore.clusterCheckinInterval", "20000");
//        prop.put("org.quartz.jobStore.dataSource", "myDS");
//        prop.put("org.quartz.jobStore.maxMisfiresToHandleAtATime", "1");
//        prop.put("org.quartz.jobStore.misfireThreshold", "120000");
//        prop.put("org.quartz.jobStore.txIsolationLevelSerializable", "true");
//        prop.put("org.quartz.jobStore.selectWithLockSQL", "SELECT * FROM {0}LOCKS WHERE LOCK_NAME = ? FOR UPDATE");
//
//        prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
//        prop.put("org.quartz.threadPool.threadCount", "10");
//        prop.put("org.quartz.threadPool.threadPriority", "5");
//        prop.put("org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread", "true");
//
//        prop.put("org.quartz.dataSource.myDS.driver", myDSDriver);
//        prop.put("org.quartz.dataSource.myDS.URL", myDSURL);
//        prop.put("org.quartz.dataSource.myDS.user", myDSUser);
//        prop.put("org.quartz.dataSource.myDS.password", myDSPassword);
//        prop.put("org.quartz.dataSource.myDS.maxConnections", myDSMaxConnections);
//
//        prop.put("org.quartz.plugin.triggHistory.class", "org.quartz.plugins.history.LoggingJobHistoryPlugin");
//        prop.put("org.quartz.plugin.shutdownhook.class", "org.quartz.plugins.management.ShutdownHookPlugin");
//        prop.put("org.quartz.plugin.shutdownhook.cleanShutdown", "true");
//        return prop;
//    }
//
//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean(@Qualifier("sendEmailTrigger") Trigger sendEmailTrigger) throws IOException {
//        SchedulerFactoryBean factory = new SchedulerFactoryBean();
//        factory.setOverwriteExistingJobs(true);
//        //用于quartz集群,加载quartz数据源
//        //factory.setDataSource(dataSource);
//        factory.setStartupDelay(10);
////        factory.setQuartzProperties(quartzProperties());
//        factory.setAutoStartup(true);
//        factory.setApplicationContextSchedulerContextKey("applicationContext");
//        //注册触发器
//        factory.setTriggers(sendEmailTrigger);
//        return factory;
//    }
//
//
//    @Bean
//    public JobDetailFactoryBean sendEmailJobDetail() {
//        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
////        factoryBean.setJobClass(InvokingJobDetailDetailFactory.class);
//        factoryBean.setDurability(true);
//        factoryBean.setRequestsRecovery(true);
//        factoryBean.setGroup("CNLM-EMAIL");
//        factoryBean.setDescription("邮件监控");
//        Map<String, String> map = new HashMap<>();
//        map.put("targetObject","emailJob");
//        map.put("targetMethod", "sendEmail");
//        factoryBean.setJobDataAsMap(map);
//        return factoryBean;
//    }
//
//    @Bean
//    public CronTriggerFactoryBean sendEmailTrigger(@Qualifier("sendEmailJobDetail") JobDetail jobDetail) {
//        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
//        factoryBean.setJobDetail(jobDetail);
////        factoryBean.setCronExpression(quartz_cron_email_message_send);
//        return factoryBean;factoryBean
//    }
//
//}