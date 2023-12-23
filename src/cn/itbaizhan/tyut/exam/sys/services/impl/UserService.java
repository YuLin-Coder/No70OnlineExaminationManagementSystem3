package cn.itbaizhan.tyut.exam.sys.services.impl;

import java.util.List;

import cn.itbaizhan.tyut.exam.common.PageControl;
import cn.itbaizhan.tyut.exam.common.Pager;
import cn.itbaizhan.tyut.exam.model.SysFunction;
import cn.itbaizhan.tyut.exam.model.Sysrole;
import cn.itbaizhan.tyut.exam.model.Sysuser;
import cn.itbaizhan.tyut.exam.sys.dao.impl.UserDao;
import cn.itbaizhan.tyut.exam.sys.dao.interfaces.IUserDao;
import cn.itbaizhan.tyut.exam.sys.services.interfaces.IUserService;

public class UserService implements IUserService {

	IUserDao dao = new UserDao();
	
	public Sysuser login(Sysuser user) {
		return dao.login(user);
	}

	public List<SysFunction> initpage(Sysuser user) {
		return dao.initpage(user);
	}
	public Pager<Sysuser> list(Sysuser user, PageControl pc) {
		return dao.list(user, pc);
	}

	public Integer add(Sysuser user) {
		try{
			return dao.add(user);
		}catch(Exception e){
			throw new RuntimeException();
		}
		
	}
	public Sysuser detail(Sysuser user) {
		return dao.detail(user);
	}
	public Integer edit(Sysuser user) {
		return dao.edit(user);
	}

	public Integer editpwd(Sysuser user) {
		// TODO Auto-generated method stub
		return dao.editpwd(user);
	}
	public Sysuser stulogin(Sysuser user) {
		return dao.stulogin(user);
	}
}
