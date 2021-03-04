package toDoListProject.app;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepositories extends CrudRepository<UserORM, String> {
    //List<UserORM> findBySurname(String surname);
    Optional<UserORM> findById( String id);
}
