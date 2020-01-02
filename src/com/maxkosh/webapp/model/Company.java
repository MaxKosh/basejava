package com.maxkosh.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Company {
    private final Link homePage;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String position;
    private final String description;

    public Company(String companyName, String url, LocalDate startDate, LocalDate endDate, String position, String description) {
        Objects.requireNonNull(startDate, "StartDate must not be null");
        Objects.requireNonNull(endDate, "EndDate must not be null");
        Objects.requireNonNull(position, "Position must not be null");
        this.homePage = new Link(companyName, url);
        this.startDate = startDate;
        this.endDate = endDate;
        this.position = position;
        this.description = description;
    }

    public Company(LocalDate startDate, LocalDate endDate, String position, String description) {
        Objects.requireNonNull(startDate, "StartDate must not be null");
        Objects.requireNonNull(endDate, "EndDate must not be null");
        Objects.requireNonNull(position, "Position must not be null");
        this.homePage = null;
        this.startDate = startDate;
        this.endDate = endDate;
        this.position = position;
        this.description = description;
    }

    public Link getHomePage() {
        return homePage;
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

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return homePage.equals(company.homePage) &&
                startDate.equals(company.startDate) &&
                endDate.equals(company.endDate) &&
                position.equals(company.position) &&
                Objects.equals(description, company.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, startDate, endDate, position, description);
    }

    @Override
    public String toString() {
        return "COMPANY: " +
                "\nhomepage = " + homePage +
                "\nstartDate = " + startDate +
                "\nendDate = " + endDate +
                "\nposition = " + position +
                "\ndescription = " + description + "\n";
    }
}
