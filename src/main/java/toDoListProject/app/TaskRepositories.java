package toDoListProject.app;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepositories extends CrudRepository<TaskORM, String> {
}
