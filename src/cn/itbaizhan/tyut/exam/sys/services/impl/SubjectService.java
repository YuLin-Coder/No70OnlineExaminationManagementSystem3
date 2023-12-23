package cn.itbaizhan.tyut.exam.sys.services.impl;

import cn.itbaizhan.tyut.exam.common.PageControl;
import cn.itbaizhan.tyut.exam.common.Pager;
import cn.itbaizhan.tyut.exam.model.Subject;

import cn.itbaizhan.tyut.exam.sys.dao.impl.SubjectDao;

import cn.itbaizhan.tyut.exam.sys.dao.interfaces.ISubjectDao;

import cn.itbaizhan.tyut.exam.sys.services.interfaces.ISubjectService;

public class SubjectService implements ISubjectService {

	ISubjectDao dao = new SubjectDao();
	
	public Integer addsubject(Subject subject) {
		try{
			return dao.addsubject(subject);
		}catch(Exception e){
			throw new RuntimeException();
		}
		
	}

	public Pager<Subject> list(Subject subject, PageControl pc) {
		return dao.list(subject, pc);
	}

	public Integer edit(Subject subject) {
		return dao.edit(subject);
	}

	public Subject detail(Subject subject) {
		// TODO Auto-generated method stub
		return dao.detail(subject);
	}

}

