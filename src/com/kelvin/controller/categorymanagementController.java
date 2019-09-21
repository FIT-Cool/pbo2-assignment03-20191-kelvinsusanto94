package com.kelvin.controller;

import com.kelvin.entity.Category;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class categorymanagementController implements Initializable {
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtName;
    @FXML
    private Button btnSave;
    @FXML
    private TableView<Category> tbCategory;
    @FXML
    private TableColumn<Category, Integer> colID;
    @FXML
    private TableColumn<Category, String> colName;
    private mainformController mainController;

    @FXML
    private void savecategoryAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        if (!txtID.getText().isEmpty() && !txtName.getText().isEmpty()) {
            int result = 0;
            for (Category _cat : mainController.getCategories()) {
                if (txtName.getText().equalsIgnoreCase(_cat.getCatName()) && txtID.getText().equalsIgnoreCase(String.valueOf(_cat.getCatID()))) {
                    alert.setContentText("Duplicate Category ID & Name");
                    alert.showAndWait();
                    result = 1;
                    break;
                }
                if (txtName.getText().equalsIgnoreCase(_cat.getCatName())) {
                    alert.setContentText("Duplicate Category Name");
                    alert.showAndWait();
                    result = 1;
                    break;
                }
                if (txtID.getText().equalsIgnoreCase(String.valueOf(_cat.getCatID()))) {
                    alert.setContentText("Duplicate Category ID");
                    alert.showAndWait();
                    result = 1;
                    break;
                }
            }

            if (result == 0) {
                Category c = new Category();
                c.setCatID(Integer.parseInt(txtID.getText().trim()));
                c.setCatName(txtName.getText().trim());
                mainController.getCategories().add(c);
                txtID.setText("");
                txtName.setText("");
            }
        } else {
            alert.setContentText("Missing Fields Required");
            alert.showAndWait();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colID.setCellValueFactory(data -> {
            Category cat = data.getValue();
            return new SimpleIntegerProperty(cat.getCatID()).asObject();
        });
        colName.setCellValueFactory(data -> {
            Category cat = data.getValue();
            return new SimpleStringProperty(cat.getCatName());
        });
    }

    public void setMainFormController(mainformController mainformController) {
        this.mainController = mainformController;
        tbCategory.setItems(mainformController.getCategories());
    }
}