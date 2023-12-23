package cn.itbaizhan.tyut.exam.sys.servlets;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.itbaizhan.tyut.exam.common.PageControl;
import cn.itbaizhan.tyut.exam.common.Pager;
import cn.itbaizhan.tyut.exam.common.Tools;
import cn.itbaizhan.tyut.exam.model.Paper;
import cn.itbaizhan.tyut.exam.model.Subject;
import cn.itbaizhan.tyut.exam.sys.services.impl.PaperService;
import cn.itbaizhan.tyut.exam.sys.services.interfaces.IPaperService;

public class PaperServlet extends HttpServlet {
	IPaperService service = new PaperService();
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	
		String cmd = request.getParameter("cmd");
		if(cmd.equals("add")){
			addsubject(request,response);
		}else if(cmd.equals("list")){
			list(request,response);
		}else if(cmd.equals("slist")){
			slist(request,response);
		}else if(cmd.equals("delete")){
			String pname = new String(request.getParameter("pname"));
			Paper paperDelete = new Paper();
			paperDelete.setPname(pname);
		service.delete(paperDelete);
			response.sendRedirect(Tools.Basepath(request, response)+"sys/paper?cmd=list");

		}
	}	
	
	/**
	 * 生成试题功能
	 * @param request
	 * @param response
	 */
	private void addsubject(HttpServletRequest request, HttpServletResponse response) {
		
		Paper paper = new Paper();
		try {
			BeanUtils.populate(paper,request.getParameterMap());
			Integer rtn = service.addpaper(paper) ;
			if(rtn>0){			
				response.sendRedirect(Tools.Basepath(request, response)+"sys/paper?cmd=list");
			}else{
				request.setAttribute("msg", "增加试题功能失败！");
				request.getRequestDispatcher("/sys/paper/add.jsp").forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
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
		String pname = request.getParameter("pname");
		Paper paper = new Paper();
		if(pname!=null && !pname.equals("")){
			paper.setPname(pname);
		}
		PageControl pc = new PageControl();
		Integer currindex = 1;
		if(request.getParameter("index")!=null){
			currindex = Integer.parseInt(request.getParameter("index"));
		}
		pc.setCurrentindex(currindex);
		//pc.setPagesize(5);
		Pager<Paper> pager = service.list(paper, pc);
//		System.out.println(pager.getList());;
		request.setAttribute("pager", pager);
		request.getRequestDispatcher("/sys/paper/list.jsp").forward(request, response);
	}
	private void slist(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Paper paper = new Paper();
//		String pname = new String(request.getParameter("pname").getBytes("iso-8859-1"), "utf-8");
		String pname = new String(request.getParameter("pname"));

		paper.setPname(pname);
		List<Subject> subjects = service.subjectlist(paper);
		request.setAttribute("subjects", subjects);
		request.getRequestDispatcher("/sys/paper/subjects.jsp").forward(request, response);
	}
}
