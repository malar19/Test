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
        out.println("</head>");
        out.println("<body bgcolor=\"#d3d3d3\">");
        out.println("<br><h2><center>TEST CASE DATABASE</center></h2><br><hr><br><br><br><br>");
        out.println("<table border=\"1px\" cols=\"7\" align=\"center\" cellpadding=\"10px\">");
        out.println("<tr>");
        out.println("<td><b>TEST ID</b></td>");
        out.println("<td><b>DESCRIPTION</b></td>");
        out.println("<td><b>TAGS</b></td>");
        out.println("<td><b>EXPECTED OUTPUT</b></td>");
        out.println("<td><b>ACTUAL OUTPUT</b></td>");
        out.println("<td><b>RESULT</b></td>");
        out.println("<td><b>DATE</b></td>");
        out.println("</tr>");
        try {

            Statement s = con.createStatement();
            ResultSet r = s.executeQuery("select * from test;");
            while (r.next()) {
                out.println("<tr>");
                out.println("<td>" + r.getInt(1) + "</td>");
                out.println("<td>" + r.getString(2) + "</td>");
                out.println("<td>" + r.getString(3) + "</td>");
                out.println("<td>" + r.getString(4) + "</td>");
                out.println("<td>" + r.getString(5) + "</td>");
                out.println("<td>" + r.getBoolean(6) + "</td>");
                out.println("<td>" + r.getDate(7) + " " + r.getTime(7) + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("<br><br><center><a href=\"welcome.html\"><input type=\"button\" value=\"Back to main page.\"></a></center>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
