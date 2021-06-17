package com.xxiaoqie.mapper;

import java.util.List;
import java.util.Map;

import com.xxiaoqie.pojo.Emp;

public interface EmpMapper {
	public List<Emp> selectAll();
	public Emp selectById(Integer empid);
	public List<Emp> selectEmpByPage(Map<String, Object> map);
	public int selectEmpCount(Emp emp);
	public int insertEmp(Emp emp);
	public int updateEmp(Emp emp);
	public int deleteEmpById(Integer id);
	public int delEmpMany(Integer[] empids);
	public int selectEmpByDeptId(Integer deptid);
}
