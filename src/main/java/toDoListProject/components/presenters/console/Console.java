package toDoListProject.components.presenters.console;

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

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Console implements IPresenter {
    private final IInteractor iInteractor;
    private boolean work = false;
    private String userName;
    private Map<Integer, Task> tasks = new HashMap<>();

    public Console(IInteractor iInteractor) {
        this.iInteractor = iInteractor;
    }

    @Override
    public void start() {
        if (authenticated()) {
            Display.menu();
            work = true;
        }
        while (work) {
            Command cmd = enterCmd();
            execute(cmd);
        }
    }

    private void execute(Command cmd) {
        switch (cmd) {
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
            case HLP:
                Display.menu();
                break;
        }
    }

    private void deleteTask() {
        Optional<Task> task = chooseTask(this.tasks);
        if (task.isPresent()) {
            UpdateTask update = new UpdateTask(this.userName, task.get().getName(), task.get().getDescription(), task.get().isOpen());
            try {
                iInteractor.deleteTask(task.get().getId(), update);
            } catch (TaskNotFoundException | NotHavePermission e) {
                e.printStackTrace();
            }
        }
    }


    private Optional<Task> chooseTask(Map<Integer, Task> mapTasks) {
        if (mapTasks.isEmpty())
            return Optional.empty();
        Display.show(mapTasks);
        String str = enterNameOrNumberOfTask();
        Map<Integer, Task> newMapTasks = new HashMap<>();
        AtomicInteger count = new AtomicInteger();
        count.set(0);

        //Если было введно число, то пытаемся достать по номеру из mapTasks
        if (StringParser.isNumeric(str)) {
            int num = Integer.parseInt(str);
            Task task = mapTasks.get(num);
            if (task != null)
                newMapTasks.put(count.getAndIncrement(), task);
        }
        // Так же проверяем есть ли задача с похожим названием.
        mapTasks.values().stream().filter((item) -> item.getName().equals(str)).forEach((item) -> newMapTasks.put(count.getAndIncrement(), item));
        //mapTasks.values().stream().filter((task -> task.getName().equals(str))).peek((item -> newMapTasks.put(count.getAndIncrement(), item)));
        if (newMapTasks.isEmpty())
            return Optional.empty();
        if (newMapTasks.size() == 1)
            return Optional.ofNullable(newMapTasks.get(0));
        // Если в выборе оказалось больше чем одна задача повторяем операцию.
        return chooseTask(newMapTasks);
    }

    private String enterNameOrNumberOfTask() {
        Display.EnterNameOrNumberOfTask();
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void observeTasks() {
        Map<Integer, Task> freshList = requestTasks();
        Display.show(freshList);
    }

    private Map<Integer, Task> requestTasks() {
        try {
            List<Task> tasks = iInteractor.getTaskList(userName);
            List<Task> sortedTasks = tasks.stream()
                    .sorted(Comparator.comparing(Task::getName))
                    .collect(Collectors.toList());
            this.tasks.clear();
            for (int i = 0; i < sortedTasks.size(); i++) {
                this.tasks.put(i, sortedTasks.get(i));
            }
            return this.tasks;
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
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
        return Command.fromString(word);
    }

    private boolean authenticated() {
        return doLogin();
    }

    private boolean doLogin() {
        String name = enterLogin();
        if (this.iInteractor.isUserPresent(name)) {
            this.userName = name;
            Display.successful();
            return true;
        } else {
            Display.unsuccessful();
            Display.doYouWantAddNewUser();
            boolean want = false;
            try {
                want = enterYesNo();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            if (want) {
                String surname = enterSurname();
                UpdateUser user = new UpdateUser(name, surname);
                try {
                    if(iInteractor.addUser(user))
                        this.userName = iInteractor.getUser(user).getId();
                    Display.successfulAddingNewUser();
                    return true;
                } catch (UsernameExistsException | UserNotFoundException e) {
                    e.printStackTrace();
                    Display.unsuccessful();
                }
            }
        }
        return false;
    }

    private boolean enterYesNo() throws IllegalArgumentException {
        System.out.print("Enter yes or no :");
        Scanner scanner = new Scanner(System.in);
        String name;
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
