package ru.javawebinar.topjava.model;

public enum Role {
    USER("user"),
    ADMIN("admin");

    private String title;

    Role(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}