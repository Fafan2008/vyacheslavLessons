package WebToDoList.App;

import WebToDoList.Console.AppConsole;
import WebToDoList.DataBase.Db;
import WebToDoList.DataBase.Dispetcher;
import WebToDoList.Interfaces.AppWeb.AppWeb;
import WebToDoList.Interfaces.IMyInterface;
import WebToDoList.Interfaces.Telegram.AppTelega;
import WebToDoList.Models.User.User;

import java.util.Scanner;
//how use console input in IntelIdea
//        https://examples.javacodegeeks.com/java-input-example/
//        https://data-flair.training/blogs/read-java-console-input/

// Касательно логирования https://www.cyberforum.ru/java-j2se/thread1682804.html

public class Application {
    private Db db = new Db();
    private User activeUser;
    private IMyInterface currentInterface;

    public static void main(String[] args){
        Application app = new Application();
        System.out.println("Choose your interface: ");
        System.out.println("1 = Console");
        System.out.println("2 = Telegramm");
        System.out.println("3 = Web");
        Scanner scanner = new Scanner(System.in);
        int answer =  scanner.nextInt();
        switch (answer){
            case 1:
                app.currentInterface = new AppConsole();
                break;
            case 2:
                app.currentInterface = new AppTelega();
                break;
            case 3:
                app.currentInterface = new AppWeb();
                break;
        }
        app.currentInterface.initialize(new Dispetcher(app.db));
        app.currentInterface.start();
//        while (app.currentInterface.isLife()) {
//            try {
//                System.out.println("Main thread work.");
//                Thread.sleep(400);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//                break;
//            }
//        }
    }
}
