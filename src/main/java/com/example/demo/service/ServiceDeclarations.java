package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Company;
import com.example.demo.entity.ServiceType;
import com.example.demo.entity.Test;
import com.example.demo.entity.User;
import com.example.demo.entity.VulnerabilitySet;
import com.example.demo.entity.Vulnerabilityfound;

public interface ServiceDeclarations {

	public List<Company> DisplayAllCompanies();
	public Company getCompanyById(int id);
	public void insertCompany(Company company);
	public void deleteCompany(int id);
	public void updateCompany(Company company);
	public List<Company> searchCompByName(String cname);
	
	public List<ServiceType> DisplayAllServices();
	public ServiceType getServiceById(int id);
	public void insertService(ServiceType stype);
	public void deleteService(int id);
	public void updateService(ServiceType stype);
	
	public List<Test> DisplayAllTests();
	public Test getTestById(int id);
	public void insertTest(Test test);
	public void deleteTest(int id);
	public void updateTest(Test test);
	public List<Vulnerabilityfound> searchByTid(int tid);
	
	
	public List<Vulnerabilityfound> DisplayAllVulfound();
	public Vulnerabilityfound getVulfoundById(int id);
	public void insertVulfound(Vulnerabilityfound vfound);
	public void deleteVulfound(int id);
	public void updateVulfound(Vulnerabilityfound vfound);
	
	
	public List<VulnerabilitySet> DisplayAllVulSet();
	public VulnerabilitySet getVulSetById(int id);
	public void insertVulSet(VulnerabilitySet vset);
	public void deleteVulSet(int id);
	public void updateVulSet(VulnerabilitySet vset);
	public VulnerabilitySet searchVulByname(String vname);
	
	
	public List<User> DisplayAllUsers();
	public User getUserById(int id);
	public void insertUser(User user);
	public void deleteUser(int id);
	public void updateUser(User user);
	public User getUserByName(String uname);
}
