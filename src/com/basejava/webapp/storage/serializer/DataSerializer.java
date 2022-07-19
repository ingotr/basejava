package com.basejava.webapp.storage.serializer;

import com.basejava.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataSerializer implements Serializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            writeCollection(dos, contacts.entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            Map<SectionType, Section> sections = r.getSections();
            writeCollection(dos, sections.entrySet(), entry -> {
                String type = entry.getKey().name();
                dos.writeUTF(entry.getKey().name());
                switch (type) {
                    case "OBJECTIVE":
                    case "PERSONAL":
                        dos.writeUTF(String.valueOf(entry.getValue()));
                        break;
                    case "ACHIEVEMENTS":
                    case "QUALIFICATIONS":
                        writeCollection(dos, ((ListSection) (entry.getValue())).getList(), str -> dos.writeUTF(str));
                        break;
                    case "EXPERIENCE":
                    case "EDUCATION":
                        writeCollection(dos, ((OrganizationSection) (entry.getValue())).getOrganizations(), org -> {
                            dos.writeUTF(org.getTitle());
                            dos.writeUTF(org.getWebsite());
                            writeCollection(dos, org.getPeriods(), period -> {
                                dos.writeUTF(period.getStartDate().toString());
                                dos.writeUTF(period.getEndDate().toString());
                                dos.writeUTF(period.getPosition());
                                dos.writeUTF(period.getDuties());
                            });
                        });
                        break;
                }
            });
        }
    }

    private interface ItemWriter<T> {
        void write(T t) throws IOException;
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection,
                                     ItemWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            writer.write(item);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readCollection(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readCollection(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sectionType, readSection(dis, sectionType));
            });
            return resume;
        }
    }

    private Section readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case OBJECTIVE:
            case PERSONAL:
                return new TextSection(dis.readUTF());
            case ACHIEVEMENTS:
            case QUALIFICATIONS:
                ListSection newListSection = new ListSection();
                newListSection.addAllToList(readList(dis, () -> dis.readUTF()));
                return newListSection;
            case EXPERIENCE:
            case EDUCATION:
                OrganizationSection organizationSection = new OrganizationSection();
                List<Organization> organizations = readList(dis, () -> {
                    String title = dis.readUTF();
                    String website = dis.readUTF();
                    Organization newOrg = new Organization(title, website);
                    List<Period> periods = readList(dis, () ->
                            new Period(readLocalDate(dis), readLocalDate(dis),
                            dis.readUTF(), dis.readUTF()));
                    newOrg.addAllPeriods(periods);
                    return newOrg;
                });
                organizationSection.addAllOrganizations(organizations);
                return organizationSection;
            default:
                throw new IllegalStateException();
        }
    }

    private interface ItemReader<T> {
        T read() throws IOException;
    }

    private <T> List<T> readList(DataInputStream dis, ItemReader<T> reader) throws IOException {
        int contentSize = dis.readInt();
        List<T> content = new ArrayList<>(contentSize);
        for (int j = 0; j < contentSize; j++) {
            content.add(reader.read());
        }
        return content;
    }

    private interface ElementReader {
        void read() throws IOException;
    }

    private void readCollection(DataInputStream dis, ElementReader reader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            reader.read();
        }
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return YearMonth.parse(dis.readUTF()).atEndOfMonth();
    }
}
