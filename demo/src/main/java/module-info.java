module geniemoviesandgames {
    requires javafx.controls;
    requires javafx.fxml;

    opens geniemoviesandgames to javafx.fxml;
    exports geniemoviesandgames;
    

    opens geniemoviesandgames.controller to javafx.fxml;
    exports geniemoviesandgames.controller;

    opens geniemoviesandgames.model.product to javafx.fxml;
    exports geniemoviesandgames.model.product;

    opens geniemoviesandgames.model.user to javafx.fxml;
    exports geniemoviesandgames.model.user;

    opens geniemoviesandgames.model to javafx.fxml;
    exports geniemoviesandgames.model;

}
