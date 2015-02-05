package example.servlet;

import org.junit.Test;

import static org.junit.Assert.*;

public class SearchServletTest {

    @Test
    public void testConnection() throws Exception {
            SearchServlet t=new SearchServlet();
            Boolean r=t.connection("root","malar123");
            assertEquals(true,r);
    }

    @Test
    public void testByID() throws Exception {
        SearchServlet t=new SearchServlet();
        t.connection("root","malar123");
        String r="&1#Test case 1#search#123#123#true#2015-01-31#11:22:33";
        String res=t.SearchById(1);
        assertEquals(r,res);


    }


    @Test
    public void testByID_neg() throws Exception {
        SearchServlet t=new SearchServlet();
        t.connection("root","malar123");
        String r=null;
        String res=t.SearchById(2);
        assertEquals(r,res);


    }

    @Test
    public void testByResult() throws Exception {
        SearchServlet t=new SearchServlet();
        t.connection("root","malar123");
        String r="&1#Test case 1#search#123#123#true#2015-01-31#11:22:33&";
        String res=t.SearchByResult(true);
        assertEquals(r,res);
    }


    @Test
    public void testByTags() throws Exception {
        SearchServlet t=new SearchServlet();
        t.connection("root","malar123");
        String r="&4#test case 4#lookup#456#457#false#2014-01-30#23:43:22&5#test case 5#lookup#98#99#false#2015-05-19#06:11:44&";
        String res=t.SearchByTags("lookup");
        assertEquals(r,res);
    }

    @Test
    public void testByTags_neg() throws Exception {
        SearchServlet t=new SearchServlet();
        t.connection("root","malar123");
        String r=null;
        String res=t.SearchByTags("look");
        assertEquals(r,res);
    }
    @Test
    public void testByDate() throws Exception {
        SearchServlet t=new SearchServlet();
        t.connection("root","malar123");
        String r="&4#test case 4#lookup#456#457#false#2014-01-30#23:43:22&6#test case 6#search#123#321#false#2014-01-30#23:43:22&";
        String res=t.SearchByDate("2014-01-30");
        assertEquals(r,res);
    }
    @Test
    public void testByDate_neg() throws Exception {
        SearchServlet t=new SearchServlet();
        t.connection("root","malar123");
        String r=null;
        String res=t.SearchByDate("2014-02-30");
        assertEquals(r,res);
    }
}
