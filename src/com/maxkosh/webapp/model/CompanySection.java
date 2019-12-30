package com.maxkosh.webapp.model;

import java.util.List;
import java.util.Objects;


public class CompanySection extends Section {
    private final List<Company> companyList;

    public CompanySection(List<Company> companyList) {
        Objects.requireNonNull(companyList, "List must not be null");
        this.companyList = companyList;
    }

    public List<Company> getCompanyList() {
        return companyList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanySection that = (CompanySection) o;
        return companyList.equals(that.companyList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyList);
    }

    @Override
    public String toString() {
        return "\n" + companyList.toString() + "\n";
    }
}
