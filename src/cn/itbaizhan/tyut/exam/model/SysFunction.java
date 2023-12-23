package cn.itbaizhan.tyut.exam.model;

public class SysFunction {
	
	public Integer getRr() {
		return rr;
	}
	public void setRr(Integer rr) {
		this.rr = rr;
	}
	public String getFunpname() {
		return funpname;
	}
	public void setFunpname(String funpname) {
		this.funpname = funpname;
	}
	/**
	 * 系统功能ID
	 */
	private Integer funid;
	/**
	 * 系统功能名称
	 */
	private String funname;
	/**
	 * 功能地址
	 */
	private String funurl;
	/**
	 * 父功能ID
	 */
	private Integer funpid;
	/**
	 *  父功能名称
	 */
	private String funpname;
	/**
	 * 功能状态
	 */
	private Integer funstate;
	
	private Integer rr;
	
	public Integer getFunid() {
		return funid;
	}
	public void setFunid(Integer funid) {
		this.funid = funid;
	}
	public String getFunname() {
		return funname;
	}
	public void setFunname(String funname) {
		this.funname = funname;
	}
	public String getFunurl() {
		return funurl;
	}
	public void setFunurl(String funurl) {
		this.funurl = funurl;
	}
	public Integer getFunpid() {
		return funpid;
	}
	public void setFunpid(Integer funpid) {
		this.funpid = funpid;
	}
	public Integer getFunstate() {
		return funstate;
	}
	public void setFunstate(Integer funstate) {
		this.funstate = funstate;
	}
	
	
	
}
