import java.util.Random;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FlashCards{
    private BorderPane borderPane;
    private StackPane textPane;
    private GridPane textBoxesPane;
    private HBox homeBox;
    private HBox buttonBox;
    private VBox vBox;
    private Scene flashCardsScreen;    
    
    private TextArea textArea1;
    private TextArea textArea2;
    private Button addButton;
    private Button studyButton;
    private Button homeButton;
    private Text side1;
    private Text side2;
    private Text text;
    private String[][] flashCards;
    private int[] counter;
    
    private BorderPane borderPane2;
    private StackPane textPane2;
    private GridPane layout;
    private HBox buttons;
    private Stage primaryStageStudy;
    private Scene studyScene;
    
    private Button flipButton;
    private Button nextButton;
    private TextArea flashcard;
    private Text text2;
    private int[] randomInt;
    

    @SuppressWarnings("static-access")
    public void display(Stage primaryStage, Scene homeScreen){        
        //Matrix to store flashcards
        flashCards = new String[99][2];
        counter = new int[1];
        counter[0] = 0;
        
        borderPane = new BorderPane();
        
        //Adds text to stackpane which is at the top of the borderpane
        textPane = new StackPane();        
        text = new Text("Flashcards!");
        text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 20));
        textPane.setAlignment(text, Pos.CENTER);
        textPane.setMargin(text, new Insets(100, 0, 0, 0));
        textPane.getChildren().add(text);
        borderPane.setTop(textPane);
        
        //Add and Study buttons
        buttonBox = new HBox(10);
        addButton = new Button("Add");        
        studyButton = new Button("Study");
        buttonBox.getChildren().addAll(addButton, studyButton);
        buttonBox.setAlignment(Pos.CENTER);

        //labels and textareas
        textBoxesPane = new GridPane();
        textBoxesPane.setAlignment(Pos.CENTER); // Set center alignment
        textBoxesPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        textBoxesPane.setHgap(50);
        textBoxesPane.setVgap(20);

        side1 = new Text("Side 1");
        GridPane.setHalignment(side1, HPos.CENTER);
        GridPane.setValignment(side1, VPos.BOTTOM);
        textBoxesPane.add(side1, 0,0);

        side2 = new Text("Side 2");
        GridPane.setHalignment(side2, HPos.CENTER);
        GridPane.setValignment(side2, VPos.BOTTOM);
        textBoxesPane.add(side2, 1,0);

        textArea1 = new TextArea();
        textArea1.setPrefHeight(100);
        textArea1.setPrefWidth(300);
        textArea1.setWrapText(true);
        GridPane.setHalignment(textArea1, HPos.CENTER);
        GridPane.setValignment(textArea1, VPos.TOP);
        textBoxesPane.add(textArea1, 0,1);

        textArea2 = new TextArea();
        textArea2.setPrefHeight(100);
        textArea2.setPrefWidth(300);
        textArea2.setWrapText(true);
        GridPane.setHalignment(textArea2, HPos.CENTER);
        GridPane.setValignment(textArea2, VPos.TOP);
        textBoxesPane.add(textArea2, 1,1);

        //Home Button
        homeBox = new HBox(10);
        homeButton = new Button("Home");
        homeBox.getChildren().add(homeButton);     
        homeBox.setAlignment(Pos.CENTER); 

        //Sets the buttons and textareas vertically
        vBox = new VBox(10);
        vBox.getChildren().addAll(buttonBox, textBoxesPane, homeBox);
        vBox.setAlignment(Pos.CENTER);
        borderPane.setCenter(vBox);

        //Button actions        
        homeButton.setOnAction(e -> App.mainDisplay(primaryStage, homeScreen));
        studyButton.setOnAction(e -> study(flashCards, counter));
        addButton.setOnAction (e -> {
            flashCards[counter[0]][0] = textArea1.getText();
            flashCards[counter[0]][1] = textArea2.getText();
            textArea1.clear();
            textArea2.clear(); 
            //Changing counter to a single number in a array is a workaround for increasing the counter
            counter[0]++;
        });
    
        //Displays flashcards input scene
        flashCardsScreen = new Scene(borderPane, 450, 450);
        primaryStage.setScene(flashCardsScreen);
        primaryStage.show();
    }

   

    @SuppressWarnings("static-access")
    public void study(String flashCards[][], int counter[]){
        borderPane2 = new BorderPane();

        textPane2 = new StackPane();        
        text2 = new Text("Study!");
        text2.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 20));
        textPane2.setAlignment(text2, Pos.CENTER);
        textPane2.setMargin(text2, new Insets(100, 0, 0, 0));
        textPane2.getChildren().add(text2);
        borderPane2.setTop(textPane2);

        layout = new GridPane();
        borderPane2.setCenter(layout); 
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        layout.setVgap(20);

        //Sets buttons
        buttons = new HBox(10);
        flipButton = new Button("Flip");
        nextButton = new Button("Next");
        buttons.getChildren().addAll(flipButton, nextButton); 
        buttons.setAlignment(Pos.CENTER);
        layout.add(buttons, 0,0);
        
        //Sets Textarea to display flashcards
        flashcard = new TextArea();
        flashcard.setWrapText(false);
        flashcard.setEditable(false);        
        GridPane.setHalignment(flashcard, HPos.CENTER);
        GridPane.setValignment(flashcard, VPos.CENTER);
        layout.add(flashcard, 0,1);
        
        Random random = new Random();
        randomInt = new int[1];
        randomInt[0] = random.nextInt(counter[0]);
        flashcard.setText(flashCards[randomInt[0]][0]);

        //Next button 
        nextButton.setOnAction(e -> {            
            randomInt[0] = random.nextInt(counter[0]);
            flashcard.setText(flashCards[randomInt[0]][0]);
        });

        //Flip button flips flashcard to other side
        flipButton.setOnAction(e -> {
            String currentText = flashcard.getText();
            if (currentText.equals(flashCards[randomInt[0]][0])) {
                flashcard.setText(flashCards[randomInt[0]][1]);
            } else {
                flashcard.setText(flashCards[randomInt[0]][0]);
            }
        });

        //Sets scene to study
        primaryStageStudy = new Stage();
        studyScene = new Scene(borderPane2, 450, 450);
        primaryStageStudy.setScene(studyScene);
        primaryStageStudy.show();
    }

}
