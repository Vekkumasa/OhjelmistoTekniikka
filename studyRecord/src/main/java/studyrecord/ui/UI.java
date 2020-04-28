package studyrecord.ui;

import java.text.DecimalFormat;

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
import javafx.scene.control.PasswordField;
import javafx.scene.Node;

import studyrecord.domain.User;
import studyrecord.domain.Course;
import studyrecord.domain.Service;

import studyrecord.dao.DBUserDao;
import studyrecord.dao.DBCourseDao;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;


public class UI extends Application {
    private Service service;
    
    private Scene mainScene;
    private Scene newUserScene;
    private Scene loginScene;
    
    private VBox vboxCourses;
    private Label dataLabel;
    
    private DecimalFormat df2;
    private BackgroundFill bf;
    
    @Override
    public void init() throws Exception {
        DBUserDao userDao = new DBUserDao("jdbc:h2:./studies");
        DBCourseDao courseDao = new DBCourseDao(userDao, "jdbc:h2:./studies");
        service = new Service(userDao, courseDao);
        dataLabel = new Label("");
        df2 = new DecimalFormat("#.##");
        bf = new BackgroundFill(Color.ANTIQUEWHITE, new CornerRadii(1),
         new Insets(0.0, 0.0, 0.0, 0.0));
    }
    
    /**
     * Checks that input in textfield is integer and between min and max value
     * @param text
     * @param min
     * @param max
     * @return true if text is valid
     */
    
    public boolean isInputValid(TextField text, int min, int max) {
        boolean b = false;
        if (!(text.getText() == null || text.getText().length() == 0)) {
            String valid = text.getText();
            if (valid.matches("[" + min + "-" + max + "]")) {
                b = true;
            }
        }
        return b;
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
        Button delete = new Button("Delete course");
        Label notification = new Label("");
        box.setBackground(new Background(bf));
        
        if (course.isCompleted()) {
            label.setText(course.getCourseName() + " " + course.getCredits() 
                    + " Grade: " + course.getGrade() + " Date: " 
                    + course.getCompletionDate().getDayOfMonth()
                    + "." + course.getCompletionDate().getMonthOfYear() 
                    + "." + course.getCompletionDate().getYear());
            label.setTextFill(Color.TEAL);
            box.getChildren().addAll(label, spacer, delete, notification);
            delete.setOnAction(e -> {
                service.deleteCourse(course, service.getUser());
                redrawList();
            });
        } else if (course.isCanceled()) {
            label.setText(course.getCourseName() + " " + course.getCredits() + " canceled");
            box.getChildren().addAll(label, spacer, delete, notification);
            delete.setOnAction(e -> {
                service.deleteCourse(course, service.getUser());
                redrawList();

            });
        } else {        
            box.getChildren().addAll(label, spacer, textfield, complete, cancel, notification);            
            complete.setOnAction(e -> {
                if (isInputValid(textfield, 1 , 5)) {
                    int luku = Integer.parseInt(textfield.getText());
                    service.setComplete(course, luku, service.getUser());
                    redrawList();
                } else {
                    notification.setText("Course grade must be 1-5");
                    notification.setTextFill(Color.RED);
                }
                
            });
            cancel.setOnAction(e -> {
                service.setCanceled(course, service.getUser());
                redrawList();
            });
        }
        return box;
    }
    
    /**
     * Clears mainscreen and then retrieves updated courses from a database and
     * creates mainscreen view again.
     */
    public void redrawList() {
        vboxCourses.getChildren().clear();
        List<Course> courses = service.getCourses(service.getUser());
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            vboxCourses.getChildren().add(createCourse(course));
        }
        double avg = service.getUserAverage(service.getUser());
        double totalCredits = service.getUserCredits(service.getUser());
        dataLabel.setText("Total credits: " + totalCredits + " Average: " + df2.format(avg));
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
        usernameInput.setPromptText("Username");
        Label passwordLabel = new Label("password");
        PasswordField passwordInput = new PasswordField();
        passwordInput.setPromptText("Password");
        loginPane.setBackground(new Background(bf));
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
        newUsernameField.setPromptText("Username");
        PasswordField newPasswordField = new PasswordField();
        newPasswordField.setPromptText("Password");
        
