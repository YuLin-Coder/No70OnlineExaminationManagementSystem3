package cn.itbaizhan.tyut.exam.sys.servlets;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.jspsmart.upload.SmartUpload;

import cn.itbaizhan.tyut.exam.common.DBUnitHelper;
import cn.itbaizhan.tyut.exam.common.PageControl;
import cn.itbaizhan.tyut.exam.common.Pager;
import cn.itbaizhan.tyut.exam.common.Tools;
import cn.itbaizhan.tyut.exam.model.Subject;

import cn.itbaizhan.tyut.exam.sys.services.impl.SubjectService;
import cn.itbaizhan.tyut.exam.sys.services.interfaces.ISubjectService;


public class SubjectServlet extends HttpServlet {

	ISubjectService service = new SubjectService();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String cmd = request.getParameter("cmd");
		if(cmd.equals("add")){
			addsubject(request,response);
		}else if(cmd.equals("list")){
			 list(request,response);
		}else if(cmd.equals("toedit")){
			toedit(request,response);
		}else if(cmd.equals("edit")){
			edit(request,response);
		}
	}

	/**
	 * 修改试题功能
	 * @param request
	 * @param response
	 */
	private void edit(HttpServletRequest request, HttpServletResponse response) {
		
		Subject subject = new Subject();
		
		try {
			BeanUtils.populate(subject, request.getParameterMap());
			Integer rtn = service.edit(subject);
			if(rtn>0){			
				response.sendRedirect(Tools.Basepath(request, response)+"sys/subject?cmd=list");
			}else{
				request.setAttribute("msg", "编辑试题功能失败！");
				request.getRequestDispatcher("/sys/subject/edit.jsp").forward(request, response);
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
		
		Subject subject = new Subject();
		subject.setSid(Integer.parseInt(request.getParameter("id")));
		subject = service.detail(subject);
		if(subject!=null){
			request.setAttribute("item",subject);
			request.getRequestDispatcher("/sys/subject/edit.jsp").forward(request, response);
		}else{
			request.setAttribute("msg", "需要修改的试题功能不存在。");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	/**
	 * 查询试题列表
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void list(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String scontent = request.getParameter("scontent");
		Subject subject = new Subject();
		if(scontent!=null && !scontent.equals("")){
			subject.setScontent(scontent);
		}
		
		PageControl pc = new PageControl();
		Integer currindex = 1;
		if(request.getParameter("index")!=null){
			currindex = Integer.parseInt(request.getParameter("index"));
		}
		pc.setCurrentindex(currindex);
		//pc.setPagesize(5);
		
		Pager<Subject> pager = service.list(subject, pc);
		request.setAttribute("pager", pager);
		request.getRequestDispatcher("/sys/subject/list.jsp").forward(request, response);
	}

	/**
	 * 增加试题功能
	 * @param request
	 * @param response
	 */
	private void addsubject(HttpServletRequest request, HttpServletResponse response) {
		
		Subject subject = new Subject();
		try {
			BeanUtils.populate(subject,request.getParameterMap());
			Integer rtn = service.addsubject(subject);
			if(rtn>0){			
				response.sendRedirect(Tools.Basepath(request, response)+"sys/subject?cmd=list");
			}else{
				request.setAttribute("msg", "增加试题功能失败或请不要添加相同试题！");
				request.getRequestDispatcher("/sys/subject/add.jsp").forward(request, response);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}	
	}

	
	
}
