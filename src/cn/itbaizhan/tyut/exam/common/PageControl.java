package cn.itbaizhan.tyut.exam.common;

/**
 * 分页控制对象
 * @author zw
 */
public class PageControl {
	/**
	 * 当前第几页
	 */
	private Integer currentindex;
	/**
	 * 每页多少行数据(默认每页3行数据)
	 */
	private Integer pagesize = 3;
	/**
	 * 总记录数
	 */
	private Integer rscount;
	
	/**
	 * 总页数
	 */
	private Integer pagecount;
	/**
	 * 分页控件一次显示多少页(默认一次50页)
	 */
	private Integer showpcount = 50;
	/**
	 * 当前分页控件显示的最大页数
	 */
	private Integer maxpage;
	
	/**
	 * 当前分页控件显示的最小页数
	 */
	private Integer minpage;
	
	
	public Integer getCurrentindex() {
		return currentindex;
	}
	public void setCurrentindex(Integer currentindex) {
		this.currentindex = currentindex;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	public Integer getRscount() {
		return rscount;
	}
	public void setRscount(Integer rscount) {
		this.rscount = rscount;
	}
	public Integer getPagecount() {
		return pagecount;
	}
	public void setPagecount(Integer pagecount) {
		this.pagecount = pagecount;
	}
	public Integer getShowpcount() {
		return showpcount;
	}
	public void setShowpcount(Integer showpcount) {
		this.showpcount = showpcount;
	}
	public Integer getMaxpage() {
		return maxpage;
	}
	public void setMaxpage(Integer maxpage) {
		this.maxpage = maxpage;
	}
	public Integer getMinpage() {
		return minpage;
	}
	public void setMinpage(Integer minpage) {
		this.minpage = minpage;
	}
}
