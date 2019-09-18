package com.kelvin.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class categorymanagementController {
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtName;
    @FXML
    private Button btnSave;
    @FXML
    private TableView tbCategory;
    @FXML
    private TableColumn colID;
    @FXML
    private TableColumn colName;

    @FXML
    private void savecategoryAction(ActionEvent actionEvent) {
    }
}
