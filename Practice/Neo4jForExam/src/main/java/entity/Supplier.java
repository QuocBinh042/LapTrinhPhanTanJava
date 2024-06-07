package entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Supplier implements Serializable{
	private static final long serialVersionUID = -6479744029830732683L;
	@SerializedName("supplierID")
	private String supplierID;
	@SerializedName("supplierName")
	private String name;
	
	private String companyName;
	private transient Address address;
	private transient Contact contact;
	public String getId() {
		return supplierID;
	}
	public void setId(String id) {
		this.supplierID = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	@Override
	public String toString() {
		return "Supplier [id=" + supplierID + ", name=" + name + ", companyName=" + companyName + ", address=" + address
				+ ", contact=" + contact + "]";
	}
	public Supplier(String id, String name, String companyName, Address address, Contact contact) {
		super();
		this.supplierID = id;
		this.name = name;
		this.companyName = companyName;
		this.address = address;
		this.contact = contact;
	}
	public Supplier() {
		super();
	}
	public Supplier(String id, String name, String companyName) {
		super();
		this.supplierID = id;
		this.name = name;
		this.companyName = companyName;
	}
	public Supplier(String id, String companyName) {
		super();
		this.supplierID = id;
		this.companyName = companyName;
	}
	public Supplier(String supplierID) {
		super();
		this.supplierID = supplierID;
	}
	
}
