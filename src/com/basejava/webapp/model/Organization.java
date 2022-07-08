package com.basejava.webapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Organization {

    private final String title;
    private final String website;
    private final List<Period> periods;

    public Organization(String title, String website) {
        this.title = title;
        this.website = website;
        this.periods = new ArrayList<>();
    }

    public void addPeriod(LocalDate startDate, LocalDate endDate, String title, String description) {
        periods.add(new Period(startDate, endDate, title, description));
    }

    @Override
    public String toString() {
        return "Organization: " +
                "title=" + title + " " + "\n" +
                "website=" + website + "\n" +
                "periods=" + getPeriods();
    }

    private String getPeriods() {
        StringBuilder listToString = new StringBuilder();
        for (Period item : periods) {
            listToString.append(item).append("\n");
        }
        return String.valueOf(listToString);
    }
}
