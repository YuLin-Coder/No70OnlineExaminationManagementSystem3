package cn.itbaizhan.tyut.exam.sys.services.impl;

import java.util.ArrayList;
import java.util.List;

import cn.itbaizhan.tyut.exam.common.PageControl;
import cn.itbaizhan.tyut.exam.common.Pager;
import cn.itbaizhan.tyut.exam.model.SysFunction;
import cn.itbaizhan.tyut.exam.model.Sysrole;
import cn.itbaizhan.tyut.exam.sys.dao.impl.RoleDao;
import cn.itbaizhan.tyut.exam.sys.dao.interfaces.IRoleDao;
import cn.itbaizhan.tyut.exam.sys.services.interfaces.IRoleService;

public class RoleService implements IRoleService {

	IRoleDao dao = new RoleDao();
	
	public Pager<Sysrole> list(Sysrole role, PageControl pc) {
		return dao.list(role, pc);
	}

	public Integer add(Sysrole role) {
		return dao.add(role);
	}

	public List<SysFunction> initfunlist(Sysrole role) {
		return dao.initfunlist(role);
	}

	public Sysrole detail(Sysrole role) {
		return dao.detail(role);
	}

	public Integer saveright(String roleid, String[] funids) {
		return dao.saveright(roleid, funids);
	}
	public Integer edit(Sysrole role) {
		return dao.edit(role);
	}

	public ArrayList<Sysrole> getALL() {
		// TODO Auto-generated method stub
		return dao.getALL();
	}
}
