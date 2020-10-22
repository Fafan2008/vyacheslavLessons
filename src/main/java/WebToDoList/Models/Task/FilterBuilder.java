package WebToDoList.Models.Task;

import java.util.Date;

public class FilterBuilder {
    private String id;
    private String userID;
    private Priority priority = Priority.UNDEFINED;
    private Date start = new Date(0);
    private Date end = new Date(-1);

    public FilterBuilder() {}

    public Filter buildFilter(){
        return new Filter(id, userID, priority, start, end);
    }

    public FilterBuilder id(String id){
        this.id = id;
        return this;
    }
    public FilterBuilder userID(String userID){
        this.userID = userID;
        return this;
    }
    public FilterBuilder priority(Priority priority){
        this.priority = priority;
        return this;
    }
    public FilterBuilder start(Date start){
        this.start = start;
        return this;
    }
    public FilterBuilder end(Date end){
        this.end = end;
        return this;
    }
}
