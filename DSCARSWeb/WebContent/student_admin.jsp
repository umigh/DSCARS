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
<h2>Student Preferences Course History.</h2>	
<s:form action="studentAdmin" method="post">
<input type="hidden"  id="pchSubIds" name="pchSubIds" />
<table id="selectTable">
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
            <td><a class="go" href="javascript:void(0)"><span class="ui-icon ui-icon-circle-triangle-e"></span></a></td>
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
<th >Course</th>
<th >Priority</th>
<th >Instructor</th>
<th >Max Class Size</th>
<th >Max TAs</th>
<th >Demand</th>
<th >Recommended</th>
<th >Date</th>
</tr>

  
<s:iterator value="pchList" status="pch">
	<s:iterator value="pchSubs" status="pchSub">
	  <tr class="ui-corner-all" style="margin-top: 10px; padding: 0 .9em;">
	  		<td><s:property value="student.contact.firstName+' '+student.contact.lastName"/> </td>
	  		<td><s:property value="section.course.courseId+' '+section.course.courseName"/> </td>
	    	<td align="center"><s:property value="priority"/> </td>
	    	<td><s:property value="section.instructor.name"/> </td>
	    	<td><s:property value="section.maxClassSize"/> </td>
	    	<td><s:property value="section.maxTas"/> </td>
	    	<td><s:property value="section.courseDemand"/> </td>
	    	<td align="center">
	    	<s:if test="%{recommendedDate!=null}">
	    	<input type="checkbox" name="recommended" value="true" checked>
	    	</s:if>
	    	<s:else>
	    	<input type="checkbox" name="recommended" value="false">
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