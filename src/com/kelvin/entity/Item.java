package com.kelvin.entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Item {
    private final SimpleIntegerProperty ItemID = new SimpleIntegerProperty();
    private final SimpleStringProperty ItemName = new SimpleStringProperty();
    private final SimpleObjectProperty<Category> ItemCategory = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<Date> ItemExpireddate = new SimpleObjectProperty<>();

    public int getItemID() {
        return ItemID.get();
    }

    public void setItemID(int itemID) {
        this.ItemID.set(itemID);
    }

    public SimpleIntegerProperty itemIDProperty() {
        return ItemID;
    }

    public String getItemName() {
        return ItemName.get();
    }

    public void setItemName(String itemName) {
        this.ItemName.set(itemName);
    }

    public SimpleStringProperty itemNameProperty() {
        return ItemName;
    }

    public Category getItemCategory() {
        return ItemCategory.get();
    }

    public void setItemCategory(Category itemCategory) {
        this.ItemCategory.set(itemCategory);
    }

    public SimpleObjectProperty<Category> itemCategoryProperty() {
        return ItemCategory;
    }

    public Date getItemExpireddate() {
        return ItemExpireddate.get();
    }

    public void setItemExpireddate(Date itemExpireddate) {
        this.ItemExpireddate.set(itemExpireddate);
    }

    public SimpleObjectProperty<Date> itemExpireddateProperty() {
        return ItemExpireddate;
    }
}
