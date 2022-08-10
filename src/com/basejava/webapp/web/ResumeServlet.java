package com.basejava.webapp.web;

import com.basejava.webapp.Config;
import com.basejava.webapp.model.*;
import com.basejava.webapp.storage.Storage;
import com.basejava.webapp.util.DateUtil;
import com.basejava.webapp.util.HtmlUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResumeServlet extends HttpServlet {
    private Storage storage;


    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        Resume r = storage.get(uuid);
        r.setFullName(fullName);
        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (HtmlUtil.isEmpty(value)) {
                r.getContacts().remove(type);
            } else {
                r.addContact(type, value);
            }
        }
        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            String[] values = request.getParameterValues(type.name());
//            if (HtmlUtil.isEmpty(value) && values.length < 2) {
//                //r.getSections().remove(type);
//            } else {
            switch (type) {
                case OBJECTIVE:
                case PERSONAL:
                    r.addSection(type, new TextSection(value));
                    break;
                case ACHIEVEMENTS:
                case QUALIFICATIONS:
                    r.addSection(type, new ListSection(Objects.requireNonNull(value).split("\\n")));
                    break;
                case EDUCATION:
                case EXPERIENCE:
                    List<Organization> orgs = new ArrayList<>();
                    String[] urls = request.getParameterValues(type.name() + "url");
                    for (int i = 0; i < values.length; i++) {
                        String name = values[i];
                        if (!HtmlUtil.isEmpty(name)) {
                            List<Period> periods = new ArrayList<>();
                            String pfx = type.name() + i;
                            String[] startDates = request.getParameterValues(pfx + "startDate");
                            String[] endDates = request.getParameterValues(pfx + "endDate");
                            String[] positions = request.getParameterValues(pfx + "position");
                            String[] duties = request.getParameterValues(pfx + "duties");
                            for (int j = 0; j < positions.length; j++) {
                                if (!HtmlUtil.isEmpty(positions[j])) {
                                    periods.add(new Period(DateUtil.parse(startDates[j]), DateUtil.parse(endDates[j]), positions[j], duties[j]));
                                }
                            }
                            orgs.add(new Organization(name, urls[i], periods));
                        }
                    }
                    r.addSection(type, new OrganizationSection(orgs));
                    break;
            }
                //}
        }
        storage.update(r);
        response.sendRedirect("resume");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");

        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume r;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
                r = storage.get(uuid);
                break;
            case "edit":
                r = storage.get(uuid);
                for (SectionType type : new SectionType[]{SectionType.EXPERIENCE, SectionType.EDUCATION}) {
                    OrganizationSection section = (OrganizationSection) r.getSection(type);
                    List<Organization> emptyFirstOrganizations = new ArrayList<>();
                    emptyFirstOrganizations.add(Organization.EMPTY);
                    if (section != null) {
                        for (Organization org : section.getOrganizations()) {
                            List<Period> emptyFirstPeriods = new ArrayList<>();
                            emptyFirstPeriods.add(Period.EMPTY);
                            emptyFirstPeriods.addAll(org.getPeriods());
                            emptyFirstOrganizations.add(new Organization(org.getTitle(), org.getWebsite(), emptyFirstPeriods));
                        }
                    }
                    r.addSection(type, new OrganizationSection(emptyFirstOrganizations));
                }
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }
}