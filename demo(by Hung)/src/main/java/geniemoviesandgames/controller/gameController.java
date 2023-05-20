package geniemoviesandgames.controller;

import geniemoviesandgames.Switchingscene;
import geniemoviesandgames.model.game;
import geniemoviesandgames.model.item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Cell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import geniemoviesandgames.backend.mainSystem;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import geniemoviesandgames.App;

import static geniemoviesandgames.backend.mainSystem.*;


public class gameController extends Switchingscene implements Initializable {


    @FXML
    private AnchorPane menuHomePane;
    @FXML
    private AnchorPane customerPane;
    @FXML
    private AnchorPane adminEditBar;
    @FXML
    private TableView<game> gameTableView;
    @FXML
    private TableColumn<game, String> idColumn;
    @FXML
    private TableColumn<game, String> titleColumn;
    @FXML
    private TableColumn<game, Integer> copyColumn;
    @FXML
    private TableColumn<game, Double> feeColumn;
    @FXML
    private TableColumn<game, Boolean> typeColumn;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField titleTextField;
    @FXML
    private TextField typeTextField;
    @FXML
    private TextField copyTextField;
    @FXML
    private TextField feeTextField;

//    @FXML
//            private TableColumn<game,Boolean> typeColumn;

//    ObservableList<item> listOfGame = FXCollections.observableArrayList(
//          game gm = new game("C01", "l4d", true ,1, 10.5);
//            new item(idTextField.getText(), titleTextField.getText(), Boolean.valueOf(statusTextField.getText()), Integer.parseInt(copyTextField.getText()) , Double.parseDouble(feeTextField.getText()))
//    );

    @FXML
    protected void addGame(){
        game gm = new game(idTextField.getText(), titleTextField.getText(), Boolean.valueOf(typeTextField.getText()), Integer.parseInt(copyTextField.getText()), Double.parseDouble(feeTextField.getText()));
        ObservableList<game> gameItems = gameTableView.getItems();
        gameItems.add(gm);
        gameTableView.setItems(gameItems);
        mainSystem.addListOfItems(gm);
        mainSystem.addItemToTextFile(gm);

        idTextField.clear();
        titleTextField.clear();
        typeTextField.clear();
        copyTextField.clear();
        feeTextField.clear();
    }
    @FXML
    protected void rentGameItem(){

        item rentItem =  gameTableView.getSelectionModel().getSelectedItem();
        int row = gameTableView.getSelectionModel().getSelectedIndex();
        item outOfStockItem =  gameTableView.getSelectionModel().getSelectedItem();

            gameTableView.getItems().get(row).setItemCopies(rentItem.getItemCopies() - 1);


            if (rentItem.getItemCopies() == 0) {
//                //remove item from buying list when stock equals 0
                outOfStockListOfItem.add(rentItem);

                removeGame();


            }

            gameTableView.refresh();

    }
    @FXML
    protected void updateGameItem(){

    }
    @FXML
    protected void removeGame(){
//        int selectedGame = gameTableView.getSelectionModel().getSelectedIndex();
        item gameItem =  gameTableView.getSelectionModel().getSelectedItem();
        listOfItems.remove(gameItem);

        loadSavedGameItemToTable();
//        gameTableView.getItems().remove(selectedGame);
//        listOfItems.remove();
    }

    protected void loadSavedGameItemToTable(){
        ObservableList<game> gameItems = gameTableView.getItems();
        gameItems.clear();
        for (item itm : listOfItems){
            if (itm instanceof game){
                gameItems.add((game) itm);
            }

        }
        gameTableView.setItems(gameItems);
        gameTableView.refresh();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadSavedGameItemToTable();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/geniemoviesandgames/screen_design/menuBar.fxml"));
        FXMLLoader adminMenu = new FXMLLoader(getClass().getResource("/geniemoviesandgames/screen_design/adminMenuBar.fxml"));
//        FXMLLoader edit = new FXMLLoader(getClass().getResource("/geniemoviesandgames/screen_design/editBar.fxml"));

        idColumn.setCellValueFactory(new PropertyValueFactory<game, String>("itemID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<game,String>("itemTitle"));
        copyColumn.setCellValueFactory(new PropertyValueFactory<game,Integer>("itemCopies"));
        feeColumn.setCellValueFactory(new PropertyValueFactory<game,Double>("itemFees"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<game, Boolean>("item2DayLoan"));

//        gameTableView.setItems(listOfGame);

        try {
            if(!App.isRole) {
                AnchorPane menuPane = loader.load();
                menuHomePane.getChildren().add(menuPane);
                adminEditBar.setVisible(false);

            } else {
                AnchorPane adminMenuPane = adminMenu.load();
                menuHomePane.getChildren().add(adminMenuPane);
                customerPane.setVisible(false);
            }
//            if (App.isRole) {
//                AnchorPane editBar = edit.load();
//                adminEditBar.getChildren().add(editBar);
//            }

        } catch (IOException err) {
            err.printStackTrace();
        }
    }


}
