package OpintojenSeurantaJarjestelma.domain;

public class Course {
    private String courseName;
    private int credits;
    
    public Course(String courseName, int credits) {
        this.courseName = courseName;
        this.credits = credits;
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
    
    @Override
    public String toString() {
        return this.courseName + ": " + this.credits;
    }
}
