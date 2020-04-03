package OpintojenSeurantaJarjestelma.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.sun.javafx.scene.control.IntegerField;
import javafx.scene.control.PasswordField;
import javafx.scene.Node;

import OpintojenSeurantaJarjestelma.domain.User;
import OpintojenSeurantaJarjestelma.domain.Course;
import java.util.ArrayList;


public class ui extends Application {
    
    private Scene mainScene;
    private Scene newUserScene;
    private Scene loginScene;
    
    private User testUser;
    private VBox vboxCourses;
    
    public Node createCourse(Course course) {
        HBox box = new HBox(10);
        Label label = new Label(course.getCourseName() + " " + course.getCredits());
        box.getChildren().add(label);
        return box;
    }
    
    public void redrawList() {
        vboxCourses.getChildren().clear();
        
        ArrayList<Course> courses = testUser.getList();
        System.out.println("JEJEJEJE" + courses.size());
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(i);
            Course course = courses.get(i);
            vboxCourses.getChildren().add(createCourse(course));
        }
    }
    
    @Override
    public void start(Stage window) {  
        
        testUser = new User("Testi", "Testi", "Testi");
        Course testCourse = new Course("OHTE" , 5);
        testUser.addCredit(testCourse);
        // login
        VBox loginPane = new VBox(10);
        HBox usernamePane = new HBox(10);
        HBox passwordPane = new HBox(10);
        loginPane.setPadding(new Insets(10));
        Label usernameLabel = new Label("username");
        TextField usernameInput = new TextField();
        Label passwordLabel = new Label("password");
        PasswordField passwordInput = new PasswordField();
        
        
        usernamePane.getChildren().addAll(usernameLabel, usernameInput);
        passwordPane.getChildren().addAll(passwordLabel, passwordInput);
        
        Button loginButton = new Button("login");
        Button createButton = new Button("create new user");
        
        createButton.setOnAction(e ->{
            usernameInput.setText("");
            passwordInput.setText("");
            window.setScene(newUserScene);
        });
        
        loginButton.setOnAction(e -> {
            usernameInput.setText("");
            passwordInput.setText("");
            window.setScene(mainScene);
        });
        
        loginPane.getChildren().addAll(usernamePane, passwordPane, loginButton, createButton);       
        
        loginScene = new Scene(loginPane, 500, 300);  
        
        // newUser
        
        VBox newUserPane = new VBox(10);
        HBox newUsernamePane = new HBox(10);
        HBox newPasswordPane = new HBox(10);
        Label newUsernameLabel = new Label("username");
        Label newPasswordLabel = new Label("password");
        TextField newUsernameField = new TextField();
        PasswordField newPasswordField = new PasswordField();
        
        Button createUserButton = new Button("create user");
        Button backToLoginButton = new Button("Back to login");
        
        newUsernamePane.getChildren().addAll(newUsernameLabel, newUsernameField);
        newPasswordPane.getChildren().addAll(newPasswordLabel, newPasswordField);
        
        newUserPane.getChildren().addAll(newUsernamePane, newPasswordPane, createUserButton, backToLoginButton);
        
        backToLoginButton.setOnAction(e -> {
            newUsernameField.setText("");
            newPasswordField.setText("");
            window.setScene(loginScene);
        });
        
        newUserScene = new Scene(newUserPane, 500, 300);
        
        // Main scene
    
        vboxCourses = new VBox(10);
        VBox mainScenePane = new VBox(10);
        HBox informationPane = new HBox(10);
        Region menuSpacer = new Region();
        HBox.setHgrow(menuSpacer, Priority.ALWAYS);
        Label nameLabel = new Label(testUser.getName());
        HBox testCoursePane = new HBox(10);
        Label course = new Label(testCourse.getCourseName() +" " + testCourse.getCredits());
        Button logoutButton = new Button("Log out");
        
        HBox addCourse = new HBox(10);
        Button addCourseButton = new Button("Add Course");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        TextField addCourseName = new TextField();
        IntegerField addCourseCredits = new IntegerField();
        
        addCourseButton.setOnAction(e -> {
            String courseToBeAdded = addCourseName.getText();
            int courseAddedCredits = addCourseCredits.getValue();
            Course newCourse = new Course(courseToBeAdded, courseAddedCredits);
            testUser.addCredit(newCourse);
            redrawList();
        });
        
        addCourse.getChildren().addAll(addCourseName, addCourseCredits, spacer, addCourseButton);
        informationPane.getChildren().addAll(nameLabel, menuSpacer, logoutButton);
        testCoursePane.getChildren().addAll(course);
        mainScenePane.getChildren().addAll(informationPane, testCoursePane, vboxCourses, addCourse);
        
        
        
        logoutButton.setOnAction(e -> {
            window.setScene(loginScene);
        });
        
        mainScene = new Scene(mainScenePane, 500, 300);
        
        window.setTitle("OpintojenSeurantaJarjestelma");
        window.setScene(loginScene);
        window.show();
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}