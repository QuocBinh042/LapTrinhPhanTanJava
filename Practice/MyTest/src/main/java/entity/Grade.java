package entity;

public class Grade {
	private Date date;
	private String grade;
	private Integer score;
	
	public Grade() {
		super();
	}
	public Grade(Date date, String grade, Integer score) {
		super();
		this.date = date;
		this.grade = grade;
		this.score = score;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "Grades [date=" + date + ", grade=" + grade + ", score=" + score + "]";
	}
	
	
	
}
