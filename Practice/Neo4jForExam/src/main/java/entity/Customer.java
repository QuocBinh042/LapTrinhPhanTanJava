package entity;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Customer implements Serializable {
	
	private static final long serialVersionUID = -6479744029830732683L;
	@SerializedName("customerID")
	private String id;
	private String companyName;
	private transient Address address;
	private transient Contact contact;
	
	private transient List<Order> orders;
	@Override
	public String toString() {
		return "Customer [id=" + id + ", companyName=" + companyName + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Customer(String id, String companyName, Address address, Contact contact) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.address = address;
		this.contact = contact;
	}
	public Customer(String id, String companyName) {
		super();
		this.id = id;
		this.companyName = companyName;
	}
	public Customer() {
		super();
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public Customer(String id, String companyName, Address address, Contact contact, List<Order> orders) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.address = address;
		this.contact = contact;
		this.orders = orders;
	}
}
