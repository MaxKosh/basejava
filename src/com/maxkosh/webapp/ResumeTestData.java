package com.maxkosh.webapp;

import com.maxkosh.webapp.model.*;

import java.util.*;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume = new Resume("uuid", "Max Koshelev");

        TextSection resumeTextSection = new TextSection("some text");
        ListSection qualification = new ListSection(new ArrayList<>(), "some text");
        Company firstCompany = new Company("TopJava", "01.01.2019 - 01.01.2020", "Java Dev", "some text");
        DateSection experience = new DateSection(new ArrayList<Company>(), firstCompany);

        resume.sectionMap.put(SectionType.PERSONAL, resumeTextSection);
        resume.sectionMap.put(SectionType.QUALIFICATIONS, qualification);
        resume.sectionMap.put(SectionType.EXPERIENCE, experience);

        resume.contactMap.put(ContactType.E_MAIL, "maxdiorio@yandex.ru");
        resume.contactMap.put(ContactType.PHONE_NUMBER, "+7(999)999-99-99");

        System.out.println(resume);





    }
}
