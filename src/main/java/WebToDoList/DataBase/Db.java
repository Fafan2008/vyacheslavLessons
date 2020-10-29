package WebToDoList.DataBase;
import WebToDoList.Models.Task.Task;
import WebToDoList.Models.User.User;

import java.util.HashMap;
import java.util.Map;

public class Db{
    Map<String,Task> taskMap = new HashMap<>();
    Map<String, User> userMap = new HashMap<>();
}
