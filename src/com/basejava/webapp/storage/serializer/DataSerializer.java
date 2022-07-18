package com.basejava.webapp.storage.serializer;

import com.basejava.webapp.model.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataSerializer implements Serializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            // TODO implements sections
            Map<SectionType, Section> sections = r.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                String type = entry.getKey().name();
                switch (type) {
                    case "OBJECTIVE":
                    case "PERSONAL":
                        dos.writeUTF(entry.getKey().name());
                        dos.writeUTF(String.valueOf(entry.getValue()));
                        break;
                    case "ACHIEVEMENTS":
                    case "QUALIFICATIONS":
                        dos.writeUTF(entry.getKey().name());
                        List<String> content = ((ListSection)(entry.getValue())).getList();
                        dos.writeInt(content.size());
                        for (String line : content) {
                            dos.writeUTF(line);
                        }
                        break;
                    case "EXPERIENCE":
                    case "EDUCATION":
                        dos.writeUTF(entry.getKey().name());
                        List<Organization> organizations = ((OrganizationSection)(entry.getValue())).getOrganizations();
                        dos.writeInt(organizations.size());
                        for (Organization org : organizations) {
                            dos.writeUTF(org.getTitle());
                            dos.writeUTF(org.getWebsite());
                            dos.writeInt(org.getPeriods().size());
                            for (Period period : org.getPeriods()) {
                                dos.writeUTF(String.valueOf(period.getStartDate()));
                                dos.writeUTF(String.valueOf(period.getEndDate()));
                                dos.writeUTF(period.getPosition());
                                dos.writeUTF(period.getDuties());
                            }
                        }
                        break;
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            // TODO implements sections
            int sectionsSize = dis.readInt();
            //System.out.println(sectionsSize);
            for (int i = 0; i < sectionsSize; i++) {
                String type = dis.readUTF();
                switch (type) {
                    case "OBJECTIVE":
                    case "PERSONAL":
                        String text = dis.readUTF();
                        resume.addSection(SectionType.valueOf(type), text);
                        break;
                    case "ACHIEVEMENTS":
                    case "QUALIFICATIONS":
                        int contentSize = dis.readInt();
                        List<String> content = new ArrayList<>();
                        for(int j = 0; j < contentSize; j++) {
                            content.add(dis.readUTF());
                        }
                        ListSection listSection = new ListSection();
                        listSection.addAllToList(content);
                        resume.addSection(SectionType.valueOf(type), listSection);
                        break;
                    case "EXPERIENCE":
                    case "EDUCATION":
                        OrganizationSection organizationSection = new OrganizationSection();
                        int organizationsSize = dis.readInt();
                        List<Organization> organizations = new ArrayList<>();
                        for (int j = 0; j < organizationsSize; j++) {
                            String title = dis.readUTF();
                            String website = dis.readUTF();
//                            System.out.println("title: " + title);
//                            System.out.println("website: " + website);
                            Organization newOrg = new Organization(title, website);
                            int periodsSize = dis.readInt();
                            for (int k = 0; k < periodsSize; k++) {
                                LocalDate startdate = LocalDate.parse(dis.readUTF());
                                LocalDate enddate = LocalDate.parse(dis.readUTF());
//                                System.out.println("stardate: " + startdate);
//                                System.out.println("enddate: " + enddate);
                                String position = dis.readUTF();
                                String duties = dis.readUTF();
//                                System.out.println("position: " + position);
//                                System.out.println("duties: " + duties);
                                newOrg.addPeriod(startdate, enddate, position, duties);
                            }
                            //System.out.println(newOrg);
                            organizations.add(newOrg);
                        }
                        organizationSection.addAllOrganizations(organizations);
                        //System.out.println(organizationSection);
                        resume.addSection(SectionType.valueOf(type), organizationSection);
                        break;
                }
            }
            return resume;
        }
    }
}
