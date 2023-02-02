package com.example.HotelBooking.DAO;

import com.example.HotelBooking.Models.Company;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcCompanyDao implements CompanyDao{

    private final JdbcTemplate jdbcTemplate;

    private JdbcCompanyDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Company> getAllCompanies() {
        String sql = "SELECT * FROM company;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        List<Company> companies = new ArrayList<Company>();
        while(results.next()){
            companies.add(mapRowToCompany(results));
        }
        return companies;
    }

    @Override
    public Company getCompanyById(int companyId) {
        String sql = "SELECT * FROM company WHERE company_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, companyId);

        return mapRowToCompany(results);
    }

    @Override
    public Company getCompanyByName(String companyName) {
        String sql = "SELECT * FROM company WHERE name = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, companyName);

        return mapRowToCompany(results);
    }

    @Override
    public void addCompany(Company newCompany) {
        String sql = "INSERT INTO company (name, description, website) VALUES (?, ?, ?) RETURNING *;";
        jdbcTemplate.queryForObject(sql, Long.class, newCompany.getName(), newCompany.getDescription(),newCompany.getWebsite() );

    }

    @Override
    public void removeCompany(int companyId) {
        String sql = "DELETE FROM company WHERE company_id = ? ;";
        jdbcTemplate.update(sql, companyId);
    }

    private Company mapRowToCompany(SqlRowSet rowSet){
        Company company = new Company();
        company.setCompanyId(rowSet.getInt("company_id"));
        company.setName(rowSet.getString("name"));
        company.setDescription(rowSet.getString("description"));
        company.setWebsite(rowSet.getString("website"));

        return company;
    }
}
