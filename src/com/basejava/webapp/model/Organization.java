package com.basejava.webapp.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {

    private String title;
    private String website;
    private List<Period> periods;

    public Organization() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(title, that.title) && Objects.equals(website, that.website)
                && Objects.equals(periods, that.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, website, periods);
    }
}
