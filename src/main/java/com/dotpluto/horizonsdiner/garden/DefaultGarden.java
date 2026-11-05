package com.dotpluto.horizonsdiner.garden;

import com.dotpluto.horizonsdiner.DropPool;
import com.dotpluto.horizonsdiner.HorizonsDiner;
import com.dotpluto.horizonsdiner.Items;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.UniqueIdentifier;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static cpw.mods.fml.common.registry.GameRegistry.findUniqueIdentifierFor;

final public class DefaultGarden extends BaseGarden {

    public static HashSet<UniqueIdentifier> groundBlocks = new HashSet<>(
        Arrays.asList(
            new UniqueIdentifier("minecraft:dirt"),
            new UniqueIdentifier("minecraft:grass")
        )
    );

    public DefaultGarden(String id) {
        super(id);
    }

    @Override
    public HashSet<UniqueIdentifier> getGroundBlocks() {
        return groundBlocks;
    }

    @Override
    DropPool getDropPool() {
        return Items.defaultGardenDrops;
    }
}
