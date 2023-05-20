package geniemoviesandgames.controller;

import geniemoviesandgames.Switchingscene;
import geniemoviesandgames.model.DVD;
import geniemoviesandgames.model.game;
import geniemoviesandgames.model.item;
import geniemoviesandgames.model.movies;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

import static geniemoviesandgames.backend.mainSystem.listOfItems;
import static geniemoviesandgames.backend.mainSystem.outOfStockListOfItem;

public class outOfStockController extends Switchingscene implements Initializable {

    @FXML
    private AnchorPane menuHomePane;
    @FXML
    private TableView<item> outOfStockTableView;
    @FXML
    private TableColumn<item, String> idColumn;
    @FXML
    private TableColumn<item, String> titleColumn;
    @FXML
    private TableColumn<item, Integer> copyColumn;
    @FXML
    private TableColumn<item, Double> feeColumn;
    @FXML
    private TableColumn<item, Boolean> typeColumn;
    @FXML
    private TableColumn<item,String> itemTypeColumn;
    @FXML
    private TextField updateTextField;
//    @FXML
//    private TableColumn<DVD,String> genreColumn;



    protected void loadOutOfStockItemToTableView(){
        ObservableList<item> outOfStockItem = outOfStockTableView.getItems();
        outOfStockItem.clear();
        outOfStockItem.setAll(outOfStockListOfItem);
        outOfStockTableView.setItems(outOfStockItem);
        outOfStockTableView.refresh();
//
    }
    @FXML
    protected void updateStock(){
        item itm = outOfStockTableView.getSelectionModel().getSelectedItem();
        int row = outOfStockTableView.getSelectionModel().getSelectedIndex();

        outOfStockTableView.getItems().get(row).setItemCopies(Integer.parseInt(updateTextField.getText()));
        updateTextField.clear();
        outOfStockListOfItem.remove(itm);
        listOfItems.add(itm);
        loadOutOfStockItemToTableView();
        outOfStockTableView.refresh();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadOutOfStockItemToTableView();
        FXMLLoader adminMenu = new FXMLLoader(getClass().getResource("/geniemoviesandgames/screen_design/adminMenuBar.fxml"));
        idColumn.setCellValueFactory(new PropertyValueFactory<item, String>("itemID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<item,String>("itemTitle"));
        copyColumn.setCellValueFactory(new PropertyValueFactory<item,Integer>("itemCopies"));
        feeColumn.setCellValueFactory(new PropertyValueFactory<item,Double>("itemFees"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<item, Boolean>("item2DayLoan"));
//        genreColumn.setCellValueFactory(new PropertyValueFactory<DVD,String>("itemGenre"));
        try {
            AnchorPane adminMenuPane = adminMenu.load();
            menuHomePane.getChildren().add(adminMenuPane);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
