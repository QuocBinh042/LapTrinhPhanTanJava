package Json_ObjectModelAPI_Patient.entity;

public class Testt {
	private Datee date;
	private String result;
	private Integer test_id;
	private String test_type;
	private Double cost;

	public Testt(Datee date, String result, Integer test_id, String test_type, Double cost) {
		super();
		this.date = date;
		this.result = result;
		this.test_id = test_id;
		this.test_type = test_type;
		this.cost = cost;
	}

	public Testt() {
		super();
	}

	public Datee getDate() {
		return date;
	}

	public void setDate(Datee date) {
		this.date = date;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Integer getTest_id() {
		return test_id;
	}

	public void setTest_id(Integer test_id) {
		this.test_id = test_id;
	}

	public String getTest_type() {
		return test_type;
	}

	public void setTest_type(String test_type) {
		this.test_type = test_type;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Tests [date=" + date + ", result=" + result + ", test_id=" + test_id + ", test_type=" + test_type
				+ ", cost=" + cost + "]";
	}

}
