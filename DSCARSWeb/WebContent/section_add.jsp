<!doctype html>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
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
        $(document).ready(function() {
        	var list = [];
    		$('#addTA').click(function () {
   				$('#selectedTA').val($('#fullTaList option:selected').val());
         	  	$('#fullTaList option:selected').remove().appendTo('#selectedTas');
         	  	$('#buttonName').val('addTA');
    		});
    		
    		$('#removeTA').click(function () {
        	  	$('#selectedTA').val($('#selectedTas option:selected').val());
        	  	$('#selectedTas option:selected').remove().appendTo('#fullTaList');
        	  	$('#buttonName').val('removeTA');
    		});
    		
    		$('#save').click(function () {
        	  	$('#buttonName').val('save');
    		});
        });
        </script>		
    </head>
    <body>
        <%@include file="header.jsp" %>          
        <%@include file="admin_menu.jsp" %>
        <div class="ui-widget-content ui-corner-all" style="margin-top: 10px; padding: 0 .9em;">
         <s:if test="%{buttonName=='edit'}">
		    	<h2>Edit Course</h2>
		 </s:if>    
		 <s:if test="%{buttonName=='add'}">
		    	<h2>Add Course</h2>
		 </s:if>
        
            <s:form action="sectionAdd" method="post">
            <s:hidden name="sectionId" id="sectionId" />
            <s:hidden name="selectedTA" id="selectedTA" />
            <s:hidden name="buttonName" id="buttonName" />
            <input type="hidden"  id="selectedTAString" name="selectedTAString" />
            <input type="hidden"  id="selectedTAs" name="selectedTAs" />
            
                    <s:select property="programId" 
                        label="Select Prorgam"
                        list="programs"
                        name="programId"
                        listKey="programId"
                        listValue="programName"
                        emptyOption="false"
                        headerKey="-1"
                        headerValue="Select Prorgam" />
                    <s:select property="semesterId" id="semesterId"
                        label="Semester"
                        list="semesters"
                        name="semesterId"
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
                    <s:textfield name="maxClassSize" label="Capacity"/>
                    <s:checkbox name="offered" label="Offered?" />
                    <s:select   label="Professor"
                        list="profList"
                        listKey="instructorId"
                        listValue="contact.lastName"
                        name="instructorId"
                        headerValue="Select Professor" />
                    <DIV>
                    <table>
                        <tr>
                            <td>TA Assignment:&nbsp</td><td>
                                <s:select 
                                	label="Full TA List"
                                    class="fullTaList"
                                    id="fullTaList"
                                    list="semesterTaList"
                                    listKey="instructorId"
                                    listValue="contact.lastName"
                                    mutliple="true"
                                    size="5"
                                    emptyOption="false"
                                    headerKey="-1" theme="simple"/>
                            </td>
                            <td>
                                <table>
                                    <tr>
                                        <td>
                                        <button type="submit" value="addTA" id="addTA" name="addTA" class="SavePch">Add</button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                        	<button type="submit" value="removeTA" id="removeTA" name="removeTA" class="SavePch">Remove</button>
                                        </td>                                        
                                    </tr>
                                </table>
                            </td>
                            <td>
                                <s:select 
                                	label="Section TA List"
                                    class="sectionTaList"
                                    id="selectedTas"
                                    name="selectedTas"
                                    list="sectionTaList"
                                    listKey="instructor.instructorId"
                                    listValue="instructor.contact.lastName"
                                    mutliple='true'
                                    size="5"
                                    headerKey="-1"
                                    theme="simple"/>
                            </td>
                            <%
                            	List list=(List) request.getAttribute("sectionTaList");
                            System.out.println(list);
                            
                            %>
                        </tr>
                    </table>
                    <br/>
                    <div style="margin-top: 10px; padding: 0 .9em;"  align="center">
						    <button type="submit" value="save" id="save" name="save" class="SavePch">Save</button>
                    		<input type="button" class="button" onclick="javascript:history.go(-1)" value="Go back" />
                	</div>
                	<br/>
                	</DIV>
                <br/>
            </s:form>
        </div>
        <%@include file="footer.jsp" %>
    </body>