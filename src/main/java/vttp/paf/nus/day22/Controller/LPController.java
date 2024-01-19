package vttp.paf.nus.day22.Controller;

import java.io.StringReader;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.paf.nus.day22.Utils;
import vttp.paf.nus.day22.Repo.TaskRepo;
import vttp.paf.nus.day22.model.Task;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;


@RestController
public class LPController {

    @Autowired
    TaskRepo repo;
    
   @GetMapping(path={"/api/tasks"})
   public ResponseEntity<String> showTask() {
    List<Task> tasks = repo.findAllTask(); // Assuming you have a method called findAll() in your repository to retrieve tasks.
    JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
    
    tasks.forEach(t -> arrBuilder.add(Utils.toJson(t))); // covert to json.
   
   return ResponseEntity.status(200).body(arrBuilder.build().toString()); // json > string.
   }

   @PatchMapping(path={"/api/task/{taskId}"})
   @ResponseBody
   public ResponseEntity<String> patchTask(@PathVariable int taskId,@RequestBody String payload){
      System.out.printf(">>> patch: %s\n", payload);

      JsonReader r = Json.createReader( new StringReader(payload));
      JsonObject j = r.readObject();
      boolean result = repo.updateTask(j.getBoolean("completed"), taskId);
      System.out.printf(">>> after patch: %b\n", result);

      return ResponseEntity.ok("Task updated successfully");
      // you always need a response body
   }

   
   @PostMapping(path = { "/api/task" }, consumes = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<String> postTask(@RequestBody String payload) {
        // Log the received payload
        System.out.println("Received JSON payload: " + payload);

        // Parse the JSON payload into a JsonObject
        JsonReader jsonReader = Json.createReader(new StringReader(payload));
        JsonObject jsonObject = jsonReader.readObject();

        // Extract individual fields from the JsonObject
        String title = jsonObject.getString("title");
        long dueDateMillis = jsonObject.getJsonNumber("dueDate").longValue();
        int priority = jsonObject.getInt("priority");

        // Assuming you have a repository method that can insert a Task object directly.
        repo.insertTaskShort(title,new Date(dueDateMillis),priority);

        return ResponseEntity.status(200).body("Task created successfully");
   }

   @DeleteMapping(path={ "/api/task/{taskId}" })
   public ResponseEntity<String> deleteTask(@PathVariable int taskId){

      boolean result = repo.deleteTask(taskId);
      System.out.println("is this working");
      if (result == true)
      {return ResponseEntity.status(200).body("");
      }

      else{
         return ResponseEntity.status(500).body("");// you cant write shit here,because you're writing the backend and chuk is front end so ur body doesnt fit his.
      }  
   }
}
