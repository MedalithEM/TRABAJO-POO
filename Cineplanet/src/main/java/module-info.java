module cineplanet {
    requires javafx.controls;
    requires javafx.fxml;

    exports upeu.edu.pe.cineplanet;
    opens upeu.edu.pe.cineplanet to javafx.fxml;
    exports cineplanet;
    opens cineplanet to javafx.fxml;
}