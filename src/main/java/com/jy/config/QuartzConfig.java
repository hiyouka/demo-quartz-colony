package com.jy.config;

import com.jy.factory.QuartzJobFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 〈〉
 *
 * @author jianglei
 * @create 2018/6/28
 * @since 1.0.0
 */
/**
 * 分布式定时任务管理配置
 * @author kerry
 * @date 2018-05-09 11:36:21
 */
@Configuration
//@ConditionalOnProperty(prefix = "qybd", name = "quartz-open", havingValue = "true")
public class QuartzConfig{

    @Autowired
    DataSource dataSource;

    @Autowired
    Environment environment;

    private static Properties properties;

    static{
        properties = new Properties();
        try {
            properties.load(QuartzConfig.class.getClassLoader().getResourceAsStream("quartz.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(QuartzJobFactory myJobFactory) throws Exception {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setDataSource(dataSource);
        //使job实例支持spring 容器管理
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        schedulerFactoryBean.setJobFactory(myJobFactory);
        schedulerFactoryBean.setQuartzProperties(properties);
        // 延迟10s启动quartz
        schedulerFactoryBean.setStartupDelay(10);
        return schedulerFactoryBean;
    }



    @Bean
    public Scheduler scheduler(SchedulerFactoryBean schedulerFactoryBean) throws IOException, SchedulerException {
//		SchedulerFactory schedulerFactory = new StdSchedulerFactory(quartzProperties());
//		Scheduler scheduler = schedulerFactory.getScheduler();
//		scheduler.start();//初始化bean并启动scheduler
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduler.start();
        return scheduler;
    }

}