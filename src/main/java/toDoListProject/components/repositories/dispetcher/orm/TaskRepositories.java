package toDoListProject.components.repositories.dispetcher.orm;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import toDoListProject.components.entities.task.TaskORM;
import toDoListProject.components.entities.user.UserORM;

import java.util.List;

@Repository
public interface TaskRepositories extends CrudRepository<TaskORM, String> {
    List <TaskORM> findByOwner(UserORM owner);
    List <TaskORM> findByOwnerAndIsOpen(UserORM owner, Boolean isOpen);
}
