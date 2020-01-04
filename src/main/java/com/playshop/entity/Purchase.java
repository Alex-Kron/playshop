package com.playshop.entity;

public class Purchase {
    private int userID;
    private int itemID;
    private int quantity;
    private float cost;

    public Purchase(int uid, int iid, int q) {
        userID = uid;
        itemID = iid;
        quantity = q;
    }

    public int getUserID() {
        return userID;
    }

    public int getItemID() {
        return itemID;
    }

    public int getQuantity() {
        return quantity;
    }
}
