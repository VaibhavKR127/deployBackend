package com.example.demo.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="PocDes")
public class PocDes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="pid")
	private int pdid;
	
	@Column(name="pdesc",length=500000)
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private String pdesc;

	public int getPdid() {
		return pdid;
	}

	public void setPdid(int pdid) {
		this.pdid = pdid;
	}

	public String getPdesc() {
		return pdesc;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	public PocDes(@JsonProperty("pdesc")String pdesc) {
		super();
		//this.pdid = pdid;
		this.pdesc = pdesc;
	}

	public PocDes() {
		//super();
		// TODO Auto-generated constructor stub
	}
	
	

	
}
