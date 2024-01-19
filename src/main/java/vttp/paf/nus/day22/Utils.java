package vttp.paf.nus.day22;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp.paf.nus.day22.model.Bff;
import vttp.paf.nus.day22.model.Task;

public class Utils {
    
    public static Task toTask(SqlRowSet rs) {
        Task t = new Task();
        t.setTaskId(rs.getInt("taskId"));
        t.setTitle(rs.getString("title"));
        t.setDueDate(rs.getDate("dueDate"));
        t.setPriority(rs.getInt("priority"));
        t.setCompleted(rs.getBoolean("completed"));
        return t;
    }
    
    public static Bff toBff(SqlRowSet rs) {
        Bff bff = new Bff();
        bff.setEmail(rs.getString("email"));
        bff.setName(rs.getString("name"));
        bff.setDob(rs.getDate("dob"));
        bff.setPhone(rs.getString("phone"));
        bff.setComments(rs.getString("comments"));
        bff.setLastUpdate(rs.getTimestamp("last_update"));
        return bff;
    }

    //so it makes a model class into a json object.
    public static JsonObject toJson(Task t){
        return Json.createObjectBuilder()
            .add("taskId",t.getTaskId())
            .add("title",t.getTitle())
            .add("completed",t.isCompleted())
            .build();
    }
}

