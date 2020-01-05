package com.maxkosh.webapp.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSection extends Section {
    private final List<String> stringList;

    public ListSection(String... stringList) {
        this(Arrays.asList(stringList));
    }

    public ListSection(List<String> stringList) {
        Objects.requireNonNull(stringList, "List must not be null");
        this.stringList = stringList;
    }

    public List<String> getStringList() {
        return stringList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return stringList.equals(that.stringList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stringList);
    }

    @Override
    public String toString() {
        return stringList.toString() + "\n";
    }
}
