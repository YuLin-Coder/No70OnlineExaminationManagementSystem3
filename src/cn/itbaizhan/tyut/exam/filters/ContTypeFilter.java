package cn.itbaizhan.tyut.exam.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ContTypeFilter implements Filter {

	private String contenttype = "";
	
	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
 		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		contenttype = filterConfig.getInitParameter("contenttype");
	}

}
