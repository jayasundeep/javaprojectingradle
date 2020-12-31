package servlets;

public class Task {
    int id;
    String task;
    String status;
    String priority;

    public void setId(int id) {
        this.id = id;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public String getTask() {
        return task;
    }
}
