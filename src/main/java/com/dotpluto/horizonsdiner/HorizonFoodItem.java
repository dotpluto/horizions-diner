package com.dotpluto.horizonsdiner;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

public class HorizonFoodItem extends ItemFood {

    HorizonFoodItem(int hungerRestored, float amountOfHungerAsSaturation) {
        super(hungerRestored, amountOfHungerAsSaturation, false);
    }

    public static HorizonFoodItem Create(String name, int hungerRestored, float amountOfHungerAsSaturation) {
        HorizonFoodItem item = new HorizonFoodItem(hungerRestored, amountOfHungerAsSaturation);
        item.setUnlocalizedName(name);
        GameRegistry.registerItem(item, name);
        return item;
    }
}
