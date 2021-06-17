package com.xxiaoqie.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxiaoqie.pojo.Dept;
import com.xxiaoqie.pojo.Emp;
import com.xxiaoqie.service.DeptService;
import com.xxiaoqie.service.EmpService;
import com.xxiaoqie.service.impl.DeptServiceImpl;
import com.xxiaoqie.service.impl.EmpServiceImpl;

@WebServlet("/doemp")
public class EmpServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String action = request.getParameter("op");
		if("doadd".equals(action)) {
			doAdd(request,response);
		} else if("list".equals(action)) {
			list(request, response);
		} else if("toedit".equals(action)) {
			toEdit(request,response);
		} else if("toadd".equals(action)) {
			toAdd(request,response);
		} else if("doedit".equals(action)) {
			doEdit(request,response);
		} else if("del".equals(action)) {
			doDel(request,response);
		} else if("delmany".equals(action)) {
			doDelMany(request,response);
		} 
	}

	
	private void doDelMany(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] empids = request.getParameterValues("empid");
		Integer[] empid = new Integer[empids.length];
		for (int i = 0; i < empids.length; i++) {
			empid[i] = Integer.parseInt(empids[i]);
		}
		EmpService service = new EmpServiceImpl();
		int result = service.delEmpMany(empid);
		if(result > 0) {
			response.sendRedirect(request.getContextPath()+"/doemp?op=list");
		} else {
			response.getWriter().print("<script>alert('批量删除失败');history.go(-1);</script>");
		}
		
	}


	private void doEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String empname = request.getParameter("empname");
		String tel = request.getParameter("emptel");
		String empidStr = request.getParameter("empid");
		Integer empid = Integer.parseInt(empidStr);
		String deptidStr = request.getParameter("deptid");
		Integer deptid = Integer.parseInt(deptidStr);
		Emp emp = new Emp(empid,empname,tel,new Dept(deptid,null,null));
		
		EmpService service = new EmpServiceImpl();
		int result = service.editEmp(emp);
		if(result > 0) {
			response.sendRedirect(request.getContextPath()+"/doemp?op=list");
		} else {
			response.getWriter().print("<script>alert('修改失败');history.go(-1);</script>");
		}
		
	}


	protected void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		//select用
		DeptService deptservice = new DeptServiceImpl();
		List<Dept> depts = deptservice.getAllDepts();
		request.setAttribute("depts", depts);
		//拿id
		Integer empid = Integer.parseInt(request.getParameter("id"));
		
		//放值
		EmpService service = new EmpServiceImpl();
		Emp emp = service.getEmpById(empid);
		request.setAttribute("emp", emp);
		request.setAttribute("depts", depts);
		request.getRequestDispatcher("/emp_toedit.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pagesize = 10;
		String pn = request.getParameter("pn");
		String empname = request.getParameter("empname");
		String deptidstr = request.getParameter("deptid");
		Integer pageindex = 1;
		if(pn != null && !"".equals(pn)) {
			pageindex = Integer.parseInt(pn);
		}
		
		Dept dept = new Dept();
		if(deptidstr != null && !"".equals(deptidstr)) {
			dept.setDeptid(Integer.parseInt(deptidstr));
		}
		Emp emp = new Emp(null,empname,null,dept);
		
		
		EmpService service = new EmpServiceImpl();
		List<Emp> emps = service.getEmpsByPage(emp, pageindex, pagesize);
		int pagecount = service.getPageCount(emp, pagesize);
		
		DeptService deptservice = new DeptServiceImpl();
		List<Dept> depts = deptservice.getAllDepts();
		request.setAttribute("depts", depts);
		
		request.setAttribute("pagecount", pagecount);
		request.setAttribute("emps", emps);
		request.setAttribute("pageindex", pageindex);
		request.setAttribute("emp", emp);
		request.getRequestDispatcher("/emp_list.jsp").forward(request, response);
	}
	
	protected void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empname = request.getParameter("empname");
		String emptel = request.getParameter("emptel");
		Integer deptid = Integer.parseInt(request.getParameter("deptid"));
		Dept dept = new Dept();
		dept.setDeptid(deptid);
		Emp emp = new Emp(0,empname,emptel,dept);
		EmpService service = new EmpServiceImpl();
		int result = service.addEmp(emp);
		if(result > 0) {
			response.sendRedirect(request.getContextPath()+"/doemp?op=list");
		} else if(result == 0) {
			response.getWriter().print("<script>alert('添加失败');history.go(-1);</script>");
		}
	}

	protected void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DeptService service = new DeptServiceImpl();
		List<Dept> depts = service.getAllDepts();
		request.setAttribute("depts", depts);
		request.getRequestDispatcher("/emp_toadd.jsp").forward(request, response);
	}

	protected void doDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		Integer id = Integer.parseInt(idStr);
		EmpService service = new EmpServiceImpl();
		int result = service.delEmpById(id);
		if(result > 0) {
			response.sendRedirect(request.getContextPath()+"/doemp?op=list");
		} else if(result == 0) {
			response.getWriter().print("<script>alert('删除失败');history.go(-1);</script>");
		}
		
	}
}