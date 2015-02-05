package example.servlet;

import static org.junit.Assert.*;

public class InsertServletTest {

    @org.junit.Test
    public void testConnection() throws Exception {
        InsertServlet tservlet=new InsertServlet();
        Boolean r=tservlet.connection("root","malar123");
        assertEquals(true,r);

    }

    @org.junit.Test
    public void testConnection_neg() throws Exception {
        InsertServlet tservlet=new InsertServlet();
        Boolean r=tservlet.connection("root","123");
        assertEquals(false,r);

    }

    @org.junit.Test
    public void testInsert() throws Exception {
        InsertServlet tservlet=new InsertServlet();
        tservlet.connection("root","malar123");
        Boolean r=tservlet.insert(11,"test case 11","search","123","123",true,"2014-09-10 11:22:33");
        assertEquals(true,r);

    }

    @org.junit.Test
    public void testInsert_neg() throws Exception {
        InsertServlet tservlet=new InsertServlet();
        tservlet.connection("root", "malar123");
        Boolean r=tservlet.insert(12,"test case 11","search","123","123",true,"2014-09-10 11:98:33");
        assertEquals(false,r);

    }


}