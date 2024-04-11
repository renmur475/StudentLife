import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application{
    private BorderPane borderPane;
    private StackPane textPane;
    private Text text;
    private GridPane appsPane;

    private Image calendarImage;
    private ImageView calculatorImageView;
    private Button calendarBtn;

    private Image calculatorImage;
    private ImageView calendarImageView;
    private Button calculatorBtn;

    private Image notesImage;
    private ImageView notesImageView;
    private Button notesBtn;

    private Image flashcardsImage;
    private ImageView flashcardsImageView;
    private Button flashcardsBtn;

    private Scene homeScreen;
    private Notes notes;
    private FlashCards flashCards;
    private Calculator calculator;
    private Calendar calendar;

    @SuppressWarnings("static-access")
    @Override 
    public void start(Stage primaryStage) {
        borderPane = new BorderPane();
        
        //Places text at top
        textPane = new StackPane();
        borderPane.setTop(textPane);
        text = new Text("Student Life");
        text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 20));
        textPane.setAlignment(text, Pos.CENTER);
        textPane.setMargin(text, new Insets(100, 0, 0, 0)); 
        textPane.getChildren().add(text);

        //Pane where apps appear
        appsPane = new GridPane();
        borderPane.setCenter(appsPane); 
        appsPane.setAlignment(Pos.CENTER);
        appsPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        appsPane.setHgap(100);
        appsPane.setVgap(50);        
        RowConstraints row0 = new RowConstraints();
        row0.setPrefHeight(20); // Row 0 height
        RowConstraints row1 = new RowConstraints();
        row1.setPrefHeight(100); // Row 1 height
        RowConstraints row2 = new RowConstraints();
        row2.setPrefHeight(20); // Row 2 height
        appsPane.getRowConstraints().addAll(row0, row1, row2);

        //Calendar
        calendarImage = new Image("file:src/Images/calculator.png", 100, 100, false, false);
        calendarImageView = new ImageView(calendarImage);
        appsPane.add(calendarImageView, 0, 0);
        GridPane.setHalignment(calendarImageView, HPos.CENTER);

        calendarBtn = new Button("Calendar");
        appsPane.add(calendarBtn, 1, 1);
        GridPane.setHalignment(calendarBtn, HPos.CENTER);
        GridPane.setValignment(calendarBtn, VPos.TOP);

        //Calculator
        calculatorImage = new Image("file:src/Images/calendar.png", 90, 90, false, false);
        calculatorImageView = new ImageView(calculatorImage);
        appsPane.add(calculatorImageView, 1, 0);
        GridPane.setHalignment(calculatorImageView, HPos.CENTER);

        calculatorBtn = new Button("Calculator");
        appsPane.add(calculatorBtn, 0, 1);
        GridPane.setHalignment(calculatorBtn, HPos.CENTER);
        GridPane.setValignment(calculatorBtn, VPos.TOP);       

        //Notes
        notesImage = new Image("file:src/Images/writing.png", 90, 90, false, false);
        notesImageView = new ImageView(notesImage);
        appsPane.add(notesImageView, 0, 2);
        GridPane.setHalignment(notesImageView, HPos.CENTER);

        notesBtn = new Button("Notes");
        appsPane.add(notesBtn, 0, 3);
        GridPane.setHalignment(notesBtn, HPos.CENTER);
        GridPane.setValignment(notesBtn, VPos.TOP);        
        
        //FlashCards
        flashcardsImage = new Image("file:src/Images/icons8-flashcards-96.png", 100, 100, false, false);
        flashcardsImageView = new ImageView(flashcardsImage);
        appsPane.add(flashcardsImageView, 1, 2);
        GridPane.setHalignment(flashcardsImageView, HPos.CENTER);

        flashcardsBtn = new Button("Flashcards");
        appsPane.add(flashcardsBtn, 1, 3);
        GridPane.setHalignment(flashcardsBtn, HPos.CENTER);
        GridPane.setValignment(flashcardsBtn, VPos.TOP);
        
        //Defaults to home screen
        homeScreen = new Scene(borderPane, 450, 600);        
        mainDisplay(primaryStage, homeScreen);

        //Switches to the different apps
        notes = new Notes();
        notesBtn.setOnAction(e -> notes.display(primaryStage, homeScreen));

        flashCards = new FlashCards();
        flashcardsBtn.setOnAction(e -> flashCards.display(primaryStage, homeScreen));        
        
        calculator = new Calculator();
        calculatorBtn.setOnAction(e -> {
            try {
                calculator.start(primaryStage, homeScreen);
            } catch (Exception e1) {                
                e1.printStackTrace();
            }
        });
        
        calendar = new Calendar();
        calendarBtn.setOnAction(e -> {
            try {
                calendar.start(primaryStage, homeScreen);
            } catch (IOException e1) {                
                e1.printStackTrace();
            }
        });
    }

    //Allows for the apps to switch back to the home screen
    public static void mainDisplay(Stage primaryStage, Scene homeScreen){
        primaryStage.setTitle("Student Life");
        primaryStage.setScene(homeScreen);
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
        launch();
    }
}
