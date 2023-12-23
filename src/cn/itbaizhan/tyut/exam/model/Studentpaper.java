package cn.itbaizhan.tyut.exam.model;

public class Studentpaper {
	private String spid;
	private Integer userid;
	private Integer sid;
	private String studentkey;
	private Integer studentstate;
	private String pname;
	private Integer rightcount;
	private Integer errorcount;
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getStudentkey() {
		return studentkey;
	}
	public void setStudentkey(String studentkey) {
		this.studentkey = studentkey;
	}
	public Integer getStudentstate() {
		return studentstate;
	}
	public void setStudentstate(Integer studentstate) {
		this.studentstate = studentstate;
	}
	public void setRightcount(Integer rightcount) {
		this.rightcount = rightcount;
	}
	public Integer getRightcount() {
		return rightcount;
	}
	

	public Integer getErrorcount() {
		return errorcount;
	}
	public void setErrorcount(Integer errorcount) {
		this.errorcount = errorcount;
	}
	public String getSpid() {
		return spid;
	}
	public void setSpid(String spid) {
		this.spid = spid;
	}
	@Override
	public String toString() {
		return "Studentpaper [pname=" + pname + ", rightcount=" + rightcount
				+ ", sid=" + sid + ", spid=" + spid + ", studentkey="
				+ studentkey + ", studentstate=" + studentstate + ", userid="
				+ userid + "]";
	}
}
