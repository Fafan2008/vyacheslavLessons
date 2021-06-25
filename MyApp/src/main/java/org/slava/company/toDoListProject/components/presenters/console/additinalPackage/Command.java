package org.slava.company.toDoListProject.components.presenters.console.additinalPackage;

public enum Command {
    OBS("/obs"),
    VIT("/vit"),
    VIU("/viu"),
    ADD("/add"),
    DEL("/del"),
    TUP("/tup"),
    UUP("/uup"),
    EXT("/ext"),
    HLP("/hlp"),
    UND("/und");
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
        return UND;
    }
}
