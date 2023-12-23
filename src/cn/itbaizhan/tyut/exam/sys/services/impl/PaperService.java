package cn.itbaizhan.tyut.exam.sys.services.impl;

import java.util.List;

import cn.itbaizhan.tyut.exam.common.PageControl;
import cn.itbaizhan.tyut.exam.common.Pager;
import cn.itbaizhan.tyut.exam.model.Paper;
import cn.itbaizhan.tyut.exam.model.Subject;
import cn.itbaizhan.tyut.exam.sys.services.interfaces.IPaperService;
import cn.itbaizhan.tyut.exam.sys.dao.interfaces.IPaperDao;
import cn.itbaizhan.tyut.exam.sys.dao.impl.PaperDao;

public class PaperService implements IPaperService {
	
	IPaperDao dao = new PaperDao();
	@Override
	public Integer addpaper(Paper paper) {
		// TODO Auto-generated method stub
		return dao.addpaper(paper);
	}
	@Override
	public Pager<Paper> list(Paper paper, PageControl pc) {
		// TODO Auto-generated method stub
		return dao.list(paper, pc);
	}
	@Override
	public List<Subject> subjectlist(Paper paper) {
		// TODO Auto-generated method stub
		return dao.subjectlist(paper);
	}
	@Override
	public List<Paper> list(Paper paper) {
		// TODO Auto-generated method stub
		return dao.list(paper);
	}
	@Override
	public int delete(Paper paper){
		return dao.delete(paper);
	}
}
