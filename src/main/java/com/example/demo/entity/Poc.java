package com.example.demo.entity;



import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Poc")
public class Poc {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="pid")
	private int pid;
	
	@Column(name="pname")
	private String pname;
	
	@Column(name="ptype")
	private String ptype;
	
	@Column(name="",length=50000000)
	private byte[] pic_byte;
	
	
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL )
	@JoinTable(name="pdesc_tab",
	joinColumns= {
			@JoinColumn(name="pid")
	},
			inverseJoinColumns = {
					@JoinColumn(name="pdid")
	})
	private Set<PocDes> pocdes;
	

	
	public int getPid() {
		return pid;
	}

	public Set<PocDes> getPocdes() {
		return pocdes;
	}

	public void setPocdes(Set<PocDes> pocdes) {
		this.pocdes = pocdes;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPtype() {
		return ptype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	public byte[] getPic_byte() {
		return pic_byte;
	}

	public void setPic_byte(byte[] pic_byte) {
		this.pic_byte = pic_byte;
	}

	public Poc() {
		//super();
		// TODO Auto-generated constructor stub
	}

	public Poc( String pname, String ptype, byte[] pic_byte, Set<PocDes> pocdes) {
		super();
		//this.pid = pid;
		this.pname = pname;
		this.ptype = ptype;
		this.pic_byte = pic_byte;
		this.pocdes = pocdes;
	}



	
}
