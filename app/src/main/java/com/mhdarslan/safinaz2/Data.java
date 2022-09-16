package com.mhdarslan.safinaz2;

public class Data {
    private String category;
    private String product;
    private int price;

    public Data(String category, String product, int price) {
        this.category = category;
        this.product = product;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
