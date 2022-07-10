package com.basejava.webapp;

import com.basejava.webapp.model.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume test = new Resume("Григорий Кислин");
        addMockContacts(test);
        addMockListSections(test);
        addMockTextSections(test);
        addMockOrganizationsSections(test);

        printMockResume(test);
    }

    private static void printMockResume(Resume test) {
        System.out.println(test.getFullName());
        for (Map.Entry<ContactType, String> entry : test.getContacts().entrySet()) {
            System.out.println(entry.getKey().getValue() + ": " + entry.getValue());
        }
        System.out.println();
        for (Map.Entry<SectionType, Section> entry : test.getSections().entrySet()) {
            System.out.println(entry.getKey().getValue() + ": \n" + entry.getValue());
            System.out.println();
        }
    }

    private static void addMockOrganizationsSections(Resume test) {
        test.addSection(SectionType.EXPERIENCE);
        OrganizationSection experienceSection = (OrganizationSection) test.getSection(SectionType.EXPERIENCE);
        (experienceSection.addOrganization("Java Online Projects", "http://javaops.ru/")).
                addPeriod((YearMonth.parse("2013-10").atEndOfMonth()), LocalDate.now(), "Автор проекта.",
                        "Создание, организация и проведение Java онлайн проектов и стажировок.");
        (experienceSection.addOrganization("Wrike", "https://www.wrike.com/")).
                addPeriod((YearMonth.parse("2014-10").atEndOfMonth()), (YearMonth.parse("2016-01").atEndOfMonth()),
                        "Автор проекта.Старший разработчик (backend)",
                        "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API," +
                                " Maven, Spring, MyBatis, Guava, " + "Vaadin, PostgreSQL, Redis). Двухфакторная " +
                                "аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        (experienceSection.addOrganization("RIT Center", "")).
                addPeriod((YearMonth.parse("2012-04").atEndOfMonth()), (YearMonth.parse("2014-10").atEndOfMonth()),
                        "Автор проекта.", "Организация процесса" +
                                " разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI " +
                                "(Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), " +
                                "AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: " +
                                "CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). " +
                                "Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. " +
                                "Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, " +
                                "xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
        (experienceSection.addOrganization("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/")).
                addPeriod((YearMonth.parse("2010-12").atEndOfMonth()), (YearMonth.parse("2012-04").atEndOfMonth()), "Ведущий программист",
                        "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, " +
                                "SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. " +
                                "Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в " +
                                "области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), " +
                                "Highstock, Commet, HTML5.");
        (experienceSection.addOrganization("Yota", "https://www.yota.ru/")).
                addPeriod((YearMonth.parse("2008-06").atEndOfMonth()), (YearMonth.parse("2010-12").atEndOfMonth()), "Ведущий специалист",
                        "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" " +
                                "(GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). " +
                                "Реализация администрирования, статистики и мониторинга фреймворка. Разработка online " +
                                "JMX клиента (Python/ Jython, Django, ExtJS)");
        (experienceSection.addOrganization("Enkata", "http://enkata.com/")).
                addPeriod((YearMonth.parse("2007-03").atEndOfMonth()), (YearMonth.parse("2008-06").atEndOfMonth()), "Разработчик ПО",
                        "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, " +
                                "JMS) частей кластерного J2EE приложения (OLAP, Data mining).");
        (experienceSection.addOrganization("Siemens AG", "https://www.siemens.com/ru/ru/home.html")).
                addPeriod((YearMonth.parse("2005-01").atEndOfMonth()), (YearMonth.parse("2007-02").atEndOfMonth()), "Разработчик ПО",
                        "Разработка информационной модели, проектирование интерфейсов, реализация и отладка " +
                                "ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");
        (experienceSection.addOrganization("Alcatel", "http://www.alcatel.ru/")).
                addPeriod((YearMonth.parse("1997-09").atEndOfMonth()), (YearMonth.parse("2005-01").atEndOfMonth()), "Инженер по аппаратному и программному тестированию",
                        "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).s");

        test.addSection(SectionType.EDUCATION);
        ((OrganizationSection) test.getSection(SectionType.EDUCATION)).addOrganization("Courser",
                        "https://www.coursera.org/course/progfun")
                .addPeriod((YearMonth.parse("2013-03").atEndOfMonth()), (YearMonth.parse("2013-05").atEndOfMonth()), "'Functional Programming Principles in Scala' " +
                        "by Martin Odersky", "");
        ((OrganizationSection) test.getSection(SectionType.EDUCATION)).addOrganization("Luxoft",
                        "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366")
                .addPeriod((YearMonth.parse("2011-03").atEndOfMonth()), (YearMonth.parse("2011-04").atEndOfMonth()), "Курс 'Объектно-ориентированный анализ ИС. " +
                        "Концептуальное моделирование на UML.'", "");
        ((OrganizationSection) test.getSection(SectionType.EDUCATION)).addOrganization("Siemens AG",
                        "http://www.siemens.ru/")
                .addPeriod((YearMonth.parse("2005-01").atEndOfMonth()), (YearMonth.parse("2005-04").atEndOfMonth()), "6 месяцев обучения цифровым телефонным сетям (Москва)", "");
        ((OrganizationSection) test.getSection(SectionType.EDUCATION)).addOrganization("Alcatel",
                        "http://www.alcatel.ru/")
                .addPeriod((YearMonth.parse("1997-09").atEndOfMonth()), (YearMonth.parse("1998-03").atEndOfMonth()), "6 месяцев обучения цифровым телефонным сетям (Москва)", "");
        ((OrganizationSection) test.getSection(SectionType.EDUCATION)).addOrganization("Санкт-Петербургский национальный" +
                                " исследовательский университет информационных технологий, механики и оптики",
                        "http://www.ifmo.ru/")
                .addPeriod((YearMonth.parse("1993-09").atEndOfMonth()), (YearMonth.parse("1996-07").atEndOfMonth()), "Аспирантура (программист С, С++)", "");
        ((OrganizationSection) test.getSection(SectionType.EDUCATION)).getOrganization(3)
                .addPeriod((YearMonth.parse("1987-09").atEndOfMonth()), (YearMonth.parse("1993-07").atEndOfMonth()), "Инженер (программист Fortran, C)", "");
        ((OrganizationSection) test.getSection(SectionType.EDUCATION)).addOrganization("Заочная физико-техническая" +
                        " школа при МФТИ", "http://www.school.mipt.ru/")
                .addPeriod((YearMonth.parse("1984-09").atEndOfMonth()), (YearMonth.parse("1987-06").atEndOfMonth()), "Курс 'Объектно-ориентированный анализ ИС. " +
                        "Закончил с отличием", "");
    }

    private static void addMockListSections(Resume test) {
        test.addSection(SectionType.ACHIEVEMENTS);
        ListSection achievementsSection = (ListSection) test.getSection(SectionType.ACHIEVEMENTS);
        achievementsSection.addToList("Организация команды и успешная реализация Java проектов" +
                " для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы," +
                " система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ" +
                " на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет");
        achievementsSection.addToList("С 2013 года: разработка проектов \"Разработка Web приложения\"," +
                "\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). " +
                "Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн" +
                " стажировок и ведение проектов. Более 3500 выпускников. ");
        achievementsSection.addToList("Реализация двухфакторной аутентификации для онлайн платформы" +
                " управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator," +
                " Jira, Zendesk. ");
        achievementsSection.addToList("Налаживание процесса разработки и непрерывной интеграции ERP" +
                " системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения" +
                " управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации" +
                " и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        achievementsSection.addToList("Реализация c нуля Rich Internet Application приложения на стеке технологий" +
                " JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического" +
                " трейдинга. ");
        achievementsSection.addToList("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных" +
                " сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации " +
                "о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и " +
                "мониторинга системы по JMX (Jython/ Django). ");
        achievementsSection.addToList("Реализация протоколов по приему платежей всех основных платежных системы России " +
                "(Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");

        test.addSection(SectionType.QUALIFICATIONS);
        ListSection qualificationsSection = (ListSection) test.getSection(SectionType.QUALIFICATIONS);
        qualificationsSection.addToList("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualificationsSection.addToList("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualificationsSection.addToList("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB");
        qualificationsSection.addToList("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
        qualificationsSection.addToList("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
        qualificationsSection.addToList("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis," +
                " Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT," +
                " ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements). ");
        qualificationsSection.addToList("Python: Django.");
        qualificationsSection.addToList("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js ");
        qualificationsSection.addToList("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka ");
        qualificationsSection.addToList("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX," +
                " SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, " +
                "OAuth1, OAuth2, JWT.");
        qualificationsSection.addToList("Инструменты: Maven + plugin development, Gradle, настройка Ngnix ");
        qualificationsSection.addToList("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, " +
                "Nagios, iReport, OpenCmis, Bonita, pgBouncer");
        qualificationsSection.addToList("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, " +
                "архитектурных шаблонов, UML, функционального программирования");
        qualificationsSection.addToList("Родной русский, английский \"upper intermediate\"");
    }

    private static void addMockTextSections(Resume test) {
        test.addSection(SectionType.OBJECTIVE, "Ведущий стажировок и корпоративного " +
                "обучения по Java Web и Enterprise технологиям ");
        test.addSection(SectionType.PERSONAL, "Аналитический склад ума, сильная логика," +
                " креативность, инициативность. Пурист кода и архитектуры.");
    }

    private static void addMockContacts(Resume test) {
        test.addContact(ContactType.PHONE, "+7(921) 855-0482");
        test.addContact(ContactType.SKYPE, "skype:skype:grigory.kislin");
        test.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        test.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        test.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        test.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        test.addContact(ContactType.HOMEPAGE, "http://gkislin.ru/");
    }
}
