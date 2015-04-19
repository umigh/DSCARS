<!doctype html>
<%@page import="edu.gatech.omscs.dscars.model.Student"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html lang="en">
<head>
  <meta charset="utf-8">
  <title>DSCARS</title>
  <link href="jquery-ui.mini.css" rel="stylesheet">
  <link href="themes/dscars.css" rel="stylesheet"> 
  <script src="external/jquery/jquery.js"></script>
  <script>
  	$(document).ready(function(){
			$('#semesterId').change(function() {
        		this.form.submit();
   			 });

			$(document).ready(function()
					{
						$('#search').keyup(function()
						{
							searchTable($(this).val());
						});
					});

					function searchTable(inputVal)
					{
						var table = $('#studentPchTable');
						table.find('tr').each(function(index, row)
						{
							var allCells = $(row).find('td');
							if(allCells.length > 0)
							{
								var found = false;
								allCells.each(function(index, td)
								{
									var regExp = new RegExp(inputVal, 'i');
									if(regExp.test($(td).text()))
									{
										found = true;
										return false;
									}
								});
								if(found == true)$(row).show();else $(row).hide();
							}
						});
					}
		});
  </script>

</head>
<body>
<%@include file="header.jsp" %>          
<%@include file="admin_menu.jsp" %>
<div class="ui-widget" >
<div class="ui-widget-content ui-corner-all" style="margin-top: 10px; padding: 0 .9em;">
<h2>Student Recommendation.</h2>	
<s:form action="studentAdmin" method="post">
<input type="hidden"  id="pchSubIds" name="pchSubIds" />
<table id="selectTable">
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
            <td>
<s:if test="%{semesterId!=null and semesterId>0}">
	<button type="submit" value="RunRecommendation" id="buttonName" name="buttonName" class="SavePch" class="ui-icon ui-icon-circle-triangle-e">Run Recommendations</button>
</s:if>
</td>

            
     </tr>
</table>
<br/>
<div style="margin-top: 10px; padding: 0 .9em;" align="right">
Search: <input type="text" id="search" name="search" class="ui-widget-content ui-corner-all" >
</div>
<div class="ui-widget-content ui-corner-all" style="margin-top: 10px; padding: 0 .9em;">
<table id="studentPchTable">
<tr class="ui-widget-header ui-corner-all" style="margin-top: 10px; padding: 0 .9em;">
<th>Student</th>
<th width="5%">Completed courses</th>
<th width="5%">Desired courses</th>
<th >Course</th>
<th >Program</th>
<th >Instructor</th>
<th >Priority</th>
<th >Max Class Size</th>
<th width="10%"> Recommended Class Size</th>
<th >Demand</th>
<th >Recommended</th>
<th >Date</th>
</tr>

  
<s:iterator value="pchList" status="pch">
	<s:iterator value="pchSubs" status="pchSub">
	  <tr class="ui-corner-all" style="margin-top: 10px; padding: 0 .9em;">
	  		<td><s:property value="student.contact.firstName+' '+student.contact.lastName"/> </td>
	  		<td align="center"><s:property value="student.numCoursesCompleted"/> </td>
	  		<td align="center"><s:property value="numCoursesDesired"/> </td>
	  		<td><s:property value="section.course.courseId+' '+section.course.courseName"/> </td>
	    	<td><s:property value="section.program.programName"/> </td>
	    	<td><s:property value="section.instructor.contact.firstName+' '+section.instructor.contact.lastName"/> </td>
	    	<td align="center"><s:property value="priority"/> </td>
	    	<td align="center"><s:property value="section.maxClassSize"/> </td>
	    	<td align="center"><s:property value="section.generatedClassSize"/> </td>
	    	<td align="center"><s:property value="%{demand[section.sectionId]}"/> </td>
	    	<td align="center" >
	    	<s:if test="%{recommendedDate!=null}">
	    	<input type="checkbox" name="recommended" value="true" disabled="disabled" checked="checked" >
	    	</s:if>
	    	<s:else>
	    	<input type="checkbox" name="recommended" value="false" disabled="disabled">
	    	</s:else>
	    	</td>
	    	<td><s:property value="section.date"/> </td>
	  </tr>
	</s:iterator>
</s:iterator>
</table>
</div>
<br/>
<br/><br/>
</s:form>
</div>
</div>
<%@include file="footer.jsp" %>
</body>
</html>