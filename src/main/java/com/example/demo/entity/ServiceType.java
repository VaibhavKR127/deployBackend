package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "ServiceType")
public class ServiceType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sid")
	private int sid;
	
	@Column(name="sname")
	private String sname;

	@OneToMany(mappedBy = "service")
	private List<Test> test;

	public ServiceType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServiceType(int sid, String sname) {
		super();
		this.sid = sid;
		this.sname = sname;
	}
	
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	@Override
	public String toString() {
		return "ServiceType [sid=" + sid + ", sname=" + sname + "]";
	}

	
	
	
}
