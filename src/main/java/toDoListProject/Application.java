package toDoListProject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import toDoListProject.components.repositories.dispetcher.orm.TaskRepositories;
import toDoListProject.components.repositories.dispetcher.orm.UserRepositories;
import toDoListProject.components.interactors.IInteractor;
import toDoListProject.components.interactors.Interactor;
import toDoListProject.components.presenters.IPresenter;
import toDoListProject.components.presenters.console.Console;
import toDoListProject.components.repositories.dispetcher.IDB;
import toDoListProject.components.repositories.dispetcher.orm.DBOrm;

@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    //Важно! Я не до конца понимаю что тут написано.
    @Bean
    public CommandLineRunner runner(UserRepositories userRepositories, TaskRepositories taskRepositories) {
        return (args) -> {
            //Вариант с DB ORM
            IDB db = new DBOrm(userRepositories, taskRepositories);
            IInteractor interact = new Interactor(db);
            IPresenter iPresenter = new Console(interact);
            iPresenter.start();

            //Вариант с DB оперативной памятью
//            db = new DBMemory();
//            interact = new Interactor(db);
//            iPresenter = new Console(interact);
            //iPresenter.start();

            //Вариант с DB H2
//            String propertiesPath = Paths.get("", "db.properties").toAbsolutePath().toString();
//            Properties dbProps = new Properties();
//            try {
//                dbProps.load(new FileInputStream(propertiesPath));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            db = new DBH2(dbProps);
//            interact = new Interactor(db);
//            iPresenter = new Console(interact);
            //iPresenter.start();
        };
    }
}
