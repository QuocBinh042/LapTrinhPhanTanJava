package entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Instructor extends Person{
	@Column(name = "HireDate")
	private LocalDate hireDate;
	
	public Instructor(String firstName, String lastName, LocalDate hireDate) {
		super(firstName, lastName);
		// TODO Auto-generated constructor stub
		this.hireDate = hireDate;
	}
	
}
