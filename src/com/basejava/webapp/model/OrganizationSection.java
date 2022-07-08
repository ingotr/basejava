package com.basejava.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class OrganizationSection extends Section {
    private final List<Organization> organizations;

    public OrganizationSection(SectionType type) {
        super(type);
        organizations = new ArrayList<>();
    }

    public Organization addOrganization(String title, String website) {
        Organization newOrganization = new Organization(title, website);
        organizations.add(newOrganization);
        return newOrganization;
    }

    public Organization getOrganization(int index) {
        return organizations.get(index);
    }

    @Override
    public String toString() {
        StringBuilder listToString = new StringBuilder();
        listToString.append(this.getType().getName()).append(" {\n");
        for (Organization item : organizations) {
            listToString.append(item).append("\n");
        }
        listToString.append("}");
        return String.valueOf(listToString);
    }
}
