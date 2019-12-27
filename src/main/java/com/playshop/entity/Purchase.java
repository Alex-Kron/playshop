package com.playshop.entity;

public class Purchase {
    private int userID;
    private int itemID;
    private int quantity;
    private float cost;

    public Purchase(int uid, int iid, int q, float c) {
        userID = uid;
        itemID = iid;
        quantity = q;
        cost = c;
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

    public float getCost() {
        return cost;
    }
}
