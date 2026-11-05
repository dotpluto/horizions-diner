package com.dotpluto.horizonsdiner;

import net.minecraft.item.Item;

import java.util.Random;

public class DropPool {
    Item[] items;
    public DropPool(Item[] items) {
        if(items.length < 1) {
            throw new RuntimeException("DropPool is empty.");
        }
        this.items = items;
    }

    public Item getRandomItem(Random random) {
        return items[random.nextInt(items.length)];
    }
}
