package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "groups")
public class Group {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "group_id")
	private int id;
	
	@Column(name = "name", unique = true, nullable = false)
	private String name;
//	
//	@ManyToMany(mappedBy = "groups")
//	private Set<User> users;
	
//	public void setUsers(Set<User> users) {
//		this.users = users;
//	}
//	
//	public Set<User> getUsers() {
//		return users;
//	}
	
	public Group() {
	}

	public Group(String name) {
		super();
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
