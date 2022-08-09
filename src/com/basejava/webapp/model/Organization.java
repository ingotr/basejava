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

    public static final Organization EMPTY = new Organization("", "");

    private String title;
    private String website;
    private List<Period> periods;

    public Organization() {
    }

    public Organization(String title, String website) {
        this.title = title;
        this.website = website == null ? "" : website;
        this.periods = new ArrayList<>();
    }

    public Organization(String website, List<Period> periods) {
        this.website = website;
        this.periods = periods;
    }

    public Organization(String title, String website, List<Period> periods) {
        this.title = title;
        this.website = website;
        this.periods = periods;
    }

    public void addPeriod(Period period) {
        periods.add(period);
    }

    public void addPeriod(LocalDate startDate, String position, String duties) {
        periods.add(new Period(startDate, position, duties));
    }

    public void addPeriod(LocalDate startDate, LocalDate endDate, String position, String duties) {
        periods.add(new Period(startDate, endDate, position, duties));
    }

    @Override
    public String toString() {
        return "Organization: " +
                "title=" + title + " " + "\n" +
                "website=" + website + "\n" +
                "periods=" + getPeriods();
    }

    public String getTitle() {
        return title;
    }

    public String getWebsite() {
        return website;
    }

    public List<Period> getPeriods() {
        return periods;
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

    public void addAllPeriods(List<Period> periods) {
        this.periods.addAll(periods);
    }
}
