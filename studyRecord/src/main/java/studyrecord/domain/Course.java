package studyrecord.domain;

public class Course {
    private String courseName;
    private int credits;
    private boolean completed;
    private boolean canceled;
    private int grade;
    
    public Course(String courseName, int credits) {
        this.courseName = courseName;
        this.credits = credits;
        this.grade = -1;
        this.completed = false;
        this.canceled = false;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
        
    @Override
    public String toString() {
        return this.getCourseName() + ": " + this.getCredits() + " credits";
    }
}
