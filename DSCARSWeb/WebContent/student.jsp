<!doctype html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>DSCARS Recommendations!</title>
  <link rel="stylesheet" href="themes/jquery-ui.css">
  <link href="themes/dscars.css" rel="stylesheet"> 
  <script src="external/jquery/jquery.js"></script>
  <script src="js/jquery-ui.js"></script>
</head>
<body>
<%@include file="header.jsp" %>

<div class="ui-widget" >
<div class="ui-widget-content ui-corner-all" style="margin-top: 10px; padding: 0 .9em;">
<h2>Student Preferences.</h2>	
<s:form action="studentPreference" method="post">
<s:hidden name="studentPreference.student.studentId" value="%{#session.user.gtid}" />
<s:select
            label="Select Prorgam"
            list="programs"
            name="studentPreference.program.programId"
            listKey="programId"
            listValue="programName"
            emptyOption="false"
            headerKey="-1"
            headerValue="Select Prorgam"/>

		
<s:select
            label="Term"
            list="terms"
            name="studentPreference.term"
            emptyOption="false"
            headerKey="-1"
            headerValue="Select Term"/>		
		
<s:textfield name="studentPreference.numberOfCourses" label="Number of courses" value="2"/>

<s:select
            label="1st Preference"
            list="courses"
            name="studentPreference.firstPreference.courseId"
            listKey="courseId"
            listValue='courseName'
            emptyOption="false"
            headerKey="-1"
            headerValue="Select Course"/>
            
<s:select
            label="2nd Preference"
            list="courses"
            name="studentPreference.secondPreference.courseId"
            listKey="courseId"
            listValue='courseName'
            emptyOption="false"
            headerKey="-1"
            headerValue="Select Course"/>

<s:select
            label="3rd Preference"
            list="courses"
            name="studentPreference.thirdPreference.courseId"
            listKey="courseId"
            listValue='courseName'
            emptyOption="false"
            headerKey="-1"
            headerValue="Select Course"/>
		
<s:select
            label="4th Preference"
            list="courses"
            name="studentPreference.fourthPreference.courseId"
            listKey="courseId"
            listValue='courseName'
            emptyOption="false"
            headerKey="-1"
            headerValue="Select Course"/>
		
<table>
<tr>
<td>
<button type="submit" value="Save" id="buttonName" name="buttonName">Save Preferences</button>
<button type="submit" value="Preview" id="buttonName" name="buttonName">Preview Recommendation</button>
<button type="submit" value="Run" id="buttonName" name="buttonName">Run Recommendation</button>
</td>
</tr>
</table>
</s:form>
</div>
</div>


	
<br>
<div class="ui-state-highlight  ui-corner-all" style="margin-top: 10px; padding: 0 .9em;">
Georgia Tech Â© 2014 Georgia Institute of Technology
</div>
  <script>
   	$( "#tabs" ).tabs();
  </script>
</body>
</html>