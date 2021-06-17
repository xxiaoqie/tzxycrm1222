package com.xxiaoqie.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.xxiaoqie.mapper.EmpMapper;
import com.xxiaoqie.pojo.Emp;
import com.xxiaoqie.service.EmpService;
import com.xxiaoqie.utils.MybatisUtils;

public class EmpServiceImpl implements EmpService{
	private EmpMapper mapper; 
	@Override
	public int addEmp(Emp emp) {
		int result = 0;
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtils.createFactory().openSession();
			mapper = sqlSession.getMapper(EmpMapper.class);
			result = mapper.insertEmp(emp);
			sqlSession.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
		
		return result;
	}

	@Override
	public List<Emp> getAllEmps() {
		SqlSession sqlSession = null;
		List<Emp> emps = null;
		try {
			sqlSession = MybatisUtils.createFactory().openSession();
			mapper = sqlSession.getMapper(EmpMapper.class);
			emps = mapper.selectAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
		return emps;
	}
	
	@Override
	public List<Emp> getEmpsByPage(Emp emp, Integer pageindex, Integer pagesize) {
		List<Emp> emps = null;
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtils.createFactory().openSession();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("startindex", (pageindex-1)*pagesize);
			map.put("pagesize", pagesize);
			map.put("emp", emp);
			mapper = sqlSession.getMapper(EmpMapper.class);
			emps = mapper.selectEmpByPage(map);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
		return emps;
	}

	@Override
	public int getPageCount(Emp emp, int pagesize) {
		int pagecount = 0;
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtils.createFactory().openSession();
			mapper = sqlSession.getMapper(EmpMapper.class);
			int rscount = mapper.selectEmpCount(emp);
			pagecount = (rscount + pagesize - 1) / pagesize;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
		return pagecount;
	}

	@Override
	public Emp getEmpById(int id) {
		Emp emp = null;
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtils.createFactory().openSession();
			mapper = sqlSession.getMapper(EmpMapper.class);
			emp = mapper.selectById(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
		
		return emp;
	}

	@Override
	public int editEmp(Emp emp) {
		SqlSession sqlSession = null;
		int result = 0;
		try {
			sqlSession = MybatisUtils.createFactory().openSession();
			mapper = sqlSession.getMapper(EmpMapper.class);
			result = mapper.updateEmp(emp);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
		return result;
	}

	@Override
	public int delEmpById(int id) {
		SqlSession sqlSession = null;
		int result = 0;
		try {
			sqlSession = MybatisUtils.createFactory().openSession();
			mapper = sqlSession.getMapper(EmpMapper.class);
			result = mapper.deleteEmpById(id);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
		return result;
	}

	@Override
	public int delEmpMany(Integer[] empids) {
		SqlSession sqlSession = null;
		int result = 0;
		try {
			sqlSession = MybatisUtils.createFactory().openSession();
			mapper = sqlSession.getMapper(EmpMapper.class);
			result = mapper.delEmpMany(empids);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
		return result;
	}

}
