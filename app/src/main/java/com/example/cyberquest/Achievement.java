package com.example.cyberquest;

public class Achievement {

    private final String id;
    private final String title;
    private final String description;
    private final String icon;

    public Achievement(
            String id,
            String title,
            String description,
            String icon
    ) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.icon = icon;
    }

    public String getId() {

        return id;
    }

    public String getTitle() {

        return title;
    }

    public String getDescription() {

        return description;
    }

    public String getIcon() {

        return icon;
    }
}