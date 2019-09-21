package com.kelvin.controller;

import com.kelvin.Main;
import com.kelvin.entity.Category;
import com.kelvin.entity.Item;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class mainformController implements Initializable {
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtName;
    @FXML
    private ComboBox<Category> cbxCategory;
    @FXML
    private DatePicker dprExpireddate;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnUpdate;
    @FXML
    private TableView<Item> tbItem;
    @FXML
    private TableColumn<Item, Integer> colID;
    @FXML
    private TableColumn<Item, String> colName;
    @FXML
    private TableColumn<Item, Category> colCategory;
    @FXML
    private TableColumn<Item, LocalDate> colExpireddate;

    private ObservableList<Category> Categories;
    private ObservableList<Item> Items;

    public ObservableList<Category> getCategories() {
        if (Categories == null) {
            Categories = FXCollections.observableArrayList();
        }
        return Categories;
    }

    public ObservableList<Item> getItems() {
        if (Items == null) {
            Items = FXCollections.observableArrayList();
        }
        return Items;
    }

    @FXML
    private void saveitemAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (!txtID.getText().trim().isEmpty() && cbxCategory.getValue() != null && !txtName.getText().trim().isEmpty() && dprExpireddate.getValue() != null) {
            int result = 0;
            for (Item _item : Items) {
                if (txtName.getText().equalsIgnoreCase(_item.getItemName()) && txtID.getText().equalsIgnoreCase(String.valueOf(_item.getItemID()))) {
                    alert.setContentText("Duplicate Item");
                    alert.showAndWait();
                    result = 1;
                    break;
                }
                if (txtName.getText().equalsIgnoreCase(_item.getItemName())) {
                    alert.setContentText("Duplicate Item Name");
                    alert.showAndWait();
                    result = 1;
                    break;
                }
                if (txtID.getText().equalsIgnoreCase(String.valueOf(_item.getItemID()))) {
                    alert.setContentText("Duplicate Item ID");
                    alert.showAndWait();
                    result = 1;
                    break;
                }
            }

            if (result == 0) {
                Item i = new Item();
                i.setItemID(Integer.parseInt(txtID.getText().trim()));
                i.setItemName(txtName.getText().trim());
                i.setItemCategory(cbxCategory.getValue());
                i.setItemExpireddate(dprExpireddate.getValue());
                Items.add(i);
                txtID.setText("");
                txtName.setText("");
                cbxCategory.setValue(null);
                dprExpireddate.setValue(null);
            }
        } else {
            alert.setContentText("Missing Fields Required");
            alert.showAndWait();
        }
    }

    @FXML
    private void resetitemAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Click Ok to delete all data");
        alert.setHeaderText("Confirm Delete");
        alert.showAndWait().ifPresent((btnType) -> {
            if (btnType == ButtonType.OK) {
                Categories.clear();
                Items.clear();
            }
        });
    }

    @FXML
    private void updateitemAction(ActionEvent actionEvent) {
        Item i = tbItem.getSelectionModel().getSelectedItem();
        i.setItemID(Integer.parseInt(txtID.getText().trim()));
        i.setItemName(txtName.getText().trim());
        i.setItemCategory(cbxCategory.getValue());
        i.setItemExpireddate(dprExpireddate.getValue());
        tbItem.refresh();
        btnReset.setDisable(false);
        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        txtID.setText("");
        txtName.setText("");
        cbxCategory.setValue(null);
        dprExpireddate.setValue(null);
        tbItem.setSelectionModel(null);
    }

    @FXML
    private void categorymanagementForm(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/categorymanagementView.fxml"));
            BorderPane root = loader.load();
            categorymanagementController controller = loader.getController();
            controller.setMainFormController(this);
            Stage mainStage = new Stage();
            mainStage.setTitle("Category Management Form");
            mainStage.setScene(new Scene(root));
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnUpdate.setDisable(true);
        cbxCategory.setItems(getCategories());
        tbItem.setItems(getItems());

        colID.setCellValueFactory(data -> {
            Item item = data.getValue();
            return new SimpleIntegerProperty(item.getItemID()).asObject();
        });
        colName.setCellValueFactory(data -> {
            Item item = data.getValue();
            return new SimpleStringProperty(item.getItemName());
        });
        colCategory.setCellValueFactory(data -> {
            Item item = data.getValue();
            return new SimpleObjectProperty<Category>(item.getItemCategory());
        });
        colExpireddate.setCellValueFactory(data -> {
            Item item = data.getValue();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return new SimpleObjectProperty<LocalDate>(item.getItemExpireddate());
        });
    }


    @FXML
    private void selectAction(MouseEvent mouseEvent) {
        if (!tbItem.getSelectionModel().getSelectedItem().getItemName().isEmpty()) {
            btnUpdate.setDisable(false);
            btnSave.setDisable(true);
            btnReset.setDisable(true);
            txtName.setText(tbItem.getSelectionModel().getSelectedItem().getItemName());
            txtID.setText(String.valueOf(tbItem.getSelectionModel().getSelectedItem().getItemID()));
            cbxCategory.setValue(tbItem.getSelectionModel().getSelectedItem().getItemCategory());
            dprExpireddate.setValue(tbItem.getSelectionModel().getSelectedItem().getItemExpireddate());
        }
    }
}
