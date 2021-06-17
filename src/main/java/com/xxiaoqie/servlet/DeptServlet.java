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

@WebServlet("/dodept")
public class DeptServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String action = request.getParameter("op");
		if("doadd".equals(action)) {
			doAdd(request,response);
		} else if("list".equals(action)) {
			list(request, response);
		} else if("toedit".equals(action)) {
			toEdit(request, response);
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
		String[] deptids = request.getParameterValues("deptid");
		Integer[] deptid = new Integer[deptids.length];
		for (int i = 0; i < deptids.length; i++) {
			deptid[i] = Integer.parseInt(deptids[i]);
		}
		DeptService service = new DeptServiceImpl();
		int result = service.delDeptMany(deptid);
		if(result == 0) {
			response.sendRedirect(request.getContextPath()+"/dodept?op=list");
		} else {
			response.getWriter().print(String.format("<script>alert('批量删除失败，有%d个部门有员工');history.go(-1);</script>", result));
		}
		
	}


	private void doEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String deptname = request.getParameter("deptname");
		String deptdesc = request.getParameter("deptdesc");
		String deptidStr = request.getParameter("deptid");
		Integer deptid = Integer.parseInt(deptidStr);

		Dept dept = new Dept(deptid,deptname,deptdesc);
		
		DeptService service = new DeptServiceImpl();
		int result = service.editDept(dept);
		
		if(result > 0) {
			response.sendRedirect(request.getContextPath()+"/dodept?op=list");
		} else if(result == 0){
			response.getWriter().print("<script>alert('修改失败');history.go(-1);</script>");
		} else {
			response.getWriter().print("<script>alert('部门名称重复');history.go(-1);</script>");
		}
		
	}


	private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/dept_toadd.jsp").forward(request, response);
		
	}

	protected void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		int deptid = Integer.parseInt(request.getParameter("id"));
		DeptService service = new DeptServiceImpl();
		Dept dept = service.getDeptById(deptid);
		
		
		request.setAttribute("dept", dept);
		request.getRequestDispatcher("/dept_toedit.jsp").forward(request, response);
		
	}

	protected void doDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		Integer id = Integer.parseInt(idStr);
		DeptService service = new DeptServiceImpl();
		int result = service.delDept(id);
		if(result > 0) {
			response.sendRedirect(request.getContextPath()+"/dodept?op=list");
		} else if(result == 0) {
			response.getWriter().print("<script>alert('删除失败');history.go(-1);</script>");
		} else if(result == -1) {
			response.getWriter().print("<script>alert('该部门下存在员工');history.go(-1);</script>");
		}
	}

	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pagesize = 10;
		String pn = request.getParameter("pn");
		String deptname = request.getParameter("deptname");
		String deptdesc = request.getParameter("deptdesc");
		Integer pageindex = 1;
		if(pn != null && !"".equals(pn)) {
			pageindex = Integer.parseInt(pn);
		}
		
		Dept dept = new Dept(null,deptname,deptdesc);
		DeptService service = new DeptServiceImpl();
		List<Dept> depts = service.getDeptsByPage(dept, pageindex, pagesize);
		int pagecount = service.getPageCount(dept, pagesize);
		
		request.setAttribute("deptname", deptname);
		request.setAttribute("deptdesc", deptdesc);
		request.setAttribute("depts", depts);
		request.setAttribute("pagecount", pagecount);
		request.setAttribute("pageindex", pageindex);
		request.getRequestDispatcher("/dept_list.jsp").forward(request, response);
	}
	
	protected void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deptname = request.getParameter("deptname");
		String deptdesc = request.getParameter("deptdesc");
		Dept dept = new Dept(null,deptname,deptdesc);
		DeptService service = new DeptServiceImpl();
		int result = service.addDept(dept);
		if(result > 0) {
			response.sendRedirect(request.getContextPath()+"/dodept?op=list");
		} else if(result == 0) {
			response.getWriter().print("<script>alert('添加失败');history.go(-1);</script>");
		} else {
			response.getWriter().print("<script>alert('添加失败，部门重复！');history.go(-1);</script>");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
