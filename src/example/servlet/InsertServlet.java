package example.servlet;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
/**
 * Created by malarv on 1/31/2015.
 */
@javax.servlet.annotation.WebServlet(name = "InsertServlet")
public class InsertServlet extends HttpServlet {
    Connection con=null;
    public Boolean connection(String user, String pass)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Testcases", user, pass);
            if(con!=null)
                return true;
            else
                return false;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    public boolean insert(int id, String des, String tags, String exp, String actual, Boolean result, String date) throws SQLException
    {
        try {
            Statement s = con.createStatement();
            int r = s.executeUpdate("insert into test values(" + id + ",'" + des + "','" + tags + "','" + exp + "','" + actual + "'," + result + ",'" + date + "');");
            if (r == 1)
            {
                s.executeUpdate("delete from test where test_id="+id+";");
                return true;
            }
            else
                return false;
        }
        catch(Exception e)
        {
            //e.printStackTrace();
            return false;
        }

    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        connection("root","malar123");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body bgcolor=\"#d3d3d3\">");
        int id;
        String des,tags,exp,actual,t_date;
        Boolean result;
        id = Integer.parseInt(request.getParameter("id"));
        des=request.getParameter("des");
        tags=request.getParameter("tags");
        exp=request.getParameter("exp");
        actual=request.getParameter("actual");
        if(exp.equals(actual))
            result=true;
        else
            result=false;
        t_date=request.getParameter("date");
        try {
            insert(id,des,tags,exp,actual,result,t_date);
            out.println("<br><br><center><h2>Record inserted successfully! :D</h2></center> ");
            out.println("<form action=\"insert.html\">");
            out.println("<br><br><center><b>Do you want to insert more records?</b></center><input type=\"submit\" value=\"YES\">");
            out.println("<br><br></form>");
            out.println("<center><a href=\"display.html\"><input type=\"button\" value=\"Display\"></a></center>");
            out.println("<br><br><a href=\"welcome.html\"><input type=\"button\" value=\"Go back to main page\"></a>");
            out.println("</body>");
            out.println("</html>");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            out.println("<html>");
            out.println("<head>");
            out.println("</head>");
            out.println("<body bgcolor=\"#d3d3d3\">");
            out.println("<br><br><center><h2>Record insertion failed :( </h2></center> ");
            out.println("<br><br><center><b>Try again?</b><input type=\"submit\" value=\"YES\"></center>");
            out.println("<br><br></form>");
            out.println("<center><a href=\"display.html\"><input type=\"button\" value=\"Display\"></a></center>");
            out.println("<br><br><a href=\"welcome.html\"><input type=\"button\" value=\"Go back to main page\"></a>");
        }
    }
}
