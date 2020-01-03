package com.maxkosh.webapp.storage;

import com.maxkosh.webapp.model.*;
import com.maxkosh.webapp.util.DateUtil;

import java.time.Month;
import java.util.*;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume = new Resume("uuid", "Григорий Кислин");

        resume.getContacts().put(ContactType.E_MAIL, "gkislin@yandex.ru");
        resume.getContacts().put(ContactType.PHONE_NUMBER, "+7(921)855-0482");
        resume.getContacts().put(ContactType.SKYPE, "grigory.kislin");
        resume.getContacts().put(ContactType.LINKEDIN, "LinkedIn");
        resume.getContacts().put(ContactType.GITHUB, "GitHub");
        resume.getContacts().put(ContactType.STACKOVERFLOW, "StackOverFlow");
        resume.getContacts().put(ContactType.HOMEPAGE, "TopJava");

        TextSection objective = new TextSection("\nВедущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям\n");

        TextSection personal = new TextSection("\nАналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.\n");

        List<String> achievementList = new ArrayList<>();
        achievementList.add("\n- С 2013 года: разработка проектов \"Разработка Web приложения\"," +
                "\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). " +
                "Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        achievementList.add("\n- Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievementList.add("\n- Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. " +
                "Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. " +
                "Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        achievementList.add("\n- Реализация c нуля Rich Internet Application приложения на стеке технологий " +
                "JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        ListSection achievement = new ListSection(achievementList);

        List<String> qualificationList = new ArrayList<>();
        qualificationList.add("\n- JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualificationList.add("\n- Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualificationList.add("\n- DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        qualificationList.add("\n- MySQL, SQLite, MS SQL, HSQLDB");
        ListSection qualification = new ListSection(qualificationList);

        Company firstCompany = new Company(
                "Alcatel",
                null,
                DateUtil.of(1997, Month.SEPTEMBER),
                DateUtil.of(2005, Month.JANUARY),
                "Инженер по аппаратному и программному тестированию",
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).");

        Company secondCompany = new Company(
                "Siemens AG",
                "www.siemens.com",
                DateUtil.of(2005, Month.JANUARY),
                DateUtil.of(2007, Month.FEBRUARY),
                "Разработчик ПО",
                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");

        CompanySection experience = new CompanySection(new ArrayList<>());
        experience.getCompanyList().add(firstCompany);
        experience.getCompanyList().add(secondCompany);

        Company firstEducation = new Company(
                "Заочная физико-техническая школа при МФТИ",
                null,
                DateUtil.of(1984, Month.SEPTEMBER),
                DateUtil.of(1987, Month.JANUARY),
                "Студент",
                null);

        Company ifmo = new Company(
                "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                "www.ifmo.ru",
                DateUtil.of(1987, Month.SEPTEMBER),
                DateUtil.of(1993, Month.JULY),
                "Инженер",
                "Программист Fortran, С");

        Company ifmoCont = new Company(
                DateUtil.of(1993, Month.SEPTEMBER),
                DateUtil.of(1996, Month.JULY),
                "Аспирант",
                "Программист С, С++");

        CompanySection education = new CompanySection(new ArrayList<>());
        education.getCompanyList().add(firstEducation);
        education.getCompanyList().add(ifmo);
        education.getCompanyList().add(ifmoCont);

        resume.getSections().put(SectionType.OBJECTIVE, objective);
        resume.getSections().put(SectionType.PERSONAL, personal);
        resume.getSections().put(SectionType.ACHIEVEMENT, achievement);
        resume.getSections().put(SectionType.QUALIFICATIONS, qualification);
        resume.getSections().put(SectionType.EXPERIENCE, experience);
        resume.getSections().put(SectionType.EDUCATION, education);

        System.out.println(resume);
    }
}
