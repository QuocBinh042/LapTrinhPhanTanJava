package entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "OnlineCourse")
public class OnlineCourse extends Course implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 8044014042400423518L;
	private String url;

    public OnlineCourse(String title, int credits, String url) {
        super(title, credits);
        this.url = url;
    }

    public OnlineCourse() {
    }

    
    
    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
    public String toString() {
        return String.format("OnlineCourse{id=%d, title='%s', credits=%d, url='%s'}", id, title, credits, url);
    }
}