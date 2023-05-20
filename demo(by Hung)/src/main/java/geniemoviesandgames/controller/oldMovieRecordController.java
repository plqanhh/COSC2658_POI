package geniemoviesandgames.controller;

import geniemoviesandgames.App;
import geniemoviesandgames.Switchingscene;
import geniemoviesandgames.backend.mainSystem;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static geniemoviesandgames.backend.mainSystem.listOfItems;
import static geniemoviesandgames.backend.mainSystem.outOfStockListOfItem;

public class oldMovieRecordController extends Switchingscene implements Initializable {
    @FXML
    private AnchorPane menuHomePane;
    @FXML
    private AnchorPane adminEditBar;
    @FXML
    private TableView<movies> movieTableView;
    @FXML
    private TableColumn<movies, String> idColumn;
    @FXML
    private TableColumn<movies, String> titleColumn;
    @FXML
    private TableColumn<movies, Integer> copyColumn;
    @FXML
    private TableColumn<movies, Double> feeColumn;
    @FXML
    private TableColumn<movies, Boolean> typeColumn;
    @FXML
    private TableColumn<movies, String> genreColumn;
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
    private AnchorPane customerPane;
    @FXML
    protected void addMovie(){
        movies movie = new movies(idTextField.getText(), titleTextField.getText(), Boolean.valueOf(typeTextField.getText()), Integer.parseInt(copyTextField.getText()), Double.parseDouble(feeTextField.getText()), genreTextField.getText());
        ObservableList<movies> movieItem = movieTableView.getItems();
        movieItem.add(movie);
//movies ds = new movies("f", "f", true, 2, 2.5, "s");
        movieTableView.setItems(movieItem);
        mainSystem.addListOfItems(movie);
        mainSystem.addItemToTextFile(movie);

        idTextField.clear();
        titleTextField.clear();
        typeTextField.clear();
        copyTextField.clear();
        feeTextField.clear();
        genreTextField.clear();
    }
    @FXML
    protected void rentMovie(){
        item rentItem =  movieTableView.getSelectionModel().getSelectedItem();
        int row = movieTableView.getSelectionModel().getSelectedIndex();


        movieTableView.getItems().get(row).setItemCopies(rentItem.getItemCopies() - 1);


        if (rentItem.getItemCopies() == 0) {
            //remove item from buying list when stock equals 0
            outOfStockListOfItem.add(rentItem);

            removeMovie();


        }

        movieTableView.refresh();
    }

    @FXML
    protected void removeMovie(){
        item movieItem =  movieTableView.getSelectionModel().getSelectedItem();
        listOfItems.remove(movieItem);

        loadSaveMovieItem();
    }
    protected void loadSaveMovieItem(){
        ObservableList<movies> movieItem = movieTableView.getItems();
        movieItem.clear();
        for (item itm : listOfItems){
            if (itm instanceof movies){;
                movieItem.add((movies) itm);
            }

        }
        movieTableView.setItems(movieItem);
        movieTableView.refresh();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadSaveMovieItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/geniemoviesandgames/screen_design/menuBar.fxml"));
        FXMLLoader adminMenu = new FXMLLoader(getClass().getResource("/geniemoviesandgames/screen_design/adminMenuBar.fxml"));
//        FXMLLoader edit = new FXMLLoader(getClass().getResource("/geniemoviesandgames/screen_design/editBarForMovieAndDVD.fxml"));

        idColumn.setCellValueFactory(new PropertyValueFactory<movies, String>("itemID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<movies,String>("itemTitle"));
        copyColumn.setCellValueFactory(new PropertyValueFactory<movies,Integer>("itemCopies"));
        feeColumn.setCellValueFactory(new PropertyValueFactory<movies,Double>("itemFees"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<movies, Boolean>("item2DayLoan"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<movies, String>("itemGenre"));


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
//
//            }

        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
