package com.xxiaoqie.pojo;

public class Emp {
	private Integer empid;
	private String empname;
	private String tel;
	private Dept dept;					
	@Override
	public String toString() {
		return "Emp [empid=" + empid + ", empname=" + empname + ", tel=" + tel + ", dept=" + dept + "]";
	}
	public Emp() {
		super();
	}
	public Emp(Integer empid, String empname, String tel, Dept dept) {
		super();
		this.empid = empid;
		this.empname = empname;
		this.tel = tel;
		this.dept = dept;
	}
	public Integer getEmpid() {
		return empid;
	}
	public void setEmpid(Integer empid) {
		this.empid = empid;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
}
