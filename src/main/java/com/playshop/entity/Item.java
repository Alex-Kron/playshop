package com.playshop.entity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return getQuantity() == item.getQuantity() &&
                Float.compare(item.getCost(), getCost()) == 0 &&
                getName().equals(item.getName()) &&
                getDescription().equals(item.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuantity(), getName(), getDescription(), getCost());
    }
}
