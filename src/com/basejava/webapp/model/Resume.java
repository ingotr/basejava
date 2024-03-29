package com.basejava.webapp.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.*;

/**
 * Initial resume class
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Comparable<Resume>, Serializable {
    // Unique identifier

    public static final Resume EMPTY = new Resume();

    static {
        EMPTY.addSection(SectionType.OBJECTIVE, TextSection.EMPTY);
        EMPTY.addSection(SectionType.PERSONAL, TextSection.EMPTY);
        EMPTY.addSection(SectionType.ACHIEVEMENTS, ListSection.EMPTY);
        EMPTY.addSection(SectionType.QUALIFICATIONS, ListSection.EMPTY);
        EMPTY.addSection(SectionType.EXPERIENCE, new OrganizationSection(Organization.EMPTY));
        EMPTY.addSection(SectionType.EDUCATION, new OrganizationSection(Organization.EMPTY));
    }
    private String uuid;
    private String fullName;

    private final Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);
    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);

    public Resume() {
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void addContact(ContactType type, String contact) {
        contacts.put(type, contact);
    }

    public String getContact(ContactType type) {
        return contacts.get(type);
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public void addSection(SectionType type, Section section) {
        sections.put(type, section);
    }

    public void addSection(SectionType type) {
        switch (type) {
            case PERSONAL:
            case OBJECTIVE:
                sections.put(type, new TextSection(""));
                break;
            case ACHIEVEMENTS:
            case QUALIFICATIONS:
                sections.put(type, new ListSection());
                break;
            case EXPERIENCE:
            case EDUCATION:
                sections.put(type, new OrganizationSection());
                break;
            default:
                break;
        }
    }

    public void addSection(SectionType type, String content) {
        switch (type) {
            case PERSONAL:
            case OBJECTIVE:
                sections.put(type, new TextSection(content));
                break;
            case ACHIEVEMENTS:
            case QUALIFICATIONS:
                sections.put(type, new ListSection());
                break;
            case EXPERIENCE:
            case EDUCATION:
                sections.put(type, new OrganizationSection());
                break;
            default:
                break;
        }
    }
    

    public Section getSection(SectionType type) {
        return sections.get(type);
    }

    public Map<SectionType, Section> getSections() {
        return sections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullName, resume.fullName) &&
                Objects.equals(sections, resume.sections) &&
                Objects.equals(contacts, resume.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, sections, contacts);
    }

    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }

    @Override
    public int compareTo(Resume o) {
        int compareName = fullName.compareTo(o.fullName);
        return compareName != 0  ? compareName : uuid.compareTo(o.uuid);
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
