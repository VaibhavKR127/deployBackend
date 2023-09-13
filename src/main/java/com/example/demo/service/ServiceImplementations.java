package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Company;
import com.example.demo.entity.ServiceType;
import com.example.demo.entity.Test;
import com.example.demo.entity.User;
import com.example.demo.entity.VulnerabilitySet;
import com.example.demo.entity.Vulnerabilityfound;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.ServiceTypeRepository;
import com.example.demo.repository.TestRepository;
import com.example.demo.repository.UserRepo;
import com.example.demo.repository.VulnerabilitySetRepository;
import com.example.demo.repository.VulnerabilityfoundRepository;

import jakarta.transaction.Transactional;

@Service
public class ServiceImplementations implements ServiceDeclarations{

	private CompanyRepository crep;
	private ServiceTypeRepository strep;
	private TestRepository trep;
	private VulnerabilityfoundRepository vfrep;
	private VulnerabilitySetRepository vsrep;
	
	@Autowired
	private UserRepo urep;
	
	@Autowired
	public ServiceImplementations(CompanyRepository crep, ServiceTypeRepository strep, TestRepository trep,
			VulnerabilityfoundRepository vfrep, VulnerabilitySetRepository vsrep) {
		super();
		this.crep = crep;
		this.strep = strep;
		this.trep = trep;
		this.vfrep = vfrep;
		this.vsrep = vsrep;
	}
	
	
	@Override
	@Transactional
	public List<Company> DisplayAllCompanies(){
		return crep.findAll();
	}


	@Override
	@Transactional
	public Company getCompanyById(int id) {
		// TODO Auto-generated method stub
		return crep.findById(id).get();
	}


	@Override
	@Transactional
	public void insertCompany(Company company) {
		crep.save(company);
		
	}


	@Override
	@Transactional
	public void deleteCompany(int id) {
		crep.deleteById(id);
		
	}


	@Override
	@Transactional
	public void updateCompany(Company company) {
		crep.save(company);
		
	}


	@Override
	@Transactional
	public List<ServiceType> DisplayAllServices() {
		
		return strep.findAll();
	}


	@Override
	@Transactional
	public ServiceType getServiceById(int id) {
		// TODO Auto-generated method stub
		return strep.findById(id).get();
	}


	@Override
	@Transactional
	public void insertService(ServiceType stype) {
		// TODO Auto-generated method stub
		strep.save(stype);
	}


	@Override
	@Transactional
	public void deleteService(int id) {
		// TODO Auto-generated method stub
		strep.deleteById(id);
	}


	@Override
	@Transactional
	public void updateService(ServiceType stype) {
		// TODO Auto-generated method stub
		strep.save(stype);
	}


	@Override
	@Transactional
	public List<Test> DisplayAllTests() {
		// TODO Auto-generated method stub
		return trep.findAll();
	}


	@Override
	@Transactional
	public Test getTestById(int id) {
		// TODO Auto-generated method stub
		return trep.findById(id).get();
	}


	@Override
	@Transactional
	public void insertTest(Test test) {
		// TODO Auto-generated method stub
		trep.save(test);
	}


	@Override
	@Transactional
	public void deleteTest(int id) {
		// TODO Auto-generated method stub
		trep.deleteById(id);
	}


	@Override
	@Transactional
	public void updateTest(Test test) {
		// TODO Auto-generated method stub
		trep.save(test);
	}


	@Override
	@Transactional
	public List<Vulnerabilityfound> DisplayAllVulfound() {
		// TODO Auto-generated method stub
		return vfrep.findAll();
	}


	@Override
	@Transactional
	public Vulnerabilityfound getVulfoundById(int id) {
		// TODO Auto-generated method stub
		return vfrep.findById(id).get();
	}


	@Override
	@Transactional
	public void insertVulfound(Vulnerabilityfound vfound) {
		// TODO Auto-generated method stub
		vfrep.save(vfound);
	}


	@Override
	@Transactional
	public void deleteVulfound(int id) {
		// TODO Auto-generated method stub
		vfrep.deleteById(id);
	}


