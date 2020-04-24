package studyrecord.ui;

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

import studyrecord.domain.User;
import studyrecord.domain.Course;
import studyrecord.domain.Service;

import studyrecord.dao.DBUserDao;
import studyrecord.dao.DBCourseDao;

import java.util.ArrayList;
import java.util.List;


public class UI extends Application {
    private Service service;
    
    private Scene mainScene;
    private Scene newUserScene;
    private Scene loginScene;
    
    private VBox vboxCourses;
    private Label dataLabel;
    
    private int totalCredits;
    private double average;
    
    @Override
    public void init() throws Exception {
        DBUserDao userDao = new DBUserDao("jdbc:h2:./studies");
        DBCourseDao courseDao = new DBCourseDao(userDao, "jdbc:h2:./studies");
        service = new Service(userDao, courseDao);
        dataLabel = new Label("");
    }
    
    /**
     * Creates HBox Node, TextField, two Buttons one for setting course completed
     * and one for setting course canceled, and label with course information
     * @param course
     * @return HBox Node
     */
    public Node createCourse(Course course) {
        Label label = new Label(course.getCourseName() + " " + course.getCredits());
        HBox box = new HBox(10);
        Region spacer = new Region();
        TextField textfield = new TextField();
        Button complete = new Button("Set completed");
        box.setHgrow(spacer, Priority.ALWAYS);
        Button cancel = new Button("Set Canceled");
        if (course.isCompleted()) {
            label.setText(course.getCourseName() + " " + course.getCredits() + " Completed with grade: " + course.getGrade());
            box.getChildren().addAll(label);
            return box;
        } else if (course.isCanceled()) {
            label.setText(course.getCourseName() + " " + course.getCredits() + " canceled");
            box.getChildren().addAll(label);
            return box;
        } else {        
            box.getChildren().addAll(label, spacer, textfield, complete, cancel);            
            complete.setOnAction(e -> {
                int luku = Integer.parseInt(textfield.getText());
                service.setComplete(course, luku, service.getUser());
                redrawList();
            });
            cancel.setOnAction(e -> {
                service.setCanceled(course, service.getUser());
                redrawList();
            });
            return box;
        }
    }
    
    /**
     * Clears mainscreen and then retrieves updated courses from a database and
     * creates mainscreen view again.
     */
    public void redrawList() {
        vboxCourses.getChildren().clear();
        totalCredits = 0;
        double count = 0;
        double total = 0;
        average = 0;
        List<Course> courses = service.getCourses(service.getUser());
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            totalCredits += course.getCredits();
            if (course.isCompleted()) {
                total += course.getGrade();
                count++;
            }
            vboxCourses.getChildren().add(createCourse(course));
        }
        average = total / count;
        dataLabel.setText("Total credits: " + totalCredits + " Average: " + average);
    }
    
    @Override
    public void start(Stage window) {  
        
        //logged user label
        Label nameLabel = new Label();
        
        // login scene
        
        VBox loginPane = new VBox(10);
        Label errorMessage = new Label();
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
        
        createButton.setOnAction(e -> {
            usernameInput.setText("");
            passwordInput.setText("");
            window.setScene(newUserScene);
        });
        
        loginButton.setOnAction(e -> {
            String username = usernameInput.getText();
            String password = passwordInput.getText();
            if (service.logIn(username, password)) {
                nameLabel.setText("Logged in as: " + username);
                redrawList();
                window.setScene(mainScene);
            } else {
                errorMessage.setText("Invalid username or password");
                errorMessage.setTextFill(Color.RED);
            }
            usernameInput.setText("");
            passwordInput.setText("");
            
        });
        
        loginPane.getChildren().addAll(errorMessage, usernamePane, passwordPane, loginButton, createButton);       
        
        loginScene = new Scene(loginPane, 500, 300);  
        
        // newUser scene
        
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
        
        Label newUserMessage = new Label();
        
        newUserPane.getChildren().addAll(newUserMessage, newUsernamePane, newPasswordPane, createUserButton, backToLoginButton);
        
        // Create user
        
        createUserButton.setOnAction(e -> {
            String username = newUsernameField.getText();
            String password = newPasswordField.getText();
            
            if (service.createUser(username, password)) {
                newUserMessage.setText("Created new user");
                newUserMessage.setTextFill(Color.GREEN);
            } else {
                newUserMessage.setText("ERROR");
                newUserMessage.setTextFill(Color.RED);
            }
        });
        
        backToLoginButton.setOnAction(e -> {
            newUsernameField.setText("");
            newPasswordField.setText("");
            newUserMessage.setText("");
            window.setScene(loginScene);
        });
        
        newUserScene = new Scene(newUserPane, 500, 300);
        
        // Main scene
    
        vboxCourses = new VBox(10);
        VBox mainScenePane = new VBox(10);
        HBox informationPane = new HBox(10);
        Region menuSpacer = new Region();
        HBox.setHgrow(menuSpacer, Priority.ALWAYS);
        
        HBox hopsData = new HBox(10);
        
        Button logoutButton = new Button("Log out");
        
        HBox addCourse = new HBox(10);
        Button addCourseButton = new Button("Add Course");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        TextField addCourseName = new TextField();
        TextField addCourseCredits = new TextField();
        
        addCourseButton.setOnAction(e -> {
            String courseToBeAdded = addCourseName.getText();
            int courseAddedCredits = Integer.parseInt(addCourseCredits.getText());
            service.createCourse(courseToBeAdded, courseAddedCredits, service.getUser());
            redrawList();
        });
        
        hopsData.getChildren().addAll(dataLabel);
        addCourse.getChildren().addAll(addCourseName, addCourseCredits, spacer, addCourseButton);
        informationPane.getChildren().addAll(nameLabel, menuSpacer, logoutButton);
        mainScenePane.getChildren().addAll(informationPane, vboxCourses, hopsData, addCourse);
                
        
        logoutButton.setOnAction(e -> {
            service.logOut();
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
