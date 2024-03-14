package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString

@Table(name = "groups")
public class Group {
	@Id
	@Column(name = "group_id", unique = true, nullable = false)
	private Integer group_id;
	@Column(columnDefinition = "nvarchar(45)")
	private String name;
	
	
}
