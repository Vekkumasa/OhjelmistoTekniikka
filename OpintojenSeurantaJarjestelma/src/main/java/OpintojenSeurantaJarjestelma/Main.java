package OpintojenSeurantaJarjestelma;

import OpintojenSeurantaJarjestelma.domain.*;

public class Main {
    public static void main(String[] args) {
        Course course = new Course("OHTE", 5);
        User user = new User("User", "Username", "Password");
        
        user.addCredit(course);
        System.out.println(user.sizeOfArray());
    }
}
