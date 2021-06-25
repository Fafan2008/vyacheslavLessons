package org.slava.company.toDoListProject.components.repositories.dispetcher.orm;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.slava.company.toDoListProject.components.entities.task.TaskORM;
import org.slava.company.toDoListProject.components.entities.user.UserORM;

import java.util.List;

@Repository
public interface TaskRepositories extends CrudRepository<TaskORM, String> {
    List <TaskORM> findByOwner(UserORM owner);
    List <TaskORM> findByOwnerAndIsOpen(UserORM owner, Boolean isOpen);
}
