//package com.jy.config;
//
//import org.quartz.utils.ConnectionProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.SQLException;
//
///**
// * 〈〉
// *
// * @author jianglei
// * @create 2018/6/28
// * @since 1.0.0
// */
////重写quartz的connectionProvider 共用项目配置的数据库连接
//public class DataSourceConnectionProvider implements ConnectionProvider {
//
//    @Autowired
//    private DriverManagerDataSource dataSource;
//
//    // 数据库最大连接数
//    public int maxConnection;
//
//    // 数据库SQL查询每次连接返回执行到连接池，以确保它仍然是有效的。
//    public String validationQuery;
//
//    private boolean validateOnCheckout;
//
//    private int idleConnectionValidationSeconds;
//
//    public String maxCachedStatementsPerConnection;
//
//    public static final int DEFAULT_DB_MAX_CONNECTIONS = 10;
//
//    public static final int DEFAULT_DB_MAX_CACHED_STATEMENTS_PER_CONNECTION = 120;
//
//    @Value("${spring.datasource.url}")
//    private String url;
//
//    @Value("${spring.datasource.username}")
//    private String username;
//
//    @Value("${spring.datasource.password}")
//    private String password;
//
//    @Value("${spring.datasource.driver-class-name}")
//    private String driverClassName;
//
//    // Druid连接池
//
////    private DataSourcesProperties dataSourcesProperties;
//
//    @Override
//    public Connection getConnection() throws SQLException {
//        return dataSource.getConnection();
//    }
//
//    @Override
//    public void shutdown() throws SQLException {
//        Connection connection = dataSource.getConnection();
//        if(connection != null){
//            connection.close();
//        }
//    }
//
//    @Override
//    public void initialize() throws SQLException {
//
//        if (url == null)
//        {
//            throw new SQLException("DBPool could not be created: DB URL cannot be null");
//        }
//        if (driverClassName == null)
//        {
//            throw new SQLException(
//                    "DBPool driver could not be created: DB driver class name cannot be null!");
//        }
//        if (this.maxConnection < 0)
//        {
//            throw new SQLException(
//                    "DBPool maxConnectins could not be created: Max connections must be greater than zero!");
//        }
//
//
//        try
//        {
//            dataSource.setDriverClassName(dataSourcesProperties.getDriverClassName());
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//        dataSource.setUrl(url);
//        dataSource.setUsername(dataSourcesProperties.getUsername());
//        dataSource.setPassword(dataSourcesProperties.getPassword());
//        dataSource.set
//        dataSource.setMinIdle(1);
//        dataSource.setMaxWait(0);
//        dataSource.setMaxPoolPreparedStatementPerConnectionSize(this.maxConnection);
//        if (this.validationQuery != null)
//        {
//            dataSource.setValidationQuery(this.validationQuery);
//            if (!this.validateOnCheckout)
//                dataSource.setTestOnReturn(true);
//            else
//                dataSource.setTestOnBorrow(true);
//            dataSource.setValidationQueryTimeout(this.idleConnectionValidationSeconds);
//        }
//    }
//}