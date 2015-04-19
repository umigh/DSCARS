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
			$(document).ready(function()
			{
				$('#search').keyup(function()
				{
					searchTable($(this).val());
				});
				
				$('#semesterId').change(function() {
	        		this.form.submit();
	   			 });
   			 
	   			$('#sectionAdd').on('click', function() {	
					var link = $("#sectionAdd").attr("href");
	            	$("#sectionAdd").attr("href", link);
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
  </script>

</head>
<body>
<%@include file="header.jsp" %>          
<%@include file="admin_menu.jsp" %>
<div class="ui-widget" >
<div class="ui-widget-content ui-corner-all" style="margin-top: 10px; padding: 0 .9em;">
<h2>Course Management</h2>	
<s:form action="sectionAdmin" method="post">
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
<div style="margin-top: 10px; padding: 0 .9em;" align="right">
<table>
<tr>
<td>
<ul id="icons" class="ui-widget ui-helper-clearfix">
<li class="ui-state-default ui-corner-all" title=".ui-icon-locked"><a id="sectionAdd" class="add" href='sectionAdd?buttonName=add&semesterId=${semesterId}'><span class="ui-icon ui-icon-plusthick"></span></a></li>
</ul>
</td>
<td>Search: <input type="text" id="search" name="search" class="ui-widget-content ui-corner-all" ></td>
</tr>
</table>
</div>
<div class="ui-widget-content ui-corner-all" style="margin-top: 10px; padding: 0 .9em;">
<table id="studentPchTable">
<tr class="ui-widget-header ui-corner-all" style="margin-top: 10px; padding: 0 .9em;">
<th >Course</th>
<th >Program</th>
<th >Instructor</th>
<th >Max Class Size</th>
<th width="10%"> Recommended Class Size</th>
<th >Demand</th>
<th >Over/Under</th>
<th >%Over</th>
<th colspan="4">Action</th>
</tr>

  
<s:iterator value="sections" status="section">
	  <tr class="ui-corner-all" style="margin-top: 10px; padding: 0 .9em;">
	  		<td><s:property value="course.courseId+' '+course.courseName"/> </td>
	  		<td><s:property value="program.programName"/> </td>
	    	<td><s:property value="instructor.contact.firstName+' '+instructor.contact.lastName"/> </td>
	    	<td align="center"><s:property value="maxClassSize"/> </td>
	    	<td align="center"><s:property value="generatedClassSize"/> </td>
	    	<td align="center"><s:property value="%{demand[sectionId]}"/> </td>
	    	<td align="center"><s:property value="demand[sectionId]-studentCapacity"/> </td>
	    	<td align="center"><s:property value="(100*(demand[sectionId]-studentCapacity))/studentCapacity"/> </td>
	    	<td width="2%"><a class="edit" href="sectionAdd?buttonName=edit&sectionId=${sectionId}&semesterId=${semesterId}"><span class="ui-icon ui-icon-pencil"></span></a></td>
    		<td width="2%"><a class="delete" href='sectionAdmin?buttonName=delete&sectionId=${sectionId}&semesterId=${semesterId}'><span class="ui-icon ui-icon-trash"></span></a>
	    	
	  </tr>
</s:iterator>
</table>
</div>
<br/>
</s:form>
</div>
</div>
<%@include file="footer.jsp" %>
</body>
</html>