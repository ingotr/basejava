package com.basejava.webapp.model;

public enum ContactType {
    EMAIL("электронная почта"),
    MOBILE_PHONE("мобильный телефон"),
    GITHUB("github профиль"),
    STACKOVERFLOW("stackoverflow профиль"),
    HOMEPAGE("домашняя страница"),
    TELEGRAM("телеграм"),
    SLACK("slack"),
    ;

    ContactType(String value) {
    }
}
