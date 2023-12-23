package cn.itbaizhan.tyut.exam.sys.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
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
import cn.itbaizhan.tyut.exam.model.Paper;
import cn.itbaizhan.tyut.exam.model.Studentpaper;
import cn.itbaizhan.tyut.exam.model.Subject;

import cn.itbaizhan.tyut.exam.sys.services.impl.StudentpaperService;
import cn.itbaizhan.tyut.exam.sys.services.impl.SubjectService;
import cn.itbaizhan.tyut.exam.sys.services.interfaces.IStudentpaperService;
import cn.itbaizhan.tyut.exam.sys.services.interfaces.ISubjectService;


public class StudentpaperServlet extends HttpServlet {

	IStudentpaperService service = new StudentpaperService();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String cmd = request.getParameter("cmd");
//		if(cmd.equals("add")){
//			add(request,response);
//		}
//		else if(cmd.equals("list")){
//			list(request,response);
//		}
//		else if(cmd.equals("listByRightcount")){
//			listByRightcount(request,response);
//		}
		if(cmd.equals("list")){
			list(request,response);
		}else if(cmd.equals("score")){
			score(request,response);
		}else if(cmd.equals("stupaper")){
			StudentPaperList(request,response);
		}
	}
	
	/**
	 * 查询试卷得分
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void score(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Studentpaper stupaper = new Studentpaper();
		try {
			BeanUtils.populate(stupaper,request.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(stupaper.getUserid()+stupaper.getSpid());
		List<Studentpaper> studentpaper = service.listByRightcount(stupaper);
		Integer score = studentpaper.get(0).getRightcount(); 
		request.setAttribute("score", score);
		PrintWriter out = response.getWriter();  
		  
        out.println("您本次得分" + score * 2 + "分!");  
        out.flush();  
        out.close(); 
		//request.getRequestDispatcher("/sys/paper/subjects.jsp").forward(request, response);
	}
	
	/**
	 * 查询详细错题
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void list(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Studentpaper studentpaper = new Studentpaper();
		PageControl pc = new PageControl();
		Integer currindex = 1;
		if(request.getParameter("index")!=null){
			currindex = Integer.parseInt(request.getParameter("index"));
		}
		pc.setCurrentindex(currindex);
		//pc.setPagesize(5);
		studentpaper.setUserid((Integer) request.getSession().getAttribute("userid"));
		studentpaper.setSpid(request.getParameter("spid"));
		Pager<Subject> pager = service.list(studentpaper, pc);
		request.setAttribute("pager", pager);
		request.getRequestDispatcher("/user/paper/studenterror.jsp").forward(request, response);
	}

	/**
	 * 增加试题功能
	 * @param request
	 * @param response
	 *//*
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
	*/
	private void StudentPaperList(HttpServletRequest request, HttpServletResponse response) {
		
		Studentpaper studentpaper = new Studentpaper();
		Integer userid = (Integer) request.getSession().getAttribute("userid");
		studentpaper.setUserid(userid);
		List<Studentpaper> papers = service.StudentPaperList(studentpaper);
		request.setAttribute("papers", papers);
		try {
			request.getRequestDispatcher("/user/paper/studentpaper.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
