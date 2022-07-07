package com.basejava.webapp.model;

public abstract class Section {
    private final SectionType type;

    public Section(SectionType type) {
        this.type = type;
    }

    public SectionType getType() {
        return type;
    }
}
