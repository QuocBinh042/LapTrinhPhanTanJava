package entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "StudentGrade")
public class StudentGrade implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1927517864283489179L;

	@Id
    @Column(name = "EnrollmentID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int enrollmentID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StudentID")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CourseID")
    private Course course;
    private double grade;

    public StudentGrade(Student student, Course course, double grade) {
        this.student = student;
        this.course = course;
        this.grade = grade;
    }

    public StudentGrade() {
    }

	public int getEnrollmentID() {
		return enrollmentID;
	}

	public void setEnrollmentID(int enrollmentID) {
		this.enrollmentID = enrollmentID;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "StudentGrade [enrollmentID=" + enrollmentID + ", student=" + student.getId() + ", course=" + course.getId() + ", grade="
				+ grade + "]";
	}
    
    
}