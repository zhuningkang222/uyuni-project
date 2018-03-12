package com.uyuni.main.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class MyFilter extends ZuulFilter{
	
	private static final Logger log = LoggerFactory.getLogger(MyFilter.class);

	/**
	 * 判断逻辑，是否过滤，这里设置为true，一直过滤
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * 过滤逻辑
	 */
	@Override
	public Object run() {
		RequestContext rc = RequestContext.getCurrentContext();
		HttpServletRequest request = rc.getRequest();
		log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
		Object accessToken = request.getParameter("token");
        if(accessToken == null) {
            log.warn("token is empty");
            rc.setSendZuulResponse(false);
            rc.setResponseStatusCode(401);
            try {
                rc.getResponse().getWriter().write("token is empty");
            }catch (Exception e){
            	e.printStackTrace();
            }
            return null;
        }
        log.info("ok");
        return null;
	}

	/**
	 * 	pre：路由之前
	 *	routing：路由之时
	 *	post： 路由之后
	 *	error：发送错误调用
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	/**
	 * 过滤顺序
	 */
	@Override
	public int filterOrder() {
		return 0;
	}

}
