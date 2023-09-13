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
import com.example.demo.entity.ServiceType;
import com.example.demo.service.ServiceImplementations;

@RestController
@RequestMapping("servicetype")
@CrossOrigin(origins = "http://localhost:4200")
public class ServiceTypeController {

private ServiceImplementations serimp;
	
	@Autowired
	public ServiceTypeController(ServiceImplementations serimp) {
		this.serimp = serimp;
	}
	
	
	@GetMapping("/list")
	public List<ServiceType> displayAllComp(){
		List<ServiceType> stypeList = serimp.DisplayAllServices();
		return stypeList;
	}
	
	@GetMapping("/list/{id}")
	public ServiceType displayStypeById(@PathVariable("id") int id){
		ServiceType stype = serimp.getServiceById(id);
		return stype;
	}
	
	@PostMapping("/list")
	public void insertStype(@RequestBody ServiceType stype) {
		serimp.insertService(stype);
		
	}

	@DeleteMapping("/list/{id}")
	public void DeleteStype(@PathVariable("id") int id) {
		serimp.deleteService(id);
	}
	
	@PutMapping("/list")
	public void updateStype(@RequestBody ServiceType stype) {
		serimp.updateService(stype);
	}
	
	
}
