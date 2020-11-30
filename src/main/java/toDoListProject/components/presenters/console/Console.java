package toDoListProject.components.presenters.console;

import com.sun.deploy.cache.BaseLocalApplicationProperties;
import toDoListProject.components.entities.task.Task;
import toDoListProject.components.entities.task.UpdateTask;
import toDoListProject.components.entities.user.UpdateUser;
import toDoListProject.components.interactors.IInteractor;
import toDoListProject.components.interactors.exceptions.NotHavePermission;
import toDoListProject.components.interactors.exceptions.TaskNotFoundException;
import toDoListProject.components.interactors.exceptions.UserNotFoundException;
import toDoListProject.components.interactors.exceptions.UsernameExistsException;
import toDoListProject.components.presenters.IPresenter;
import toDoListProject.components.presenters.console.additinalPackage.Command;
import toDoListProject.components.presenters.console.additinalPackage.StringParser;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Console implements IPresenter {
    private final IInteractor iInteractor;
    private boolean authentificated = false;
    private boolean work = true;
    private String userName;
    private Map<Integer, Task> tasks = new HashMap<>();

    public Console(IInteractor iInteractor) {
        this.iInteractor = iInteractor;
    }

    @Override
    public void start() {
        while (work){
            if(!authentificated){
                authenticated();
            }
            else{
                Display.menu();
                Command cmd = enterCmd();
                execute(cmd);
            }
        }
    }

    private void execute(Command cmd) {
            switch (cmd){
                case ADD:
                    addTask();
                    break;
                case DEL:
                    deleteTask();
                    break;
                case OBS:
                    observeTasks();
                    break;
                case EXT:
                    stop();
                    break;
            }
    }

    private void deleteTask() {
        Task task = chooseTask();
        UpdateTask update = new UpdateTask(this.userName, task.getName(), task.getDescription(), task.isOpen());
            try {
                iInteractor.deleteTask(task.getId(), update);
            } catch (TaskNotFoundException e) {
                e.printStackTrace();
            } catch (NotHavePermission notHavePermission) {
                notHavePermission.printStackTrace();
            }
        }

    private Task chooseTask() {
        String str = enterNameOrNumberOfTask();
        if (StringParser.isNumeric(str)){
            int num = Integer.parseInt(str);
            Task task = this.tasks.get(num);

            if (task == null)
                this.tasks.values().stream().filter((item)->item.getName() == str).collect(Collectors.toList());
            //throw new TaskNotFoundException();
        }
        return null;
    }

    private String enterNameOrNumberOfTask() {
        Display.EnterNameOrNumberOfTask();
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void observeTasks() {
        try {
            iInteractor.getTaskList(userName);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addTask() {
        UpdateTask update = enterTask();
        if (!iInteractor.addTask(update).isPresent())
            Display.unsuccessful();
        else
            Display.successful();
    }

    private UpdateTask enterTask() {
        String nameTask = enterNameOfTask();
        String descriptionOfTask = enterDescriptionOfTask();
        return new UpdateTask(userName, nameTask, descriptionOfTask, false);
    }

    private Command enterCmd() {
        Display.enterCmd();
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        Command cmd = Command.fromString(word);
        return cmd;
    }

    private void authenticated() {
        this.authentificated = doLogin();
    }

    private boolean doLogin() {
        String name = enterLogin();
        if(this.iInteractor.isUserPresent(name)){
            this.userName = name;
            Display.successful();
            return true;
        }else {
            Display.unsuccessful();
            Display.doYouWantAddNewUser();
            boolean want = enterYesNo();
            if(want){
                String surname = enterSurname();
                UpdateUser user = new UpdateUser(name, surname);
                try {
                    iInteractor.addUser(user);
                    Display.successfulAddingNewUser();
                } catch (UsernameExistsException e) {
                    e.printStackTrace();
                    Display.unsuccessful();
                    return false;
                }
            }
            return false;
        }

    }

    private boolean enterYesNo() {
        System.out.print("Enter yes or no :");
        Scanner scanner = new Scanner(System.in);
        String name = new String();
        name = scanner.nextLine();
        return StringParser.ToBoolean(name);
    }
    private String enterLogin() {
        Display.enterLogin();
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    private String enterSurname() {
        Display.enterSurname();
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    private String enterNameOfTask() {
        Display.enterNameOfTask();
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    private String enterDescriptionOfTask() {
        Display.enterDescriptionOfTask();
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    @Override
    public void stop() {
        this.work = false;
    }
}
