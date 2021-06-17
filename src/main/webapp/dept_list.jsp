<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload = function(){
	document.getElementById("firstCb").onclick = function(){
	    //2.获取下边列表中所有的cb
	    var cbs = document.getElementsByName("deptid");
	    //3.遍历
	    for (var i = 0; i < cbs.length; i++) {
	        //4.设置这些cbs[i]的checked状态 = firstCb.checked
	        cbs[i].checked = this.checked;
	    }
	}
}
</script>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
	<form class="form-inline" action="dodept?op=list" method="post">
		<div class="form-group">
			<label>部门名称</label> 
			<input type="text" name="deptname" value="${deptname}" class="form-control">
		</div>
		<div class="form-group">
			<label>部门描述</label> 
			<input type="text" name="deptdesc" value="${deptdesc}" class="form-control">
		</div>
		<button type="submit" class="btn btn-default">查询</button>
	</form>
	
	<form action="dodept?op=delmany" method="post">
		<table border="1" class="table table-bordered table-hover">
			<tr>
				<th><input type="checkbox" id="firstCb"></th>
				<td>部门名称</td>
				<td>部门描述</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${depts}" var="dept" varStatus="status">
				<tr>
					<td><input type="checkbox" name="deptid" value="${ dept.deptid}"></td>
					<td>${ dept.deptname }</td>
					<td>${ dept.deptdesc }</td>
					<td>
						<a href="dodept?op=del&id=${dept.deptid}" onclick="return confirm('确定要删除吗？')">删除</a>&ensp;
						<a href="dodept?op=toedit&id=${dept.deptid}">修改</a>
					</td>
				</tr>
		   </c:forEach>
		</table>
		<button class="btn btn-primary" name="submit">删除所选</button>
	</form>
	
	<ul class="pagination">
		<li><a href="dodept?op=list&pn=1&deptname=${deptname}&deptdesc=${deptdesc}">首页</a></li>
		<c:if test="${ pageindex == 1 }">
			<li class="disabled"><a href="javascript:void(0);">上一页</a></li>
		</c:if>
		<c:if test="${ pageindex > 1 }">
			<li><a href="dodept?op=list&pn=${pageindex-1}&deptname=${deptname}&deptdesc=${deptdesc}">上一页</a></li>
		</c:if>
		<c:if test="${ pageindex == pagecount }">
			<li class="disabled"><a href="javascript:void(0);">下一页</a></li>
		</c:if>
		<c:if test="${ pageindex < pagecount }">
			<li><a href="dodept?op=list&pn=${pageindex+1}&deptname=${deptname}&deptdesc=${deptdesc}">下一页</a></li>
		</c:if>
		<li><a href="dodept?op=list&pn=${pagecount}&deptname=${deptname}&deptdesc=${deptdesc}">末页</a></li>
	</ul>
</div>
</body>
</html>