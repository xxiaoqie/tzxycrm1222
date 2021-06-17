package t1222;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.xxiaoqie.mapper.DeptMapper;
import com.xxiaoqie.pojo.Dept;
import com.xxiaoqie.utils.MybatisUtils;
public class DeptTest {
	
	public static void main(String[] args) throws IOException {
		
	    
	}

	@Test
	public void testDeleteDept() {
	    SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtils.createFactory().openSession();
		    DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
		    mapper.deleteDept(55);
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
	public void testUpdateDept() {
	    SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtils.createFactory().openSession();
		    DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
		    Dept dept = new Dept();
		    dept.setDeptname("策划部"); 
		    dept.setDeptdesc("负责公司的策划");
		    dept.setDeptid(1);
		    mapper.updateDept(dept);
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
	public void testInsertDept() {
	    SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtils.createFactory().openSession();
		    DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
		    Dept dept = new Dept();
		    dept.setDeptname("bll"); 
		    dept.setDeptdesc("zyjdxw");
		    mapper.insertDept(dept);
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
	public void testSelectById() {
	    SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtils.createFactory().openSession();
		    DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
		    Dept dept = mapper.selectDeptById(4);
		    System.out.println(dept);
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
	public void testSelectByName() {
	    SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtils.createFactory().openSession();
		    DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
		    Dept dept = mapper.selectDeptByName("宣传部");
		    System.out.println(dept);
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
	public void testDeptSelectAll() {
	    SqlSession sqlSession = null;
		try {
		    sqlSession = MybatisUtils.createFactory().openSession();
		    DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
		    List<Dept> depts = mapper.findAll();
		    for (Dept dept : depts) {
				System.out.println(dept);
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
}
