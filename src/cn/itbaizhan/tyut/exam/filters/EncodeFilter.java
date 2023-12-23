package cn.itbaizhan.tyut.exam.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class EncodeFilter implements Filter {

	private String encode="";

	
	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
	
		request.setCharacterEncoding(encode);
		response.setCharacterEncoding(encode);
	
		chain.doFilter(request, response);

	}

	public void init(FilterConfig config) throws ServletException {
	
		encode = config.getInitParameter("encode");
		
	}

}
