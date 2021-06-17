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
	<h3>部门添加</h3>
	<form name="frmdept" method="post" action="dodept?op=doadd">
		部门名称：<input name="deptname" type="text"/> <br/>
		部门描述：<input name="deptdesc" type="text"/> <br/>
		<input name="btnadd" type="submit" value="提交"/>
		<input name="btnrest" type="reset" value="重置"/>
	</form>
</body>
</html>