package com.maxkosh.webapp.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.maxkosh.webapp.util.DateUtil.NOW;
import static com.maxkosh.webapp.util.DateUtil.of;

public class Company {
    private final Link homePage;
    private final List<Position> positions;

    public Company(String companyName, String url, Position... positions) {
        this(new Link(companyName, url), Arrays.asList(positions));
    }

    public Company(Link homePage, List<Position> positions) {
        this.homePage = homePage;
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
        return Objects.equals(homePage, company.homePage) &&
                Objects.equals(positions, company.positions);
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

    public static class Position {
        private final String positionTitle;
        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String description;

        public Position(int startYear, Month startMonth, String positionTitle, String description) {
            this(positionTitle, of(startYear, startMonth), NOW, description);
        }

        public Position(int startYear, int endYear, Month startMonth, Month endMonth, String positionTitle, String description) {
            this(positionTitle, of(startYear, startMonth), of(endYear, endMonth), description);
        }

        public Position(String positionTitle, LocalDate startDate, LocalDate endDate, String description) {
            Objects.requireNonNull(positionTitle, "positionTitle must not be null");
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(endDate, "endDate must not be null");
            this.positionTitle = positionTitle;
            this.startDate = startDate;
            this.endDate = endDate;
            this.description = description;
        }

        public String getPositionTitle() {
            return positionTitle;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return positionTitle.equals(position.positionTitle) &&
                    startDate.equals(position.startDate) &&
                    endDate.equals(position.endDate) &&
                    Objects.equals(description, position.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(positionTitle, startDate, endDate, description);
        }

        @Override
        public String toString() {
            return "positionTitle = " + positionTitle +
                    "\nstartDate = " + startDate +
                    "\nendDate = " + endDate +
                    "\ndescription = " + description + "\n";
        }
    }
}
