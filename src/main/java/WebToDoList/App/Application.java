package WebToDoList.App;

import WebToDoList.Console.AppConsole;
import WebToDoList.Console.Command;
import WebToDoList.DataBase.Db;
import WebToDoList.Models.User.User;

import java.util.Scanner;

//how use console input in IntelIdea
//        https://examples.javacodegeeks.com/java-input-example/
//        https://data-flair.training/blogs/read-java-console-input/

public class Application {
    private Db db = new Db();
    private User activeUser;

    public static void main(String[] args){
        Application app = new Application();
        if (app.authenticate())
            app.run();
        else
            System.out.println("Bye.");
    }

    private void run() {
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        do{
            printHomeMenu();
            System.out.println("wait cmnd: ");
            Command cmnd =  Command.fromString(scanner.nextLine());
            execute(cmnd);
            if (cmnd == Command.EXT)
                exit = true;
        }while (!exit);
    }

    private void execute(Command cmnd) {
        switch (cmnd){
            case ADD:
                AppConsole.addTask(db);
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
            if (db.getUser(login)==null) {
                System.out.println(login + " user was not found.");
                System.out.println("Create new user? yes/no");
                String answer =  scanner.nextLine();
                if (stringToBoolean(answer)){
                    do{
                        System.out.println("Enter user name: ");
                        login =  scanner.nextLine();
                        if (exit.equals(login)) break;
                        if (db.getUser(login)!=null)
                            System.out.println("User name is busy. Pls enter another name, or print \"exit\" for out.");
                        else
                            loginIsCorrected = true;
                    }while (!loginIsCorrected);
                    if (!loginIsCorrected) break;
                }else
                    continue;
            }else {
                loginIsCorrected = true;
                activeUser = db.getUser(login);
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
