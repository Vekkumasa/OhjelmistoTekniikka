package OpintojenSeurantaJarjestelma.domain;

public class Course {
    private String courseName;
    private int credits;
    
    public Course(String courseName, int credits) {
        this.courseName = courseName;
        this.credits = credits;
    }

    /**
     * @return the courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param courseName the courseName to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * @return the credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * @param credits the credits to set
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }
    
    @Override
    public String toString() {
        return this.courseName + ": " + this.credits;
    }
}
