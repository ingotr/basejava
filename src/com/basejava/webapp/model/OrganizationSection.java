package com.basejava.webapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OrganizationSection extends Section implements Serializable {
    private final List<Organization> organizations;

    public OrganizationSection() {
        organizations = new ArrayList<>();
    }

    public OrganizationSection(Organization... organizations) {
        this(Arrays.asList(organizations));
    }

    public OrganizationSection(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public Organization addOrganization(String title, String website) {
        Organization newOrganization = new Organization(title, website);
        organizations.add(newOrganization);
        return newOrganization;
    }

    public void addAllOrganizations(List<Organization> organizations) {
        this.organizations.addAll(organizations);
    }

    public Organization getOrganization(int index) {
        return organizations.get(index);
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    @Override
    public String toString() {
        StringBuilder listToString = new StringBuilder();
        listToString.append(this.getClass().getName()).append(" {\n");
        for (Organization item : organizations) {
            listToString.append(item).append("\n");
        }
        listToString.append("}");
        return String.valueOf(listToString);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationSection that = (OrganizationSection) o;
        return Objects.equals(organizations, that.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizations);
    }
}
