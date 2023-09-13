package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

	@Query(value="from Company where cname= ?1")
	public List<Company> searchCompany(String cname);
}
