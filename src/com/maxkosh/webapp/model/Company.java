package com.maxkosh.webapp.model;

import java.util.Date;
import java.util.Objects;

public class Company {
    String companyName;
    private String activePeriod;
    private String position;
    private String description;

    public Company(String companyName, String activePeriod, String position, String description) {
        this.companyName = companyName;
        this.activePeriod = activePeriod;
        this.position = position;
        this.description = description;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getActivePeriod() {
        return activePeriod;
    }

    public void setActivePeriod(String activePeriod) {
        this.activePeriod = activePeriod;
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
                activePeriod.equals(company.activePeriod) &&
                position.equals(company.position) &&
                description.equals(company.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyName, activePeriod, position, description);
    }

    @Override
    public String toString() {
        return "companyName = " + companyName + '\'' +
                ", activePeriod = " + activePeriod +
                ", position = " + position + '\'' +
                ", description = " + description + '\'';
    }
}
