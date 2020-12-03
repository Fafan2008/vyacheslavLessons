package toDoListProject.components.presenters.console;

import toDoListProject.components.entities.task.Task;
import toDoListProject.components.presenters.console.additinalPackage.Command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Display {
    public static void menu() {
        System.out.println("Actions: ");
        System.out.println("1) CMND " + Command.OBS.getText() + " \"Observe my tasks.\" ");
        System.out.println("2) CMND " + Command.VIE.getText() + " \"View specific task.\" ");
        System.out.println("3) CMND " + Command.ADD.getText() + " \"Add task.\" ");
        System.out.println("4) CMND " + Command.DEL.getText() + " \"Delete task.\" ");
        System.out.println("5) CMND " + Command.UPD.getText() + " \"Update my task.\" ");
        System.out.println("6) CMND " + Command.HLP.getText() + " \"Help menu.\" ");
        System.out.println("7) CMND " + Command.EXT.getText() + " \"Exit.\" ");
    }

    public static void enterLogin() { System.out.print("Pls enter login name: "); }

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
        System.out.println("Do you want add new user?");
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

    public static void EnterNumberOfTask() {
        System.out.print("Pls enter Number Of Task: ");
    }

    public static void show(Map<Integer, Task> mapTasks) {
        if (mapTasks.isEmpty())
            System.out.println("You haven't tasks.");
        else {
            System.out.println("List of your tasks:");
            List<Integer> tasksByKey = new ArrayList<>(mapTasks.keySet());
            Collections.sort(tasksByKey);
            for (Integer num : tasksByKey) {
                Task task = mapTasks.get(num);
                System.out.println(num.toString()+") " + task.getName());
            }
        }
    }

    public static void itIsNotNumber() {
        System.out.println("It is not number!");
    }

    public static void doYouAgree() {
        System.out.println("Do you agree?");
    }

    public static void show(Task task) {
        System.out.println("Name: "+ task.getName() +"\n"
                + "Description: " + task.getDescription() + "\n"
                + "Is open: " + task.isOpen() + "\n"
                + "Created: " + task.getCreated());
    }

    public static void whatYouWantChange() {
        System.out.println("What you want change?");
    }

    public static void haveNotPermission() {
        System.out.println("Have Not Permission!!!");
    }

    public static void undefinedCommand() {
        System.out.println("Undefined command!!!");
    }
}
