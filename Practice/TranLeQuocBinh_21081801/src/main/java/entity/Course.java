package entity;

import java.util.*;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "Course")
public class Course {

	@Id
	@Column(name = "courseID", nullable = false)
	private int courseID;
	@Column(name = "credits", nullable = false)
	private int credits;
	@Column(name = "title", columnDefinition = "nvarchar(25)", nullable = false)
	private String title;
	@OneToOne(mappedBy = "course", cascade = CascadeType.ALL)
	private OnlineCourse onlineCourse;
	@OneToOne(mappedBy = "course", cascade = CascadeType.ALL)
	private OnsiteCourse onsiteCourse;
	@ManyToOne
	@JoinColumn(name = "departmentID")
	private Department department;

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private List<StudentGrade> listStudentGrade = new ArrayList<>();

	public Course(int courseID, int credits, String title) {
		super();
		this.courseID = courseID;
		this.credits = credits;
		this.title = title;
	}

	@Override
	public int hashCode() {
		return Objects.hash(courseID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return courseID == other.courseID;
	}

}
