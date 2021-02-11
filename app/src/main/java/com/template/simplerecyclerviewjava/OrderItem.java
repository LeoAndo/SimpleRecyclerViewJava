package com.template.simplerecyclerviewjava;

import androidx.annotation.DrawableRes;

public class OrderItem {
    @DrawableRes
    private int imageResource;
    private String title;
    private int price;
    private int amount;

    public OrderItem(int imageResource, String title, int price, int amount) {
        this.imageResource = imageResource;
        this.title = title;
        this.price = price;
        this.amount = amount;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
