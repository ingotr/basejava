package com.basejava.webapp.model;

public class Period {
    private String startDate;
    private String endDate;
    private String position;
    private String duties;

    public Period(String startDate, String endDate, String position, String duties) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.position = position;
        this.duties = duties;
}

    @Override
    public String toString() {
        return "Period{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", position='" + position + '\'' +
                ", duties='" + duties + '\'' +
                '}';
    }
}
