<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript">
window.onload = function(){
	document.getElementById("firstCb").onclick = function(){
	    //2.获取下边列表中所有的cb
	    var cbs = document.getElementsByName("empid");
	    //3.遍历
	    for (var i = 0; i < cbs.length; i++) {
	        //4.设置这些cbs[i]的checked状态 = firstCb.checked
	        cbs[i].checked = this.checked;
	    }
	}
}
</script>
</head>
<body>
	<div class="container">
		<div>
			<form class="form-inline" action="doemp?op=list" method="post">
				<div class="form-group">
					<label>姓名</label> 
					<input type="text" name="empname" value="${emp.empname}" class="form-control">
				</div>
				<div class="form-group">
					<label>部门</label> 
					<select name="deptid" class="selectpicker">
						<option value="0">请选择</option>
						<c:forEach items="${depts}" var="dept" varStatus="status">
							<c:if test="${ dept.deptid eq emp.dept.deptid }">
								<option value="${ dept.deptid }" selected="selected">
							</c:if>
							<c:if test="${ dept.deptid != emp.dept.deptid }">
								<option value="${ dept.deptid }">
							</c:if>
							${ dept.deptname }
						</option>
						</c:forEach>
					</select>
				</div>
				<button type="submit" class="btn btn-default">查询</button>
			</form>
		</div>
		
		<form action="doemp?op=delmany" method="post">
		<table border="1" class="table table-bordered table-hover">
			<tr class="success">
				<th><input type="checkbox" id="firstCb"></th>
				<th>姓名</th>
				<th>电话</th>
				<th>部门</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${emps}" var="emp" varStatus="status">
				<tr>
					<td><input type="checkbox" name="empid" value="${emp.empid}"></td>
					<td>${ emp.empname }</td>
					<td>${ emp.tel }</td>
					<td>${ emp.dept.deptname }</td>
					<td>
						<a href="doemp?op=del&id=${ emp.empid }" onclick="return confirm('确定要删除吗？')">删除</a>&ensp;
						<a href="doemp?op=toedit&id=${ emp.empid }">修改</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<button class="btn btn-primary" name="submit">删除所选</button>
		</form>
		<ul class="pagination">
			<li><a href="doemp?op=list&pn=1&empname=${emp.empname}&deptid=${emp.dept.deptid}">首页</a></li>
			<c:if test="${ pageindex == 1 }">
				<li class="disabled"><a href="javascript:void(0);">上一页</a></li>
			</c:if>
			<c:if test="${ pageindex > 1 }">
				<li><a href="doemp?op=list&pn=${pageindex-1}&empname=${emp.empname}&deptid=${emp.dept.deptid}">上一页</a></li>
			</c:if>
			<c:if test="${ pageindex == pagecount }">
				<li class="disabled"><a href="javascript:void(0);">下一页</a></li>
			</c:if>
			<c:if test="${ pageindex < pagecount }">
				<li><a href="doemp?op=list&pn=${pageindex+1}&empname=${emp.empname}&deptid=${emp.dept.deptid}">下一页</a></li>
			</c:if>
			<li><a href="doemp?op=list&pn=${pagecount}&empname=${emp.empname}&deptid=${emp.dept.deptid}">末页</a></li>
		</ul>
	</div>
</body>
</html>