package com.maxkosh.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Position {
    private final String positionTitle;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String description;

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
