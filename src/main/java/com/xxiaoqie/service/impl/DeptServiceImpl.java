package com.xxiaoqie.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.xxiaoqie.dao.DeptDao;
import com.xxiaoqie.dao.impl.DeptDaoImpl;
import com.xxiaoqie.mapper.DeptMapper;
import com.xxiaoqie.mapper.EmpMapper;
import com.xxiaoqie.pojo.Dept;
import com.xxiaoqie.pojo.Emp;
import com.xxiaoqie.service.DeptService;
import com.xxiaoqie.utils.MybatisUtils;

public class DeptServiceImpl implements DeptService {
	private DeptMapper mapper = null;
	@Override
	public int addDept(Dept dept) {
		int result = 0;
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtils.createFactory().openSession();
			DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
			Dept realDept = mapper.selectDeptByName(dept.getDeptname());
			if(realDept == null) {
				result = mapper.insertDept(dept);
			} else {
				result = -1;
			}
			sqlSession.commit();
		} catch (IOException e) {
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
	public List<Dept> getAllDepts() {
		SqlSession sqlSession = null;
		List<Dept> depts = null;
		try {
			sqlSession = MybatisUtils.createFactory().openSession();
			DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
			depts = mapper.findAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
		return depts;
	}

	@Override
	public int delDept(int deptid) {
		int result = 0;
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtils.createFactory().openSession();
			DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
			EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
			if ( empMapper.selectEmpByDeptId(deptid) > 0 ) {
				return -1;
			}
			result = mapper.deleteDept(deptid);
			sqlSession.commit();
		} catch (IOException e) {
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
	public int getPageCount(Dept dept, int pagesize) {
		int pagecount = 0;
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtils.createFactory().openSession();
			mapper = sqlSession.getMapper(DeptMapper.class);
			int rscount = mapper.selectDeptCount(dept);
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
	public List<Dept> getDeptsByPage(Dept dept, Integer pageindex, Integer pagesize) {
		List<Dept> depts = null;
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtils.createFactory().openSession();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("startindex", (pageindex-1)*pagesize);
			map.put("pagesize", pagesize);
			map.put("dept", dept);
			mapper = sqlSession.getMapper(DeptMapper.class);
			depts = mapper.selectDeptByPage(map);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
		return depts;
	}

	@Override
	public Dept getDeptById(int id) {
		Dept dept = null;
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtils.createFactory().openSession();
			mapper = sqlSession.getMapper(DeptMapper.class);
			dept = mapper.selectDeptById(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
		
		return dept;
	}

	@Override
	public int editDept(Dept dept) {
		SqlSession sqlSession = null;
		int result = 0;
		try {
			sqlSession = MybatisUtils.createFactory().openSession();
			mapper = sqlSession.getMapper(DeptMapper.class);
			Dept rdept = mapper.selectDeptByName(dept.getDeptname());
			if(rdept != null && rdept.getDeptid() != dept.getDeptid()) {
				return -1;
			}
			result = mapper.updateDept(dept);
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
	public int delDeptMany(Integer[] deptids) {
		SqlSession sqlSession = null;
		int result = 0;
		try {
			sqlSession = MybatisUtils.createFactory().openSession();
			mapper = sqlSession.getMapper(DeptMapper.class);
			EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
			List<Integer> ndeptids = new ArrayList<Integer>();
			for(int i=deptids.length-1;i>=0;i--) {
				if (empMapper.selectEmpByDeptId(deptids[i]) == 0) {
					ndeptids.add(deptids[i]);
				} else {
					result++;
				}
			}
			mapper.delDeptMany(ndeptids);
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
