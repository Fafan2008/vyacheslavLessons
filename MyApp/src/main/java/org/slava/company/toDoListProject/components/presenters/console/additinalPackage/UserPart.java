package org.slava.company.toDoListProject.components.presenters.console.additinalPackage;

public enum UserPart {
    NAME("Name"),
    SURNAME("Surname"),
    UND("Undefined");

    private String text;

    UserPart(String text) {
        this.text = text;
    }
    public String getText() {
        return this.text;
    }
    public static UserPart fromString(String text) {
        for (UserPart part : UserPart.values()) {
            if (part.text.equalsIgnoreCase(text)) {
                return part;
            }
        }
        return UND;
    }
}
