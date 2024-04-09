import java.io.IOException;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class App extends Application implements EventHandler<ActionEvent>{
    private TableView<Assignment> table = new TableView<Assignment>(); //Table to store assignments

    //User input variables
    private TextField enterName;
    private DatePicker chooseDate;

    //Buttons
    private Button add;
    private Button hide;
    private Button homeButton;
    
    final ObservableList<Assignment> data = FXCollections.observableArrayList(
      new Assignment("Course Project", LocalDate.parse("2024-04-10"))); //Populating the table

    @Override
    public void start(Stage primaryStage) throws IOException{
    
    //Creating columns and associating data with each column
    TableColumn<Assignment, String> nameCol = new TableColumn<>("Assignment");
    TableColumn<Assignment, LocalDate> dateCol = new TableColumn<>("Due Date");
    TableColumn<Assignment, Boolean> checkCol = new TableColumn<>("Done");

    nameCol.setCellValueFactory(new PropertyValueFactory<Assignment, String>("name"));
    dateCol.setCellValueFactory(new PropertyValueFactory<Assignment, LocalDate>("date"));
    checkCol.setCellFactory(CheckBoxTableCell.forTableColumn(checkCol));
    checkCol.setCellValueFactory(cd -> cd.getValue().getSelected());

    table.getColumns().addAll(nameCol, dateCol, checkCol);
    table.setItems(data);    

    table.setEditable(true);
    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
    dateCol.setSortType(TableColumn.SortType.ASCENDING);
    
    //HBox to house user input fields
    HBox hbox = new HBox();
    hbox.setSpacing(10);
    hbox.setPadding(new Insets(10));

    //Creating the user input fields like the name and date plus the buttons and adding them to HBox
    enterName = new TextField();
    chooseDate = new DatePicker();
    hide = new Button("Hide finished assignments");
    hide.setOnAction(this); 
    add = new Button("Add");
    add.setOnAction(this);
    hbox.getChildren().addAll(enterName, chooseDate, add, hide);

    //VBox to house the home button
    VBox vbox = new VBox();
    vbox.setPadding(new Insets(10));
    vbox.setAlignment(Pos.BOTTOM_CENTER);

    homeButton = new Button("Home");
    vbox.getChildren().addAll(hbox, homeButton);
    
    //BorderPane to house the HBox and VBox
    BorderPane bp = new BorderPane();
    bp.setCenter(table);
    bp.setBottom(vbox);

    //Adding all of these elements to the main stage
    Scene scene = new Scene(bp, 600, 400);
    primaryStage.setTitle("Assignment Calendar");
    primaryStage.setScene(scene);
    primaryStage.show();
    }

    public class Assignment { //Creating the data model
        private SimpleStringProperty assignmentName;
        private LocalDate dueDate;
        private BooleanProperty checkBox;
    
        Assignment(String name, LocalDate date){ //Constructor class
            this.assignmentName = new SimpleStringProperty(name);
            this.dueDate = date;
            this.checkBox = new SimpleBooleanProperty(false);
        }

    //Each property has a getter and setter
        public String getName(){
            return assignmentName.get();
        }
    
        public void setName(String name){
            this.assignmentName = new SimpleStringProperty(name);
        }
    
        public LocalDate getDate(){
            return dueDate;
        }
    
        public void setDate(LocalDate date){
            this.dueDate = date;
        }

        public BooleanProperty getSelected(){
            return checkBox;
        }
    }
    
    @Override
    public void handle(ActionEvent event){

        //Adding assignments
        if(event.getSource() == add){ 
        data.add(new Assignment(enterName.getText(), chooseDate.getValue())); //Add the assignment by getting the name from the text field and date from the date picker
        enterName.clear();
        chooseDate.setValue(null);
        }
        
        //Hiding finished assignments
        if(event.getSource() == hide){
            ObservableList<Assignment> toHide = FXCollections.observableArrayList(); //Creates a list of assignments that will be hidden
            for(Assignment row : data){ //For each row, if the checkbox returns true add that row to the toHide list
                if(row.getSelected().getValue() == true){
                    toHide.add(row);
                }
            }
            data.removeAll(toHide); //Hide the rows in the toHide list
        }

        if(event.getSource() == homeButton){
            //home button code goes here
        }
    }

    public static void main(String[] args) throws Exception {
        launch(args);
        
    }

}