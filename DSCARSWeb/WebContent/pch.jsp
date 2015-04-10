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
			$('#semesterId').change(function() {
        		this.form.submit();
   			 });
   			$('.add').on('click', function() {			    
			    //$('#pchSubs').append("<tr><td style='background: #CCCCCC'>CourseId</td><td style="background: #CCCCCC">Course Name </td><td style="background: #CCCCCC">Priority </td><td style="background: #CCCCCC">Instructor </td><td style="background: #CCCCCC">max class size </td><td style="background: #CCCCCC">Max TAs</td><td style="background: #CCCCCC">Demand </td><td style="background: #CCCCCC">Date </td></tr>");
			    alert("Save?");
			    return false;			    
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
<h2>Student Preferences Course History.</h2>	
<s:form action="pch" method="post">
<input type="hidden"  id="pchSubIdcount" name="pchSubIdcount" />
<input type="hidden"  id="pchSubIds" name="pchSubIds" />
<table>
<tr><td>Program</td><td><s:select property="programId" 
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
<table id="pchSubsTable">
<tr class="ui-widget-header ui-corner-all" style="margin-top: 10px; padding: 0 .9em;">
<th hidden="true">Section Id</th>
<th >Course Id</th>
<th >Course Name</th>
<th >PchSubId</th>
<th >Instructor</th>
<th >Max Class Size</th>
<th >Max TAs</th>
<th >Demand</th>
<th >Recommended</th>
<th >Date</th>
<th colspan="4">Action</th>
</tr>
<s:iterator value="pch.pchSubs" status="sub">
  <tr class="ui-corner-all" style="margin-top: 10px; padding: 0 .9em;">
  		<td hidden="true"><s:property value="pchSubId"/> </td>
  		<td><s:property value="section.course.courseId"/> </td>
    	<td><s:property value="section.course.courseName"/> </td>
    	<td><s:property value="pchSubId"/> </td>
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
    	<td width="2%"><a class="move up" href="javascript:void(0)"><span class="ui-icon ui-icon-circle-arrow-n"></span></a><td>
    	<td width="2%"><a class="move down" href="javascript:void(0)"><span class="ui-icon ui-icon-circle-arrow-s"></span></a></td>
    	<td width="2%"><a class="delete" href="javascript:void(0)"><span class="ui-icon ui-icon-trash"></span></a></td>
  </tr>
</s:iterator>
</table>
</div>
<br/>
<div style="margin-top: 10px; padding: 0 .9em;"  align="center">
<button type="submit" value="SavePch" id="buttonName" name="buttonName" class="SavePch">Save</button>
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