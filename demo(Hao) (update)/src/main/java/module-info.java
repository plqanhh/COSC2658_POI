module geniemoviesandgames {
    requires javafx.controls;
    requires javafx.fxml;

    opens geniemoviesandgames to javafx.fxml;
    exports geniemoviesandgames;
    

    opens geniemoviesandgames.controller to javafx.fxml;
    exports geniemoviesandgames.controller;

    opens geniemoviesandgames.model.item to javafx.fxml;
    exports geniemoviesandgames.model.item;

    opens geniemoviesandgames.model.account to javafx.fxml;
    exports geniemoviesandgames.model.account;
    exports geniemoviesandgames.model;
    opens geniemoviesandgames.model to javafx.fxml;


}
