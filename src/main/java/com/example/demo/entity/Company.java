package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Company")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cid")
	private int cid;
	
	@Column(name="cname")
	private String cname;
	
	@OneToMany(mappedBy = "company")
	private List<Test> tests;
	
	public Company(int cid, String cname) {
		super();
		this.cid = cid;
		this.cname = cname;
	}
	
	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	/*public List<Test> getTests() {
		return tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}*/

	@Override
	public String toString() {
		return "Company [cid=" + cid + ", cname=" + cname + ", tests=" + tests + "]";
	}
	
	
	
	
	
}
