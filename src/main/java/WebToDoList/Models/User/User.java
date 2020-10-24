package WebToDoList.Models.User;

import java.util.List;

public class User implements IUser {
    String id;
    String name;
    String surname;
    List<String> taskIds;
    @Override
    public DisplayData GetDisplayData() {
        return new DisplayData(this.name, this.surname);
    }
}
