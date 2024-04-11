import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Notes{

    private BorderPane borderPane;
    private StackPane textPane;
    private Scene notesScreen;
    private HBox buttonBox;
    private HBox textBox;
    private HBox homeBox;
    private VBox vBox;
    
    private Button openButton;
    private Button saveButton;
    private Button homeButton;
    private TextArea textArea;
    private Text text;

    @SuppressWarnings("static-access")
    public void display(Stage primaryStage, Scene homeScreen){        
        borderPane = new BorderPane();
        
        //Adds text to stackpane which is at the top of the borderpane
        textPane = new StackPane();        
        text = new Text("Notes!");
        text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 20));
        textPane.setAlignment(text, Pos.CENTER);
        textPane.setMargin(text, new Insets(100, 0, 0, 0)); 
        textPane.getChildren().add(text);
        borderPane.setTop(textPane);        
        
        //Open and Save buttons
        buttonBox = new HBox(10);
        openButton = new Button("Open");        
        saveButton = new Button("Save");
        buttonBox.getChildren().addAll(openButton, saveButton);
        buttonBox.setAlignment(Pos.CENTER);

        //Textarea to enter notes
        textBox = new HBox(10);
        textArea = new TextArea();
        textArea.setPrefHeight(200);
        textArea.setPrefWidth(300);
        textArea.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        textArea.setWrapText(true);
        textBox.getChildren().add(textArea);
        textBox.setAlignment(Pos.CENTER);               

        //Home Button
        homeBox = new HBox(10);
        homeButton = new Button("Home");
        homeBox.getChildren().add(homeButton);     
        homeBox.setAlignment(Pos.CENTER);   
        
        //Sets the buttons and textarea vertically
        vBox = new VBox(10);
        vBox.getChildren().addAll(buttonBox, textArea, homeBox);
        vBox.setAlignment(Pos.CENTER);
        borderPane.setCenter(vBox);

        //Button actions
        openButton.setOnAction(e -> fileOpen(textArea));
        saveButton.setOnAction(e -> fileSave(textArea));
        homeButton.setOnAction(e -> App.mainDisplay(primaryStage, homeScreen));
        
        //Sets/displays scene
        notesScreen = new Scene(borderPane, 450, 450);
        primaryStage.setScene(notesScreen);
        primaryStage.show();
       
    }

    //User selects a file from their computer
    public static void fileOpen(TextArea textArea){        
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                textArea.appendText(scanner.nextLine() + "\n");
            }
            scanner.close();
        } catch (FileNotFoundException e) {            
            e.printStackTrace();
        }
    }

    //User can save a file to their computer
    public static void fileSave(TextArea textArea){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(new Stage());
        if(file != null){
            PrintWriter printWriter;
            try {
                printWriter = new PrintWriter(file);
                printWriter.write(textArea.getText());
                printWriter.close();
            } catch (FileNotFoundException e) {                
                e.printStackTrace();
            }
           
        }
    }
}
