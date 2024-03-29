package com.basejava.webapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListSection extends Section implements Serializable {

    public static final ListSection EMPTY = new ListSection();
    private final List<String> list;

    public ListSection() {
        list = new ArrayList<>();
    }
    
    public ListSection(String[] value) {
        list = new ArrayList<>(List.of(value));
    }

    public ListSection(List<String> list) {
        this.list = list;
    }

    public void addToList(String text) {
        list.add(text);
    }

    public void addAllToList(List<String> content) {
        list.addAll(content);
    }

    public List<String> getList() {
        return list;
    }

    @Override
    public String toString() {
        StringBuilder listToString = new StringBuilder();
        listToString.append(this.getClass().getName()).append(" {\n");
        for (String item : list) {
            listToString.append(item).append("\n");
        }
        listToString.append("}");
        return String.valueOf(listToString);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}
