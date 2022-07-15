package com.basejava.webapp.model;

import java.io.Serializable;
import java.util.Objects;

public class TextSection extends Section implements Serializable {
    private String content;

    public TextSection() {
    }

    public TextSection(String content) {
        //super(type);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "content: " + content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextSection that = (TextSection) o;
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}
