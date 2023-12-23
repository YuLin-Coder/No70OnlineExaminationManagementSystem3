package cn.itbaizhan.tyut.exam.sys.dao.impl;

import java.util.List;

import cn.itbaizhan.tyut.exam.common.DBUnitHelper;
import cn.itbaizhan.tyut.exam.common.PageControl;
import cn.itbaizhan.tyut.exam.common.Pager;
import cn.itbaizhan.tyut.exam.model.Studentpaper;
import cn.itbaizhan.tyut.exam.model.Subject;

import cn.itbaizhan.tyut.exam.sys.dao.interfaces.IStudentpaperDao;

public class StudentpaperDao implements IStudentpaperDao {
	public Pager<Subject> list(Studentpaper studentpaper,PageControl pc) {
		String sql = "SELECT subject.sid,subject.scontent,subject.sa,subject.sb,subject.sc,subject.sd,subject.skey,studentpaper.studentkey FROM studentpaper,subject where studentstate=0 AND subject.sid=studentpaper.sid AND studentpaper.USERID = ? AND spid = ?";
		String sid="sid";
		Pager<Subject> pager;
		pager = DBUnitHelper.execlist(sql, pc, Subject.class, sid,studentpaper.getUserid(),studentpaper.getSpid());	
		return pager;
	}

	@Override
	public Integer addPaper(Studentpaper studentpaper) {
		// TODO Auto-generated method stub
		String sql = "insert into studentpaper values(?,?,?,?,?,?)";
		Integer rtn =DBUnitHelper.executeUpdate(sql,studentpaper.getSpid(),studentpaper.getUserid(),studentpaper.getSid(),studentpaper.getStudentkey(),studentpaper.getStudentstate(),studentpaper.getPname());
		return rtn;
	}

	@Override
	public List<Studentpaper> listByRightcount(Studentpaper studentpaper) {
		String sql = "SELECT COUNT(*) rightcount FROM studentpaper where studentstate=1 AND studentpaper.USERID = ? AND spid = ?";
		List<Studentpaper> list = DBUnitHelper.executeQuery(sql,Studentpaper.class,studentpaper.getUserid(),studentpaper.getSpid());
		return list;
	}
	public List<Studentpaper> StudentPaperList(Studentpaper studentpaper) {
		String sql = "SELECT spid,userid,pname,count(if(studentstate=1,true,null)) AS rightcount,count(if(studentstate=0,true,null)) AS errorcount FROM studentpaper where userid = ? GROUP BY spid ;";
		List<Studentpaper> list = DBUnitHelper.executeQuery(sql,Studentpaper.class,studentpaper.getUserid());
		return list;
	}
}