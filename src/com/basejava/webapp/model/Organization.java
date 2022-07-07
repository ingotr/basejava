package com.basejava.webapp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Organization {

    private String title;
    private String website;
    private List<Period> periods;

    public Organization(String title, String website) {
        this.title = title;
        this.website = website;
        this.periods = new ArrayList<>();
    }

    public void addPeriod(String startDate, String endDate, String title, String description) {
        periods.add(new Period(startDate, endDate, title, description));
    }

    @Override
    public String toString() {
        return "Organization{" +
                "title='" + title + '\'' +
                ", website='" + website + '\'' +
                ", periods=" + periods +
                '}';
    }
}
