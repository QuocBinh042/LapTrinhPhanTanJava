package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;

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
@Table(name = "Department")
public class Department {
	@Id
	@Column(name = "departmentID", nullable = false)
	private int departmentID;
	@Column(name = "administrator", nullable = false)
	private int administrator;
	@Column(name = "budget", nullable = false)
	private double budget;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "startDate", nullable = false)
	private LocalDateTime startDate;
	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	private List<Course> listCourse = new ArrayList<>();

	public Department(int departmentID, int administrator, double budget, String name, LocalDateTime startDate) {
		super();
		this.departmentID = departmentID;
		this.administrator = administrator;
		this.budget = budget;
		this.name = name;
		this.startDate = startDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(departmentID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		return departmentID == other.departmentID;
	}

}
