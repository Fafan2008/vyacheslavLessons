package toDoListProject.components.presenters.console.additinalPackage;

public class StringParser {
    static public boolean ToBoolean(String input) {
        if (input == null)
            throw new IllegalArgumentException("Null input for stringToBoolean");
        input = input.trim().toLowerCase();
        if (input.equals("1") || input.equals("yes") || input.equals("true"))
            return true;
        if (input.equals("0") || input.equals("no") || input.equals("false"))
            return false;
        throw new IllegalArgumentException("Bad input for stringToBoolean: " + input);
    }
}