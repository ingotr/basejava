package com.basejava.webapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Period implements Serializable {
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getPosition() {
        return position;
    }

    public String getDuties() {
        return duties;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return Objects.equals(startDate, period.startDate) && Objects.equals(endDate, period.endDate)
                && Objects.equals(position, period.position) && Objects.equals(duties, period.duties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, position, duties);
    }
}
