<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>jQuery UI Tabs - Default functionality</title>
  <link rel="stylesheet" href="../jquery/jquery-ui.css">
  <script src="../jquery/jquery.js"></script>
  <script src="../jquery/jquery-ui.js"></script>
  <link href="../theme/dscars.css" rel="stylesheet">
  <script>
  $(function() {
    $( "#tabs" ).tabs();
  });
  </script>
</head>
<body>
 <div class="header">
 <table>
 <tr>
 <td width="80%" align="left"><H2>Georgia Institute of Technology</H2></td>
 <td align="right">
 <table>
 <tr>
 <td><p>Welcome Umashankar</p></td>
 <td>|</td>
 <td><p><a href="#tabs-1">Logoff</a></p></td>
 <td>|</td>
 <td><p><a href="#tabs-1">Admin</a></p></td>
 </tr>
 </table>
 </td>
 </tr>
 </table>
 </div>
<div id="tabs">
  <ul>
    <li><a href="admin_sections.jsp">Sections</a></li>
    <li><a href="admin_TAs.jsp">TAs</a></li>
    <li><a href="admin_students.jsp">Students</a></li>
  </ul>
</div>
<footer>Georgia Tech © 2014 Georgia Institute of Technology</footer>
 
</body>
</html>