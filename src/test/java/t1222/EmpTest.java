package t1222;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.xxiaoqie.mapper.EmpMapper;
import com.xxiaoqie.pojo.Dept;
import com.xxiaoqie.pojo.Emp;
import com.xxiaoqie.utils.MybatisUtils;

public class EmpTest {

	@Test
	public void testupdateEmp() {
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtils.createFactory().openSession();
			EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
			Dept dept = new Dept(); dept.setDeptid(3);
			Emp emp = new Emp(1, "blldsb", "18757078727", dept);
			mapper.updateEmp(emp);
			sqlSession.commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			if(sqlSession!=null) {
				sqlSession.close();
			}
		}
	}
	
	@Test
	public void testSelectAll() {
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtils.createFactory().openSession();
			EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
			List<Emp> emps = mapper.selectAll();
			for (Emp emp : emps) {
				System.out.println(emp);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(sqlSession!=null) {
				sqlSession.close();
			}
		}
	}
	
	@Test
	public void testInsert() {
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtils.createFactory().openSession();
			EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
			Emp emp = new Emp();
			emp.setEmpname("bll");
			emp.setTel("18757078727");
			Dept dept = new Dept();
			dept.setDeptid(1);
			emp.setDept(dept);
			mapper.insertEmp(emp);
			sqlSession.commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			if(sqlSession!=null) {
				sqlSession.close();
			}
		}
	}
}
