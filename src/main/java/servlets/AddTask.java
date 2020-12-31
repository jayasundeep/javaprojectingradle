package servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/additem/*")
public class AddTask extends HttpServlet {
    public void doPost (HttpServletRequest req, HttpServletResponse res)
            throws ServletException,IOException {
        res.setContentType("text/html");//setting the content type
        PrintWriter pw=res.getWriter();//get the stream to write the data

        // writing html in the stream
        String taskText = req.getParameter("task");
        String taskStatus = req.getParameter("status");
        String taskPriority = req.getParameter("priority");
        Task task = new Task();
        task.setTask(taskText);
        task.setStatus(taskStatus);
        task.setPriority(taskPriority);
        int result = TaskDao.insertTask(task);
        res.setContentType("text/html");
        if(result > 0){
            pw.println("Item is successfully added! :)");
        }else{
            pw.println("Sorry, there was some issue while adding :(");
        }

        pw.println("<a href=\"index.html\">Home Page</a>");
        pw.println("<a href=\"alltasks\">view all tasks</a>");

        pw.close(); //closing the stream
    }
}
