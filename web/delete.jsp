<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.io.*" %>
<%@ page import="javax.servlet.Servlet.*" %>
<%--
  Created by IntelliJ IDEA.
  User: malarv
  Date: 2/3/2015
  Time: 10:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title></title>
<script>
  var c;
  function check()
  {
    c=confirm("Are you sure you want to delete?");
    return true;
  }
  function ret_c()
  {
    return c;
  }
</script>
</head>
<body bgcolor="#d3d3d3">
<br>
<h2><center>TEST CASE DATABASE</center></h2><br>
<hr>
<br><br><br><br>
<table border="1px" cols="7" align="center" cellpadding="10px">
<tr>
   <td><b>TEST ID</b></td>
   <td><b>DESCRIPTION</b></td>
   <td><b>TAGS</b></td>
   <td><b>EXPECTED OUTPUT</b></td>
   <td><b>ACTUAL OUTPUT</b></td>
   <td><b>RESULT</b></td>
   <td><b>DATE</b></td>
</tr>
<%
try
{
  Class.forName("com.mysql.jdbc.Driver");
  Connection con;
  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Testcases", "root", "malar123");
  Statement s = con.createStatement();
  ResultSet r = s.executeQuery("select * from test;");
  while (r.next()) {%>
    <tr>
      <td><%= r.getInt(1) %></td>
      <td><%= r.getString(2) %></td>
      <td><%= r.getString(3) %></td>
      <td><%= r.getString(4) %></td>
      <td><%= r.getString(5) %></td>
      <td><%= r.getBoolean(6) %></td>
      <td><%= r.getDate(7) %><%out.print(" ");%><%= r.getTime(7) %></td>
    </tr>
  <%}%>
  </table>
  <br><br>
  <center><span style="font-size: large"> Select test id to delete :</span>


  <%
  Statement st = con.createStatement();
  ResultSet rs = st.executeQuery("select * from test;");%>
<form>
  <select name="test_id" id="id1">
  <% while(rs.next()){%>
    <option value="<% Integer i=rs.getInt(1); out.print(i); %>">  <%=rs.getInt(1)%>  </option >
  <%}%>
  </select><br><br>
  <input type="submit" value="Delete">
</form>
<% int id=Integer.parseInt(request.getParameter("test_id"));
      int a = st.executeUpdate("delete from test where test_id=" + id + ";");
        out.println("Record deleted successfully! :D");
    }
catch(Exception e)
{
  out.println("");
}
%>

<br><br><center><a href="welcome.html"><input type="button" value="Back to main page."></a></center>
</body>
</html>
