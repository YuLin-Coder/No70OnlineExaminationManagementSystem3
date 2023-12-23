package cn.itbaizhan.tyut.exam.sys.servlets;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import cn.itbaizhan.tyut.exam.common.PageControl;
import cn.itbaizhan.tyut.exam.common.Pager;
import cn.itbaizhan.tyut.exam.common.Tools;
import cn.itbaizhan.tyut.exam.model.Paper;
import cn.itbaizhan.tyut.exam.model.Studentpaper;
import cn.itbaizhan.tyut.exam.model.Subject;
import cn.itbaizhan.tyut.exam.model.SysFunction;
import cn.itbaizhan.tyut.exam.model.Sysrole;
import cn.itbaizhan.tyut.exam.model.Sysuser;
import cn.itbaizhan.tyut.exam.sys.services.impl.PaperService;
import cn.itbaizhan.tyut.exam.sys.services.impl.StudentpaperService;
import cn.itbaizhan.tyut.exam.sys.services.impl.UserService;
import cn.itbaizhan.tyut.exam.sys.services.interfaces.IPaperService;
import cn.itbaizhan.tyut.exam.sys.services.interfaces.IStudentpaperService;
import cn.itbaizhan.tyut.exam.sys.services.interfaces.IUserService;


public class UserServlet extends HttpServlet {

