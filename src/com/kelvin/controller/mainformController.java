package com.kelvin.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class mainformController {
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtName;
    @FXML
    private ComboBox cbxCategory;
    @FXML
    private DatePicker dprExpireddate;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnUpdate;
    @FXML
    private TableView tbItem;
    @FXML
    private TableColumn colID;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colCategory;
    @FXML
    private TableColumn colExpireddate;

    @FXML
    private void saveitemAction(ActionEvent actionEvent) {
    }

    @FXML
    private void resetitemAction(ActionEvent actionEvent) {
    }

    @FXML
    private void updateitemAction(ActionEvent actionEvent) {
    }
}
