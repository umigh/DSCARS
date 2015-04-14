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
  $(function () { function moveItems(origin, dest) {
	    $(origin).find(':selected').appendTo(dest);
	}
	               
	$('#add').on('click', function () {
	    moveItems('#fullTaList', '#taInsertList');
	});             
	$('#remove').on('click', function () {
	    moveItems('#taInsertList','#fullTaList');
	});
	});
</script>		
</head>
<body>

<%@include file="header.jsp" %>          
<%@include file="admin_menu.jsp" %>
<h1>Session Management</h1>
<div class="ui-widget-content ui-corner-all" style="margin-top: 10px; padding: 0 .9em;">
<s:form action="sessionAdmin" method="post">
<div >
<s:select property="programId" 
            label="Select Prorgam"
            list="programs"
            name="program.programId"
            listKey="programId"
            listValue="programName"
            emptyOption="false"
            headerKey="-1"
            headerValue="Select Prorgam" />
            
<s:select property="semesterId" id="semesterId"
            label="Semester"
            list="semesters"
            name="semester.semesterId"
            listKey="semesterId"
            listValue='year+" "+termName'
            emptyOption="false"
            headerKey="-1"
            headerValue="Select Semester" />
          
<s:select
            label="Course"
            list="courses"
            name="courseId"
            listKey="courseId"
            listValue='courseName'
            emptyOption="false"
            headerKey="-1"
            headerValue="Select Course"/>
            
            
<s:textfield name="capacity" label="Capacity" value="200"/>
<s:checkbox name="offered" fieldValue="true" label="Offered?" />

<s:select   label="Professor"
            list="profList"
            listKey="userId"
            listValue="userName"
            headerValue="Select Professor" />
		
<table>
<tr>
<td>
<s:select 
	class="fullTaList"
	id="fullTaList"
	list="taList"
	name="userId"
	listKey="userId"
	listValue='userName'
	mutliple="true"
	size="6"
	emptyOption="false"
    headerKey="-1"
	/>	
</td>


<td>
<table>
	<tr>
		<td>
			<!-- <button class="add">Add</button> -->
			<input type="button" value="Add" id="add"/>
		</td>
	</tr>
	<tr>
		<td>
			<input type="button" value="Remove" id="remove"/>
		</td>
	</tr>
</table>
</td>

<td>
<s:select 
	class="taInsertList"
	id="taInsertList"
	list="{}"
	mutliple="true"
	size="6"
    headerKey="-1"
	/>	
</td>
</tr>
</table>  		
</div>
<br/>
<div style="margin-top: 10px; padding: 0 .9em;"  align="center">
<button type="submit" value="SavePch" id="buttonName" name="buttonName" class="SavePch">Save</button>
</div>
<br/>
</s:form>
</div>
<%@include file="footer.jsp" %>
</body>