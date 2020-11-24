package toDoListProject.components.presenters.console;

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
        System.out.println("Pls enter username: ");
    }

    public static void successful() {
        System.out.println("Successful !");
    }

    public static void unsuccessful() {
        System.out.println("Unsuccessful !");
    }

    public static void doYouWantAddNewUser() {
        System.out.println("Do you want add new user? :");
    }

    public static void enterSurname() {
        System.out.println("Pls enter Surname: ");
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
}
