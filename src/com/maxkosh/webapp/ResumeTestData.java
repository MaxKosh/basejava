package com.maxkosh.webapp;

import com.maxkosh.webapp.model.*;

import java.time.LocalDate;
import java.util.*;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume = new Resume("uuid", "Григорий Кислин");

        resume.contactMap.put(ContactType.E_MAIL, "gkislin@yandex.ru");
        resume.contactMap.put(ContactType.PHONE_NUMBER, "+7(921)855-0482");
        resume.contactMap.put(ContactType.SKYPE, "grigory.kislin");
        resume.contactMap.put(ContactType.LINKEDIN, "LinkedIn");
        resume.contactMap.put(ContactType.GITHUB, "GitHub");
        resume.contactMap.put(ContactType.STACKOVERFLOW, "StackOverFlow");
        resume.contactMap.put(ContactType.HOMEPAGE, "TopJava");

        TextSection objective = new TextSection("\nВедущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям\n");

        TextSection personal = new TextSection("\nАналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.\n");

        ListSection achievement = new ListSection(new ArrayList<>(), "\n- С 2013 года: разработка проектов \"Разработка Web приложения\"," +
                "\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). " +
                "Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        achievement.getStringList().add("\n- Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievement.getStringList().add("\n- Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. " +
                "Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. " +
                "Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        achievement.getStringList().add("\n- Реализация c нуля Rich Internet Application приложения на стеке технологий " +
                "JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");


        ListSection qualification = new ListSection(new ArrayList<>(), "\n- JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualification.getStringList().add("\n- Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualification.getStringList().add("\n- DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        qualification.getStringList().add("\n- MySQL, SQLite, MS SQL, HSQLDB");

        LocalDate firstJobStartDate = LocalDate.of(1997, 9, 1);
        LocalDate firstJobEndDate = LocalDate.of(2005, 1, 1);

        Company firstCompany = new Company(
                "Alcatel",
                firstJobStartDate,
                firstJobEndDate,
                "Инженер по аппаратному и программному тестированию",
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).");

        LocalDate secondJobStartDate = LocalDate.of(2005, 1, 1);
        LocalDate secondJobEndDate = LocalDate.of(2007, 2, 1);

        Company secondCompany = new Company(
                "Siemens AG",
                secondJobStartDate,
                secondJobEndDate,
                "Разработчик ПО",
                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");

        CompanySection experience = new CompanySection(new ArrayList<Company>(), firstCompany);
        experience.getCompanyList().add(secondCompany);

        LocalDate firstEduStartDate = LocalDate.of(1984, 9, 1);
        LocalDate firstEduEndDate = LocalDate.of(1987, 1, 1);

        Company firstEducation = new Company(
                "Заочная физико-техническая школа при МФТИ",
                firstEduStartDate,
                firstEduEndDate,
                "Студент",
                "Закончил с отличием");

        LocalDate secondEduStartDate = LocalDate.of(1993, 9, 1);
        LocalDate secondEduEndDate = LocalDate.of(1996, 7, 1);

        Company secondEducation = new Company(
                "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                secondEduStartDate,
                secondEduEndDate,
                "Аспирант",
                "Программист С, С++");

        CompanySection education = new CompanySection(new ArrayList<Company>(), firstEducation);
        education.getCompanyList().add(secondEducation);

        resume.sectionMap.put(SectionType.OBJECTIVE, objective);
        resume.sectionMap.put(SectionType.PERSONAL, personal);
        resume.sectionMap.put(SectionType.ACHIEVEMENT, achievement);
        resume.sectionMap.put(SectionType.QUALIFICATIONS, qualification);
        resume.sectionMap.put(SectionType.EXPERIENCE, experience);
        resume.sectionMap.put(SectionType.EDUCATION, education);

        System.out.println(resume);
    }
}
