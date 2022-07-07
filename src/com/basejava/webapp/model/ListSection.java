package com.basejava.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class ListSection extends Section {
    private List<String> descriptionList;

    public ListSection(SectionType type) {
        super(type);
        descriptionList = new ArrayList<>();
    }

//    public ListSection(SectionType type, List<String> descriptionList) {
//        super(type);
//        this.descriptionList = descriptionList;
//    }

    public void addToList(String text) {
        descriptionList.add(text);
    }

    public List<String> getDescriptionList() {
        return descriptionList;
    }
}
