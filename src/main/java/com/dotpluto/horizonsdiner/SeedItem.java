package com.dotpluto.horizonsdiner;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.function.Supplier;

public class SeedItem extends Item implements IPlantable {
    Supplier<Block> cropBlockSupplier;

    public SeedItem(String rawId, Supplier<Block> cropBlockSupplier) {
        setUnlocalizedName(HorizonsDiner.appendModId(rawId));
        setCreativeTab(HorizonsDiner.creativeTab);

        this.cropBlockSupplier = cropBlockSupplier;

        GameRegistry.registerItem(this, rawId);
    }
    /**
     * Adapted from ItemSeedFood
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack heldItem, EntityPlayer player, World world, int x, int y, int z, int side, float subX, float subY, float subZ)
    {
        if (side != 1)
        {
            return false;
        }
        else if (player.canPlayerEdit(x, y, z, side, heldItem) && player.canPlayerEdit(x, y + 1, z, side, heldItem))
        {
            if (world.getBlock(x, y, z).canSustainPlant(world, x, y, z, ForgeDirection.UP, this) && world.isAirBlock(x, y + 1, z))
            {
                Block block = cropBlockSupplier.get();
                world.setBlock(x, y + 1, z, block);

                --heldItem.stackSize;
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return EnumPlantType.Plains;
    }

    @Override
    public Block getPlant(IBlockAccess world, int x, int y, int z) {
        return cropBlockSupplier.get();
    }

    @Override
    public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
        return 0;
    }
}
