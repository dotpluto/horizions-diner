package com.dotpluto.horizonsdiner;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockCake;

public class HorizonCake extends BlockCake {
    public HorizonCake() {
        super();
        GameRegistry.registerBlock(this, "cake");
    }

}
