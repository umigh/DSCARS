<!doctype html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Dynamic Student Course Assignment</title>
  <link rel="stylesheet" href="jquery-ui.css">
  <link href="themes/dscars.css" rel="stylesheet"> 
  <script src="external/jquery/jquery.js"></script>
  <script src="jquery-ui.js"></script>
  <script>
		$(document).ready(function(){
		});
</script>
</head>
<body>
<%@include file="header.jsp" %>          
<div id="tabs">
	<ul>
		<li><a href="courseAdmin">Course</a></li>
		<li><a href="instructor_admin.jsp">Instructor</a></li>
		<li><a href="student_admin.jsp">Student</a></li>
	</ul>
</div>
<%@include file="footer.jsp" %>
  <script>
   	$( "#tabs" ).tabs();
  </script>
</body>
</html>