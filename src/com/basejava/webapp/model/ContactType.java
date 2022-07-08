package com.basejava.webapp.model;

public enum ContactType {
    PHONE("тел."),
    SKYPE("skype"),
    EMAIL("почта"),
    GITHUB("профиль GitHub"),
    LINKEDIN("профиль Linkedin"),
    STACKOVERFLOW("профиль Stackoverflow"),
    HOMEPAGE("домашняя страница"),
    ;
    private final String value;

    ContactType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
