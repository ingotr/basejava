package com.basejava.webapp.storage.serializer;

import com.basejava.webapp.model.ContactType;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.model.Section;
import com.basejava.webapp.model.SectionType;

import java.io.*;
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
//            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
//                dos.writeUTF(entry.getKey().name());
//                dos.writeUTF(String.valueOf(entry.getValue()));
//            }
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
            System.out.println(sectionsSize);
//            for (int i = 0; i < sectionsSize; i++) {
//                resume.addSection(SectionType.valueOf(dis.readUTF()), dis.readUTF());
//            }
            return resume;
        }
    }
}
