package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("Student")
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "select s from Student s"),
    @NamedQuery(name = "Student.findByEnrollmentYear", query = "select s from Student s where year(s.enrollmentDate) = :year"),
    @NamedQuery(name = "Student.findByEnrollmentCourse", 
    			query = "select s from Student s inner join s.studentGrades sg "
    					+ "where sg.course.id in ("
    						+ "select c.id "
    						+ "from Course c where c.title like :title)"),
    @NamedQuery(name = "Student.findGPAs", 
    			query = "select s.id, avg(sg.grade) as avg "
    					+ "from Student s inner join s.studentGrades sg "
    					+ "where year(s.enrollmentDate) = :year "
    					+ "group by s.id "
    					+ "order by avg desc"),
    
    @NamedQuery(name = "Student.findGPAs2", 
    query = "select s.id, avg(sg.grade) as avg "
    		+ "from Student s inner join s.studentGrades sg "
    		+ "where year(s.enrollmentDate) = :year "
    		+ "group by s.id "),
    
    @NamedQuery(name = "Student.findMaxGPAs", 
    query = "select s.id, avg(sg.grade) as avg from Student s inner join s.studentGrades sg where year(s.enrollmentDate) = :year group by s.id "
    		+ "having avg(sg.grade) >= all (select avg(sg.grade) from Student s inner join s.studentGrades sg where year(s.enrollmentDate) = :year group by s.id )"),
})
public class Student extends Person implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 5054814556079295778L;

	private LocalDate enrollmentDate;
    
    @OneToMany(mappedBy = "student")
    private Set<StudentGrade> studentGrades;

    public Student(String firstName, String lastName, LocalDate enrollmentDate) {
        super(firstName, lastName);
        this.enrollmentDate = enrollmentDate;
    }

    public Student() {
    }

	public LocalDate getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(LocalDate enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
	
	public Set<StudentGrade> getStudentGrades() {
		return studentGrades;
	}

	public void setStudentGrades(Set<StudentGrade> studentGrades) {
		this.studentGrades = studentGrades;
	}

	@Override
	public String toString() {
		return String.format("Student [enrollmentDate=%s, getId()=%s, getFirstName()=%s, getLastName()=%s]",
				enrollmentDate, getId(), getFirstName(), getLastName());
	}
    
    
}