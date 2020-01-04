package com.maxkosh.webapp.model;

import java.util.List;
import java.util.Objects;

public class Company {
    private final Link homePage;
    private final List<Position> positions;

    public Company(String companyName, String url, List<Position> positions) {
        Objects.requireNonNull(positions, "Position must not be null");
        this.homePage = new Link(companyName, url);
        this.positions = positions;
    }

    public Link getHomePage() {
        return homePage;
    }

    public List<Position> getPositions() {
        return positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return homePage.equals(company.homePage) &&
                positions.equals(company.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, positions);
    }

    @Override
    public String toString() {
        return "homePage = " + homePage +
                "\npositions = " + positions + "\n";
    }
}
