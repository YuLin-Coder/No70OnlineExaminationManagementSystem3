package cn.itbaizhan.tyut.exam.sys.services.interfaces;

import java.util.List;

import cn.itbaizhan.tyut.exam.common.PageControl;
import cn.itbaizhan.tyut.exam.common.Pager;
import cn.itbaizhan.tyut.exam.model.Paper;
import cn.itbaizhan.tyut.exam.model.Subject;

public interface IPaperService {
	/**
	 * 生成试卷功能
	 * @param paper
	 * @return
	 */
	public Integer addpaper(Paper paper);
	/**
	 * 查询全部试题
	 * @param paper
	 * @return
	 */
	public Pager<Paper> list(Paper paper,PageControl pc);
	
	/**
	 * 查询试题内容
	 * @param paper
	 * @return
	 */
	public List<Subject> subjectlist(Paper paper);
	
	/**
	 * 查询全部试题(首页)
	 * @param paper
	 * @return
	 */
	public List<Paper> list(Paper paper);

	/**
	 * 删除一套试题
	 * @param paper
	 * @return
	 */
	public int delete(Paper paper);
}
