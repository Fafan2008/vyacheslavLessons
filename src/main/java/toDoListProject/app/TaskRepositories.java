package toDoListProject.app;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepositories extends CrudRepository<TaskORM, String> {
    List <TaskORM> findByOwner(UserORM owner);
    List <TaskORM> findByOwnerAndIsOpen(UserORM owner, Boolean isOpen);
}
