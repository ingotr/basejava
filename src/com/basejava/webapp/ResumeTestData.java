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
        TextSection newSection1 = (TextSection) test.getSection(SectionType.OBJECTIVE);
        newSection1.setDescription("Ведущий стажировок и корпоративного " +
                "обучения по Java Web и Enterprise технологиям ");
        test.addSection(SectionType.PERSONAL);
        TextSection newSection2 = (TextSection) test.getSection(SectionType.PERSONAL);
        newSection2.setDescription("Аналитический склад ума, сильная логика," +
                " креативность, инициативность. Пурист кода и архитектуры.");
        test.addSection(SectionType.ACHIEVMENTS);

        ListSection newSection3 = (ListSection) test.getSection(SectionType.ACHIEVMENTS);
        newSection3.addToList("Организация команды и успешная реализация Java проектов" +
                " для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы," +
                " система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ" +
                " на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет");
        newSection3.addToList("С 2013 года: разработка проектов \"Разработка Web приложения\"," +
                "\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). " +
                "Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн" +
                " стажировок и ведение проектов. Более 3500 выпускников. ");
        newSection3.addToList("Реализация двухфакторной аутентификации для онлайн платформы" +
                " управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator," +
                " Jira, Zendesk. ");
        newSection3.addToList("Налаживание процесса разработки и непрерывной интеграции ERP" +
                " системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения" +
                " управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации" +
                " и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        newSection3.addToList("Реализация c нуля Rich Internet Application приложения на стеке технологий" +
                " JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического" +
                " трейдинга. ");
        newSection3.addToList("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных" +
                " сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации " +
                "о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и " +
                "мониторинга системы по JMX (Jython/ Django). ");
        newSection3.addToList("Реализация протоколов по приему платежей всех основных платежных системы России " +
                "(Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");

        test.addSection(SectionType.QUALIFICATIONS);
        ListSection newSection4 = (ListSection) test.getSection(SectionType.QUALIFICATIONS);
        newSection4.addToList("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        newSection4.addToList("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        newSection4.addToList("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB");
        newSection4.addToList("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
        newSection4.addToList("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
        newSection4.addToList("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis," +
                " Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT," +
                " ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements). ");
        newSection4.addToList("Python: Django.");
        newSection4.addToList("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js ");
        newSection4.addToList("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka ");
        newSection4.addToList("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX," +
                " SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, " +
                "OAuth1, OAuth2, JWT.");
        newSection4.addToList("Инструменты: Maven + plugin development, Gradle, настройка Ngnix ");
        newSection4.addToList("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, " +
                "Nagios, iReport, OpenCmis, Bonita, pgBouncer");
        newSection4.addToList("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, " +
                "архитектурных шаблонов, UML, функционального программирования");
        newSection4.addToList("Родной русский, английский \"upper intermediate\"");

        test.addSection(SectionType.EXPERIENCE);
        ObjectSection newSection5 = (ObjectSection) test.getSection(SectionType.EXPERIENCE);
        Organization currentOrg = newSection5.addOrganization("Java Online Projects", "http://javaops.ru/");
        currentOrg.addPeriod("10/2013", "07/07/2022", "Автор проекта.", "Создание, организация " +
                "и проведение Java онлайн проектов и стажировок.");

        test.addSection(SectionType.EDUCATION);
        ObjectSection newSection6 = (ObjectSection) test.getSection(SectionType.EDUCATION);

        //TODO тут сделать вывод всех данных
        System.out.println(test.getFullName());
        System.out.println(test.getContacts());
        System.out.println(newSection1.getDescription());
        System.out.println(newSection2.getDescription());
        System.out.println(newSection3.getDescriptionList());
        System.out.println(newSection4.getDescriptionList());
        System.out.println(newSection5.getRecords());
    }
}
