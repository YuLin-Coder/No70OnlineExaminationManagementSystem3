package cn.itbaizhan.tyut.exam.filters;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse rsp = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		if(session==null || session.getAttribute("user")==null){
			//处理未登陆问题。重定向到登陆页面
			//String path = req.getContextPath();
			//String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path+"/";
			rsp.sendRedirect("/projdemo/login.jsp");
			return;
		}
		else{
			System.out.println("成功执行操作");
		}
		chain.doFilter(request, response);
	}


	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
