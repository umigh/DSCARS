<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<style>
		body, input{
			font-family: Calibri, Arial;
		}
		table#user {
			border-collapse: collapse;
			width:550px;
		}
		th {
			height: 40px;
			background-color: #ffee55;
		}
	</style>
	<title>Dynamic Student Course Assignment</title>
</head>
<body>

<h1><a href="/DSCARS/">Dynamic Student Course Assignment</a></h1>
<s:actionerror/>

<s:form action="addUser" method="post">
	<s:textfield name="user.userName" label="User Name"/>
	<s:password name="user.password" label="Password"/>
	<s:select label="Role" 
		headerKey="-1" 
		headerValue="Select Role" 
		list="#{'Admin':'Admin', 'Student':'Studnet','TA':'Teaching Assistant', 'Professor':'Professor'}" name="user.role" value = "#{'Student'}"/>
	<s:submit value="Add User" align="center"/>
</s:form>


<h2>Existing Users.</h2><a href="listUsers">Refresh</a>
<table id="user" border="1">
<tr>
	<th>User Id</th>
	<th>User Name</th>
	<th>Password</th>
	<th>Role</th>
	<th>Delete</th>
</tr>
<s:iterator value="userList" var="users">
	<tr>
		<td><s:property value="userId"/></td>
		<td><s:property value="userName"/> </td>
		<td><s:property value="password"/></td>
		<td><s:property value="role"/></td>
		<td><a href="deleteUser?userId=<s:property value="userId"/>">delete</a></td>
	</tr>	
</s:iterator>
</table>
</body>
</html>