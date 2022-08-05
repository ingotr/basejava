package com.basejava.webapp.model;

public enum ContactType {
    PHONE("тел."),
    SKYPE("Skype") {
        @Override
        public String toHtml0(String value) {
            return "<a href='skype" + value + "'>" + value + "</a>";
        }
    },
    EMAIL("Почта") {
        @Override
        public String toHtml0(String value) {
            return "<a href='mailto" + value + "'>" + value + "</a>";
        }
    },
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

    protected String toHtml0(String value) {
        return value + ": " + value;
    }

    public String toHtml(String value) {
        return (value == null) ? "" : toHtml0(value);
    }
}
