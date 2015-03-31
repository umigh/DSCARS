<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<title>Dynamic Student Course Assignment</title>
</head>
<body>

<h1>Sections</h1>
<s:actionerror/>

<s:form action="addUser" method="post">
<table class="table">
<tr>
<td><b>Program: </b></td><td><s:select label="Program" 
		headerKey="-1" 
		headerValue="Select Role" 
		list="#{'OMSCS':'OMSCS'}" name="user.role" value = "#{'OMSCS'}" theme="simple"/></td>
		
<td><b>Term: </b></td><td><s:select label="Program" 
		headerKey="-1" 
		headerValue="Select Role" 
		list="#{'Summer2015':'Summer 2015'}" name="user.role" value = "#{'Summer2015'}" theme="simple"/></td>
</tr>		

<tr>
<td><b>Course: </b></td><td>CS 6310 Software Design and Architecture</td>
<td><b>Capacity: </b></td><td><s:textfield name="user.userName" label="User Name" theme="simple" value="200"/></td>
</tr>
<tr>
<td><b>Section: </b></td><td>CS6310-2015-02</td>
<td><b>Offered: </b></td><td><s:checkbox name="offered" fieldValue="true" theme="simple" /></td>
</tr>
<tr>
<td><b>Professor: </b></td><td><s:select label="professor" 
		headerKey="-1" 
		headerValue="Select Role" 
		list="#{'E.Feron':'E.Feron'}" name="user.role" value = "#{'E.Feron'}" theme="simple"/></td>
<td><b>Select TAs: </b></td>
<td>
<s:select id = "s4" label="Select TAs:" name="ids" list="      {'Pablo G','David F','Nando H','ALL','*'}" headerKey="0" headerValue="Select" property="selectedValues" multiple="true" theme="simple"/>
</td>
</tr>
<tr>
<tr>
<td colspan="4" align="center"><s:submit value="Preview Recommendation" align="center" theme="simple"/>
<s:submit value="Save" align="center" theme="simple"/>
<s:submit value="Run Recommendation" align="center" theme="simple"/></td>
</tr>
</table>
</s:form>
</body>
</html>