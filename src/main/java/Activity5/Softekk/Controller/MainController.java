package Activity5.Softekk.Controller;

import Activity5.Softekk.Exceptions.TaskNotFoundException;
import Activity5.Softekk.Model.Task;
import Activity5.Softekk.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RequestMapping("main")
@RestController
public class MainController {
    @Autowired
    TaskService taskService;
    @GetMapping("/allTask")
    public ResponseEntity<Object> getTasks(){
        try{
            return new ResponseEntity<>(taskService.getAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/details/{id}")
    public ResponseEntity<Object> getTaskById(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(taskService.getById(id), HttpStatus.OK);
        }catch(TaskNotFoundException e){
            return new ResponseEntity<>("Task not found", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/save")
    public ResponseEntity<Object> postTask(@RequestBody Task task){
        try {
            return new ResponseEntity<>("Task saved: "+taskService.postTask(task), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable("id") Long id) throws TaskNotFoundException {
        try {
            return new ResponseEntity<>("Task deleted: "+taskService.deleteTask(id), HttpStatus.OK);
        }catch(TaskNotFoundException e){
            return new ResponseEntity<>("Task not found", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<Object> updateTask(@RequestBody Task task){
        try {
            return new ResponseEntity<>("Task updated: "+taskService.updateTask(task), HttpStatus.OK);
        }catch(TaskNotFoundException e){
            return new ResponseEntity<>("Task not found", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}
