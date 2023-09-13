package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "Test")
public class Test {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="tid")
	private int tid;
	
	@Column(name = "cid")
	private int cid;

	@ManyToOne(targetEntity = Company.class,fetch = FetchType.EAGER)
	@JoinColumn(name="cid",insertable = false,updatable = false)
	private Company company;
	
	@Column(name = "sid")
	private int sid;
	
	@ManyToOne(targetEntity = ServiceType.class,fetch = FetchType.EAGER)
    @JoinColumn(name="sid",insertable = false,updatable = false)
    private ServiceType service;
	
	@OneToMany(mappedBy = "test",cascade = CascadeType.REMOVE)
	private List<Vulnerabilityfound> vulnerabilities;
	
	public Test(int tid, int cid, int sid,
			List<Vulnerabilityfound> vulnerabilities) {
		super();
		this.tid = tid;
		this.cid = cid;
		this.sid = sid;
		this.vulnerabilities = vulnerabilities;
	}
	
	public Test( int cid, int sid) {
		super();
		this.cid = cid;
		this.sid = sid;
	}
	
	
	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}


	public ServiceType getService() {
		return service;
	}


	public void setService(ServiceType service) {
		this.service = service;
	}


	public Test() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}


	public List<Vulnerabilityfound> getVulnerabilities() {
		return vulnerabilities;
	}

	public void setVulnerabilities(List<Vulnerabilityfound> vulnerabilities) {
		this.vulnerabilities = vulnerabilities;
	}

	
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}
	
	
	@Override
	public String toString() {
		return "Test [tid=" + tid + ", cid=" + cid + ", company=" + company + ", sid=" + sid + 
				 ", vulnerabilities=" + vulnerabilities + "]";
	}
	
	
	
	
	
	
	
}
