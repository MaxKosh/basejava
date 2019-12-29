package com.maxkosh.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Company {
    private String companyName;
    private String url;
    private LocalDate startDate;
    private LocalDate endDate;
    private String position;
    private String description;

    public Company(String companyName, LocalDate startDate, LocalDate endDate, String position, String description) {
        this.companyName = companyName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.position = position;
        this.description = description;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
                description.equals(company.description);
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
