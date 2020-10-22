package WebToDoList.App;

import WebToDoList.Models.Task.*;

import java.util.Date;
import java.util.UUID;

public class Application {
    public static void main(String[] args){
        Task task1 = new Task();
        task1.id = UUID.randomUUID().toString();
        task1.userID = "User1";
        task1.name = "Name 1";
        task1.description = "Description 1";
        task1.priority = Priority.DEFAULT;

        Filter filter = new FilterBuilder().userID("User1").buildFilter();
        System.out.println("Result of filter matching task1 and filter1 must be TRUE");
        System.out.println("Result is : " + Filter.match(task1, filter));
        System.out.println();

        Update updates = new Update();
        updates.timeToComplete = new Date(new Date().getTime() + 10000);
        System.out.println("Update task1 must return TRUE");
        System.out.println("Result is : " + task1.update(updates));
        System.out.println();

        filter = new FilterBuilder().start(new Date()).end(new Date(new Date().getTime() + 1000)).buildFilter();
        System.out.println("Result of filter matching task1 and filter must be FALSE");
        System.out.println("Result is : " + Filter.match(task1, filter));
        System.out.println();

        filter = new FilterBuilder().start(new Date(new Date().getTime() - 1000)).end(new Date(new Date().getTime() + 10100)).buildFilter();
        System.out.println("Result of filter matching task1 and filter must be TRUE");
        System.out.println("Result is : " + Filter.match(task1, filter));
        System.out.println();
    }
}
