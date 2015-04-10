<!doctype html>
<html lang="us">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<head>
	<meta charset="utf-8">
	<title>DSCARS</title>
	<link href="jquery-ui.css" rel="stylesheet">
	<link href="themes/dscars.css" rel="stylesheet"> 
</head>
<body>

<header class="ui-widget-header  ui-corner-all" style="margin-bottom: 10px; padding: 0 .9em;">
<table>
<tr>
<td width="60%"><h1>Georgia Institute of Technology</h1></td>
<td align="right"><p>Welcome <s:property value="%{#session.user.contact.firstName}"/> (<s:property value="%{#session.user.role}"/>) !</p></td><td><p>|</p></td><td align="right"><p><a href="logoff">Logoff</a></p></td><td><p>|</p></td><td><p>Last Login: <s:property value="%{#session.lastLoginDate}"/></p></td>
</tr>
</table>
</header>
<s:if test="hasActionErrors()">
<div class="ui-widget">
   <div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">
   	<p><s:actionerror/></p>
   </div>
 </div>
</s:if>
<s:if test="hasActionMessages()">
   <div class="ui-state-highlight ui-corner-all" style="padding: 0 .7em;">
	   <p><s:actionmessage/></p>
   </div>
</s:if>


