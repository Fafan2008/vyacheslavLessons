package WebToDoList.Console;

import WebToDoList.DataBase.IDb;
import WebToDoList.Models.Task.Priority;
import WebToDoList.Models.Task.Task;
import WebToDoList.Models.Task.TaskBuilder;

import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class AppConsole {
    public static boolean addTask(IDb db){
        Scanner scanner = new Scanner(System.in);
        String id = UUID.randomUUID().toString();
        String name = new String();
        String description = null;
        Priority priority = Priority.UNDEFINED;
        Date time = null;
        System.out.println("Enter name of task.");
        name = scanner.nextLine();
        Task task = new TaskBuilder().id(id).name(name).description(description).priority(priority).timeToComplete(time).buildTask();
        db.addTask(id, task);

        return true;
    }
}
