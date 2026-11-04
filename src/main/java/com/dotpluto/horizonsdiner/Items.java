package com.dotpluto.horizonsdiner;

public class Items {
    public static HorizonFoodItem test;

    public static void Register() {
        test = HorizonFoodItem.Create("testitem", 5, 1.0f);
    }
}
