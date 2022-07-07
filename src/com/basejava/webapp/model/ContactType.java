package com.basejava.webapp.model;

public enum ContactType {
    EMAIL("электронная почта"),
    MOBILE_PHONE("мобильный телефон"),
    GITHUB("профиль github"),
    LINKEDIN("профиль linkedin"),
    STACKOVERFLOW("профиль stackoverflow"),
    HOMEPAGE("домашняя страница"),
    TELEGRAM("телеграм"),
    SLACK("slack"),
    SKYPE("skype"),
    ;

    ContactType(String value) {
    }
}
