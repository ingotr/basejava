package com.basejava.webapp.model;

public enum SectionType {
    OBJECTIVE("позиция"),
    PERSONAL("личные качества"),
    ACHIEVEMENTS("достижения"),
    QUALIFICATIONS("квалификация"),
    EXPERIENCE("опыт"),
    EDUCATION("образование"),
    ;

    private final String value;

    SectionType(String name) {
        this.value = name;
    }

    public String getValue() {
        return this.value;
    }
}
