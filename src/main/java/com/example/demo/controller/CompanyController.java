package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Company;
import com.example.demo.service.ServiceImplementations;

@RestController
@RequestMapping("company")
@CrossOrigin(origins = "http://localhost:4200")
public class CompanyController {

	private ServiceImplementations serimp;
	
	@Autowired
	public CompanyController(ServiceImplementations serimp) {
		this.serimp = serimp;
	}
	
	
	@GetMapping("/list")
	public List<Company> displayAllComp(){
		List<Company> compList = serimp.DisplayAllCompanies();
		return compList;
	}
	
	@GetMapping("/list/{id}")
	public Company displayCompById(@PathVariable("id") int id){
		Company comp = serimp.getCompanyById(id);
		return comp;
	}
	
	@PostMapping("/list")
	public void insertComp(@RequestBody Company comp) {
		serimp.insertCompany(comp);
		
	}

	@DeleteMapping("/list/{id}")
	public void DeleteComp(@PathVariable("id") int id) {
		serimp.deleteCompany(id);
	}
	
	@PutMapping("/list")
	public void updateComp(@RequestBody Company comp) {
		serimp.updateCompany(comp);
	}
	
	@GetMapping("/list1/{name}")
	public List<Company> searchByname(@PathVariable("name") String name){
		List<Company> clist = serimp.searchCompByName(name);
		return clist;
	}
}
