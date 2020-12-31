package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/deleteTask")
public class DeleteTask extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        String sid = req.getParameter("id");
        int id = Integer.parseInt(sid);
        int result = TaskDao.deleteTaskById(id);
        if(result > 0){
            out.println("Deleted the task with id " + id);
        }else {
            out.println("There was some issue deleting the task");
        }
        out.println("<a href=\"index.html\">Home Page</a>");
        out.println("<a href=\"alltasks\">view all tasks</a>");
    }
}
