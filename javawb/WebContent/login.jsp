<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

</head>
<center><h3>用户登录</h3></center>
<body style="text-align:center;">
<form action="${pageContext.request.contextPath}/LoginServlet"method="post">
<table border="1" width="600px" cellpadding="0" cellspacing="0" align="center">
<tr>
  <td heigh="30" align="center">用户名：</td>
  <td>&nbsp;&nbsp;
  <input type="text" align="left" name="username"/>${errerMsg }</td>
</tr>
<tr>
  <td heigh="30" align="center">密  &nbsp; 码:</td>
  <td>&nbsp;&nbsp;
  <input type="password" align="left" name="password"/></td>
</tr>
<tr>
    <td height="35" align="center">
      自动登录
    </td>
    <td>
        <input type="radio" name="autologin" value="${60*60*24*31}"/>一个月
        <input type="radio" name="autologin" value="${60*60*24*31*3}"/>三个月
        <input type="radio" name="autologin" value="${60*60*24*31*6}"/>半年
        <input type="radio" name="autologin" value="${60*60*24*31*12}"/>一年
    </td>
</tr>
<tr>
   <td height="30" colspan="2" align="center">
      <input type="submit" value="登录" />
      &nbsp;&nbsp;&nbsp;&nbsp;
      <input type="reset" value="重置">
   </td>
</tr>
</table>
</form>
</body>
</html>