package com.basejava.webapp.model;

import com.basejava.webapp.util.LocalDateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import static com.basejava.webapp.util.DateUtil.NOW;

@XmlAccessorType(XmlAccessType.FIELD)
public class Period implements Serializable {

    public static final Period EMPTY = new Period();
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate startDate;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate endDate;
    private String position;
    private String duties;

    public Period() {
    }

    public Period(LocalDate startDate, String position, String duties) {
        this.startDate = startDate;
        this.endDate = NOW;
        this.position = position;
        this.duties = duties;
    }

    public Period(LocalDate startDate, LocalDate endDate, String position, String duties) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.position = position;
        this.duties = duties == null ? "" : duties;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
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
