package cn.itbaizhan.tyut.exam.sys.services.impl;

import java.util.List;

import cn.itbaizhan.tyut.exam.common.PageControl;
import cn.itbaizhan.tyut.exam.common.Pager;
import cn.itbaizhan.tyut.exam.model.Studentpaper;
import cn.itbaizhan.tyut.exam.model.Subject;
import cn.itbaizhan.tyut.exam.sys.dao.impl.StudentpaperDao;
import cn.itbaizhan.tyut.exam.sys.dao.interfaces.IStudentpaperDao;
import cn.itbaizhan.tyut.exam.sys.services.interfaces.IStudentpaperService;

public class StudentpaperService implements IStudentpaperService {
	IStudentpaperDao dao = new StudentpaperDao();

	@Override
	public Integer addPaper(Studentpaper studentpaper) {
		// TODO Auto-generated method stub
		return dao.addPaper(studentpaper);
	}

	@Override
	public Pager<Subject> list(Studentpaper studentpaper,PageControl pc){
		// TODO Auto-generated method stub
		return dao.list(studentpaper, pc);
	}

	@Override
	public List<Studentpaper> listByRightcount(Studentpaper studentpaper) {
		// TODO Auto-generated method stub
		return dao.listByRightcount(studentpaper);
	}

	@Override
	public List<Studentpaper> StudentPaperList(Studentpaper studentpaper) {
		// TODO Auto-generated method stub
		return dao.StudentPaperList(studentpaper);
	}



}
