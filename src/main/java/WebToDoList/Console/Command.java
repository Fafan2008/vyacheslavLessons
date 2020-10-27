package WebToDoList.Console;

public enum Command {
    OBS("/obs"),
    ADD("/add"),
    DEL("/del"),
    UPD("/upd"),
    EXT("/ext");
    private String text;

    Command(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static Command fromString(String text) {
        for (Command cmd : Command.values()) {
            if (cmd.text.equalsIgnoreCase(text)) {
                return cmd;
            }
        }
        return null;
    }
}
