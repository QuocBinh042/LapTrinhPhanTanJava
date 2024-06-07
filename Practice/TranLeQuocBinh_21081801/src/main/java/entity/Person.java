package entity;

import java.util.LinkedHashSet;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Discriminator", discriminatorType = DiscriminatorType.STRING, length = 20)
public abstract class Person {
	@Id
	@Column(name = "PersonID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	@Column(name = "FirstName", columnDefinition = "nvarchar(100)", nullable = false)
	private String firstName;
	@Column(name = "LastName", columnDefinition = "nvarchar(100)", nullable = false)
	private String lastName;
	
	@ManyToMany
	@JoinTable(name = "CourseInstructor", joinColumns = @JoinColumn(name = "PersonID"),
	inverseJoinColumns = @JoinColumn(name = "CourseID"))
	private LinkedHashSet<Course> course;
	
	public Person(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
}
