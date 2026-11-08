package com.dotpluto.horizonsdiner;

import com.dotpluto.horizonsdiner.blocks.Candle;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

import java.util.List;

public class CandleItem extends ItemBlockWithMetadata {

    public CandleItem(Block blockToPlace) {
        super(blockToPlace, blockToPlace);
        setHasSubtypes(true);
        GameRegistry.registerItem(this, "candle");
    }

    @Override
    public boolean getHasSubtypes() {
        return super.getHasSubtypes();
    }

    @Override
    public void getSubItems(Item item, CreativeTabs creativeTab, List<ItemStack> subItems) {
        int index = 0; //meta
        for(String _colorPrefix : Candle.colorPrefixes) {
            subItems.add(new ItemStack(item, 1, index));
            index++;
        }
    }
}
