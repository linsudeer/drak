package com.linsu.config.properties;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 数据源配置
 * @author lisonglin
 * @date 2018年3月6日 下午6:38:43
 */
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class DruidProperties {

	private Logger logger = LoggerFactory.getLogger(DruidProperties.class);
	
	/**
	 * 地址
	 */
    private String url = "jdbc:mysql://127.0.0.1:3306/blog?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";

    /**
     * 用户名
     */
    private String username = "root";
    
    /**
     * 密码
     */
    private String password = "root";
    
    /**
     * 驱动
     */
    private String driverClassName = "com.mysql.jdbc.Driver";
    
    /**
     * 初始化连接大小
     */
    private Integer initialSize = 2;
    
    /**
     * 连接池最小空闲
     */
    private Integer  minIdle = 1;
    
    /**
     * 连接池最大活跃数量
     */
    private Integer maxActive = 20;
    
    /**
     * 连接最大等待时间
     */
    private Integer maxWait = 60000;
    
    /**
     * 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
     */
    private Integer timeBetweenEvictionRunsMillis = 60000;
    
    /**
     * 配置一个连接在池中最小生存的时间，单位是毫秒
     */
    private Integer minEvictableIdleTimeMillis = 300000;
    
    private String validationQuery = "SELECT 'x'";

    private Boolean testWhileIdle = true;

    private Boolean testOnBorrow = false;

    private Boolean testOnReturn = false;

    private Boolean poolPreparedStatements = true;

    private Integer maxPoolPreparedStatementPerConnectionSize = 20;

    private String filters = "stat";
    
    public DataSource  dataSource(){
    	DruidDataSource dataSource = new DruidDataSource();
    	dataSource.setUrl(url);
    	dataSource.setUsername(username);
    	dataSource.setPassword(password);
    	dataSource.setDriverClassName(driverClassName);
    	dataSource.setInitialSize(initialSize);
    	dataSource.setMinIdle(minIdle);
    	dataSource.setMaxActive(maxActive);
    	dataSource.setMaxWait(maxWait);
    	dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
    	dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
    	dataSource.setValidationQuery(validationQuery);
    	dataSource.setTestWhileIdle(testWhileIdle);
    	dataSource.setTestOnBorrow(testOnBorrow);
    	dataSource.setTestOnReturn(testOnReturn);
    	// 打开PSCache，并且指定每个连接上PSCache的大小
    	dataSource.setPoolPreparedStatements(poolPreparedStatements);
    	dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
    	try {
			dataSource.setFilters(filters);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return dataSource;
    }

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public Integer getInitialSize() {
		return initialSize;
	}

	public void setInitialSize(Integer initialSize) {
		this.initialSize = initialSize;
	}

	public Integer getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(Integer minIdle) {
		this.minIdle = minIdle;
	}

	public Integer getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(Integer maxActive) {
		this.maxActive = maxActive;
	}

	public Integer getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(Integer maxWait) {
		this.maxWait = maxWait;
	}

	public Integer getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}

	public void setTimeBetweenEvictionRunsMillis(Integer timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}

	public Integer getMinEvictableIdleTimeMillis() {
		return minEvictableIdleTimeMillis;
	}

	public void setMinEvictableIdleTimeMillis(Integer minEvictableIdleTimeMillis) {
		this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
	}

	public String getValidationQuery() {
		return validationQuery;
	}

	public void setValidationQuery(String validationQuery) {
		this.validationQuery = validationQuery;
	}

	public Boolean getTestWhileIdle() {
		return testWhileIdle;
	}

	public void setTestWhileIdle(Boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}

	public Boolean getTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(Boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public Boolean getTestOnReturn() {
		return testOnReturn;
	}

	public void setTestOnReturn(Boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}

	public Boolean getPoolPreparedStatements() {
		return poolPreparedStatements;
	}

	public void setPoolPreparedStatements(Boolean poolPreparedStatements) {
		this.poolPreparedStatements = poolPreparedStatements;
	}

	public Integer getMaxPoolPreparedStatementPerConnectionSize() {
		return maxPoolPreparedStatementPerConnectionSize;
	}

	public void setMaxPoolPreparedStatementPerConnectionSize(Integer maxPoolPreparedStatementPerConnectionSize) {
		this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
	}

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}
    
    
}
