package entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Student extends Person{
	@Column(name = "EnrollmentDate")
	private LocalDate enrollmentDate;
	
	public Student(String firstName, String lastName, LocalDate enrollmentDate) {
		super(firstName, lastName);
		this.enrollmentDate = enrollmentDate;
		// TODO Auto-generated constructor stub
	}
	
}
