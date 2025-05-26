package com.tildawn.Enums;

public enum SortBy {
    username("username"),
    score("score"),
    killCount("killCount")
    ,TimeSurvived("TimeSurvived");
    private final String name;
    SortBy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public static SortBy getByName(String name) {
        for (SortBy sortBy : SortBy.values()) {
            if (sortBy.getName().equals(name)) {
                return sortBy;
            }
        }
        return username;
    }
}
