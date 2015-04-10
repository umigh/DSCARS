<!doctype html>
<%@page import="edu.gatech.omscs.dscars.model.Student"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html lang="en">
<head>
  <meta charset="utf-8">
  <title>DSCARS</title>
  <link href="jquery-ui.css" rel="stylesheet">
  <link href="themes/dscars.css" rel="stylesheet"> 
  <script src="external/jquery/jquery.js"></script>
  <script>
  	$(document).ready(function(){
		});
  </script>

</head>
<body>
<%@include file="header.jsp" %>          
<%@include file="admin_menu.jsp" %>
<div class="ui-widget" >
<div class="ui-widget-content ui-corner-all" style="margin-top: 10px; padding: 0 .9em;">
<h2>Student Preferences Course History.</h2>	

</div>
</div>
<%@include file="footer.jsp" %>
</body>
</html>