package entity;

import java.util.List;

public class Patient {
	private String id;
	private String firstName;
	private String lastName;
	private String bloodType;
	private String gender;
	private Address address;
	private List<String> telephones;
	private List<Testt> test;
	private Integer year_of_birth;

	public Patient() {
		super();
	}

	public Patient(String id, String firstName, String lastName, String bloodType, String gender, Address address,
			List<String> telephones, List<Testt> test, Integer year_of_birth) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.bloodType = bloodType;
		this.gender = gender;
		this.address = address;
		this.telephones = telephones;
		this.test = test;
		this.year_of_birth = year_of_birth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<String> getTelephones() {
		return telephones;
	}

	public void setTelephones(List<String> telephones) {
		this.telephones = telephones;
	}

	public List<Testt> getTest() {
		return test;
	}

	public void setTest(List<Testt> test) {
		this.test = test;
	}

	public Integer getYear_of_birth() {
		return year_of_birth;
	}

	public void setYear_of_birth(Integer year_of_birth) {
		this.year_of_birth = year_of_birth;
	}

	@Override
	public String toString() {
		return "BenhNhan [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", bloodType=" + bloodType
				+ ", gender=" + gender + ", address=" + address + ", telephones=" + telephones + ", test=" + test
				+ ", year_of_birth=" + year_of_birth + "]";
	}

}
