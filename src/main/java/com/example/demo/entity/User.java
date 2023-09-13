package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "User")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="uid")
	private int uid;
	
	@Column(name="uname")
	private String uname;
	
	@Column(name="upass")
	private String upass;
	
	@Column(name="secq")
	private String secq;
	
	@Column(name="secans")
	private String secans;
	
//	@OneToMany(mappedBy = "user")
//	private List<Test> tests;
	
	@OneToMany(mappedBy ="user")
	private List<Vulnerabilityfound> vuls;

	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}


	public String getUname() {
		return uname;
	}


	public void setUname(String uname) {
		this.uname = uname;
	}


	public String getUpass() {
		return upass;
	}


	public void setUpass(String upass) {
		this.upass = upass;
	}


	public String getSecq() {
		return secq;
	}


	public void setSecq(String secq) {
		this.secq = secq;
	}


	public String getSecans() {
		return secans;
	}


	public void setSecans(String secans) {
		this.secans = secans;
	}

	@JsonIgnore
	public List<Vulnerabilityfound> getVuls() {
		return vuls;
	}


	public void setVuls(List<Vulnerabilityfound> vuls) {
		this.vuls = vuls;
	}


	public User( String uname, String upass, String secq, String secans) {
		super();
		//this.uid = uid;
		this.uname = uname;
		this.upass = upass;
		this.secq = secq;
		this.secans = secans;
	}

	
	public User( String uname, String upass) {
		super();
		//this.uid = uid;
		this.uname = uname;
		this.upass = upass;
		
	}

	
	

}
