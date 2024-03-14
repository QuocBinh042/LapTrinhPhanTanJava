package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "enrollments")

public class Enrollment {
	@Id
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;
	@Id
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	@Id
	private String semester;
	@Id
	private int year;
	
	@EqualsAndHashCode.Exclude
	private int score;

//	@Override
//	public int hashCode() {
//		return Objects.hash(course, semester, student, year);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Enrollments other = (Enrollments) obj;
//		return Objects.equals(course, other.course) && Objects.equals(semester, other.semester)
//				&& Objects.equals(student, other.student) && year == other.year;
//	}
	
	
	
}

// Single primary key
// Composite primary key --> must be: HashCode and equals
