package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/editTask")
public class EditTask extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("id");
        int id = Integer.parseInt(sid);
        String taskText = req.getParameter("task");
        String status = req.getParameter("status");
        String priority = req.getParameter("priority");

        Task task = new Task();
        task.setTask(taskText);
        task.setStatus(status);
        task.setPriority(priority);
        task.setId(id);
        int result = TaskDao.updateTaskById(task);
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        if(result > 0){
            out.println("Task updated successfully! :)");
        }else{
            out.println("Sorry, there was some issue while updating :(");
        }
        out.println("<a href=\"index.html\">Home Page</a>");
        out.println("<a href=\"alltasks\">view all tasks</a>");
    }
}
