package com.xxiaoqie.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.xxiaoqie.dao.DeptDao;
import com.xxiaoqie.pojo.Dept;

public class DeptDaoImpl implements DeptDao {
	private String url = "jdbc:mysql://106.14.148.200:3306/bll";
	private String uid = "root";
	private String pwd = "123456";
	Connection conn = null;
	PreparedStatement stat = null;
	ResultSet rs = null;
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	@Override
	public int insertDept(Dept dept) {
		int result = 0;
		try {
			conn = DriverManager.getConnection(url,uid,pwd);
			String sql = "insert into dept(deptname,deptdesc) values(?,?)";
			stat = conn.prepareStatement(sql);
			stat.setString(1, dept.getDeptname());
			stat.setString(2, dept.getDeptdesc());
			result = stat.executeUpdate();
			if(result > 0) {
				System.out.println("insert ok");
			} else {
				System.out.println("insert no ok");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	@Override
	public List<Dept> selectAll() {
		List<Dept> depts = new ArrayList<Dept>();
		try {
			conn = DriverManager.getConnection(url,uid,pwd);
			String sql = "select * from dept";
			stat = conn.prepareStatement(sql);
			rs = stat.executeQuery();
			while(rs.next()) {
				Dept dept = new Dept();
				dept.setDeptid(rs.getInt("deptid"));
				dept.setDeptname(rs.getString("deptname"));
				dept.setDeptdesc(rs.getString("deptdesc"));
				depts.add(dept);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return depts;
	}

	@Override
	public Dept selectDeptByName(String name) {
		Dept dept = null;
		try {
			conn = DriverManager.getConnection(url,uid,pwd);
			String sql = "select * from dept where deptname =?";
			stat = conn.prepareStatement(sql);
			stat.setString(1, name);
			rs = stat.executeQuery();
			if(rs.next()) {
				dept = new Dept();
				dept.setDeptid(rs.getInt("deptid"));
				dept.setDeptname(rs.getString("deptname"));
				dept.setDeptdesc(rs.getString("deptdesc"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return dept;
	}

}
