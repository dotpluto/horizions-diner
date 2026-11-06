package com.dotpluto.horizonsdiner.blocks;

import com.dotpluto.horizonsdiner.HorizonsDiner;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;

import java.util.function.Supplier;

public class CropBlock extends BlockCrops {
    Supplier<Item> seedSupplier;
    Supplier<Item> dropSupplier;

    public CropBlock(String rawId, Supplier<Item> seedSupplier, Supplier<Item> dropSupplier) {
        super();
        setBlockName(HorizonsDiner.appendModId(rawId));

        this.seedSupplier = seedSupplier;
        this.dropSupplier = dropSupplier;

        GameRegistry.registerBlock(this, rawId);
    }

    @Override
    protected Item func_149866_i()
    {
        return seedSupplier.get();
    }

    protected Item func_149865_P()
    {
        return dropSupplier.get();
    }
}
