package com.basejava.webapp.model;

public class TextSection extends Section {
    private String content;

    public TextSection(SectionType type) {
        super(type);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return this.getType().getName() + ": " + "TextSection{" +
                "content='" + content + '\'' +
                '}';
    }
}
