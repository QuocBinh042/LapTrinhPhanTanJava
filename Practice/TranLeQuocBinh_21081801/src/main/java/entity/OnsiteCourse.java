package entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "OnsiteCourse")
public class OnsiteCourse {
	@Id
	@OneToOne
	@JoinColumn(name = "courseID")
	private Course course;
	@Column(name = "days", length = 100, columnDefinition = "nvarchar(25)", nullable = false)
	private String days;
	@Column(name = "location", length = 100, columnDefinition = "nvarchar(25)", nullable = false)
	private String location;
	@Column(name = "time", length = 100, nullable = false)
	private LocalDateTime time;

	public OnsiteCourse(String days, String location, LocalDateTime time) {
		super();
		this.days = days;
		this.location = location;
		this.time = time;
	}

}
