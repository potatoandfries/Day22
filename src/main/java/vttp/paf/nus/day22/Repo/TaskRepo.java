package vttp.paf.nus.day22.Repo;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp.paf.nus.day22.Utils;
import vttp.paf.nus.day22.model.Task;

@Repository
public class TaskRepo {
    
    @Autowired
    private JdbcTemplate template;
    // CRUD
    
    // 1. select by id <R>
    public Task  findTaskbyID(int taskId){
        SqlRowSet rs = template.queryForRowSet(Queries.SQL_FIND_BY_TASKID, taskId); 

        while (rs.next()){
            return Utils.toTask(rs);
        }
        return null; //return null if no match is found
    }   
    // 1. select ALL <R>
    public List<Task> findAllTask(){
        SqlRowSet rs = template.queryForRowSet(Queries.SQL_FIND_ALL_TASK);
        List<Task> results = new LinkedList<>();

        while (rs.next()){
            results.add(Utils.toTask(rs));
        }  
        // if no task is found , you can return empty list 
        return results; 
    }
    //2. insert Task <C>   >> add from scratch
    public boolean insertTask(int taskId, String title, Date dueDate, int priority, boolean completed) {
        return template.update(Queries.SQL_INSERT_TASK, taskId, title, dueDate, priority, completed)>= 1;
            // change return type to  Task; if you want to t
            //Task t = new Task(taskId, title, dueDate, priority, completed);
            //return t;

            // why the fuck is it boolean ? its for checking purposese 
    }
    public boolean insertTask(Task t){
        return template.update(Queries.SQL_INSERT_TASK,t.getTaskId(),t.getTitle(),t.getDueDate(),t.getPriority(),t.isCompleted()) >=1 ;
    }
    // need to give >=1 why? template.update is a boolean ype

    public boolean updateTask(boolean completed, int taskId){
        return template.update(Queries.SQL_UPDATE_TASK,completed,taskId)>=1;

    }
    public boolean insertTaskShort(String title, Date dueDate, int priority) {
        return template.update(Queries.SQL_INSERT_TASK_SHORT,title,dueDate,priority)>=1;

    }

    public boolean deleteTask(int taskId){
        return template.update(Queries.SQL_DELETE_TASK,taskId)>=1;
    }
    
}
