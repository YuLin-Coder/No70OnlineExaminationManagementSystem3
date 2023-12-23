package cn.itbaizhan.tyut.exam.sys.dao.impl;

import java.util.List;

import cn.itbaizhan.tyut.exam.common.DBUnitHelper;
import cn.itbaizhan.tyut.exam.common.PageControl;
import cn.itbaizhan.tyut.exam.common.Pager;
import cn.itbaizhan.tyut.exam.model.SysFunction;
import cn.itbaizhan.tyut.exam.sys.dao.interfaces.IFunDao;

public class FunDao implements IFunDao {

	public Integer addfun(SysFunction fun) {
		
		String sql = "INSERT INTO SYSFUNCTION(FUNNAME,FUNURL,FUNPID,FUNSTATE)" +
				" VALUES(?,?,?,?)";
		Integer rtn = DBUnitHelper.executeUpdate(sql, fun.getFunname(),
				fun.getFunurl(),fun.getFunpid(),fun.getFunstate());		
		return rtn;
	}

	public Pager<SysFunction> list(SysFunction fun,PageControl pc) {
		String sql = "SELECT A.FUNID,A.FUNNAME,A.FUNURL,A.FUNPID,A.FUNSTATE," +
				"(CASE WHEN B.FUNNAME IS NULL THEN '无' ELSE B.FUNNAME END) AS FUNPNAME" +
				" FROM SYSFUNCTION A " +
				"LEFT OUTER JOIN SYSFUNCTION B ON A.FUNPID=B.FUNID WHERE 0=0 ";
		Pager<SysFunction> pager;
		String funid="funid";
		if(fun.getFunname()!=null && !fun.getFunname().equals("")){
			sql += " AND A.FUNNAME=? ";
			
			pager = DBUnitHelper.execlist(sql, pc, SysFunction.class,funid, fun.getFunname());
		}else{
			pager = DBUnitHelper.execlist(sql, pc, SysFunction.class,funid, null);	
		}
		return pager;
	}

	public SysFunction detail(SysFunction fun) {
		String sql = "SELECT A.FUNID,A.FUNNAME,A.FUNURL,A.FUNPID,A.FUNSTATE," +
		"(CASE WHEN B.FUNNAME IS NULL THEN '无' ELSE B.FUNNAME END) AS FUNPNAME" +
		" FROM SYSFUNCTION A " +
		"LEFT OUTER JOIN SYSFUNCTION B ON A.FUNPID=B.FUNID WHERE A.FUNID=? ";
		
		List<SysFunction> list = DBUnitHelper.executeQuery(sql, SysFunction.class, fun.getFunid());		
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	public Integer edit(SysFunction fun) {
		
		String sql = "UPDATE SYSFUNCTION SET FUNNAME=?,FUNURL=?," +
				"FUNSTATE=? WHERE FUNID=?";
		Integer rtn = DBUnitHelper.executeUpdate(sql,fun.getFunname(),
				fun.getFunurl(),fun.getFunstate(),fun.getFunid());
		return rtn;
	}

}
