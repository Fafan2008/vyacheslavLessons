package toDoListProject.app;

import org.springframework.data.repository.CrudRepository;

public interface TaskRepositories extends CrudRepository<TaskORM, String> {
}
