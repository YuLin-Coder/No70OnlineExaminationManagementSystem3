package cn.itbaizhan.tyut.exam.sys.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

import cn.itbaizhan.tyut.exam.common.DBUnitHelper;
import cn.itbaizhan.tyut.exam.common.PageControl;
import cn.itbaizhan.tyut.exam.common.Pager;
import cn.itbaizhan.tyut.exam.model.SysFunction;
import cn.itbaizhan.tyut.exam.model.Sysrole;
import cn.itbaizhan.tyut.exam.sys.dao.interfaces.IRoleDao;

public class RoleDao implements IRoleDao {

	public Pager<Sysrole> list(Sysrole role, PageControl pc) {
		
		String sql = "SELECT ROLEID,ROLENAME,ROLEDESC,ROLESTATE FROM " +
				" SYSROLE WHERE ROLEID>-2 ";
		Pager<Sysrole> pager = null;
		String roleid="roleid";
		if(role.getRolename()!=null && !role.getRolename().equals(""))
		{
			sql += " AND ROLENAME=?";
			pager = DBUnitHelper.execlist(sql, pc, Sysrole.class,roleid, role.getRolename());
		}else{
			pager = DBUnitHelper.execlist(sql, pc, Sysrole.class,roleid, null);
		}
		return pager;
	}

	public Integer add(Sysrole role) {
		
		String sql = "INSERT INTO SYSROLE(ROLENAME,ROLEDESC,ROLESTATE) " +
				"VALUES (?,?,?)";
		return DBUnitHelper.executeUpdate(sql, role.getRolename(),role.getRoledesc(),role.getRolestate());
	}

	public List<SysFunction> initfunlist(Sysrole role) {
		
		String sql = "select A.funid,A.funname,A.funurl,A.funpid,A.funstate, " +
				"(CASE WHEN B.RRID IS NULL THEN '0' ELSE '1' END) AS RR " +
				"from sysfunction A " +
				"LEFT OUTER JOIN ROLERIGHT B ON A.FUNID=B.FUNID AND B.ROLEID=? " +
				"WHERE A.FUNSTATE=1  ";
		List<SysFunction> list = DBUnitHelper.executeQuery(sql, SysFunction.class, role.getRoleid());
		
		return list;
	}

	public Sysrole detail(Sysrole role) {
		String sql = "SELECT ROLEID,ROLENAME,ROLEDESC,ROLESTATE FROM " +
		" SYSROLE WHERE ROLEID=? ";
		List<Sysrole> list = DBUnitHelper.executeQuery(sql, Sysrole.class, role.getRoleid());
		return list.get(0);
	}
	
	public ArrayList<Sysrole> getALL()
	{
		String sql = "SELECT ROLEID,ROLENAME,ROLEDESC,ROLESTATE FROM " +
		" SYSROLE";
		ArrayList<Sysrole> roles=(ArrayList<Sysrole>) DBUnitHelper.executeQuery(sql, Sysrole.class);
		return roles;
	}

	public Integer saveright(String roleid, String[] funids) {
		
		String sql = "DELETE FROM ROLERIGHT WHERE ROLEID="+roleid;
		Connection conn = DBUnitHelper.getConn();
		Integer rst = 0;
		try {
			conn.setAutoCommit(false);
			QueryRunner rq = new QueryRunner();
			int rtn = rq.update(conn, sql);
			for(int i=0;i<funids.length;i++){
				String sql2 = "INSERT INTO ROLERIGHT (ROLEID,FUNID) VALUES ("+roleid+","+funids[i]+")";
				rtn = rq.update(conn,sql2);
				if(rtn>0){
					continue;
				}else{
					conn.rollback();
					break;
				}
			}
			conn.commit();
			rst = 1;
			DbUtils.close(conn);
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return rst;
	}

	public Integer edit(Sysrole role) {
		
		String sql = "UPDATE SYSROLE SET ROLENAME=?,ROLESTATE=?," +
				"ROLEDESC=? WHERE ROLEID=?";
		Integer rtn = DBUnitHelper.executeUpdate(sql,role.getRolename(),
				role.getRolestate(),role.getRoledesc(),role.getRoleid());
		return rtn;
	}
	

}
