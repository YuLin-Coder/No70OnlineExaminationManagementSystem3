package cn.itbaizhan.tyut.exam.sys.servlets;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.itbaizhan.tyut.exam.common.PageControl;
import cn.itbaizhan.tyut.exam.common.Pager;
import cn.itbaizhan.tyut.exam.common.Tools;
import cn.itbaizhan.tyut.exam.model.SysFunction;
import cn.itbaizhan.tyut.exam.sys.services.impl.FunService;
import cn.itbaizhan.tyut.exam.sys.services.interfaces.IFunService;

public class FunServlet extends HttpServlet {

	IFunService service = new FunService();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String cmd = request.getParameter("cmd");
		if(cmd.equals("add")){
			addfun(request,response);
		}else if(cmd.equals("list")){
			funlist(request,response);
		}else if(cmd.equals("toedit")){
			toedit(request,response);
		}else if(cmd.equals("edit")){
			edit(request,response);
		}
	}

	/**
	 * 修改系统功能
	 * @param request
	 * @param response
	 */
	private void edit(HttpServletRequest request, HttpServletResponse response) {
		
		SysFunction fun = new SysFunction();
		
		try {
			BeanUtils.populate(fun, request.getParameterMap());
			Integer rtn = service.edit(fun);
			if(rtn>0){			
				response.sendRedirect(Tools.Basepath(request, response)+"sys/fun?cmd=list");
			}else{
				request.setAttribute("msg", "保存系统功能失败！");
				request.getRequestDispatcher("/sys/function/edit.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化修改页面
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void toedit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SysFunction fun = new SysFunction();
		fun.setFunid(Integer.parseInt(request.getParameter("id")));
		fun = service.detail(fun);
		if(fun!=null){
			request.setAttribute("item",fun);
			request.getRequestDispatcher("/sys/function/edit.jsp").forward(request, response);
		}else{
			request.setAttribute("msg", "需要修改的系统功能不存在。");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	/**
	 * 查询功能列表
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void funlist(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String sname = request.getParameter("sname");
		SysFunction fun = new SysFunction();
		if(sname!=null && !sname.equals("")){
			fun.setFunname(sname);
		}
		
		PageControl pc = new PageControl();
		Integer currindex = 1;
		if(request.getParameter("index")!=null){
			currindex = Integer.parseInt(request.getParameter("index"));
		}
		pc.setCurrentindex(currindex);
		//pc.setPagesize(5);
		
		Pager<SysFunction> pager = service.list(fun, pc);
		request.setAttribute("pager", pager);
		request.getRequestDispatcher("/sys/function/list.jsp").forward(request, response);
	}

	/**
	 * 增加系统功能
	 * @param request
	 * @param response
	 */
	private void addfun(HttpServletRequest request, HttpServletResponse response) {
		
		SysFunction fun = new SysFunction();
		try {
			BeanUtils.populate(fun,request.getParameterMap());
			Integer rtn = service.addfun(fun);
			if(rtn>0){			
				response.sendRedirect(Tools.Basepath(request, response)+"sys/fun?cmd=list");
			}else{
				request.setAttribute("msg", "保存系统功能失败！");
				request.getRequestDispatcher("/sys/function/add.jsp").forward(request, response);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}	
	}

	
	
}
