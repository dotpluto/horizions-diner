package com.dotpluto.horizonsdiner;

import com.dotpluto.horizonsdiner.garden.BaseGarden;
import com.dotpluto.horizonsdiner.garden.DefaultGarden;

public class Blocks {
    //gardens
    public static BaseGarden garden;

    public static void Register() {
        garden = new DefaultGarden("default_garden");
    }
}
