<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>员工修改</h3>
	<form name="frmdept" method="post" action="doemp?op=doedit">
		<input name="empid" type="hidden" value="${emp.empid}">
		姓名：<input name="empname" type="text" value="${emp.empname}"/> <br/>
		电话：<input name="emptel" type="text" value="${emp.tel}"/> <br/>
		部门：<select name="deptid"> 
				<c:forEach items="${depts}" var="dept" varStatus="status">
					<c:if test="${ dept.deptid eq emp.dept.deptid }">
						<option selected="selected" value="${dept.deptid}">${dept.deptname}</option>
					</c:if>
					<c:if test="${ dept.deptid ne emp.dept.deptid }"></c:if>
					<option value="${ dept.deptid }">${ dept.deptname }</option>
				</c:forEach>
			</select>
		<input name="btnadd" type="submit" value="修改"/>
		<input name="btnrest" type="reset" value="重置"/>
	</form>
</body>
</html>