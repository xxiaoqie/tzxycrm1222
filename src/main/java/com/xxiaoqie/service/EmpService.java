package com.xxiaoqie.service;

import java.util.List;

import com.xxiaoqie.pojo.Emp;

public interface EmpService {
	public int addEmp(Emp emp);
	public List<Emp> getAllEmps();
	public int getPageCount(Emp emp, int pagesize);	
	public List<Emp> getEmpsByPage(Emp emp, Integer pagecount, Integer pagesize);
	public Emp getEmpById(int id);
	public int editEmp(Emp emp);
	public int delEmpById(int id);
	public int delEmpMany(Integer[] empids);
}
