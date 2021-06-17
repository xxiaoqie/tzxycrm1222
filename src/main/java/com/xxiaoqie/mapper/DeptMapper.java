package com.xxiaoqie.mapper;

import java.util.List;
import java.util.Map;

import com.xxiaoqie.pojo.Dept;
import com.xxiaoqie.pojo.Emp;

public interface DeptMapper {
	public List<Dept> findAll();
	public Dept selectDeptById(Integer id);
	public List<Dept> selectDeptByPage(Map<String, Object> map);
	public int selectDeptCount(Dept dept);
	public Dept selectDeptByName(String name);
	public int insertDept(Dept dept);
	public int updateDept(Dept dept);
	public int deleteDept(Integer id);    
	public int delDeptMany(List<Integer> deptids);
}
