package com.basejava.webapp.model;

public class TextSection extends Section {
    private String description;

    public TextSection(SectionType type) {
        super(type);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
