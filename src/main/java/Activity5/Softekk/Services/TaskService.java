package Activity5.Softekk.Services;

import Activity5.Softekk.Exceptions.TaskNotFoundException;
import Activity5.Softekk.Model.Task;
import Activity5.Softekk.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public List<Task> getAll(){
        return taskRepository.findAll();
    }
    public Task postTask(Task task){
        task.setCreated(LocalDateTime.now().withNano(0).toString().replace("T", " "));
        taskRepository.save(task);
        return task;
    }
    public Task deleteTask(Long id) throws TaskNotFoundException {
        try {
            Optional<Task> task = taskRepository.findById(id);
            if(task.isPresent()) {
                Task t = task.orElse(null);
                taskRepository.delete(t);
                return t;
            }
            throw new TaskNotFoundException();
        }catch(Exception e){
            throw e;
        }
    }
    public Optional<Task> updateTask(Task task) throws TaskNotFoundException {
        try {
            Optional<Task> optTask = taskRepository.findTask(task.getTitle());
            if(optTask.isPresent()){
                optTask = taskRepository.findById(optTask.get().getId());
                if(optTask.isPresent()){
                    taskRepository.updateTask(task.getTitle(), task.getContent(), optTask.get().getId());
                    return optTask;
                }
            }
            throw new TaskNotFoundException();
        }catch(Exception e){
            throw e;
        }
    }
    public Optional<Task> getById(long id) throws TaskNotFoundException {
        try {
            Optional<Task> optTask = taskRepository.findById(id);
            if (optTask.isPresent()) {
                return taskRepository.findById(id);
            }
            throw new TaskNotFoundException();
        }catch(Exception e){
            throw e;
        }
    }
}
