package com.basejava.webapp.model;

import com.sun.source.doctree.SerialDataTree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListSection extends Section implements Serializable {
    private final List<String> list;

    public ListSection() {
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
