package cn.itbaizhan.tyut.exam.common;

import java.util.List;

/**
 * 分页对象

 * @param <T> 
 */
public class Pager<T> {

	
	/**
	 * 分页控制
	 */
	private PageControl pagectrl;
	/**
	 * 需要显示的数据列表
	 */
	private List<T> list;
	
	public PageControl getPagectrl() {
		return pagectrl;
	}

	public void setPagectrl(PageControl pagectrl) {
		this.pagectrl = pagectrl;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