	@Override
	@Transactional
	public void updateVulfound(Vulnerabilityfound vfound) {
		// TODO Auto-generated method stub
		vfrep.save(vfound);
	}


	@Override
	@Transactional
	public List<VulnerabilitySet> DisplayAllVulSet() {
		// TODO Auto-generated method stub
		return vsrep.findAll();
	}


	@Override
	@Transactional
	public VulnerabilitySet getVulSetById(int id) {
		// TODO Auto-generated method stub
		return vsrep.findById(id).get();
	}


	@Override
	@Transactional
	public void insertVulSet(VulnerabilitySet vset) {
		// TODO Auto-generated method stub
		vsrep.save(vset);
	}


	@Override
	@Transactional
	public void deleteVulSet(int id) {
		// TODO Auto-generated method stub
		vsrep.deleteById(id);
	}


	@Override
	@Transactional
	public void updateVulSet(VulnerabilitySet vset) {
		// TODO Auto-generated method stub
		vsrep.save(vset);
	}


	@Override
	public List<Company> searchCompByName(String cname) {
		// TODO Auto-generated method stub
		return crep.searchCompany(cname);
	}


	@Override
	@Transactional
	public List<Vulnerabilityfound> searchByTid(int tid) {
		// TODO Auto-generated method stub
		return vfrep.searchByTid(tid);
	}

	
	@Override
	@Transactional
	public VulnerabilitySet searchVulByname(String vname) {
		// TODO Auto-generated method stub
		return vsrep.searchVulByname(vname);
	}
	
	
	@Transactional
	public List<Vulnerabilityfound> sortVul(List<Vulnerabilityfound> vfound) {
		// TODO Auto-generated method stub
		List<Vulnerabilityfound> unsortVfound = vfound;
		List<Vulnerabilityfound> sortedVfound = vfound;
		for(int k=0;k<unsortVfound.size();k++) {
			Vulnerabilityfound vf = unsortVfound.get(k);
			if(vf.getSeverity().equalsIgnoreCase("Critical")) {
				sortedVfound.add(vf);
				unsortVfound.remove(vf);
			}
		}
		for(int k=0;k<unsortVfound.size();k++) {
			Vulnerabilityfound vf = unsortVfound.get(k);
			if(vf.getSeverity().equalsIgnoreCase("High")) {
				sortedVfound.add(vf);
				unsortVfound.remove(vf);
			}
		}
		for(int k=0;k<unsortVfound.size();k++) {
			Vulnerabilityfound vf = unsortVfound.get(k);
			if(vf.getSeverity().equalsIgnoreCase("Medium")) {
				sortedVfound.add(vf);
				unsortVfound.remove(vf);
			}
		}
		for(int k=0;k<unsortVfound.size();k++) {
			Vulnerabilityfound vf = unsortVfound.get(k);
			if(vf.getSeverity().equalsIgnoreCase("Low")) {
				sortedVfound.add(vf);
				unsortVfound.remove(vf);
			}
		}
		for(int k=0;k<unsortVfound.size();k++) {
			Vulnerabilityfound vf = unsortVfound.get(k);
			if(vf.getSeverity().equalsIgnoreCase("Informational")) {
				sortedVfound.add(vf);
				unsortVfound.remove(vf);
			}
		}
		
		return sortedVfound;
		
	}
	
	@Override
	@Transactional
	public List<User> DisplayAllUsers() {
		List<User> ulist = urep.findAll();
		return ulist;
	}


	@Override
	@Transactional
	public User getUserById(int id) {
		User user = urep.findById(id).get();
		return user;
	}


	@Override
	@Transactional
	public void insertUser(User user) {
		
		
		urep.save(user);
		
	}


	@Override
	@Transactional
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		urep.deleteById(id);
	}


	@Override
	@Transactional
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		urep.save(user);
	}

	
	@Override
	public User getUserByName(String uname) {
		// TODO Auto-generated method stub
		User user = urep.searchUserByname(uname);
		return user;
	}
	
}
