package cn.itbaizhan.tyut.exam.sys.services.interfaces;

import cn.itbaizhan.tyut.exam.common.PageControl;
import cn.itbaizhan.tyut.exam.common.Pager;
import cn.itbaizhan.tyut.exam.model.Subject;


public interface ISubjectService {

	/**
	 * 增加一个试题
	 * @param subject
	 * @return
	 */
	public Integer addsubject(Subject subject);
	
	/**
	 * 查询试题列表
	 * @param subject
	 * @param pc
	 * @return
	 */
	public Pager<Subject> list(Subject subject,PageControl pc);
	
	
	/**
	 * 获取试题详细信息
	 * @param subject
	 * @return
	 */
	public Subject detail(Subject subject);
	
	/**
	 * 修改试题
	 * @param subject
	 * @return
	 */
	public Integer edit(Subject subject);
}
