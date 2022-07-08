package com.basejava.webapp.model;

import java.time.LocalDate;

public class Period {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String position;
    private final String duties;

    public Period(LocalDate startDate, LocalDate endDate, String position, String duties) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.position = position;
        this.duties = duties;
}

    @Override
    public String toString() {
        return "Period{" + "\n" +
                "   startDate = " + startDate +
                " -- endDate = " + endDate + "\n" +
                "   position= " + position + "\n" +
                "   duties=" + duties + "\n" +
                '}';
    }
}
