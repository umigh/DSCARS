<!doctype html>
<%@page import="java.util.Map"%>
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
			$('#semesterId').change(function() {
        		this.form.submit();
   			 });
   			 
   			$('.AddCourse').on('click', function() { 
        		if($('#sectionId').val()<1) {
        			alert('Please select a course!');
        			return false;
        		}        		
   			 });
   			 
			
			 $('#pchSubsTable a.move').click(function() 
			    {
			        var $row = $(this).parents('tr:first');
				    var $link = $(this);
				    // count all tr
				    var count = $('table tr').length;
				    // if tr isn't the first
				    if($row.index() !== 0) {
				        // if direction is up and there is a tr above
				        if($link.hasClass('up') && $row.index() > 1) {
				            $row.insertBefore($row.prev());
				        } 
				        // if direction is down and there is a tr below
				        else if($link.hasClass('down') && $row.index() < count - 1) {
				            $row.insertAfter($row.next());
				        }
				    }
			 });
			 
			 
			  

			$('.SavePch').on('click', function() {
				var count = $('#pchSubsTable tr').length;
				var pchSubIds="";
			    $('#pchSubIdcount').val(count-1)			     
			    $("#pchSubsTable tr:gt(0)").each(function () {
				        var this_row = $(this);
				        var pchSubId = $.trim(this_row.find('td:eq(0)').html());//td:eq(0) means first td of this row				        
				        pchSubIds=pchSubIds+","+pchSubId;
				});
			    $('#pchSubIds').val(pchSubIds);
			});
			
			$('#pchSubsTable a.delete').click(function() 
			    {
			    	var $row = $(this).parents('tr:first');
			        $row.remove();
			    	return false;
			 });
		});
  </script>

</head>
<body>
<%@include file="header.jsp" %>
<div class="ui-widget" >
<div class="ui-widget-content ui-corner-all" style="margin-top: 10px; padding: 0 .9em;">
<h2>Student Preferred Courses</h2>	
<s:form action="pch" method="post">
<input type="hidden"  id="pchSubIdcount" name="pchSubIdcount" />
<input type="hidden"  id="pchSubIds" name="pchSubIds" />
<table>
<tr>
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
     <td>Number Of Courses Completed</td>
     		<td><input type="number" min="1" max="2" step="1" name="pch.student.numCoursesCompleted"  id="pch.numCoursesDesired"   value='<s:property value="pch.student.numCoursesCompleted"/>' disabled="disabled"/ width="2" maxlength="2"></td>                        
     </td>
     <td>Number Of Courses Desired</td>
            <td>
            <input type="number" name="pch.numCoursesDesired"  id="pch.numCoursesDesired"  min="1" max="2" step="1" value='<s:property value="pch.numCoursesDesired"/>'/>
     </td>
     <s:if test="%{sections!=null}">
     <td>Course:</td>   
     <td><s:select property="sectionId" id="sectionId"
            label="Course"
            list="sections"
            name="sectionId"
            listKey="sectionId"
            listValue='course.courseId+" "+course.courseName'
            emptyOption="false"
            headerKey="-1"
            headerValue="Select Course" theme="simple"/></td> 
      <td>
      <button type="submit" value="AddCourse" id="buttonName" name="buttonName" class="AddCourse">Add</button>
      </td>
      </s:if>
     </tr>
</table>


<div class="ui-widget-content ui-corner-all" style="margin-top: 10px; padding: 0 .9em;">
<table id="pchSubsTable">
<tr class="ui-widget-header ui-corner-all" style="margin-top: 10px; padding: 0 .9em;">
<th hidden="true">Section Id</th>
<th >Course</th>
<th >Program</th>
<th >Instructor</th>
<th >Max Class Size</th>
<th> Recommended Class Size</th>
<th >Demand</th>
<th >Recommended</th>
<th >Date</th>
<th colspan="4">Action</th>
</tr>
<s:iterator value="pch.pchSubs" status="sub">
  <tr class="ui-corner-all" style="margin-top: 10px; padding: 0 .9em;">
  		<td hidden="true"><s:property value="pchSubId"/> </td>
  		<td><s:property value="section.course.courseId+' '+section.course.courseName"/> </td>
    	<td><s:property value="section.program.programName"/> </td>
    	<td><s:property value="section.instructor.contact.firstName+' '+section.instructor.contact.lastName"/> </td>
    	<td align="center"><s:property value="section.maxClassSize"/> </td>
    	<td align="center"><s:property value="section.generatedClassSize"/> </td>
    	<td align="center"><s:property value="%{demand[section.sectionId]}" /></td>
    	<td align="center" >
    	<s:if test="%{recommendedDate!=null}">
    	<input type="checkbox" name="recommended" value="true" disabled="disabled" checked="checked" >
    	</s:if>
    	<s:else>
    	<input type="checkbox" name="recommended" value="false" disabled="disabled">
    	</s:else>
    	</td>
    	<td><s:property value="section.date"/> </td>
    	<td width="2%"><a class="move up" href="javascript:void(0)"><span class="ui-icon ui-icon-circle-arrow-n"></span></a><td>
    	<td width="2%"><a class="move down" href="javascript:void(0)"><span class="ui-icon ui-icon-circle-arrow-s"></span></a></td>
    	<td width="2%"><a class="delete" href="javascript:void(0)"><span class="ui-icon ui-icon-trash"></span></a></td>
  </tr>
</s:iterator>
</table>
</div>
<br/>
<div style="margin-top: 10px; padding: 0 .9em;"  align="center">
<s:if test="%{pch.pchSubs!=null and pch.pchSubs.size()>0}">
	<button type="submit" value="SavePch" id="buttonName" name="buttonName" class="SavePch">Save Preference</button>
	&nbsp;&nbsp;
	<button type="submit" value="RunRecommendation" id="buttonName" name="buttonName" class="SavePch">Run Recommendation</button>
</s:if>
</div>
<br/><br/>
</s:form>
</div>
</div>
<%@include file="footer.jsp" %>
  <script>
   	$( "#tabs" ).tabs();
  </script>
</body>
</html>