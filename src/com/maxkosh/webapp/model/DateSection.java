package com.maxkosh.webapp.model;

import java.util.List;
import java.util.Objects;


public class DateSection extends Section {
    private Company company;
    private List<Company> companyList;

    public DateSection(List<Company> companyList, Company company) {
        this.companyList = companyList;
        this.companyList.add(company);
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Company> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateSection that = (DateSection) o;
        return company.equals(that.company) &&
                companyList.equals(that.companyList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(company, companyList);
    }

    @Override
    public String toString() {
        return "company = " + company + ", companyList = " + companyList;
    }
}
