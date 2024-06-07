package entity;
//dept_id	name	dean	building	room
//CS	Computer Science	Rubio	Ajax	100

public class Department {
	private String deptId;
	private String name;
	private String dean;
	private String building;
	private String room;

	public Department() {
		super();
	}

	public Department(String deptId, String name, String dean, String building, String room) {
		super();
		this.deptId = deptId;
		this.name = name;
		this.dean = dean;
		this.building = building;
		this.room = room;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDean() {
		return dean;
	}

	public void setDean(String dean) {
		this.dean = dean;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", name=" + name + ", dean=" + dean + ", building=" + building
				+ ", room=" + room + "]";
	}
}
