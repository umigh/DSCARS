package edu.gatech.omscs.dscars.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Contact", catalog = "DSCARS")
public class Contact implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int gtid;
	private String firstName;
	private String lastName;
	private String email;
	
	public Contact(int gtid, String firstName, String lastName,String email) {
		super();
		this.gtid = gtid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email=email;
	}


	public Contact() {
	}


	@Id
	@Column(name = "GTID", unique = true, nullable = false)
	public int getGtid() {
		return gtid;
	}



	public void setGtid(int gtid) {
		this.gtid = gtid;
	}


	@Column(name = "firtName", nullable = false)
	public String getFirstName() {
		return firstName;
	}


	@Column(name = "lastName", nullable = false)
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	@Column(name = "lastName", nullable = false)
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
