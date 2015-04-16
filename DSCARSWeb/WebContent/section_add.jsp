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
        <h1>Section Management</h1>
        <div class="ui-widget-content ui-corner-all" style="margin-top: 10px; padding: 0 .9em;">
            <s:form action="sectionAdd" method="post">
                
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
                    <s:textfield name="maxClassSize" label="Capacity" value="200"/>
                    <s:checkbox name="offered" fieldValue="true" label="Offered?" />
                    <s:select   label="Professor"
                        list="profList"
                        listKey="instructorId"
                        listValue="contact.lastName"
                        name="instructorId"
                        headerValue="Select Professor" />
                    <table>
                        <tr>
                            <td>
                                <s:select 
                                	label="Full TA List"
                                    class="fullTaList"
                                    id="fullTaList"
                                    list="taList"
                                    listKey="instructorId"
                                    listValue="contact.lastName"
                                    mutliple="true"
                                    size="5"
                                    emptyOption="false"
                                    headerKey="-1"/>
                            </td>
                            <td>
                                <table>
                                    <tr>
                                        <td>
                                        <button type="button" value="Add" id="add">Add</button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                        	<button type="button" value="Remove" id="add">remove</button>
                                        </td>                                        
                                    </tr>
                                </table>
                            </td>
                            <td>
                                <s:select 
                                	label="Section TA List"
                                    class="taInsertList"
                                    id="taInsertList"
                                    name="taInsertList"
                                    list="{}"
                                    mutliple="true"
                                    size="5"
                                    headerKey="-1"
                                    />
                            </td>
                        </tr>
                    </table>
                
                <br/>
                <div style="margin-top: 10px; padding: 0 .9em;"  align="center">
                    <button type="submit" value="SaveSection" id="buttonName" name="buttonName" class="SavePch">Add Section</button>
                </div>
                <br/>
            </s:form>
        </div>
        <%@include file="footer.jsp" %>
    </body>