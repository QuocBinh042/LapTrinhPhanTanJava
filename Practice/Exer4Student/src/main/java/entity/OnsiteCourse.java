package entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "OnsiteCourse")
public class OnsiteCourse extends Course implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6836562613666563111L;
	private String location;
    private String days;
    private LocalDateTime time;

    public OnsiteCourse(String title, int credits, String location, String days, LocalDateTime time) {
        super(title, credits);
        this.location = location;
        this.days = days;
        this.time = time;
    }

    public OnsiteCourse() {
    }

    @Override
    public String toString() {
        return String.format("OnsiteCourse{id=%d, title='%s', credits=%d, location='%s', days='%s', time=%s}", id, title, credits, location, days, time);
    }

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}
    
    
}