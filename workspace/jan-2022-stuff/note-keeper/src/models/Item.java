package models;

import utils.Utilities;

import java.util.Objects;

public class Item {
    private String itemDescription = "No Description";
    private boolean isItemCompleted = false;

    public Item(String itemDescription) {
        this.itemDescription = Utilities.truncateString(itemDescription, 50);
    }

    public Item(String itemDescription, boolean completed) {
        this.itemDescription = Utilities.truncateString(itemDescription, 50);
        setItemCompleted(completed);
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        if (Utilities.validStringlength(itemDescription, 50)) {
            this.itemDescription = itemDescription;
        }
    }

    public boolean isItemCompleted() {
        return this.isItemCompleted;
    }

    public void setItemCompleted(boolean itemCompleted) {
        this.isItemCompleted = itemCompleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return isItemCompleted == item.isItemCompleted && Objects.equals(itemDescription, item.itemDescription);
    }

    @Override
    public String toString() {
        return  itemDescription + ". [" + (isItemCompleted ? "Completed" : "TODO") + "]";
    }
}
