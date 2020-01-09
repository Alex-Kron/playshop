package com.playshop.entity;

import java.util.Objects;

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

    public Purchase(int uid, int iid, int q, float c) {
        this(uid, iid, q);
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

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase)) return false;
        Purchase purchase = (Purchase) o;
        return getUserID() == purchase.getUserID() &&
                getItemID() == purchase.getItemID() &&
                getQuantity() == purchase.getQuantity();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserID(), getItemID(), getQuantity());
    }
}
