package WebToDoList.Console;

import WebToDoList.DataBase.IDispetcher;
import WebToDoList.Interfaces.AMyInterface;
import WebToDoList.Models.Task.Priority;
import WebToDoList.Models.Task.Task;
import WebToDoList.Models.Task.TaskBuilder;
import WebToDoList.Models.User.IUser;
import WebToDoList.Models.User.User;

import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class AppConsole extends AMyInterface {
    private IDispetcher dbDispeptcher;
    private IUser activeUser;

    public void run() {
        Command cmnd;
        Scanner scanner = new Scanner(System.in);
        do{
            printHomeMenu();
            System.out.println("wait cmnd: ");
            cmnd =  Command.fromString(scanner.nextLine());
            execute(cmnd);
            if (cmnd == Command.EXT)
                Thread.currentThread().interrupt();
        }while (!Thread.currentThread().isInterrupted());
    }
    public boolean addTask(){
        Scanner scanner = new Scanner(System.in);
        String name = new String();
        String description = null;
        Priority priority = Priority.UNDEFINED;
        Date time = null;
        System.out.println("Enter name of task.");
        name = scanner.nextLine();
        Task task = new TaskBuilder().name(name).description(description).priority(priority).timeToComplete(time).buildTask();
        dbDispeptcher.addTask(task);

        return true;
    }

    @Override
    public void initialize(IDispetcher dbDispeptcher) {
        this.dbDispeptcher = dbDispeptcher;
    }

    @Override
    public boolean isLife() {
        return !Thread.currentThread().isInterrupted();
    }
    private void execute(Command cmnd) {
        switch (cmnd){
            case ADD:
                //AppConsole.addTask(db);
        }
    }
    static private void printHomeMenu() {
        System.out.println("Actions: ");
        System.out.println("1) CMND /obs \"Observe my tasks.\" ");
        System.out.println("2) CMND /add \"Add task.\" ");
        System.out.println("3) CMND /del \"Delete task.\" ");
        System.out.println("4) CMND /upd \"Update my task.\" ");
        System.out.println("4) CMND /ext \"Turn off pc power.\" ");
    }
    public boolean authenticate() {
        boolean loginIsCorrected = false;
        final String exit = "exit";
        do{
            System.out.println("Please authenticate. ");
            System.out.println("Login: ");
            Scanner scanner = new Scanner(System.in);
            String login =  scanner.nextLine();
            if (exit.equals(login)) break;
            if (dbDispeptcher.getUser(login)==null) {
                System.out.println(login + " user was not found.");
                System.out.println("Create new user? yes/no");
                String answer =  scanner.nextLine();
                if (stringToBoolean(answer)){
                    do{
                        System.out.println("Enter user name: ");
                        login =  scanner.nextLine();
                        if (exit.equals(login)) break;
                        if (dbDispeptcher.getUser(login)!=null)
                            System.out.println("User name is busy. Pls enter another name, or print \"exit\" for out.");
                        else{
                            loginIsCorrected = dbDispeptcher.createUser(login);
                            activeUser = dbDispeptcher.getUser(login);
                        }
                    }while (!loginIsCorrected);
                    if (!loginIsCorrected) break;
                }else
                    continue;
            }else {
                loginIsCorrected = true;
                activeUser = dbDispeptcher.getUser(login);
            }
        }while (!loginIsCorrected);
        return loginIsCorrected;
    }
    static public boolean stringToBoolean(String input) {
        if (input == null)
            throw new IllegalArgumentException("Null input for stringToBoolean");
        input = input.trim().toLowerCase();
        if (input.equals("1") || input.equals("yes") || input.equals("true"))
            return true;
        if (input.equals("0") || input.equals("no") || input.equals("false"))
            return false;
        throw new IllegalArgumentException("Bad input for stringToBoolean: " + input);
    }
}
