package WebToDoList.Models.Task;

import java.util.Date;

public class Filter {
    private String id;
    private String userID;
    private Priority priority;
    private Date start;
    private Date end;

    public Filter(String id, String userID, Priority priority, Date start, Date end) {
        this.id = id;
        this.userID = userID;
        this.priority = priority;
        this.start = start;
        this.end = end;
    }
    public static boolean match(Task task, Filter filter){
        // i think this function must be release in db package.
        if (task.id.equals(filter.id))
            return true;

        boolean userIDEqual = filter.userID == null;
        boolean priorityEqual = filter.priority == null;
        boolean timeEqual = task.timeToComplete == null;

        if (!userIDEqual)
            if (task.userID.equals(filter.userID))
                userIDEqual = true;
        if (!priorityEqual)
            if (task.priority == filter.priority || (task.priority == Priority.UNDEFINED || filter.priority == Priority.UNDEFINED))
                priorityEqual = true;
        if(!timeEqual && (filter.start != null || filter.end != null)) {
            if (filter.start != null && filter.end != null)
                if (task.timeToComplete.after(filter.start) && task.timeToComplete.before(filter.end))
                    timeEqual = true;
            if (filter.start != null && filter.end == null)
                if (task.timeToComplete.after(filter.start))
                    timeEqual = true;
            if (filter.start == null)
                if (task.timeToComplete.before(filter.end))
                    timeEqual = true;
        }
        return userIDEqual && priorityEqual && timeEqual;
    }
}

