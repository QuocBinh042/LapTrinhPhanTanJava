package dhkhmt17ctt.entity;

public class PhoneNumber {
	private String type;
	private String number;
	public PhoneNumber(String type, String number) {
		super();
		this.type = type;
		this.number = number;
	}
	
	public PhoneNumber() {
		// TODO Auto-generated constructor stub
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "PhoneNumber [type=" + type + ", number=" + number + "]";
	}
	
	
}
