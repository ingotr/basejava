package com.basejava.webapp.model;

public enum ContactType {
    PHONE("тел."),
    SKYPE("Skype") {
        @Override
        public String toHtml0(String value) {
            return getValue() + ": " + toLink("skype:" + value, value);
        }
    },
    EMAIL("Почта") {
        @Override
        public String toHtml0(String value) {
            return getValue() + ": " + toLink(value);
        }

        @Override
        public String toLink(String value) {
            return (value == null) ? "" : toLink("mailto:" + value, value);
        }
    },
    GITHUB("Профиль GitHub") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    LINKEDIN("Профиль LinkedIn") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    STACKOVERFLOW("Профиль Stackoverflow") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    HOMEPAGE("Домашняя страница") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    };
    private final String value;

    ContactType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    protected String toHtml0(String value) {
        return getValue() + ": " + value;
    }

    public String toHtml(String value) {
        return (value == null) ? "" : toHtml0(value);
    }

    public String toLink(String href) {
        return toLink(href, value);
    }

    public static String toLink(String href, String title) {
        return "<a class=\"contact-link\" href='" + href + "'>" + title + "</a>";
    }
}
