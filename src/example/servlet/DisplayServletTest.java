package example.servlet;

import org.junit.Test;

import static org.junit.Assert.*;

public class DisplayServletTest {

    @Test
    public void testConnection() throws Exception {
        InsertServlet tservlet=new InsertServlet();
        Boolean r=tservlet.connection("root","malar123");
        assertEquals(true,r);

    }

    @Test
    public void testConnection_neg() throws Exception {
        InsertServlet tservlet=new InsertServlet();
        Boolean r=tservlet.connection("root","123");
        assertEquals(false,r);

    }

    @Test
    public void testDoGet() throws Exception {
    }
}