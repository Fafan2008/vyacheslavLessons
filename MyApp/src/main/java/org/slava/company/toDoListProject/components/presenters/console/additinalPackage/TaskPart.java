package org.slava.company.toDoListProject.components.presenters.console.additinalPackage;

public enum TaskPart {
    NAME("Name"),
    DESCRIPTION("Description"),
    ISOPEN("Is open"),
    UND("/und");

    private String text;

    TaskPart(String text) {
        this.text = text;
    }
    public String getText() {
        return this.text;
    }
    public static TaskPart fromString(String text) {
        for (TaskPart part : TaskPart.values()) {
            if (part.text.equalsIgnoreCase(text)) {
                return part;
            }
        }
        return UND;
    }
}