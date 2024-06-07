package entity;

public class Enrollment {
	private Student student;
	private Course course;
	
	public Enrollment(Student student, Course course) {
		this.student = student;
		this.course = course;
	}
	
	public Enrollment() {
	}
	
	public Student getStudent() {
		return student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Enrollment [student=" + student + ", course=" + course + "]";
	}
	
	
}
