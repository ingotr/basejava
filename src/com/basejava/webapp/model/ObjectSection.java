package com.basejava.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class ObjectSection extends Section {
    private final List<Organization> organizations;

    public ObjectSection(SectionType type) {
        super(type);
        organizations = new ArrayList<>();
    }

    public Organization addOrganization(String title, String website) {
        Organization newOrganization = new Organization(title, website);
        organizations.add(newOrganization);
        return newOrganization;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    @Override
    public String toString() {
        return this.getType().getName() + ": " + "ObjectSection{" +
                "organizations=" + organizations +
                '}';
    }
}
