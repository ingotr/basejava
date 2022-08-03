package com.basejava.webapp.web;

import com.basejava.webapp.Config;
import com.basejava.webapp.model.ContactType;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResumeServlet extends HttpServlet {
    private Storage storage;


    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        try (PrintWriter writer = response.getWriter()) {
            StringBuilder htmlHeader = new StringBuilder();
            htmlHeader.append("<!DOCTYPE html>\n");
            htmlHeader.append("<html lang=\"en\">\n");
            htmlHeader.append("<head>\n");
            htmlHeader.append("<meta charset=\"UTF-8\">\n");
            htmlHeader.append("<title>Javabase. Разработка Web приложения.</title\n>");
            htmlHeader.append("</head>\n");
            htmlHeader.append("<body>\n");
            htmlHeader.append("<h2>Список резюме</h2>\n");
            htmlHeader.append("<section>\n");
            htmlHeader.append("<table border=1 cellpadding=5 width=auto>\n");
            htmlHeader.append("<tr>\n");
            htmlHeader.append("<th>Имя</th>\n");
            htmlHeader.append("<th>Сайт</th>\n");
            htmlHeader.append("<th>email</th>\n");
            htmlHeader.append("<th>github</th>\n");
            for (Resume resume : storage.getAllSorted()) {
                htmlHeader.append("<tr>\n");
                htmlHeader.append("<td><a href=\"resume?uuid=");
                htmlHeader.append(resume.getUuid());
                htmlHeader.append("\">");
                htmlHeader.append(resume.getFullName());
                htmlHeader.append("</a></td>\n");
                htmlHeader.append("<td>" + resume.getContact(ContactType.HOMEPAGE) + "</td>\n");
                htmlHeader.append("<td>" + resume.getContact(ContactType.EMAIL) + "</td>\n");
                htmlHeader.append("<td>" + resume.getContact(ContactType.GITHUB) + "</td>\n");
                htmlHeader.append("</tr>\n");
            }
            htmlHeader.append("</tr>\n");
            htmlHeader.append("</table>\n");
            htmlHeader.append("</section>\n");
            htmlHeader.append("</body>\n");
            htmlHeader.append("</html>\n");
            writer.print(htmlHeader);
        }
    }
}