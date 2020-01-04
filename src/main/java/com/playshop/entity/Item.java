package com.playshop.entity;

public class Item {
    private int quantity;
    private String name;
    private String description;
    private float cost;

    public Item(String n, String d, int q, float c) {
        quantity = q;
        name = n;
        description = d;
        cost = c;
    }
    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getCost() {
        return cost;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
