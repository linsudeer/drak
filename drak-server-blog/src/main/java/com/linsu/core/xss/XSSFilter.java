package com.linsu.core.xss;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class XSSFilter implements Filter{
	
	FilterConfig filterConfig = null;
	
	/**
	 * 不进行过滤验证的路径
	 */
	private List<String> urlExclusion = null;

	@Override
	public void destroy() {
		this.filterConfig = null;
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String servletPath = httpServletRequest.getServletPath();
		if(urlExclusion != null && contains(urlExclusion, servletPath)) {
			chain.doFilter(request, response);
		}else {
			chain.doFilter(new XSSHttpServletRequestWrapper((HttpServletRequest) request), response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void setUrlExclusion(List<String> urlExclusion) {
		this.urlExclusion = urlExclusion;
	}

	/**
	 * 判断 source中的字符串是否出现在element中
	 * @param source
	 * @param element
	 * @return
	 */
	private boolean contains(List<String> source, String element) {
		boolean res = false;
		if(source != null && source.size()>0){
			for(String str : source){
				if(element.contains(str)){
					res = true;
					break;
				}
			}
		}
		return res;
	}
	

}
