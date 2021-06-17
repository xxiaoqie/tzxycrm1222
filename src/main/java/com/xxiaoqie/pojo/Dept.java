package com.xxiaoqie.pojo;

public class Dept {
	private Integer deptid;
	private String deptname;
	private String deptdesc;
	@Override
	public String toString() {
		return "Dept [deptid=" + deptid + ", deptname=" + deptname + ", deptdesc=" + deptdesc + "]";
	}
	public Integer getDeptid() {
		return deptid;
	}
	public Dept() {
		super();
	}
	public Dept(Integer deptid, String deptname, String deptdesc) {
		super();
		this.deptid = deptid;
		this.deptname = deptname;
		this.deptdesc = deptdesc;
	}
	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getDeptdesc() {
		return deptdesc;
	}
	public void setDeptdesc(String deptdesc) {
		this.deptdesc = deptdesc;
	}
}
