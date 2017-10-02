package calendarReminders;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;


public class CalendarReminder extends Application {
    private double paneWidth = 1000;
    private double paneHeight = 700;
    private GridPane root;
    private Scene gridScene;
    private final TextField userInputTextField = new TextField();
    private final TextField userInputTextFieldPass = new TextField();
    private String userName = " ";
    private String password = " ";
    private Label infoText = new Label ("Welcome! Please enter your username and password, if you don't have an account" +
                                                "click 'New User' ");
    private HBox buttonHolder;
    private Button[] buttons;
    private HBox buttonHolder2;
    private Button[] buttons2;
    private String fileName = "Accounts.txt";
    private String path = "/Users/sophiekofsky/Documents/";
    private String document = "";
    private String[] accounts;
    private String[] users;
    //private Button() add = new Button;



    @Override
    public void start(Stage primaryStage) throws Exception {
        rootSetUp();
        sceneSetUp();
        stageSetUp(primaryStage);
        userInput();
        userInput2();
        buttonsetUp();
        Read r = new Read((path + fileName));
        r.readFile();
        document = r.getText();
        accounts = document.split("User: ");
        for(int i=0; i<accounts.length; i++){
            System.out.println("" + i + ": "+ accounts[i]);
        }
    }//start

    private void buttonsetUp() {
        buttonHolder = new HBox();
        buttons = new Button[1];
        //buttonHolder.setAlignment(Pos.BASELINE_LEFT);
        buttonHolder.setAlignment(Pos.BOTTOM_LEFT);
        buttonHolder.setSpacing(10);
        buttons[0] = new Button();
        buttons[0].setPrefSize(paneWidth / 8, paneHeight / 24);
        buttons[0].setText("Enter");
        buttons[0].setId("button");
        buttonHolder.getChildren().add(buttons[0]);
        root.add(buttonHolder, 2, 5);

        buttons[0].setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        buttonHolder.setVisible(false);
                        buttonHolder2.setVisible(false);
                        password = userInputTextFieldPass.getText();
                        userInputTextFieldPass.setText("");
                        userName = userInputTextField.getText();
                        userInputTextField.setText("");

                        //System.out.println(password);
                        //System.out.println(userName);

                    }
                }
        );

        buttonHolder2 = new HBox();
        buttons2 = new Button[1];
        //buttonHolder.setAlignment(Pos.BASELINE_LEFT);
        buttonHolder2.setAlignment(Pos.BOTTOM_LEFT);
        buttonHolder2.setSpacing(10);
        buttons2[0] = new Button();
        buttons2[0].setPrefSize(paneWidth / 8, paneHeight / 35);
        buttons2[0].setText("New User");
        buttons2[0].setId("button");
        buttonHolder2.getChildren().add(buttons2[0]);
        root.add(buttonHolder2, 2, 7);
        buttons2[0].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        buttonHolder.setVisible(false);
                        buttonHolder2.setVisible(false);
                        userInputTextFieldPass.setVisible(false);
                        userInputTextFieldPass.setText("");
                        userInputTextField.setText("");
                        Read r = new Read((path + fileName));
                        r.readFile();
                        document = r.getText();
                        infoText.setText("Type in what you want to be your username then hit the enter key");
                        userInputTextField.setOnAction(new EventHandler<ActionEvent>()  {
                            public void handle(ActionEvent e) {
                                    Writer w = new Writer((path + fileName));
                                    w.writeToFile(document + "\n User: " + userInputTextField.getText());

                                    Read r = new Read((path + fileName));
                                    r.readFile();
                                    document = r.getText();
                                    userInputTextField.clear();
                                    infoText.setText("Type in your password, then hit enter key.");
                                    userInputTextField.setOnAction(new EventHandler<ActionEvent>()  {
                                    public void handle(ActionEvent e) {
                                        w.writeToFile(document + "\n Password: " + userInputTextField.getText());
                                        userInputTextField.clear();
                                        infoText.setText("Thanks for Creating an account!");
                                        userInputTextField.setVisible(false);
                                    }//inner handle
                                });//EventHandler class
                            }//inner handle
                        });//EventHandler class

                    }//outer handle
                });//setOnAction
    }//button set up

    private void userInput() {
        userInputTextField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


            }//handle
        });//setOnAction
    }

    private void userInput2(){
        userInputTextFieldPass.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {



            }//handle
        });//setOnAction


    }//userInput

    private void rootSetUp() {
        root = new GridPane();
        root.setPadding(new Insets(25, 25, 25, 25));
        infoText.setWrapText(true);
        userInputTextFieldPass.setPromptText("Password");
        userInputTextField.setPromptText("Username");
        root.add(userInputTextFieldPass, 2, 4);
        root.add(userInputTextField, 2, 2);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(35);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(1);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(35);
        root.getColumnConstraints().addAll(column1,column2,column3);
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(30);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(5);
        RowConstraints row3 = new RowConstraints();
        row3.setPercentHeight(10);
        RowConstraints row4 = new RowConstraints();
        RowConstraints row5 = new RowConstraints();
        RowConstraints row6 = new RowConstraints();
        RowConstraints row7 = new RowConstraints();
        row7.setPercentHeight(1);
        root.getRowConstraints().addAll(row1,row2,row3,row4,row5,row6,row7);
        root.add(infoText,2,0);
        root.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        root.setId("background");
        //root.getChildren(newUser);

    }//rootSetUp

    public static void main(String[] args){
        Application.launch(args);
    }//main

    private void sceneSetUp() {
       gridScene = new Scene(root, paneWidth, paneHeight);
    }//sceneSetUp

    private void stageSetUp(Stage s) {
        s.setTitle("Calendar");
        s.setScene(gridScene);
        s.show();
    }//stageSetUp


}
