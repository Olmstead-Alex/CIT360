package servlets;

import java.util.Enumeration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet(name = "Servlet", urlPatterns={"/Servlet"})
class DemoServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)
            throws IOException
    {
        //set content type
        res.setContentType("text/html");
        //getting stream to write data
        PrintWriter pw=res.getWriter();

        //writing html in the stream
        pw.println("<html><body>");
        pw.println("Welcome to servlet");
        pw.println("</body></html>");

        //closing stream
        pw.close();
    }

    //adding form data passing to Servlet as requested by instructor. this involved editing index.html, creating index.jsp and changing web.xml as well
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try
        {
            //create two request strings
            String input1 = request.getParameter("text_input") ;
            String input2 = request.getParameter("password_input") ;

            //write response
            response.setContentType("text/html");
            PrintWriter writer =  response.getWriter();
            writer.println("<h2><font color=green>The Data From Input Fields :</font></h2>");
            writer.println("<br><font color=blue>Text Value Is :</font>"+input1);
            writer.println("<br><font color=blue>Password Value Is :<font> "+input2);

            //To get
            Enumeration<String> enumerate = request.getParameterNames();
            while(enumerate.hasMoreElements())
                System.out.println(enumerate.nextElement());

            writer.close();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }
}
