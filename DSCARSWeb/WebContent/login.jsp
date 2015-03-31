<!doctype html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>DSCARS</title>
  <link rel="stylesheet" href="themes/jquery-ui.css">
  <link href="themes/dscars.css" rel="stylesheet"> 
</head>
<body>
<div class="ui-state-highlight  ui-corner-all" style="margin-bottom: 10px; padding: 0 .9em;">
<h1>Georgia Institute of Technology</h1>
</div>
<s:if test="hasActionErrors()">
<div class="ui-widget">
   <div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">
   <p>
      <s:actionerror/>
    </p>
   </div>
 </div>
</s:if>



<s:if test="hasActionMessages()">
   <div class="ui-state-highlight ui-corner-all" style="padding: 0 .7em;">
	   <p><s:actionmessage/></p>
   </div>
</s:if>

<div class="ui-widget" >
	<div class="ui-widget-content ui-corner-all" style="margin-top: 10px; padding: 0 .9em;">
	<br/>
	<s:form action="login" method="post">
	<s:textfield name="userId" label="User Id" value="umashankar3"/>
	<s:password name="password" label="Password" value="dscars"/>
	
	<s:submit value="Login" align="center"/>
	</s:form>
	<br/>
	</div>
</div>
<br>
<div class="ui-state-highlight  ui-corner-all" style="margin-top: 10px; padding: 0 .9em;">
Georgia Tech Â© 2014 Georgia Institute of Technology
</div>
</body>
</html>