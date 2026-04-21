package com.ui_autotests.pages;

import java.util.ArrayList;
import java.util.List;

public class CartState {
    private static CartState instance;
    private List<String> availableProductTitles = new ArrayList<>();

    private CartState() {}

    public static CartState getInstance() {
        if (instance == null) {
            instance = new CartState();
        }
        return instance;
    }

    public List<String> getAvailableProductTitles() {
        return availableProductTitles;
    }

    public void setAvailableProductTitles(List<String> titles) {
        this.availableProductTitles = titles;
    }
}