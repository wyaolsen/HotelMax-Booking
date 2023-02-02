package com.example.HotelBooking.DAO;

import com.example.HotelBooking.Models.Company;

import java.util.ArrayList;
import java.util.List;

public interface CompanyDao {

    public List<Company> getAllCompanies();

    public Company getCompanyById(int companyId);

    public Company getCompanyByName(String companyName);

    public void addCompany(Company newCompany);

    public void removeCompany(int companyId);
}
