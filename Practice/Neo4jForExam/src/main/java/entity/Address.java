package entity;

public class Address {
	private String address;
	private String region;
	private String postalCode;
	private String city;
	private String fax;
	private String country;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "Address [address=" + address + ", region=" + region + ", postalCode=" + postalCode + ", city=" + city
				+ ", fax=" + fax + ", country=" + country + "]";
	}
	public Address(String address, String region, String postalCode, String city, String fax, String country) {
		super();
		this.address = address;
		this.region = region;
		this.postalCode = postalCode;
		this.city = city;
		this.fax = fax;
		this.country = country;
	}
	public Address() {
		super();
	}
	
	
}
