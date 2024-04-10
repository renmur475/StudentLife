import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;


public class Calculator implements EventHandler<ActionEvent>{

    private TextField resultField;

    private Button[] numButtons = new Button[10];
    private Button[] functionButtons = new Button[9];

    private Button addButton;
    private Button subButton;
    private Button mulButton;
    private Button divButton;
    private Button eqButton;
    private Button decButton;
    private Button negButton;
    private Button delButton;
    private Button clrButton;
    private Button homeButton;

    private double input1 = 0;
    private double input2 = 0;
    private double result = 0;
    private char operator;

        
    public void start(Stage primaryStage, Scene homeScreen) throws Exception{
        //Creating the window and sizing

        GridPane layout = new GridPane();
        layout.setPrefSize(450,650);
        layout.setHgap(5);
        layout.setVgap(5);

        Insets padding = new Insets(20, 10, 10, 10);
        layout.setPadding(padding);

        //Create a 4x6 grid to put objects in
        ColumnConstraints column1 = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();
        ColumnConstraints column3 = new ColumnConstraints();
        ColumnConstraints column4 = new ColumnConstraints();

        column1.setPrefWidth(100);
        column2.setPrefWidth(100);
        column3.setPrefWidth(100);
        column4.setPrefWidth(100);
        
        layout.getColumnConstraints().addAll(column1, column2, column3, column4);
        
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        RowConstraints row3 = new RowConstraints();
        RowConstraints row4 = new RowConstraints();
        RowConstraints row5 = new RowConstraints();
        RowConstraints row6 = new RowConstraints();
        RowConstraints row7 = new RowConstraints();

        row1.setPrefHeight(100);
        row2.setPrefHeight(100);
        row3.setPrefHeight(100);
        row4.setPrefHeight(100);
        row5.setPrefHeight(100);
        row6.setPrefHeight(100);
        row7.setPrefHeight(50);

        layout.getRowConstraints().addAll(row1, row2, row3, row4, row5, row6, row7);

        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calculator");
        primaryStage.show();

        resultField = new TextField();
        resultField.setEditable(false);
        resultField.setPrefSize(450, 100);
        resultField.setAlignment(Pos.CENTER_RIGHT);
        Insets textPadding = new Insets(0, 0, 5, 0);
        resultField.setPadding(textPadding);
        layout.add(resultField, 0, 0, 4, 1);

        addButton = new Button("+");
        subButton = new Button("-");
        mulButton = new Button("*");
        divButton = new Button("/");
        eqButton = new Button("=");
        decButton = new Button(".");
        negButton = new Button("(-)");
        delButton = new Button("DEL");
        clrButton = new Button("CLR");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = eqButton;
        functionButtons[5] = decButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton; 
 
        for(int i = 0; i < 9; i++)
        {
            functionButtons[i].setOnAction(this);
            functionButtons[i].setPrefSize(100, 95);
        }

        for(int i = 0; i < 10; i++)
        {
            numButtons[i] = new Button(String.valueOf(i));
            numButtons[i].setPrefSize(100, 95);
            numButtons[i].setOnAction(this);
        }

        //Add buttons to the GridPane
        clrButton.setPrefSize(200, 95);
        layout.add(clrButton, 0, 1, 4, 1);
        layout.add(numButtons[7], 0, 2);
        layout.add(numButtons[4], 0, 3);
        layout.add(numButtons[1], 0, 4);
        layout.add(numButtons[0], 0, 5);
        layout.add(numButtons[8], 1, 2);
        layout.add(numButtons[5], 1, 3);
        layout.add(numButtons[2], 1, 4);
        layout.add(decButton, 1, 5);
        layout.add(delButton, 2, 1);
        layout.add(numButtons[9], 2, 2);
        layout.add(numButtons[6], 2, 3);
        layout.add(numButtons[3], 2, 4);
        layout.add(negButton, 2, 5); 
        layout.add(divButton, 3, 1);
        layout.add(mulButton, 3, 2);
        layout.add(subButton, 3, 3);
        layout.add(addButton, 3, 4);
        layout.add(eqButton, 3, 5);

        homeButton = new Button("Home");
        homeButton.setPrefSize(200, 50);
        layout.add(homeButton, 1, 6, 2, 1);

        //Goes back to Home screen
        homeButton.setOnAction(e -> App.mainDisplay(primaryStage, homeScreen));

    }

    @Override
    public void handle(ActionEvent event){
        for(int i = 0; i < 10; i++){
            if(event.getSource() == numButtons[i])
            {
                resultField.setText(resultField.getText().concat(String.valueOf(i)));
            }
        }

        if(event.getSource() == addButton)
        {
            input1 = Double.parseDouble(resultField.getText());
            operator = '+';
            resultField.setText("");
        }

        if(event.getSource() == decButton)
        {
            resultField.setText(resultField.getText().concat("."));
        }

        if(event.getSource() == negButton)
        {
            double negative = Double.parseDouble(resultField.getText());
            negative = negative * -1;
            resultField.setText(String.valueOf(negative));
        }

        if(event.getSource() == subButton)
        {
            input1 = Double.parseDouble(resultField.getText());
            operator = '-';
            resultField.setText("");
        }

        if(event.getSource() == mulButton)
        {
            input1 = Double.parseDouble(resultField.getText());
            operator = '*';
            resultField.setText("");
        }

        if(event.getSource() == divButton)
        {
            input1 = Double.parseDouble(resultField.getText());
            operator = '/';
            resultField.setText("");
        }

        if(event.getSource() == eqButton)
        {
            input2 = Double.parseDouble(resultField.getText());

            switch(operator){
                case'+':
                        result = input1 + input2;
                        break;
                case'-':
                        result = input1 - input2;
                        break;
                case'*':
                        result = input1 * input2;
                        break;
                case'/':
                        result = input1 / input2;
                        break;
            }
            resultField.setText(String.valueOf(result));
            input1 = result;
        }

        if(event.getSource() == clrButton)
        {
            resultField.setText("");
        }

        if(event.getSource() == delButton)
        {
            String currentText = resultField.getText();
            resultField.setText("");
            for(int i = 0; i < currentText.length() - 1; i++){
                resultField.setText(resultField.getText()+currentText.charAt(i));
            }
        }

    }
}