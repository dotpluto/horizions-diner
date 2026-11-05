package com.dotpluto.horizonsdiner;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCake;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import squeek.applecore.api.food.FoodValues;
import squeek.applecore.api.food.IEdibleBlock;
import squeek.applecore.api.food.ItemFoodProxy;

//dotpluto: I don't 100% understand the applecore integration. I just copied from https://github.com/squeek502/AppleCore/wiki/Integrating-food-items-with-AppleCore
@Optional.Interface(iface = "squeek.applecore.api.food.IEdibleBlock", modid = "AppleCore")
public class HorizonCake extends BlockCake implements IEdibleBlock {
    int foodRestored = 1;
    float saturation = 1.0f;

    private boolean isEdibleAtMaxHunger = false;

    public HorizonCake() {
        super();
        String rawId = "cake";
        setBlockName(HorizonsDiner.appendModId(rawId));
        GameRegistry.registerBlock(this, rawId);
    }


    @Optional.Method(modid = "AppleCore")
    @Override
    public void setEdibleAtMaxHunger(boolean b) {
        isEdibleAtMaxHunger = b;
    }


    @Optional.Method(modid = "AppleCore")
    public void onEatenCompatibility(ItemStack itemStack, EntityPlayer player)
    {
        //dotpluto: i guess apple core injects into the item stack consumption method of the players food stats.
        // using this ItemFoodProxy which is a fake item stack
        // and then it probably applies the stats of this block instead
        new ItemFoodProxy(this).onEaten(itemStack, player);
    }

    @Override
    public FoodValues getFoodValues(ItemStack itemStack) {
        return new FoodValues(foodRestored, saturation);
    }

    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ) {
        tryEat(worldIn, player);
        return true;
    }

    public void tryEat(World world, EntityPlayer player) {
        if (player.canEat(isEdibleAtMaxHunger))
        {
            if (Loader.isModLoaded("AppleCore"))
            {
                onEatenCompatibility(new ItemStack(this), player);
            }
            else
            {
                // falling back to the simple method when apple core isn't installed
                player.getFoodStats().addStats(foodRestored, saturation);
            }
        }
    }

    @Override
    public Item getItem(World worldIn, int x, int y, int z) {
        //original implementation from Block. BlockCake overrides this
        return Item.getItemFromBlock(this);
    }
}
