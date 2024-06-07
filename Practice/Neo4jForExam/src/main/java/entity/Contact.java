package entity;

public class Contact {
	private String contactTitle;
	private String phone;
	private String contactName;
	public String getContactTitle() {
		return contactTitle;
	}
	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	@Override
	public String toString() {
		return "Contact [contactTitle=" + contactTitle + ", phone=" + phone + ", contactName=" + contactName + "]";
	}
	public Contact(String contactTitle, String phone, String contactName) {
		super();
		this.contactTitle = contactTitle;
		this.phone = phone;
		this.contactName = contactName;
	}
	public Contact() {
		super();
	}
	
	
}
