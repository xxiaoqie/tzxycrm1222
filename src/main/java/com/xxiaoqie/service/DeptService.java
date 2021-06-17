package com.xxiaoqie.service;

import java.util.List;

import com.xxiaoqie.pojo.Dept;
import com.xxiaoqie.pojo.Emp;

public interface DeptService {
	public int addDept(Dept dept);
	public List<Dept> getAllDepts();
	public int delDept(int deptid);
	public int getPageCount(Dept dept, int pagesize);	
	public List<Dept> getDeptsByPage(Dept dept, Integer pagecount, Integer pagesize);
	public Dept getDeptById(int id);
	public int editDept(Dept dept);
	public int delDeptMany(Integer[] deptids);
}
