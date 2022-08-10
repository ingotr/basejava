<%@ page import="com.basejava.webapp.model.TextSection" %>
<%@ page import="com.basejava.webapp.model.ListSection" %>
<%@ page import="com.basejava.webapp.model.OrganizationSection" %>
<%@ page import="com.basejava.webapp.util.HtmlUtil" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8" />
    <link rel="stylesheet" href="css/theme/light.css">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/view-resume-styles.css">
    <jsp:useBean id="resume" type="com.basejava.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="scrollable-panel">
    <div class="form-wrapper">
        <div class="full-name">${resume.fullName}
            <a class="no-underline-anchor" href="resume?uuid=${resume.uuid}&action=edit&theme=light">
                <img src="img/light/edit.svg" alt="">
            </a>

        </div>
        <div class="contacts">
            <c:forEach var="contactEntry" items="${resume.contacts}">
                <jsp:useBean id="contactEntry"
                             type="java.util.Map.Entry<com.basejava.webapp.model.ContactType, java.lang.String>"/>

                <div><%=contactEntry.getKey().toHtml(contactEntry.getValue())%>
                </div>
            </c:forEach>
        </div>

        <div class="spacer"></div>

        <c:forEach var="sectionEntry" items="${resume.sections}">
            <jsp:useBean id="sectionEntry"
                         type="java.util.Map.Entry<com.basejava.webapp.model.SectionType, com.basejava.webapp.model.Section>"/>
            <c:set var="type" value="${sectionEntry.key}"/>
            <c:set var="section" value="${sectionEntry.value}"/>
            <jsp:useBean id="section" type="com.basejava.webapp.model.Section"/>
            <div class="section">${type.value}</div>
            <c:choose>
                <c:when test="${type=='OBJECTIVE'}">
                    <div class="position"><%=((TextSection) section).getContent()%>
                    </div>
                </c:when>
                <c:when test="${type=='PERSONAL'}">
                    <div class="qualities"><%=((TextSection) section).getContent()%>
                    </div>
                </c:when>
                <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENTS'}">
                    <ul class="list">
                        <c:forEach var="item" items="<%=((ListSection) section).getList()%>">
                            <li>${item}</li>
                        </c:forEach>
                    </ul>
                </c:when>
                <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                    <c:forEach var="org" items="<%=((OrganizationSection) section).getOrganizations()%>">
                        <div class="section-wrapper">
                            <c:choose>
                                <c:when test="${empty org.website}">
                                    <div class="job-name">${org.title}</div>
                                </c:when>
                                <c:otherwise>
                                    <div class="job-name"><a class="contact-link"
                                                             href="${org.website}">${org.title}</a></div>
                                </c:otherwise>
                            </c:choose>
                            <c:forEach var="period" items="${org.periods}">
                                <jsp:useBean id="period" type="com.basejava.webapp.model.Period"/>
                                <div class="period-position">
                                    <div class="period"><%=HtmlUtil.formatDates(period)%>
                                    </div>
                                    <div class="position">${period.position}</div>
                                </div>
                                <c:choose>
                                    <c:when test="${empty period.duties}">
                                    </c:when>
                                    <c:otherwise>
                                        <div class="description">${period.duties}</div>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </c:when>
            </c:choose>
        </c:forEach>

        <div class="footer-spacer"></div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>