package com.aldricklevina.hadir.Model;

public class Search {
    private String itemId, itemName, itemType;

    public Search(String _itemId, String _itemName, String _itemType) {
        this.itemId = _itemId;
        this.itemName = _itemName;
        this.itemType = _itemType;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
}
