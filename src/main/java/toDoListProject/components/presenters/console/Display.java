package toDoListProject.components.presenters.console;

import toDoListProject.components.entities.task.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Display {
    public static void menu(){
        System.out.println("Actions: ");
        System.out.println("1) CMND /obs \"Observe my tasks.\" ");
        System.out.println("2) CMND /add \"Add task.\" ");
        System.out.println("3) CMND /del \"Delete task.\" ");
        System.out.println("4) CMND /upd \"Update my task.\" ");
        System.out.println("4) CMND /ext \"Turn off pc power.\" ");
    }
    public static void enterLogin(){
        System.out.print("Pls enter login name: ");
    }

    public static void successful() {
        System.out.println("Successful !");
    }
    public static void successfulAddingNewUser() {
        System.out.println("Successful adding new user!");
    }
    public static void unsuccessful() {
        System.out.println("Unsuccessful !");
    }

    public static void doYouWantAddNewUser() {
        System.out.println("Do you want add new user? : ");
    }

    public static void enterSurname() {
        System.out.print("Pls enter Surname: ");
    }

    public static void enterCmd() {
        System.out.print("Pls enter cmd: ");
    }

    public static void enterNameOfTask() {
        System.out.print("Pls enter Name Of Task: ");
    }

    public static void enterDescriptionOfTask() {
        System.out.print("Pls enter Description Of Task: ");
    }

    public static void EnterNameOrNumberOfTask() {
        System.out.print("Pls enter Name or Number Of Task: ");
    }

    public static void show(Map<Integer, Task> mapTasks) {
        if (mapTasks.isEmpty())
            System.out.println("You haven't tasks.");
        else{
            List<Integer> tasksByKey = new ArrayList<>(mapTasks.keySet());
            Collections.sort(tasksByKey);
            for (Integer num: tasksByKey) {
                System.out.println( num.getClass() + num.toString() + mapTasks.get(num).getName() + mapTasks.get(num).getDescription());
            }
        }

    }
}
