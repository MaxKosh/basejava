package com.maxkosh.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends Section {
    private String text;
    private List<String> stringList;

    public ListSection(List<String> stringList, String text) {
        this.text = text;
        this.stringList = stringList;
        this.stringList.add(text);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return text.equals(that.text) &&
                stringList.equals(that.stringList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, stringList);
    }

    @Override
    public String toString() {
        return "text = " + text + '\'' +
                ", stringList = " + stringList;
    }
}
