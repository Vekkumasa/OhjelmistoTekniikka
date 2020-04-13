package studyrecord.domain;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<Course> credits;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.credits = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void addCredit(Course course) {
        if (!this.credits.contains(course)) {
            this.credits.add(course);
        }
    }
    
    public ArrayList getList() {
        return this.credits;
    }
    
    public void getCredits() {
        for (int i = 0; i < this.credits.size(); i++) {
            System.out.println(this.credits.get(i));
        }
    }
    
    public int sizeOfArray() {
        return this.credits.size();
    }
    
    @Override
    public String toString() {
        return "username: " + this.username;
    }
}
