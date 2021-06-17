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
	<h3>员工添加</h3>
	<form name="frmdept" method="post" action="doemp?op=doadd">
		姓名：<input name="empname" type="text"/> <br/>
		电话：<input name="emptel" type="text"/> <br/>
		部门：<select name="deptid"> 
				<option value="0">请选择</option>
				<c:forEach items="${depts}" var="dept" varStatus="status">
					<option value="${ dept.deptid }">${ dept.deptname }</option>
				</c:forEach>
			</select>
		<input name="btnadd" type="submit" value="提交"/>
		<input name="btnrest" type="reset" value="重置"/>
	</form>
</body>
</html>