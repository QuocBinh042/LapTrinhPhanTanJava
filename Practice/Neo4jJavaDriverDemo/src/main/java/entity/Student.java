package entity;
//student_id	name	gpa
//11	Bush	3

public class Student {
	private String studentId;
	private String name;
	private float gpa;
	
	public Student() {
		super();
	}
	
	public Student(String studentId, String name, float gpa) {
		super();
		this.studentId = studentId;
		this.name = name;
		this.gpa = gpa;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getGpa() {
		return gpa;
	}

	public void setGpa(float gpa) {
		this.gpa = gpa;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", name=" + name + ", gpa=" + gpa + "]";
	}
	
	
}
