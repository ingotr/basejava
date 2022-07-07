package com.basejava.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class ObjectSection extends Section {
    private List<Organization> records;

    public ObjectSection(SectionType type) {
        super(type);
        records = new ArrayList<>();
    }

//    public ObjectSection(SectionType type, List<Organization> records) {
//        super(type);
//        this.records = records;
//    }

    public Organization addOrganization(String title, String website) {
        Organization newOrganization = new Organization(title, website);
        records.add(newOrganization);
        return newOrganization;
    }

    public List<Organization> getRecords() {
        return records;
    }
}