	IUserService service = new UserService();
	IPaperService paperService = new PaperService();
	IStudentpaperService spServece = new StudentpaperService();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String cmd= request.getParameter("cmd");
		if(cmd.equals("paperlist")){
			index(request,response);
		}else if(cmd.equals("login")){
			login(request,response);
		}else if(cmd.equals("init")){
			initpage(request,response);
		}else if(cmd.equals("logout")){
			logout(request,response);
		}else if(cmd.equals("list")){
			list(request,response);
		}else if(cmd.equals("add")){
			add(request,response);
		}else if(cmd.equals("toedit")){
			toedit(request,response);
		}else if(cmd.equals("edit")){
			edit(request,response);
		}else if(cmd.equals("toeditpwd")){
			toeditpwd(request,response);
		}else if(cmd.equals("editpwd")){
			editpwd(request,response);
		}else if(cmd.equals("paper")){
			paper(request,response);
		}else if(cmd.equals("stulogin")){
			stulogin(request,response);
		}else if(cmd.equals("answer")){
			answer(request,response);
		}
	}
	/**
	 * 跳转首页
	 * @param request
	 * @param response
	 */
	private void index(HttpServletRequest request, HttpServletResponse response) {
		Paper paper = new Paper();
		//String pname = request.getParameter("pname");
		//paper.setPname(pname);
		List<Paper> papers = paperService.list(paper);
		request.setAttribute("papers", papers);
		try {
			request.getRequestDispatcher("/user/index.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 修改用户功能
	 * @param request
	 * @param response
	 */
	private void edit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Sysuser user = new Sysuser();
		
		try {
			BeanUtils.populate(user, request.getParameterMap());
			Integer rtn = service.edit(user);
			if(rtn>0){			
				response.sendRedirect(Tools.Basepath(request, response)+"sys/user?cmd=list");
			}else{
				request.setAttribute("msg", "保存用户失败！");
				request.getRequestDispatcher("/sys/user/edit.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 用户初始化修改页面
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void toedit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Sysuser user = new Sysuser();
		user.setUserid(Integer.parseInt(request.getParameter("id")));
		user = service.detail(user);
		if(user!=null){
			request.setAttribute("item",user);
			request.getRequestDispatcher("/sys/user/edit.jsp").forward(request, response);
		}else{
			request.setAttribute("msg", "需要修改的用户不存在。");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}
	/**
	 * 修改用户密码功能
	 * @param request
	 * @param response
	 */
	private void editpwd(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Sysuser user = new Sysuser();
		
		try {
			BeanUtils.populate(user, request.getParameterMap());
			Integer rtn = service.editpwd(user);
			if(rtn>0){			
				response.sendRedirect(Tools.Basepath(request, response)+"sys/user?cmd=list");
			}else{
				request.setAttribute("msg", "保存用户失败！");
				request.getRequestDispatcher("/sys/user/editpwd.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 用户初始化密码修改页面
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void toeditpwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Sysuser user = new Sysuser();
		user.setUserid(Integer.parseInt(request.getParameter("id")));
		user = service.detail(user);
		if(user!=null){
			request.setAttribute("user",user);
			request.getRequestDispatcher("/sys/user/editpwd.jsp").forward(request, response);
		}else{
			request.setAttribute("msg", "需要修改的用户不存在。");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}
	/**
	 * 新增用户
	 * @param request
	 * @param response
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Sysuser user = new Sysuser();
		try {
			BeanUtils.populate(user, request.getParameterMap());
			Integer rtn = service.add(user);
			if(rtn>0){
				response.sendRedirect(Tools.Basepath(request, response)+"sys/user?cmd=list");
			}else{
				request.setAttribute("msg", "添加用户失败或请不要再重复添加");
				request.getRequestDispatcher("/sys/user/add.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取用户列表
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sname = request.getParameter("sname");
		Sysuser user = new Sysuser();
		if(sname!=null && !sname.equals("")){
			user.setUsername(sname);
		}
		
		PageControl pc = new PageControl();
		Integer currindex = 1;
		if(request.getParameter("index")!=null){
			currindex = Integer.parseInt(request.getParameter("index"));
		}
		pc.setCurrentindex(currindex);
		//pc.setPagesize(5);
		
		Pager<Sysuser> pager = service.list(user, pc);
		request.setAttribute("pager", pager);
		request.getRequestDispatcher("/sys/user/list.jsp").forward(request, response);
		
	}

	/**
	 * 注销
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		response.sendRedirect(Tools.Basepath(request, response)+"login.jsp");
	}

	/**
	 * 初始化主页
	 * @param request
	 * @param response
	 */
	private void initpage(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession(true);
		Sysuser user = (Sysuser)session.getAttribute("user");
		List<SysFunction> list = service.initpage(user);		
		try {
			request.setAttribute("list", list);
			RequestDispatcher rds=request.getRequestDispatcher("/index.jsp");
			rds.forward(request, response);
			return;
			//request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	/**
	 * 用户登陆
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Sysuser user = new Sysuser();
		user.setUsername(request.getParameter("username"));
		user.setUserpwd(request.getParameter("userpwd"));
		
		user = service.login(user);
		if(user==null){
			request.setAttribute("msg", "用户名密码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}else{
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
			initpage(request, response);
			//request.getRequestDispatcher("/sys/user?cmd=init").forward(request, response);
		}
	}
	/**
	 * 学生获取试题内容
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void paper(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Paper paper = new Paper();
//		String pname = new String(request.getParameter("pname").getBytes("iso-8859-1"), "utf-8");
		String pname = new String(request.getParameter("pname"));
		paper.setPname(pname);
		List<Subject> subjects = paperService.subjectlist(paper);
		request.setAttribute("subjects", subjects);
		request.setAttribute("pname", pname);
		request.getRequestDispatcher("/user/paper/paper.jsp").forward(request, response);
	}
	/**
	 * 提交回答问题
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException 
	 */
	private void answer(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		//JSONArray json = JSONArray.fromObject(personstr);
		// TODO Auto-generated method stub
		// System.out.println(request.getParameterMap());
		String pname = new String(request.getParameter("pname").getBytes("iso-8859-1"), "utf-8");
		Studentpaper studentpaper = new Studentpaper();
		try {
			BeanUtils.populate(studentpaper, request.getParameterMap());
			studentpaper.setPname(pname);
			Integer rtn = spServece.addPaper(studentpaper);
			if(rtn>0){
				//response.sendRedirect(Tools.Basepath(request, response)+"sys/user?cmd=list");
			}else{
//				request.setAttribute("msg", "保存用户失败");
//				request.getRequestDispatcher("/sys/user/add.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 学生登陆
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void stulogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Sysuser user = new Sysuser();
		user.setUsername(request.getParameter("username"));
		user.setUserpwd(request.getParameter("userpwd"));
		user = service.stulogin(user);
		if(user==null){
			request.setAttribute("msg", "用户名密码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}else{
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
			session.setAttribute("userid", user.getUserid());
			index(request, response);
		//	request.getRequestDispatcher("/sys/user?cmd=init").forward(request, response);
		}
	}
}
