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
  <script src="jquery-ui.min.js"></script>
  <script>
		$(document).ready(function(){			
			$('#sectionAdmin').on('click', function() {	
				var link = $("#sectionAdmin").attr("href");
             	link = link +'?semesterId=' + $('#semesterId').val();
            	$("#sectionAdmin").attr("href", link);
        	});
        	
        	$('#sessionAdmin').on('click', function() {	
				var link = $("#sectionAdd").attr("href");
             	link = link +'?semesterId=' + $('#semesterId').val();
            	$("#sectionAdd").attr("href", link);
        	});
        	
        	$('#studentAdmin').on('click', function() {	
				var link = $("#studentAdmin").attr("href");
             	link = link +'?semesterId=' + $('#semesterId').val();
            	$("#studentAdmin").attr("href", link);
        	});
		});
   </script>

	<style>
	li {
	    display: inline;
	}
	</style>
</head>
<body>     
<ul class="ui-widget-header  ui-corner-all" style="margin-bottom: 10px; padding: 0 .9em;" >
  <li><a id="sectionAdmin" href="sectionAdmin" class="menuItem"><font size="2">Course Management</font></a>&nbsp&nbsp</li>
  <li><a id="studentAdmin" href="studentAdmin" class="menuItem"><font size="2">Student Recommendation</font></a>&nbsp&nbsp</li>
  <li><a href="logoff"><font size="2">Logoff</font></a>&nbsp&nbsp</li>
</ul>
</body>