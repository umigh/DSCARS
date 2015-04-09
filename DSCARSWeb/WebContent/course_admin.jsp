<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<script>
		$(document).ready(function(){
			$('.add').on('click', function() {
			    var options = $('select.multiselect1 option:selected').sort().clone();
			    $('select.multiselect2').append(options);
			    return false;
			});
			$('.addAll').on('click', function() {
			    var options = $('select.multiselect1 option').sort().clone();
			    $('select.multiselect2').append(options);
			});
			$('.remove').on('click', function() {
			    $('select.multiselect2 option:selected').remove();
			});
			$('.removeAll').on('click', function() {
			    $('select.multiselect2').empty();
			});
		});
</script>		
</head>
<body>
<h1>Course Management</h1>
<s:actionerror/>
<s:form action="admin" method="post">
<div>
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
<s:select  label="Professor"
		headerKey="-1" 
		headerValue="Select Professor" 
		list="#{'E.Feron':'E.Feron'}" name="user.role" value = "#{'E.Feron'}"/>
		
	
		
<table>
<tr>
<td>

<s:select class="multiselect1" id = "myselecttsms1" label="Select TAs:" name="ids" list="      {'Pablo G','David F','Nando H','ALL','*'}" 
	headerKey="0" headerValue="Select TAs" property="multiselect1" multiple="true" theme="simple" size="6"/>	
</td>
<td>
<table>
<tr><td>
<button class="add">Add</button>
</td></tr>
<tr><td>
<button class="addAll">Add All</button>
</td></tr>
<tr><td>
<button class="remove">Remove</button>
</td></tr>
<tr><td>
<button class="removeAll">Remove All</button>
</td></tr>
</table>
</td>
<td>
<select multiple="true" class="multiselect2" name="myselecttsms2" size="6">
   
</select>
</td>
</tr>
</table>  		
</div>
<br/>
<div>
<s:submit value="Save" align="left"/>
</div>


</s:form>
</body>