module se2023.chapter1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens se2023.chapter1 to javafx.fxml;
    exports se2023.chapter1;
}