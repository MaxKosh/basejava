package com.maxkosh.webapp;

import com.maxkosh.webapp.model.ContactType;
import com.maxkosh.webapp.model.Resume;

public class MainResume {

    public static void main(String[] args) {
        Resume resume = new Resume("uuid_1", "Max Koshelev");
        Resume.PersonalSection personal = resume.new PersonalSection();

        personal.setContacts(ContactType.E_MAIL, "maxdiorio@yandex.ru");
        personal.setContacts(ContactType.SKYPE, "maxdiorio69");


        System.out.println(personal.getContacts(ContactType.E_MAIL));
        System.out.println(personal.getContacts(ContactType.SKYPE));

        for (ContactType contactType : ContactType.values())
        System.out.println(contactType.getTitle());
    }
}
