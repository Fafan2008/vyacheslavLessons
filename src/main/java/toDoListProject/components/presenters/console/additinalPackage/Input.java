package toDoListProject.components.presenters.console.additinalPackage;

import toDoListProject.components.presenters.console.Display;

import java.util.Scanner;

public class Input {
    static public String string(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    static public boolean yesNo() throws IllegalArgumentException {
        System.out.print("Enter yes or no :");
        return StringHelper.ToBoolean(Input.string());
    }
    static public String login() {
        Display.enterLogin();
        return Input.string();
    }
    static public String surname() {
        Display.enterSurname();
        return Input.string();
    }
    static public String nameOfTask() {
        Display.enterNameOfTask();
        return Input.string();
    }
    static public String descriptionOfTask() {
        Display.enterDescriptionOfTask();
        return Input.string();
    }
    static public String numberOfTask() {
        Display.EnterNumberOfTask();
        return Input.string();
    }
    static public Command cmd() {
        Display.enterCmd();
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        return Command.fromString(word);
    }
}
