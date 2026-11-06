package com.dotpluto.horizonsdiner.blocks;

import com.dotpluto.horizonsdiner.Items;

public class Blocks {
    //gardens
    public static BaseGarden garden;

    //cake
    public static HorizonCake cake;

    //seeds
    public static CropBlock testCrop;

    public static void Register() {
        garden = new DefaultGarden("default_garden");
        cake = new HorizonCake();
        testCrop = new CropBlock("test_crop", () -> { return Items.testSeed; }, () -> { return Items.woodChips; });
    }
}
