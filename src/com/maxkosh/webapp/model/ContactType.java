package com.maxkosh.webapp.model;

public enum ContactType {
    PHONE_NUMBER("Тел.: "),
    SKYPE("Skype: "),
    E_MAIL("e-mail: "),
    LINKEDIN("профиль LinkedIn: "),
    GITHUB("профиль GitHub: "),
    STACKOVERFLOW("профиль StackOverflow: "),
    HOMEPAGE("Домашняя страница: ");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
