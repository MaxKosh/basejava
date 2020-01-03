package com.maxkosh.webapp.storage;

import com.maxkosh.webapp.model.*;
import com.maxkosh.webapp.util.DateUtil;

import java.time.Month;
import java.util.*;

public class ResumeTestData {
    static Resume resume_1 = new Resume("Григорий Кислин");
    static Resume resume_2 = new Resume("Максим Кошелев");
    static Resume resume_3 = new Resume("Иван Иванов");
    static Resume resume_4 = new Resume("Сергей Сергеев");

    public static void resumeInit() {
        resume_1.getContacts().put(ContactType.E_MAIL, "gkislin@yandex.ru");
        resume_1.getContacts().put(ContactType.PHONE_NUMBER, "+7(921)855-0482");
        resume_1.getContacts().put(ContactType.SKYPE, "grigory.kislin");
        resume_1.getContacts().put(ContactType.LINKEDIN, "LinkedIn");
        resume_1.getContacts().put(ContactType.GITHUB, "GitHub");
        resume_1.getContacts().put(ContactType.STACKOVERFLOW, "StackOverFlow");
        resume_1.getContacts().put(ContactType.HOMEPAGE, "TopJava");

        TextSection objectiveResume1 = new TextSection("\nВедущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям\n");
        TextSection personalResume1 = new TextSection("\nАналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.\n");

        List<String> achievementListResume1 = new ArrayList<>();
        achievementListResume1.add("\n- С 2013 года: разработка проектов \"Разработка Web приложения\"," +
                "\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). " +
                "Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        achievementListResume1.add("\n- Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievementListResume1.add("\n- Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. " +
                "Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. " +
                "Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        achievementListResume1.add("\n- Реализация c нуля Rich Internet Application приложения на стеке технологий " +
                "JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        ListSection achievementResume1 = new ListSection(achievementListResume1);

        List<String> qualificationListResume1 = new ArrayList<>();
        qualificationListResume1.add("\n- JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualificationListResume1.add("\n- Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualificationListResume1.add("\n- DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        qualificationListResume1.add("\n- MySQL, SQLite, MS SQL, HSQLDB");
        ListSection qualificationResume1 = new ListSection(qualificationListResume1);

        List<Position> firstCompanyPositionsResume1 = new ArrayList<>();
        Position firstCompanyPositionResume1 = new Position(
                "Инженер по аппаратному и программному тестированию",
                DateUtil.of(1997, Month.SEPTEMBER),
                DateUtil.of(2005, Month.JANUARY));
        firstCompanyPositionsResume1.add(firstCompanyPositionResume1);

        Company firstCompanyResume1 = new Company(
                "Alcatel",
                null,
                firstCompanyPositionsResume1,
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).");

        List<Position> secondCompanyPositionsResume1 = new ArrayList<>();
        Position secondCompanyPositionResume1 = new Position(
                "Разработчик ПО",
                DateUtil.of(2005, Month.JANUARY),
                DateUtil.of(2007, Month.FEBRUARY));
        secondCompanyPositionsResume1.add(secondCompanyPositionResume1);

        Company secondCompanyResume1 = new Company(
                "Siemens AG",
                "www.siemens.com",
                secondCompanyPositionsResume1,
                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");

        CompanySection experienceResume1 = new CompanySection(new ArrayList<>());
        experienceResume1.getCompanyList().add(firstCompanyResume1);
        experienceResume1.getCompanyList().add(secondCompanyResume1);

        List<Position> firstEduPositionsResume1 = new ArrayList<>();
        Position firstEduPositionResume1 = new Position(
                "Студент",
                DateUtil.of(1984, Month.SEPTEMBER),
                DateUtil.of(1987, Month.JANUARY));
        firstEduPositionsResume1.add(firstEduPositionResume1);

        Company firstEducation = new Company(
                "Заочная физико-техническая школа при МФТИ",
                null,
                firstEduPositionsResume1,
                null);

        List<Position> secondEduPositionsResume1 = new ArrayList<>();
        Position secondEduPosition1Resume1 = new Position(
                "Инженер. Программист Fortran, C",
                DateUtil.of(1987, Month.SEPTEMBER),
                DateUtil.of(1993, Month.JULY));
        secondEduPositionsResume1.add(secondEduPosition1Resume1);

        Position secondEduPosition2Resume1 = new Position(
                "Аспирант. Программист C, C++",
                DateUtil.of(1993, Month.SEPTEMBER),
                DateUtil.of(1996, Month.JULY));
        secondEduPositionsResume1.add(secondEduPosition2Resume1);

        Company ifmo = new Company(
                "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                "www.ifmo.ru",
                secondEduPositionsResume1,
                null);

        CompanySection educationResume1 = new CompanySection(new ArrayList<>());
        educationResume1.getCompanyList().add(firstEducation);
        educationResume1.getCompanyList().add(ifmo);

        resume_1.getSections().put(SectionType.OBJECTIVE, objectiveResume1);
        resume_1.getSections().put(SectionType.PERSONAL, personalResume1);
        resume_1.getSections().put(SectionType.ACHIEVEMENT, achievementResume1);
        resume_1.getSections().put(SectionType.QUALIFICATIONS, qualificationResume1);
        resume_1.getSections().put(SectionType.EXPERIENCE, experienceResume1);
        resume_1.getSections().put(SectionType.EDUCATION, educationResume1);


        resume_2.getContacts().put(ContactType.E_MAIL, "maxdiorio@yandex.ru");
        resume_2.getContacts().put(ContactType.PHONE_NUMBER, "+7(916)202-9446");
        resume_2.getContacts().put(ContactType.SKYPE, "max.diorio");
        resume_2.getContacts().put(ContactType.GITHUB, "GitHub");

        TextSection objectiveResume2 = new TextSection("\nМенеджер отдела продаж\n");
        TextSection personalResume2 = new TextSection("\nКреативность\n");

        List<Position> firstCompanyPositionsResume2 = new ArrayList<>();
        Position firstCompanyPositionResume2 = new Position(
                "Менеджер",
                DateUtil.of(2010, Month.SEPTEMBER),
                DateUtil.of(2011, Month.JANUARY));
        firstCompanyPositionsResume2.add(firstCompanyPositionResume2);

        Company firstCompanyResume2 = new Company(
                "ООО \"Ромашка\"",
                "www.romashka.org",
                firstCompanyPositionsResume2,
                "Продажи цветов");

        List<Position> secondCompanyPositionsResume2 = new ArrayList<>();
        Position secondCompanyPositionResume2 = new Position(
                "Менеджер",
                DateUtil.of(2012, Month.JANUARY),
                DateUtil.of(2019, Month.FEBRUARY));
        secondCompanyPositionsResume2.add(secondCompanyPositionResume2);

        Company secondCompanyResume2 = new Company(
                "Рога и Копыта",
                "www.siemens.com",
                secondCompanyPositionsResume2,
                null);

        CompanySection experienceResume2 = new CompanySection(new ArrayList<>());
        experienceResume2.getCompanyList().add(firstCompanyResume2);
        experienceResume2.getCompanyList().add(secondCompanyResume2);

        resume_2.getSections().put(SectionType.OBJECTIVE, objectiveResume2);
        resume_2.getSections().put(SectionType.PERSONAL, personalResume2);
        resume_2.getSections().put(SectionType.EXPERIENCE, experienceResume2);

        resume_3.getContacts().put(ContactType.E_MAIL, "i.ivanov@yandex.ru");
        resume_3.getContacts().put(ContactType.PHONE_NUMBER, "+7(900)900-9999");
        resume_3.getContacts().put(ContactType.SKYPE, "ivan.ivanov");
        resume_3.getContacts().put(ContactType.STACKOVERFLOW, "StackOverFlow/ivan");
        resume_3.getContacts().put(ContactType.HOMEPAGE, "www.ivanov.ru");

        resume_4.getContacts().put(ContactType.E_MAIL, "sergey@gmail.com");
        resume_4.getContacts().put(ContactType.SKYPE, "sergey.sergey");
    }
}
