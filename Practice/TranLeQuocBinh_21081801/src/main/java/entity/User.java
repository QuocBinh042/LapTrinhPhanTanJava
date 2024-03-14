package entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@Getter
@Setter
@ToString

@Table (name = "users")
public class User {
	@Id
	@Column (name = "user_id", unique = true, nullable = false)	
	private Integer user_id;
	@Column(columnDefinition = "nvarchar(45)",unique = true, nullable = false)
	private String username;
	@Column(columnDefinition = "nvarchar(45)")
	private String password;
	@Column(columnDefinition = "nvarchar(45)",unique = true)
	private String email;
	
	@ManyToMany
    @JoinTable(name = "users_groups",
    		joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> groups;
}
