package com.basejava.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class ListSection extends Section {
    private final List<String> list;

    public ListSection(SectionType type) {
        super(type);
        list = new ArrayList<>();
    }

    public void addToList(String text) {
        list.add(text);
    }

    public List<String> getList() {
        return list;
    }

    @Override
    public String toString() {
        StringBuilder listToString = new StringBuilder();
        listToString.append(this.getType().getName()).append(" {\n");
        for (String item : list) {
            listToString.append(item).append("\n");
        }
        listToString.append("}");
        return String.valueOf(listToString);
    }
}
