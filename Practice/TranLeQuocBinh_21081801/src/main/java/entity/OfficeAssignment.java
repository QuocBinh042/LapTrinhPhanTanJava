package entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "OfficeAssignment")
public class OfficeAssignment {
	
	@Column(name = "Location", columnDefinition = "nvarchar(100)")
	private String location;
	@Column(name = "Timestamp")
	private Timestamp timestamp;
	
	@Id
	@OneToOne
	@JoinColumn(name = "InstructorID")
	private Instructor instructor;
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public OfficeAssignment(String location, Timestamp timestamp) {
		super();
		this.location = location;
		this.timestamp = timestamp;
	}
	
	public OfficeAssignment() {
		super();
	}
}
