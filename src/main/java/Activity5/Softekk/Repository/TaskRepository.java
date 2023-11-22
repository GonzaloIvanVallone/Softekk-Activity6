package Activity5.Softekk.Repository;

import Activity5.Softekk.Model.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Transactional
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Modifying
    @Query(value = "UPDATE Task SET title = :taskTitle, content = :taskContent WHERE id = :taskId", nativeQuery = true)
    void updateTask(@Param("taskTitle") String taskTitle, @Param("taskContent") String taskContent, @Param("taskId")  Long taskId);
    //se actualizan los 2 campos posibles dentro de nuestra entidad "tarea"
    @Query(value = "SELECT * from Task where title = :taskTitle", nativeQuery = true)
    Optional<Task> findTask(@Param("taskTitle") String taskTitle);
}
