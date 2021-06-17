package com.xxiaoqie.dao;

import java.util.List;

import com.xxiaoqie.pojo.Dept;

public interface DeptDao {
	public int insertDept(Dept dept);
	public List<Dept> selectAll();
	public Dept selectDeptByName(String name);
}
