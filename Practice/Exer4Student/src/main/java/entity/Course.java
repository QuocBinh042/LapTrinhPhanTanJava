package entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({ //JPQL
        @NamedQuery(name = "Course.findAll", query = "select c from Course c"),
        @NamedQuery(name = "Course.findByTitle", query = "select c from Course c where c.title like :title"),
        @NamedQuery(name = "Course.existsById", query = "select (count(c) > 0) from Course c where c.id = :id"),
        @NamedQuery(name="Course.findCoursesWithMaxCredits", query = "select c from Course c where c.credits = (select max(credits) from Course)")
})
public abstract class Course implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 8001296845123404462L;
	@Id
    @Column(name = "CourseID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @Column(name = "Title", unique = true, nullable = false)
    protected String title;
    protected int credits;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DepartmentID")
    protected Department department;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "CourseInstructor",
            joinColumns = @JoinColumn(name = "CourseID"),
            inverseJoinColumns = @JoinColumn(name = "PersonID")
    )
    protected Set<Instructor> instructors = new HashSet<>();

    public Course(String title, int credits) {
        this.title = title;
        this.credits = credits;
    }

    public Course() {
    }

    
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Instructor> getInstructors() {
		return instructors;
	}

	public void setInstructors(Set<Instructor> instructors) {
		this.instructors = instructors;
	}

	@Override
    public String toString() {
        return String.format("Course{id=%d, title='%s', credits=%d}", id, title, credits);
    }
}