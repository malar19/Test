package example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by malarv on 2/2/2015.
 */
@WebServlet(name = "DisplayServlet")
public class DisplayServlet extends HttpServlet {

    Connection con=null;
    public boolean connection(String user,String pass)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Testcases", user,pass);
            if(con!=null)
                return true;
            return false;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        connection("root","malar123");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<style>");
        out.println("table, th , td  {");
        out.println("    border: 1px solid grey;");
        out.println("border-collapse: collapse;");
        out.println("     padding: 5px;");
        out.println("}");
        out.println("table tr:nth-child(odd)	{");
        out.println("background-color: #f1f1f1;}");
        out.println("table tr:nth-child(even) {");
        out.println("background-color: #ffffff;}");
        out.println("div.scroll  { overflow-y:scroll; height:300;} ");
        out.println("</style>");
        out.println("<script src= \"http://ajax.googleapis.com/ajax/libs/angularjs/1.2.26/angular.min.js\"></script>\n");
      //  out.println("<script>function init() {  alert(parent.document.getElementById(\"searchby\").value }</script>");
       // out.println("function getText(val) {document.getElementById(\"val\")=val;}");
        out.println("</head>");
        out.println("<body bgcolor=\"#d3d3d3\">");
        //out.println("<br><h2><center>TEST CASE DATABASE</center></h2><br><hr><br><br><br><br>");*/
        try {

            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from test;");
            String table_data = "table_data = [";
            while(rs.next())
            {
                table_data+="{id: '"+rs.getInt(1)+"' ,description: '"+
                        rs.getString(2)+"' ,tag: '"+rs.getString(3)+"' ,expected: '"+rs.getString(4)+
                        "' ,actual: '"+rs.getString(5)+"' ,result: '"+rs.getBoolean(6)+"' ,date: '"+rs.getDate(7)+"', time: '"+rs.getTime(7)+"'},";

            }
            String new_table = table_data.substring(0,table_data.length()-1)+']';
            out.println("<div ng-app=\"\" ng-init=\""+new_table+"\">");
            out.println("<center><input type=\"text\" ng-model=\"text\" id=\"search\"><input type=\"button\" value=\"Search\"></center><br><br>");
            out.println("<div class='scroll'>");
            out.println("<table border=\"1px\" align=\"center\" cellpadding=\"10px\">");
            out.println("<tr>");
            out.println("<td></td>");
            out.println("<td><b>TEST ID</b></td>");
            out.println("<td><b>DESCRIPTION</b></td>");
            out.println("<td><b>TAGS</b></td>");
            out.println("<td><b>EXPECTED OUTPUT</b></td>");
            out.println("<td><b>ACTUAL OUTPUT</b></td>");
            out.println("<td><b>RESULT</b></td>");
            out.println("<td><b>DATE</b></td>");
            out.println("</tr>");
            out.println("<tr ng-repeat=\"record in table_data | filter:text \">");
            out.println("<td><input type=\"radio\" name=\"del\" value=\"{{record.id}}\"></td>");
            out.println("<td>{{record.id}}</td>");
            out.println("<td>{{record.description}}</td>");
            out.println("<td>{{record.tag}}</td>");
            out.println("<td>{{record.expected}}</td>");
            out.println("<td>{{record.actual}}</td>");
            out.println("<td>{{record.result}}</td>");
            out.println("<td>{{record.date}} {{record.time}}</td>");
            out.println("</tr>");

            out.println("</table>");
            out.println("</div></div>");
            //out.println("<input type=\"button\" id=\"check\" value=\"check\" onclick=\"check()\">");
            out.println("</body>");
            out.println("</html>");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
