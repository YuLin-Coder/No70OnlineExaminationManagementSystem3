package cn.itbaizhan.tyut.exam.sys.dao.interfaces;

import java.util.List;

import cn.itbaizhan.tyut.exam.common.PageControl;
import cn.itbaizhan.tyut.exam.common.Pager;
import cn.itbaizhan.tyut.exam.model.Studentpaper;
import cn.itbaizhan.tyut.exam.model.Subject;



public interface IStudentpaperDao {

	
	/**
	 * 查询全部错误试题列表
	 * @param studentpaper
	 * @return
	 */
	public Pager<Subject> list(Studentpaper studentpaper,PageControl pc);
	
	/**
	 * 查询全部正确试题数量(为计算总分)
	 * @param studentpaper
	 * @return
	 */
	public List<Studentpaper>  listByRightcount(Studentpaper studentpaper);
	
	/**
	 * 学生提交答案
	 * @param studentpaper
	 * @return
	 */
	public Integer addPaper(Studentpaper studentpaper);
	
	/**
	 * 学生查看已经做过试卷列表
	 * @param studentpaper
	 * @return
	 */
	public List<Studentpaper> StudentPaperList(Studentpaper studentpaper);
}
