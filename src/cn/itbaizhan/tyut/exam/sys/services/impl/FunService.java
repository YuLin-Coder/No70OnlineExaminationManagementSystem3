package cn.itbaizhan.tyut.exam.sys.services.impl;

import cn.itbaizhan.tyut.exam.common.PageControl;
import cn.itbaizhan.tyut.exam.common.Pager;
import cn.itbaizhan.tyut.exam.model.SysFunction;
import cn.itbaizhan.tyut.exam.sys.dao.impl.FunDao;
import cn.itbaizhan.tyut.exam.sys.dao.interfaces.IFunDao;
import cn.itbaizhan.tyut.exam.sys.services.interfaces.IFunService;

public class FunService implements IFunService {

	IFunDao dao = new FunDao();
	
	public Integer addfun(SysFunction fun) {
		return dao.addfun(fun);
	}

	public Pager<SysFunction> list(SysFunction fun, PageControl pc) {
		return dao.list(fun, pc);
	}

	public SysFunction detail(SysFunction fun) {
		return dao.detail(fun);
	}

	public Integer edit(SysFunction fun) {
		return dao.edit(fun);
	}

}
