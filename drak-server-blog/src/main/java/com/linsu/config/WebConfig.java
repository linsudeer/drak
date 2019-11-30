package com.linsu.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.BeanTypeAutoProxyCreator;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.linsu.core.fdfs.FastDFSClient;
import com.linsu.core.fdfs.FastDFSConfig;
import com.linsu.core.listener.ConfigListener;
import com.linsu.core.xss.XSSFilter;
import org.apache.catalina.filters.CorsFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * web配置类
 * @author lisonglin
 * @date 2018年3月7日 下午1:14:15
 */
@Configuration
public class WebConfig implements WebMvcConfigurer{

    private static Logger logger = LoggerFactory.getLogger(WebConfig.class);
	
	/**
     * druidServlet注册
     */
	@Bean
	public ServletRegistrationBean<StatViewServlet> druidServletRegistration(){
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<StatViewServlet>(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        return servletRegistrationBean;
    }

    /**
     * druid监控 配置URI拦截策略
     */
	@Bean
	public FilterRegistrationBean<WebStatFilter> druidStatFilter(){
		FilterRegistrationBean<WebStatFilter> filterBean = new FilterRegistrationBean<WebStatFilter>(new WebStatFilter());
		//添加过滤规则.
		filterBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
		filterBean.addInitParameter("exclusions", "/static/*,*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid,/druid/*");
        //用于session监控页面的用户名显示 需要登录后主动将username注入到session里
		filterBean.addInitParameter("principalSessionName", "username");
		return filterBean;
	}
	
	/**
     * druid数据库连接池监控
     */
    @Bean
    public DruidStatInterceptor druidStatInterceptor() {
        return new DruidStatInterceptor();
    }
    
    @Bean
    public JdkRegexpMethodPointcut druidStatPointcut() {
        JdkRegexpMethodPointcut druidStatPointcut = new JdkRegexpMethodPointcut();
        String patterns = "com.linsu.modular.*.service.*";
        //可以set多个
        druidStatPointcut.setPatterns(patterns);
        return druidStatPointcut;
    }

    /**
     * druid数据库连接池监控
     */
    @Bean
    public BeanTypeAutoProxyCreator beanTypeAutoProxyCreator() {
        BeanTypeAutoProxyCreator beanTypeAutoProxyCreator = new BeanTypeAutoProxyCreator();
        beanTypeAutoProxyCreator.setTargetBeanType(DruidDataSource.class);
        beanTypeAutoProxyCreator.setInterceptorNames("druidStatInterceptor");
        return beanTypeAutoProxyCreator;
    }

    /**
     * druid 为druidStatPointcut添加拦截
     *
     * @return
     */
    @Bean
    public Advisor druidStatAdvisor() {
        return new DefaultPointcutAdvisor(druidStatPointcut(), druidStatInterceptor());
    }
    
    /**
     * RequestContextListener注册
     */
    @Bean
    public ServletListenerRegistrationBean<RequestContextListener> requestContextListenerRegistration() {
        return new ServletListenerRegistrationBean<>(new RequestContextListener());
    }

    /**
     * ConfigListener注册
     */
    @Bean
    public ServletListenerRegistrationBean<ConfigListener> configListenerRegistration() {
        return new ServletListenerRegistrationBean<>(new ConfigListener());
    }
    
    /**
     * fastjson配置
     * @return
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        
        HttpMessageConverter<?> converter = fastJsonHttpMessageConverter;
        
        return new HttpMessageConverters(converter);

    }
    
    /**
     * 注册XSS过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean<XSSFilter> xssFiterRegistration(){
    	XSSFilter xssFilter = new XSSFilter();
    	xssFilter.setUrlExclusion(Arrays.asList("/draft", "/publish"));//设置不过滤的链接
    	FilterRegistrationBean<XSSFilter> filterRegistrationBean = new FilterRegistrationBean<>(xssFilter);
    	filterRegistrationBean.addUrlPatterns("/*");
    	return filterRegistrationBean;
    }

    /**
     * 注册CORS过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistration(){
        CorsFilter corsFilter = new CorsFilter();
        FilterRegistrationBean<CorsFilter> filterRegistrationBean = new FilterRegistrationBean<>(corsFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    @Autowired
    FastDFSConfig fastDFSConfig;

    @Bean
    public FastDFSClient fastDFSClient(){
        return new FastDFSClient(fastDFSConfig);
    }

}
