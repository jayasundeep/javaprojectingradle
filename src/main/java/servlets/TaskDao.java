package servlets;

import jdk.nashorn.internal.ir.CatchNode;

import java.sql.*;
import java.util.*;

public class TaskDao {
    static Connection connection = null;
    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "root");
        }catch (Exception e){System.out.println(e);}
        return connection;
    }
    public static List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<Task>();
        try {
            Connection con = getConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from todoapp");
            while (resultSet.next()){
                Task task = new Task();
                task.setId(resultSet.getInt(1));
                task.setTask(resultSet.getString(2));
                task.setStatus(resultSet.getString(3));
                task.setPriority(resultSet.getString(4));
                taskList.add(task);
            }
            con.close();
        }catch (Exception e){System.out.println(e);}
        return taskList;
    }
    public static Task getTaskById(int id){
        Task task = new Task();
        try {
            Connection con = getConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from todoapp where id="+id);
            resultSet.next();
            task.setId(resultSet.getInt(1));
            task.setTask(resultSet.getString(2));
            task.setStatus(resultSet.getString(3));
            task.setPriority(resultSet.getString(4));
            return task;
        } catch (Exception e){System.out.println(e);}
        return task;
    }
    public static int insertTask(Task task){
        int result = 0;
        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("insert into todoapp (task, taskStatus, priority) values (?, ?, ?)");
            statement.setString(1, task.getTask());
            statement.setString(2, task.getStatus());
            statement.setString(3, task.getPriority());
            result = statement.executeUpdate();

        } catch (Exception e){System.out.println(e);}
        return result;
    }
    public static int updateTaskById(Task task){
        int updated = 0;
        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("update todoapp set task=?, taskStatus=?, priority=? where id=?");
            statement.setString(1, task.getTask());
            statement.setString(2, task.getStatus());
            statement.setString(3, task.getPriority());
            statement.setInt(4, task.getId());
            updated = statement.executeUpdate();
        } catch (Exception e){System.out.println(e);}
        return updated;
    }
    public static int deleteTaskById(int id){
        int deleted = 0;
        try{
            Connection con = getConnection();
            Statement statement = con.createStatement();
            deleted = statement.executeUpdate("delete from todoapp where id="+id);
        }catch (Exception e){System.out.println(e);}
        return deleted;
    }
}

