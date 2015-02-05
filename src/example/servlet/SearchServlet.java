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
 * Created by malarv on 2/4/2015.
 */
@WebServlet(name = "SearchServlet")
public class SearchServlet extends HttpServlet {
    Connection con=null;
    public boolean connection(String user,String pass)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Testcases", user, pass);
            if(con!=null)
                return true;
            return false;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    public String SearchById(int i)
    {
        try {
            Statement s = con.createStatement();
            String res = "&";
            ResultSet r=s.executeQuery("select * from test where test_id="+i+";");
            while(r.next())
            {
                res=res+r.getInt(1)+"#"+r.getString(2)+"#"+r.getString(3)+"#"+r.getString(4)+"#"+r.getString(5)+"#"+r.getBoolean(6)+"#"+r.getDate(7)+"#"+r.getTime(7);
            }
            if(res!="&")
              return res;
            return null;
        }
        catch(Exception e)
        {
            //e.printStackTrace();
            return null;
        }
    }

    public String SearchByResult(boolean result){
        try {
            Statement s = con.createStatement();
            String res ="&";
            ResultSet r=s.executeQuery("select * from test where result=" + result + ";");
            while(r.next())
            {
                res=res+r.getInt(1)+"#"+r.getString(2)+"#"+r.getString(3)+"#"+r.getString(4)+"#"+r.getString(5)+"#"+r.getBoolean(6)+"#"+r.getDate(7)+"#"+r.getTime(7)+"&";
            }
            if(res!="&")
                return res;
            return null;
        }
        catch(Exception e)
        {
            //e.printStackTrace();
            return null;
        }
    }


    public String SearchByTags(String val) {
        try {
            Statement s = con.createStatement();
            String res ="&";
            ResultSet r=s.executeQuery("select * from test where tags='"+val+"';");
            while(r.next())
            {
                res=res+r.getInt(1)+"#"+r.getString(2)+"#"+r.getString(3)+"#"+r.getString(4)+"#"+r.getString(5)+"#"+r.getBoolean(6)+"#"+r.getDate(7)+"#"+r.getTime(7)+"&";
            }

            if(res!="&")
                return res;
            return null;
        }
        catch(Exception e)
        {
            //e.printStackTrace();
            return null;
        }
    }


    public String SearchByDate(String val) {
        try {
            Statement s = con.createStatement();
            String res ="&";
            ResultSet r=s.executeQuery("select * from test where t_timstamp like '"+val+"%';");
            while(r.next())
            {
                res=res+r.getInt(1)+"#"+r.getString(2)+"#"+r.getString(3)+"#"+r.getString(4)+"#"+r.getString(5)+"#"+r.getBoolean(6)+"#"+r.getDate(7)+"#"+r.getTime(7)+"&";
            }

            if(res!="&")
                return res;
            return null;
        }
        catch(Exception e)
        {
            //e.printStackTrace();
            return null;
        }
    }




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        connection("root","malar123");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String ch=request.getParameter("name");
        String val=request.getParameter("select");
        String res="";
        if(ch.equals("TestID"))
            res=SearchById(Integer.parseInt(val));
        else if(ch.equals("Tags"))
            res=SearchByTags(val);
        else if(ch.equals("Result"))
            res=SearchByResult(Boolean.parseBoolean(val));
        else if(ch.equals("Date"))
            res=SearchByDate(val);
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body bgcolor=\"#d3d3d3\">");
        out.println("<br><h2><center>TEST CASE DATABASE</center></h2><br><hr><br><br><br><br>");
        if(res!=null)
        {
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
            String[] record=res.split("&");
            for(int i=1;i<record.length;i++)
            {
                    String[] data=record[i].split("#");
                    out.println("<tr>");
                    out.println("<td>" + data[0] + "</td>");
                    out.println("<td>" + data[1] + "</td>");
                    out.println("<td>" + data[2] + "</td>");
                    out.println("<td>" + data[3] + "</td>");
                    out.println("<td>" + data[4] + "</td>");
                    out.println("<td>" + data[5] + "</td>");
                    out.println("<td>" + data[6] + " " + data[7] + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");

        }
        else {
            out.println("Record not found!<br><br>");
        }

        out.println("<br><br><center><a href=\"search.html\"><input type=\"button\" value=\"Search again\" ></a>");
        out.println("<a href=\"welcome.html\"><input type=\"button\" value=\"Go back to main page\"></a></center>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");

    }


}
