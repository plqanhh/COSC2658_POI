package geniemoviesandgames.controller;

import geniemoviesandgames.App;
import geniemoviesandgames.Switchingscene;
import geniemoviesandgames.backend.mainSystem;
import geniemoviesandgames.model.DVD;
import geniemoviesandgames.model.game;
import geniemoviesandgames.model.item;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static geniemoviesandgames.backend.mainSystem.listOfItems;
import static geniemoviesandgames.backend.mainSystem.outOfStockListOfItem;

public class DVDController extends Switchingscene implements Initializable {
    @FXML
    private AnchorPane menuHomePane;
    @FXML
    private AnchorPane adminEditBar;
    @FXML
    private AnchorPane customerPane;
    @FXML
    private TableView<DVD> DVDTableView;
    @FXML
    private TableColumn<DVD, String> idColumn;
    @FXML
    private TableColumn<DVD, String> titleColumn;
    @FXML
    private TableColumn<DVD, Integer> copyColumn;
    @FXML
    private TableColumn<DVD, Double> feeColumn;
    @FXML
    private TableColumn<DVD, Boolean> typeColumn;
    @FXML
    private TableColumn<DVD, String> genreColumn;
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
    @FXML
    private TextField genreTextField;
    @FXML
    private TextField searchDVDTextField;
    @FXML
    protected void addDVD(){
        DVD dvd = new DVD(idTextField.getText(), titleTextField.getText(), Boolean.valueOf(typeTextField.getText()), Integer.parseInt(copyTextField.getText()), Double.parseDouble(feeTextField.getText()), genreTextField.getText());
        ObservableList<DVD> dvdItems = DVDTableView.getItems();
        dvdItems.add(dvd);
//DVD ds = new DVD("f", "f", true, 2, 2.5, "s");
        DVDTableView.setItems(dvdItems);
        mainSystem.addListOfItems(dvd);
        mainSystem.addItemToTextFile(dvd);

        idTextField.clear();
        titleTextField.clear();
        typeTextField.clear();
        copyTextField.clear();
        feeTextField.clear();
        genreTextField.clear();
    }
    @FXML
    protected void showTextOnTextField(MouseEvent e){
        DVD selectedItem = DVDTableView.getSelectionModel().getSelectedItem();
        idTextField.setText(selectedItem.getItemID());
        titleTextField.setText(selectedItem.getItemTitle());
        typeTextField.setText(String.valueOf(selectedItem.isItem2DayLoan()));
        copyTextField.setText(String.valueOf(selectedItem.getItemCopies()));
        feeTextField.setText(String.valueOf(selectedItem.getItemFees()));
        genreTextField.setText(( selectedItem).getItemGenre());
    }
    @FXML
    protected void rentDVD(){
        item rentItem =  DVDTableView.getSelectionModel().getSelectedItem();
        int row = DVDTableView.getSelectionModel().getSelectedIndex();


        DVDTableView.getItems().get(row).setItemCopies(rentItem.getItemCopies() - 1);


        if (rentItem.getItemCopies() == 0) {
            //remove item from buying list when stock equals 0
            outOfStockListOfItem.add(rentItem);

            removeDVD();


        }

        DVDTableView.refresh();
    }
    @FXML
    protected void removeDVD(){
        item dvdItem =  DVDTableView.getSelectionModel().getSelectedItem();


        listOfItems.remove(dvdItem);
        loadSavedDVDItemToTable();
    }
protected void loadSavedDVDItemToTable(){
    ObservableList<DVD> dvdItem = DVDTableView.getItems();
    dvdItem.clear();
    for (item itm : listOfItems){
        if (itm instanceof DVD){
            dvdItem.add((DVD) itm);
        }

    }
    DVDTableView.setItems(dvdItem);
    DVDTableView.refresh();
}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadSavedDVDItemToTable();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/geniemoviesandgames/screen_design/menuBar.fxml"));
        FXMLLoader adminMenu = new FXMLLoader(getClass().getResource("/geniemoviesandgames/screen_design/adminMenuBar.fxml"));
//        FXMLLoader edit = new FXMLLoader(getClass().getResource("/geniemoviesandgames/screen_design/editBarForMovieAndDVD.fxml"));

        idColumn.setCellValueFactory(new PropertyValueFactory<DVD, String>("itemID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<DVD,String>("itemTitle"));
        copyColumn.setCellValueFactory(new PropertyValueFactory<DVD,Integer>("itemCopies"));
        feeColumn.setCellValueFactory(new PropertyValueFactory<DVD,Double>("itemFees"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<DVD, Boolean>("item2DayLoan"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<DVD, String>("itemGenre"));

        ObservableList<DVD> dvdItems = DVDTableView.getItems();
        FilteredList<DVD> filteredGame = new FilteredList<>(dvdItems, b->true);
        searchDVDTextField.textProperty().addListener((observable,oldValue,newValue) ->{
            filteredGame.setPredicate(game -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (game.getItemTitle().toLowerCase().contains(lowerCaseFilter)) {
                    DVDTableView.setItems(filteredGame);
                    return true;
                } else if (game.getItemID().toLowerCase().contains(lowerCaseFilter)){
                    DVDTableView.setItems(filteredGame);
                    return true;
                }
                else return false;
            });
        });


        try {
            if(!App.isRole) {
                AnchorPane menuPane = loader.load();
                menuHomePane.getChildren().add(menuPane);
                adminEditBar.setVisible(false);
                customerPane.setVisible(true);

            } else {
                AnchorPane adminMenuPane = adminMenu.load();
                menuHomePane.getChildren().add(adminMenuPane);
                customerPane.setVisible(false);

            }
//            if (App.isRole) {
//                AnchorPane editBar = edit.load();
//                adminEditBar.getChildren().add(editBar);
//
//            }

        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
