package toDoListProject.components.presenters.console;

import toDoListProject.components.entities.task.Task;
import toDoListProject.components.entities.task.UpdateTask;
import toDoListProject.components.entities.user.UpdateUser;
import toDoListProject.components.entities.user.User;
import toDoListProject.components.interactors.IInteractor;
import toDoListProject.components.interactors.exceptions.*;
import toDoListProject.components.presenters.IPresenter;
import toDoListProject.components.presenters.console.additinalPackage.*;

import java.util.*;
import java.util.stream.Collectors;

public class Console implements IPresenter {
    private final IInteractor iInteractor;
    private boolean work = false;
    private User m_user;

    public Console(IInteractor iInteractor) {
        this.iInteractor = iInteractor;
    }

    @Override
    public void start() {
        if (authenticate()) {
            Display.menu();
            requestTasks();
            work = true;
        }

        Runnable runnable = () -> {
            while (work) {
                Command cmd = Input.cmd();
                execute(cmd);
            }
        };

        new Thread(runnable).start();
    }

    @Override
    public void stop() {
        this.work = false;
    }

    private void execute(Command cmd) {
        switch (cmd) {
            case ADD:
                addTask();
                break;
            case TUP:
                updateTask();
                break;
            case UUP:
                updateUser();
                break;
            case DEL:
                deleteTask();
                break;
            case OBS:
                observeTasks();
                break;
            case VIT:
                viewTask();
                break;
            case VIU:
                viewUser();
                break;
            case HLP:
                Display.menu();
                break;
            case EXT:
                stop();
                break;
            case UND:
                Display.undefinedCommand();
                break;
        }
    }

    private void viewUser() {
        Display.show(this.m_user);
    }

    private void viewTask() {
        Optional<Task> task = chooseTask(requestTasks());
        if(task.isPresent())
            Display.show(task.get());
        else
            Display.unsuccessful();
    }

    private void updateTask() {
        try {
            Optional<Task> task = chooseTask(requestTasks());
            if (task.isPresent()) {
                Display.show(task.get());
                Display.whatYouWantChange();
                TaskPart part = TaskPart.fromString(Input.string());
                UpdateTask update = null;
                switch (part) {
                    case NAME:
                        String nameTask = Input.nameOfTask();
                        update = new UpdateTask(task.get().getOwner(), nameTask, task.get().getDescription(), task.get().isOpen());
                        break;
                    case DESCRIPTION:
                        String descriptionTask = Input.descriptionOfTask();
                        update = new UpdateTask(task.get().getOwner(), task.get().getName(), descriptionTask, task.get().isOpen());
                        break;
                    case ISOPEN:
                        update = new UpdateTask(task.get().getOwner(), task.get().getName(), task.get().getDescription(), !task.get().isOpen());
                        break;
                    case UND:
                        Display.unsuccessful();
                        return;
                    default:
                        throw new TaskUpdateOperationFail();
                }
                Display.doYouAgree();
                if (Input.yesNo()) {
                    Task newTask = iInteractor.updateTask(task.get().getId(), update);
                    Display.show(newTask);
                }
            } else {
                Display.unsuccessful();
            }
        } catch (TaskNotFoundException | NotHavePermission | IllegalArgumentException | TaskUpdateOperationFail e) {
            Display.unsuccessful();
            e.printStackTrace();
        }
    }

    private void updateUser() {
        try {
            Display.show(this.m_user);
            Display.whatYouWantChange();
            UserPart part = UserPart.fromString(Input.string());
            UpdateUser update = null;
            switch (part) {
                case NAME:
                    String name = Input.nameOfUser();
                    update = new UpdateUser(name, this.m_user.getSurname());
                    break;
                case SURNAME:
                    String surname = Input.surnameOfUser();
                    update = new UpdateUser(this.m_user.getId(), surname);
                    break;
                case UND:
                    Display.unsuccessful();
                    return;
                default:
                    throw new UserUpdateOperationFail();
            }
            Display.doYouAgree();
            if (Input.yesNo()) {
                boolean result = iInteractor.updateUser(this.m_user.getId(), update);
                if(result)
                    this.m_user = iInteractor.getUser(update);
                Display.show(this.m_user);
            }
        } catch (IllegalArgumentException | UserNotFoundException | UsernameExistsException | UserUpdateOperationFail e) {
            Display.unsuccessful();
            e.printStackTrace();
        }
    }
    private void deleteTask() {
        Optional<Task> task = chooseTask(requestTasks());
        if (task.isPresent()) {
            try {
                Display.show(task.get());
                Display.doYouAgree();
                if (Input.yesNo()) {
                    UpdateTask update = new UpdateTask(this.m_user.getId(), task.get().getName(), task.get().getDescription(), task.get().isOpen());
                    iInteractor.deleteTask(task.get().getId(), update);
                }
            } catch (TaskNotFoundException | NotHavePermission | IllegalArgumentException e) {
                Display.unsuccessful();
                e.printStackTrace();
            }
        } else {
            Display.unsuccessful();
        }
    }

    private Optional<Task> chooseTask(Map<Integer, Task> mapTasks) {
        if (mapTasks.isEmpty())
            return Optional.empty();
        Display.show(mapTasks);
        String str = Input.numberOfTask();
        //Если было введно число, то пытаемся достать по номеру из mapTasks
        Task task = null;
        if (StringHelper.isNumeric(str)) {
            int num = Integer.parseInt(str);
            task = mapTasks.get(num);
        } else {
            Display.itIsNotNumber();
        }
        return Optional.ofNullable(task);
    }

    private void observeTasks() {
        Map<Integer, Task> freshList = requestTasks();
        Display.show(freshList);
    }

    private Map<Integer, Task> requestTasks() {
        try {
            List<Task> tasks = iInteractor.getTaskList(m_user.getId());
            List<Task> sortedTasks = tasks.stream()
                    .sorted(Comparator.comparing(Task::getName))
                    .collect(Collectors.toList());
            Map<Integer, Task> mapOfTasks= new HashMap<>();
            for (int i = 0; i < sortedTasks.size(); i++) {
                mapOfTasks.put(i, sortedTasks.get(i));
            }
            return mapOfTasks;
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    private void addTask() {
        String nameTask = Input.nameOfTask();
        String descriptionOfTask = Input.descriptionOfTask();
        UpdateTask update = new UpdateTask(m_user.getId(), nameTask, descriptionOfTask, true);
        if (!iInteractor.addTask(update).isPresent())
            Display.unsuccessful();
        else
            Display.successful();
        requestTasks();
    }

    private boolean authenticate() {
        try {
            String name = Input.login();
            if (this.iInteractor.isUserPresent(name)) {
                this.m_user = iInteractor.getUser(new UpdateUser(name, ""));
                Display.successful();
                return true;
            } else {
                Display.unsuccessful();
                Display.doYouWantAddNewUser();
                boolean yesNo = Input.yesNo();
                if (yesNo) {
                    String surname = Input.surnameOfUser();
                    UpdateUser updateUser = new UpdateUser(name, surname);
                    if (iInteractor.addUser(updateUser)) {
                        this.m_user = iInteractor.getUser(updateUser);
                        Display.successfulAddingNewUser();
                        return true;
                    }
                }
            }
        } catch (UsernameExistsException | UserNotFoundException | IllegalArgumentException e) {
            Display.unsuccessful();
        }
        return false;
    }
}
