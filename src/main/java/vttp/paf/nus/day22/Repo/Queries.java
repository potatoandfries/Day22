package vttp.paf.nus.day22.Repo;

public class Queries {
    //select all <r>  done;
    public static final String SQL_FIND_ALL_TASK = """
        select *
        from task  
        
    """;
    //select s <r>  done;
    public static final String SQL_FIND_BY_TASKID =  """
        select * from task
           where taskId = ?
           
     """;

    // make an insert* <c> done;
    public static final String SQL_INSERT_TASK = """
        insert into task (title, dueDate, priority,completed) 
            values (?, ?, ?, ?)


    """;
    // insert task short <c> done;
    public static final String SQL_INSERT_TASK_SHORT="""
            insert into task(title,dueDate,priority)
            values (?,?,?)
            """;

    // make an update* <U> done.
    public static final String SQL_UPDATE_TASK= """
        update task 
        set completed = ?
        where taskId = ?  
    """; 
        // typically you would choose the primarykey

    // Delete  <D> done* 
    public static final String SQL_DELETE_TASK = """
        delete from task 
            where taskId = ?;
    """;


    //this is now for bff side*
    // >> Insert done*
    public static final String SQL_INSERT_BFF = """
        select count(*)        

    """;
    //>> Select done*
    public static final String SQL_SELECT_BFF_BY_EMAIL= """
        select * 
        from bff 
        where email= ?

    """;
}          
