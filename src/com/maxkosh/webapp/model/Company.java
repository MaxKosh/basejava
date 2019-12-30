package com.maxkosh.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Company {
    private final String companyName;
    private final String url;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String position;
    private final String description;

    public Company(String companyName, String url, LocalDate startDate, LocalDate endDate, String position, String description) {
        Objects.requireNonNull(companyName, "CompanyName must not be null");
        Objects.requireNonNull(startDate, "StartDate must not be null");
        Objects.requireNonNull(endDate, "EndDate must not be null");
        Objects.requireNonNull(position, "Position must not be null");
        this.companyName = companyName;
        this.url = url;
        this.startDate = startDate;
        this.endDate = endDate;
        this.position = position;
        this.description = description;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getUrl() {
        return url;
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
        return companyName.equals(company.companyName) &&
                Objects.equals(url, company.url) &&
                startDate.equals(company.startDate) &&
                endDate.equals(company.endDate) &&
                position.equals(company.position) &&
                Objects.equals(description, company.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyName, url, startDate, endDate, position, description);
    }

    @Override
    public String toString() {
        return "COMPANY: " +
                "\ncompanyName = " + companyName +
                "\nwebsite = " + url +
                "\nstartDate = " + startDate +
                "\nendDate = " + endDate +
                "\nposition = " + position +
                "\ndescription = " + description + "\n";
    }
}
