package com.basejava.webapp;

import com.basejava.webapp.model.*;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume test = new Resume("Григорий Кислин");
        //TODO тут заполнение моками
        test.addContact(ContactType.MOBILE_PHONE, "+7(921) 855-0482");
        test.addContact(ContactType.SKYPE, "skype:skype:grigory.kislin");
        test.addContact(ContactType.EMAIL, "+7(921) 855-0482 ");
        test.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        test.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        test.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        test.addContact(ContactType.HOMEPAGE, "http://gkislin.ru/");

        //TODO создание разделов
        test.addSection(SectionType.OBJECTIVE);
        ((TextSection) test.getSection(SectionType.OBJECTIVE)).
                setContent("Ведущий стажировок и корпоративного " +
                "обучения по Java Web и Enterprise технологиям ");
        test.addSection(SectionType.PERSONAL);
        ((TextSection) test.getSection(SectionType.PERSONAL)).
                setContent("Аналитический склад ума, сильная логика," +
                " креативность, инициативность. Пурист кода и архитектуры.");

        test.addSection(SectionType.ACHIEVMENTS);
        ListSection achievmentsSection = (ListSection) test.getSection(SectionType.ACHIEVMENTS);
        achievmentsSection.addToList("Организация команды и успешная реализация Java проектов" +
                " для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы," +
                " система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ" +
                " на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет");
        achievmentsSection.addToList("С 2013 года: разработка проектов \"Разработка Web приложения\"," +
                "\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). " +
                "Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн" +
                " стажировок и ведение проектов. Более 3500 выпускников. ");
        achievmentsSection.addToList("Реализация двухфакторной аутентификации для онлайн платформы" +
                " управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator," +
                " Jira, Zendesk. ");
        achievmentsSection.addToList("Налаживание процесса разработки и непрерывной интеграции ERP" +
                " системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения" +
                " управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации" +
                " и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        achievmentsSection.addToList("Реализация c нуля Rich Internet Application приложения на стеке технологий" +
                " JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического" +
                " трейдинга. ");
        achievmentsSection.addToList("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных" +
                " сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации " +
                "о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и " +
                "мониторинга системы по JMX (Jython/ Django). ");
        achievmentsSection.addToList("Реализация протоколов по приему платежей всех основных платежных системы России " +
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

        test.addSection(SectionType.EXPERIENCE);
        ObjectSection experienceSection = (ObjectSection) test.getSection(SectionType.EXPERIENCE);
        (experienceSection.addOrganization("Java Online Projects", "http://javaops.ru/")).
                addPeriod("10/2013", "07/07/2022", "Автор проекта.",
                        "Создание, организация и проведение Java онлайн проектов и стажировок.");
        (experienceSection.addOrganization("Wrike", "https://www.wrike.com/")).
                addPeriod("10/2014", "01/2016", "Автор проекта.Старший разработчик (backend)",
                        "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API," +
                                " Maven, Spring, MyBatis, Guava, " + "Vaadin, PostgreSQL, Redis). Двухфакторная " +
                                "аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        (experienceSection.addOrganization("RIT Center", "")).
                addPeriod("04/2012", "10/2014", "Автор проекта.", "Организация процесса" +
                        " разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI " +
                        "(Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), " +
                        "AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: " +
                        "CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). " +
                        "Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. " +
                        "Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, " +
                        "xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
        (experienceSection.addOrganization("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/")).
                addPeriod("12/2010", "04/2012", "Ведущий программист",
                        "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, " +
                                "SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. " +
                                "Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в " +
                                "области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), " +
                                "Highstock, Commet, HTML5.");
        (experienceSection.addOrganization("Yota", "https://www.yota.ru/")).
                addPeriod("06/2008", "12/2010", "Ведущий специалист",
                        "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" " +
                                "(GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). " +
                                "Реализация администрирования, статистики и мониторинга фреймворка. Разработка online " +
                                "JMX клиента (Python/ Jython, Django, ExtJS)");
        (experienceSection.addOrganization("Enkata", "http://enkata.com/")).
                addPeriod("03/2007", "06/2008", "Разработчик ПО",
                        "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, " +
                                "JMS) частей кластерного J2EE приложения (OLAP, Data mining).");
        (experienceSection.addOrganization("Siemens AG", "https://www.siemens.com/ru/ru/home.html")).
                addPeriod("01/2005", "02/2007", "Разработчик ПО",
                        "Разработка информационной модели, проектирование интерфейсов, реализация и отладка " +
                                "ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");
        (experienceSection.addOrganization("Alcatel", "http://www.alcatel.ru/")).
                addPeriod("09/1997", "01/2005", "Инженер по аппаратному и программному тестированию",
                        "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).s");

        test.addSection(SectionType.EDUCATION);
        ObjectSection educationSection = (ObjectSection) test.getSection(SectionType.EDUCATION);

        //TODO тут сделать вывод всех данных
        System.out.println(test.getFullName());
        System.out.println(test.getContacts());
        for (SectionType type :
                SectionType.values()) {
            System.out.println((test.getSection(type)).toString());
        }

//        System.out.println(test.getSection(SectionType.OBJECTIVE));
//        System.out.println(test.getSection(SectionType.PERSONAL));
//        System.out.println(achievmentsSection.getList());
//        System.out.println(qualificationsSection.getList());
//        System.out.println(experienceSection.getOrganizations());
    }
}
