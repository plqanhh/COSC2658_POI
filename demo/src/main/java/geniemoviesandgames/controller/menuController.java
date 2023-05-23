package geniemoviesandgames.controller;

import geniemoviesandgames.Switchingscence;
import geniemoviesandgames.backend.display;
import geniemoviesandgames.model.returnCheck;
import geniemoviesandgames.model.product.item;
import geniemoviesandgames.model.user.account;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class menuController extends Switchingscence {
    protected static account mainAcc;

    public static void setMainAcc(account acc) {
        mainAcc = acc;
    }

    @FXML private Pane downPane;
    @FXML private Text welcomeText;
    @FXML private Text statusText;
    @FXML private TableView<item> displayItemTable = new TableView<>();
    @FXML private TableView<item> displayBorrowTable = new TableView<>();
    @FXML private TableView<item> displayRentTable = new TableView<>();
    @FXML private TableView<returnCheck> displayRentDateTable = new TableView<>();

    //display item table
    private TableColumn<item, String> idCol = new TableColumn<>("ID");
    private TableColumn<item, String> titleCol = new TableColumn<>("Title");
    private TableColumn<item, String> loanTypeCol = new TableColumn<>("Loan type");
    private TableColumn<item, Integer> stockCol = new TableColumn<>("In stock");
    private TableColumn<item, Double> feeCol = new TableColumn<>("Price");
    private TableColumn<item, String> genreCol = new TableColumn<>("Genre");
    //display borrow item
    private TableColumn<item, String> itemBorrowCol = new TableColumn<>("Item");
    private TableColumn<item, Double> dateBorrowCol = new TableColumn<>("Date");
    private TableColumn<item, String> dateReturnCol = new TableColumn<>("Status");
    private TableColumn<item, String> dateStatusCol = new TableColumn<>("Status");
    //

    final ObservableList<item> AllitemData = FXCollections.observableArrayList(display.allItem());
    final ObservableList<item> AllDVDData = FXCollections.observableArrayList(display.allDVD());
    final ObservableList<item> AllGameData = FXCollections.observableArrayList(display.allGame());
    final ObservableList<item> AllRecordData = FXCollections.observableArrayList(display.allRecord()); 

    final ObservableList<item> itemBorrowData = FXCollections.observableArrayList(mainAcc.getListOfRentals());
    final ObservableList<returnCheck> itemBorrowStatusData = FXCollections.observableArrayList();

    public void displayitem(ObservableList<item> listIn){
        displayItemTable.getColumns().clear();
        displayItemTable.setItems(listIn);
        displayItemTable.getColumns().addAll(idCol,titleCol,loanTypeCol,stockCol,feeCol,genreCol);
    }
    public void displayBorrowItem(){
        
    }
    @FXML public void displayAllItem(){
        displayitem(AllitemData);
    }
    @FXML public void displayAllDVD(){
        displayitem(AllDVDData);
    }
    @FXML public void displayAllGame(){
        displayitem(AllGameData);
    }
    @FXML public void displayAllRecord(){
        displayitem(AllRecordData);
    }
    public void initialize() {
        welcomeText.setText(mainAcc.getFullname());
        statusText.setText(mainAcc.getLevelOfServices().toString());
        // set collumn width
        idCol.setPrefWidth(70);
        titleCol.setPrefWidth(125);
        loanTypeCol.setPrefWidth(90);
        stockCol.setPrefWidth(70);
        feeCol.setPrefWidth(70);
        genreCol.setPrefWidth(90);
        //
        itemBorrowCol.setPrefWidth(90);
        dateBorrowCol.setPrefWidth(90);
        dateReturnCol.setPrefWidth(90);
        dateStatusCol.setPrefWidth(90);
        // set collumn data
        idCol.setCellValueFactory(new PropertyValueFactory<item, String>("ID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<item, String>("title"));
        loanTypeCol.setCellValueFactory(new PropertyValueFactory<item, String>("loantype"));
        stockCol.setCellValueFactory(new PropertyValueFactory<item, Integer>("stock"));
        feeCol.setCellValueFactory(new PropertyValueFactory<item, Double>("fees"));
        genreCol.setCellValueFactory(new PropertyValueFactory<item, String>("genre")); 
        displayItemTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //
        itemBorrowCol.setCellValueFactory(new PropertyValueFactory<item, String>("title"));
        //
        // display
        displayitem(AllitemData);
    }

}
