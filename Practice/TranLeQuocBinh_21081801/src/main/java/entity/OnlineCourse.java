package entity;

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
@Table(name = "OnlineCourse")
public class OnlineCourse {

	@Id
	@OneToOne
	@JoinColumn(name = "courseID")
	private Course course;
	@Column(name = "url", columnDefinition = "nvarchar(25)", nullable = false)
	private String url;

	public OnlineCourse(String url) {
		super();
		this.url = url;
	}

}
