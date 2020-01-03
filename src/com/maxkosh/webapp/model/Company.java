package com.maxkosh.webapp.model;

import java.util.List;
import java.util.Objects;

public class Company {
    private final Link homePage;
    private List<Position> positions;
    private final String description;

    public Company(String companyName, String url, List<Position> positions, String description) {
        Objects.requireNonNull(positions, "Position must not be null");
        this.homePage = new Link(companyName, url);
        this.positions = positions;
        this.description = description;
    }

    public Link getHomePage() {
        return homePage;
    }

    public List<Position> getPositions() {
        return positions;
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
                positions.equals(company.positions) &&
                Objects.equals(description, company.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, positions, description);
    }

    @Override
    public String toString() {
        return "homePage = " + homePage +
                "\npositions = " + positions +
                "\ndescription = " + description;
    }
}
