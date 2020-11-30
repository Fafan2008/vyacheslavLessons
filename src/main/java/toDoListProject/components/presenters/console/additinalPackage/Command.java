package toDoListProject.components.presenters.console.additinalPackage;

public enum Command {
    OBS("/obs"),
    ADD("/add"),
    DEL("/del"),
    UPD("/upd"),
    EXT("/ext"),
    HLP("/hlp"),
    UND(null);
    private String text;

    private Command(String text) {
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
        return UND;
    }
}
