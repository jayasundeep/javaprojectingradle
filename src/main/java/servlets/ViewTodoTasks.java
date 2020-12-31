package servlets;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/alltasks")
public class ViewTodoTasks extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");//setting the content type
        PrintWriter pw=resp.getWriter();//get the stream to write the data

        // writing html in the stream
        pw.println("<html><body>");
        List<Task> taskList = TaskDao.getAllTasks();

        pw.print("<table border='1' width='75%'");
        pw.print("<tr><th>Task</th><th>Status</th><th>Priority</th> <th>Edit</th><th>Delete</th></tr>");
        for(int i=0; i<taskList.size(); i++){
            Task task = taskList.get(i);
            pw.print("<tr><td>"+task.getTask()+"</td><td>"+task.getStatus()+"</td><td>"+task.getPriority()+"</td> <td><a href='gettask?id="+task.getId()+"'>edit</a></td> <td><a href='deleteTask?id="+task.getId()+"'>delete</a></td></tr>");
        }
        pw.print("</table>");
        pw.println("<a href=\"index.html\">Home Page</a>");
        pw.println("</body></html>");

        pw.close(); //closing the stream
    }
}
