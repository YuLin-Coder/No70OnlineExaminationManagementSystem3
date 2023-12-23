package cn.itbaizhan.tyut.exam.model;

/**
 * 系统角色实体
 * @author Administrator
 *
 */
public class Sysrole {

	/**
	 * 角色ID
	 */
	private Integer roleid;
	/**
	 * 角色名称
	 */
	private String rolename;
	/**
	 * 角色状态
	 */
	private Integer rolestate;
	/**
	 * 角色说明
	 */
	private String roledesc;
	
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public Integer getRolestate() {
		return rolestate;
	}
	public void setRolestate(Integer rolestate) {
		this.rolestate = rolestate;
	}
	public String getRoledesc() {
		return roledesc;
	}
	public void setRoledesc(String roledesc) {
		this.roledesc = roledesc;
	}
	
	
	
	
}
