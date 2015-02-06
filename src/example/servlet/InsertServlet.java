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

    public Boolean insert(int id, String des, String tags, String exp, String actual, Boolean result, String date) throws SQLException
    {
        try {
            Statement s = con.createStatement();
            int r = s.executeUpdate("insert into test values(" + id + ",'" + des + "','" + tags + "','" + exp + "','" + actual + "'," + result + ",'" + date + "');");
            if (r == 1)
            {
             //   s.executeUpdate("delete from test where test_id="+id+";");
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
        Boolean flag = null;
        try {
            flag=insert(id, des, tags, exp, actual, result, t_date);
            if(flag.equals(true)) {
                out.println("<html>");
                out.println("<head>");
                out.println("<script>");
                out.println("function success()");
                out.println("{ alert(\"Record inserted successfully! \"); window.location.assign(\"http://localhost:9102/display_main.html\");}");
                out.println("</script>");
                out.println("</head>");
                out.println("<body onload=\"success()\">");
                out.println("</body>");
                out.println("</html>");

            }
            else
            {
                out.println("<html>");
                out.println("<head>");
                out.println("<script>");
                out.println("function failure()");
                out.println("{ alert(\"Record insertion failed! \"); window.location.assign(\"http://localhost:9102/display_main.html\");}");
                out.println("</script>");
                out.println("</head>");
                out.println("<body onload=\"failure()\">");
                out.println("</body>");
                out.println("</html>");
            }
        }
        catch(Exception e)
        {

        }
    }
}
