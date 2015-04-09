<!doctype html>
<%@page import="edu.gatech.omscs.dscars.model.Student"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html lang="en">
<head>
  <meta charset="utf-8">
  <title>DSCARS</title>
  <link rel="stylesheet" href="themes/jquery-ui.css">
  <link href="themes/dscars.css" rel="stylesheet"> 
  <script src="external/jquery/jquery.js"></script>
  <script src="js/jquery-ui.js"></script>
  <script>
		$(document).ready(function(){
			$('#semesterId').change(function() {
        		this.form.submit();
   			 });
   			$('.add').on('click', function() {			    
			    //$('#pchSubs').append("<tr><td style='background: #CCCCCC'>CourseId</td><td style="background: #CCCCCC">Course Name </td><td style="background: #CCCCCC">Priority </td><td style="background: #CCCCCC">Instructor </td><td style="background: #CCCCCC">max class size </td><td style="background: #CCCCCC">Max TAs</td><td style="background: #CCCCCC">Demand </td><td style="background: #CCCCCC">Date </td></tr>");
			    alert("Save?");
			    return false;			    
			});
			
			$('#moveDown').on('click', function() {	
				    var thisRow = $(this).closest('tr');
				    var nextRow = thisRow.next();
				    if (nextRow.length) {
				        nextRow.after(thisRow);
				    }
			});
			
			$('#moveUp').on('click', function() {	
				   var thisRow = $(this).closest('tr');
				    var prevRow = thisRow.prev();
				    if (prevRow.length) {
				        prevRow.before(thisRow);
				    }
			});

		});
		
		function moveup() {
			jQuery('#rowid').next().after(jQuery('#rowid'));
		}
  </script>

</head>
<body>
<%@include file="header.jsp" %>

<div class="ui-widget" >
<div class="ui-widget-content ui-corner-all" style="margin-top: 10px; padding: 0 .9em;">
<h2>Student Preferences Course History.</h2>	
<s:form action="pch" method="post">
<table>
<tr><td>Prorgam</td><td><s:select property="programId" 
            label="Select Prorgam"
            list="programs"
            name="programId"
            listKey="programId"
            listValue="programName"
            emptyOption="false"
			 theme="simple"/></td>
            
            <td>Semester</td><td><s:select property="semesterId" id="semesterId"
            label="Semester"
            list="semesters"
            name="semesterId"
            listKey="semesterId"
            listValue='year+" "+termName'
            emptyOption="false"
            headerKey="-1"
            headerValue="Select Semester" theme="simple"/></td>
     </tr>
</table>
<br/>
<table>     
     <tr>
     <td>Number Of Courses</td>
            <td>
            <input type="number" name="pch.numCoursesDesired"  id="pch.numCoursesDesired"  min="1" max="2" step="1" value='<s:property value="pch.numCoursesDesired"/>'/>
     </td>
     <s:if test="%{sections!=null}">
     <td>Course:</td>   
     <td><s:select
            label="Course"
            list="sections"
            name="sectionId"
            listKey="sectionId"
            listValue='course.courseId+" "+course.courseName +"."+sectionId'
            emptyOption="false"
            headerKey="-1"
            headerValue="Select Course" theme="simple"/></td> 
      <td>
      <button type="submit" value="AddCourse" id="buttonName" name="buttonName">Add</button>
      </td>
      </s:if>
     </tr>
</table>


<div class="ui-widget-content ui-corner-all" style="margin-top: 10px; padding: 0 .9em;">
<table id="pchSubs">
<th class="ui-state-highlight">Course Id</th><th class="ui-state-highlight">Course Name</th>
<th class="ui-state-highlight">Priority</th><th class="ui-state-highlight">Instructor</th>
<th class="ui-state-highlight">Max Class Size</th><th class="ui-state-highlight">Max TAs</th>
<th class="ui-state-highlight">Demand</th><th class="ui-state-highlight">Recommended</th><th class="ui-state-highlight">Date</th>
<s:iterator value="pch.pchSubs" status="subStatus">
  <tr id="section.sectionId">
  	<s:if test="#subStatus.even == true">
        <td style="background: #CCCCCC"><s:property value="section.course.courseId"/> </td>
    	<td style="background: #CCCCCC"><s:property value="section.course.courseName"/> </td>
    	<td style="background: #CCCCCC">
    	<input type="number" name="priority"  id="priority"  min="1" max="5" step="1" value='<s:property value="priority"/>'/></td>
    	<td style="background: #CCCCCC"><s:property value="section.instructor.name"/> </td>
    	<td style="background: #CCCCCC"><s:property value="section.maxClassSize"/> </td>
    	<td style="background: #CCCCCC"><s:property value="section.maxTas"/> </td>
    	<td style="background: #CCCCCC"><s:property value="section.courseDemand"/> </td>
    	<td style="background: #CCCCCC" align="center">
    	<s:if test="%{recommended==true}">
    	<input type="checkbox" name="recommended" value="true" checked>
    	</s:if>
    	<s:else>
    	<input type="checkbox" name="recommended" value="false">
    	</s:else>
    	</td>
    	<td style="background: #CCCCCC"><s:property value="section.date"/> </td>
    	<td style="background: #CCCCCC"><a href="#" id="moveUp">Up</a></td>
    	<td style="background: #CCCCCC"><a href="#" id="moveDown">Down</a></td>
    </s:if>
    <s:elseif test="#subStatus.even == false">
        <td><s:property value="section.course.courseId"/> </td>
    	<td><s:property value="section.course.courseName"/> </td>
    	<td><input type="number" name="priority"  id="priority"  min="1" max="5" step="1" value='<s:property value="priority"/>'/></td>
    	<td><s:property value="section.instructor.name"/> </td>
    	<td><s:property value="section.maxClassSize"/> </td>
    	<td><s:property value="section.maxTas"/> </td>
    	<td><s:property value="section.courseDemand"/> </td>
    	<td align="center">
    	<s:if test="%{recommended==true}">
    	<input type="checkbox" name="recommended" value="true" checked>
    	</s:if>
    	<s:else>
    	<input type="checkbox" name="recommended" value="false">
    	</s:else>
    	</td>
    	<td><s:property value="section.date"/> </td>
    	<td style="background: #CCCCCC"><a href="#" id="moveUp">Up</a></td>
    	<td style="background: #CCCCCC"><a href="#" id="moveDown">Down</a></td>
    </s:elseif>
  </tr>
</s:iterator>
</table>
</div>
<br/></div>
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