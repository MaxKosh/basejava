package com.maxkosh.webapp.model;

import java.util.Objects;

public class Link {
    private final String companyName;
    private final String url;

    public Link(String companyName, String url) {
        Objects.requireNonNull(companyName, "Company name must not be null");
        this.companyName = companyName;
        this.url = url;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return companyName.equals(link.companyName) &&
                Objects.equals(url, link.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyName, url);
    }

    @Override
    public String toString() {
        return "companyName = " + companyName +
                "\nurl = " + url;
    }
}