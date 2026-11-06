package com.dotpluto.horizonsdiner.blocks;

import com.dotpluto.horizonsdiner.DropPool;
import com.dotpluto.horizonsdiner.Items;
import cpw.mods.fml.common.registry.GameRegistry.UniqueIdentifier;


import java.util.Arrays;
import java.util.HashSet;

import static cpw.mods.fml.common.registry.GameRegistry.findUniqueIdentifierFor;

final public class DefaultGarden extends BaseGarden {

    public static HashSet<UniqueIdentifier> groundBlocks = new HashSet<>(
        Arrays.asList(
            new UniqueIdentifier("minecraft:dirt"),
            new UniqueIdentifier("minecraft:grass")
        )
    );

    public DefaultGarden(String rawId) {
        super(rawId);
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
