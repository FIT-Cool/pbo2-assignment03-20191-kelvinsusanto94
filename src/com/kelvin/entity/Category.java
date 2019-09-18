package com.kelvin.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Category {
    private final IntegerProperty catID = new SimpleIntegerProperty();
    private final StringProperty catName = new SimpleStringProperty();

    public String getCatName() {
        return catName.get();
    }

    public void setCatName(String value) {
        this.catName.set(value);
    }

    public StringProperty catNameProperty() {
        return catName;
    }

    public int getCatID() {
        return catID.get();
    }

    public void setCatID(int catID) {
        this.catID.set(catID);
    }

    public IntegerProperty catIDProperty() {
        return catID;
    }

    @Override
    public String toString(){
        return getCatName();
    }
}
