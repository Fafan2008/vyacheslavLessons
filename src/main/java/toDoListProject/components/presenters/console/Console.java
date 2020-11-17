package toDoListProject.components.presenters.console;

import toDoListProject.components.entities.task.UpdateTask;
import toDoListProject.components.entities.user.IUpdateUser;
import toDoListProject.components.entities.user.UpdateUser;
import toDoListProject.components.interactors.IInteractor;
import toDoListProject.components.interactors.exceptions.UserNotFoundException;
import toDoListProject.components.interactors.exceptions.UsernameExistsException;
import toDoListProject.components.presenters.IPresenter;
import toDoListProject.components.presenters.console.additinalPackage.Command;
import toDoListProject.components.presenters.console.additinalPackage.StringParser;

import java.util.Scanner;

public class Console implements IPresenter {
    private final IInteractor iInteractor;
    private boolean authentificated = false;

    public Console(IInteractor iInteractor) {
        this.iInteractor = iInteractor;
    }

    @Override
    public void start() {
        //example
        while (work){
            if(!authentificated){
                authentificate();
            }
            else{
                Display.menu();
                Command cmd = enterCmd();
                execute(cmd);
            }
        }
//        try {
//            iInteractor.deleteUser("abc");
//        } catch (UserNotFoundException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//
//        } finally {
//
//        }
    }

    private void execute(Command cmd) {
        switch (cmd){
            case ADD:
                UpdateTask update = enterTask();
                iInteractor.addTask(update);
                break;
            case DEL:
                break;
            case OBS:
                break;
            case EXT:
                stop();
                break;
        }
    }

    private UpdateTask enterTask() {
        //enter some data of task
        return new UpdateTask("name");
    }

    private Command enterCmd() {
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        Command cmd = Command.fromString(word);
        return cmd;
    }

    private void authentificate() {
        authentificated = doLogin();
    }

    private boolean doLogin() {
        Display.enterLogin();
        String name = enterLogin();
        if(this.iInteractor.isUserPresent(name)){
            this.userName = name;
            Display.successfullLogin();
            return true;
        }else {
            Display.unsuccessfullLogin();
            Display.doYouWantAddNewUser();
            boolean want = enterYesNo();
            if(want){
                IUpdateUser user = new UpdateUser(name);
                try {
                    iInteractor.addUser(user);
                } catch (UsernameExistsException e) {
                    e.printStackTrace();
                    Display.unsuccessfullLogin();
                    return false;
                }
            }
            return false;
        }

    }

    private boolean enterYesNo() {
        Scanner scanner = new Scanner(System.in);
        String name = new String();
        name = scanner.nextLine();
        return StringParser.ToBoolean(name);
    }

    private String enterLogin() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public void stop() {
        work = false;
    }

    private boolean work = true;
    private String userName;
}
