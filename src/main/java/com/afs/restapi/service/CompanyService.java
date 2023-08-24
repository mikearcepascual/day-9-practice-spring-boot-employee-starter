package com.afs.restapi.service;

import com.afs.restapi.entity.Company;
import com.afs.restapi.exception.CompanyNotFoundException;
import com.afs.restapi.repository.CompanyRepository;
import com.afs.restapi.repository.EmployeeRepository;
import com.afs.restapi.entity.Employee;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {


    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

    public CompanyService(CompanyRepository companyRepository, EmployeeRepository employeeRepository) {

        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public List<Company> findByPage(Integer pageNumber, Integer pageSize) {
        return companyRepository.findAll(PageRequest.of(pageNumber - 1, pageSize)).toList();
    }

    public Company findById(Long id) {
        return companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
    }

    public void update(Long id, Company company) {
        Company toBeUpdatedCompany = findById(id);
        toBeUpdatedCompany.setName(company.getName());
        companyRepository.save(toBeUpdatedCompany);
    }

    public Company create(Company company) {
        return companyRepository.save(company);
    }

    public List<Employee> findEmployeesByCompanyId(Long id) {
        return employeeRepository.findByCompanyId(id);
    }

    public void delete(Long id) {
        companyRepository.deleteById(id);
    }
}
