package com.basejava.webapp.model;

public enum SectionType {
    OBJECTIVE("позиция"),
    PERSONAL("личные качества"),
    ACHIEVEMENTS("достижения"),
    QUALIFICATIONS("квалификация"),
    EXPERIENCE("опыт"),
    EDUCATION("образование"),
    ;

    private final String name;

    SectionType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
