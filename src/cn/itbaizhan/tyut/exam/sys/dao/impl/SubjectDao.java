package cn.itbaizhan.tyut.exam.sys.dao.impl;

import java.util.List;

import cn.itbaizhan.tyut.exam.common.DBUnitHelper;
import cn.itbaizhan.tyut.exam.common.PageControl;
import cn.itbaizhan.tyut.exam.common.Pager;
import cn.itbaizhan.tyut.exam.model.Subject;

import cn.itbaizhan.tyut.exam.sys.dao.interfaces.ISubjectDao;

public class SubjectDao implements ISubjectDao {

	public Integer addsubject(Subject subject) {
		
		String sql = "INSERT INTO SUBJECT(SCONTENT,SA,SB,SC,SD,SKEY,SSTATE)" +
				" VALUES(?,?,?,?,?,?,?)";
		Integer rtn = DBUnitHelper.executeUpdate(sql, subject.getScontent(),
				subject.getSa(),subject.getSb(),subject.getSc(),subject.getSd(),
				subject.getSkey(),subject.getSstate());		
		return rtn;
	}

	public Pager<Subject> list(Subject subject,PageControl pc) {
		String sql = "SELECT SID,SCONTENT,SA,SB,SC,SD,SKEY,SSTATE FROM " +
		" SUBJECT WHERE SID>0 ";
		Pager<Subject> pager;
		String sid="sid";
		if(subject.getScontent()!=null && !subject.getScontent().equals("")){
			sql += " AND SCONTENT LIKE '%' "+" ? "+" '%' ";
			
			pager = DBUnitHelper.execlist(sql, pc, Subject.class,sid, subject.getScontent());
		}else{
			pager = DBUnitHelper.execlist(sql, pc, Subject.class,sid, null);	
		}
		return pager;
	}

	public Integer edit(Subject subject) {
		
		String sql = "UPDATE SUBJECT SET SCONTENT=?,SA=?," +
				"SB=?,SC=?,SD=?,SKEY=?,SSTATE=? WHERE SID=?";
		Integer rtn = DBUnitHelper.executeUpdate(sql,subject.getScontent(),
				subject.getSa(),subject.getSb(),subject.getSc(),subject.getSd(),subject.getSkey(),subject.getSstate(),subject.getSid());
		return rtn;
	}

	public Subject detail(Subject subject) {
		String sql = "SELECT * FROM " +
		" SUBJECT WHERE SID=? ";
		List<Subject> list = DBUnitHelper.executeQuery(sql, Subject.class, subject.getSid());
		return list.get(0);
	}
}


	

	