        Button createUserButton = new Button("create user");
        Button backToLoginButton = new Button("Back to login");
        
        newUsernamePane.getChildren().addAll(newUsernameLabel, newUsernameField);
        newPasswordPane.getChildren().addAll(newPasswordLabel, newPasswordField);
        
        Label newUserMessage = new Label();
        
        newUserPane.getChildren().addAll(newUserMessage, newUsernamePane, newPasswordPane, createUserButton, backToLoginButton);
        newUserPane.setBackground(new Background(bf));
        // Create user
        
        createUserButton.setOnAction(e -> {
            String username = newUsernameField.getText();
            String password = newPasswordField.getText();
            
            if (!(newUsernameField.getText() == null || newUsernameField.getText().length() == 0)) {

                if (service.createUser(username, password)) {
                    newUserMessage.setText("Created new user");
                    newUserMessage.setTextFill(Color.GREEN);
                } else {
                    newUserMessage.setText("Username is already in use");
                    newUserMessage.setTextFill(Color.RED);
                }
            } else {
                newUserMessage.setText("Username field cant be blank");
                newUserMessage.setTextFill(Color.RED);
            }
        });
        
        backToLoginButton.setOnAction(e -> {
            newUsernameField.setText("");
            newPasswordField.setText("");
            newUserMessage.setText("");
            errorMessage.setText("");
            window.setScene(loginScene);
        });
        
        newUserScene = new Scene(newUserPane, 500, 300);
        
        // Main scene
    
        ScrollPane scroll = new ScrollPane();       
        BorderPane mainPane = new BorderPane(scroll);
        vboxCourses = new VBox(10);
        mainScene = new Scene(mainPane, 700, 500);
        scroll.setContent(vboxCourses);
        
        scroll.setStyle("-fx-background: rgb(250, 235, 215);\n -fx-background-color: rgb(250, 235, 215)");
        mainPane.setBackground(new Background(bf));
        
        HBox informationPane = new HBox(10);
        Region menuSpacer = new Region();
        HBox.setHgrow(menuSpacer, Priority.ALWAYS);
        
        HBox hopsData = new HBox(10);
        Label validationLabel = new Label("");
        
        Button logoutButton = new Button("Log out");
        
        HBox addCourse = new HBox(10);
        Button addCourseButton = new Button("Add Course");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        TextField addCourseName = new TextField();
        addCourseName.setPromptText("Course name");
        TextField addCourseCredits = new TextField();
        addCourseCredits.setPromptText("Course credits (1-9)");
        VBox addCourseBox = new VBox(10);
        addCourseBox.getChildren().addAll(addCourse, hopsData, validationLabel);
        
        addCourseButton.setOnAction(e -> {
            String courseToBeAdded = addCourseName.getText();
            if (isInputValid(addCourseCredits, 1, 9)) {
                int courseAddedCredits = Integer.parseInt(addCourseCredits.getText());
                service.createCourse(courseToBeAdded, courseAddedCredits, service.getUser());
                redrawList();
                validationLabel.setText("");
            } else {
                validationLabel.setText("credits must be between 1-9");
                validationLabel.setTextFill(Color.RED);
            }
            redrawList();
        });
        
        hopsData.getChildren().addAll(dataLabel);
        addCourse.getChildren().addAll(addCourseName, addCourseCredits, spacer, addCourseButton);
        informationPane.getChildren().addAll(nameLabel, menuSpacer, logoutButton);
        mainPane.setTop(informationPane);
        mainPane.setBottom(addCourseBox);
        
        logoutButton.setOnAction(e -> {
            service.logOut();
            errorMessage.setText("");
            window.setScene(loginScene);
        });
        
        window.setTitle("Study Record");
        window.setScene(loginScene);
        window.show();
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
