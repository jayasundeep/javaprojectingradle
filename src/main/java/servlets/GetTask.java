package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/gettask")
public class GetTask extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        out.println("<h1>Update Task</h1>");
        String sid = req.getParameter("id");
        int id=Integer.parseInt(sid);

        Task task = TaskDao.getTaskById(id);
        System.out.println(task.getTask() + " " + task.getStatus() + " " + task.getPriority());

        out.print("<form action='editTask' method='post'>");
        out.print("<table>");
        out.print("<tr><td></td><td><input type='hidden' name='id' value='"+task.getId()+"'/></td></tr>");
        out.print("<tr><td>Name:</td><td><input type='text' name='task' value='"+task.getTask()+"'/></td></tr>");

        out.print("<tr><td>Status:</td><td>");
        out.print("<select name='status' value='"+task.getStatus()+"' style='width:200px'>");
        out.print("<option value='Pending'>Pending</option>");
        out.print("<option value='In progress'>In progress</option>");
        out.print("<option value='Completed'>Completed</option>");
        out.print("<option value='Deferred'>Deferred</option>");
        out.print("</select>");

        out.print("<tr><td>Priority:</td><td>");
        out.print("<select name='priority' value='"+task.getPriority()+"' style='width:200px'>");
        out.print("<option value='Important - not Urgent'>Important - not Urgent</option>");
        out.print("<option value='not Important - not Urgent'>not Important - not Urgent</option>");
        out.print("<option value='Important - Urgent'>Important - Urgent</option>");
        out.print("<option value='not Important - Urgent'>not Important - Urgent</option>");
        out.print("</select>");

        out.print("</td></tr>");
        out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>");
        out.print("</table>");
        out.print("</form>");

        out.close();
    }
}
