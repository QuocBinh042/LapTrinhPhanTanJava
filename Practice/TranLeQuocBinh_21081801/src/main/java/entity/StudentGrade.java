package entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@Table(name = "StudentGrade")
public class StudentGrade {

	@Id
	@Column(name = "enrollmentID", nullable = false)
	private int enrollmentID;
	@Column(name = "grade",  nullable = false)
	private double grade;
	// có khối 1.5 nha cô
	// Nối
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "courseID")
	private Course course;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "personID")
	private Person person;

	public StudentGrade(int enrollmentID, double grade) {
		super();
		this.enrollmentID = enrollmentID;
		this.grade = grade;
	}

}
