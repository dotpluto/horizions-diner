package com.dotpluto.horizonsdiner.garden;

import com.dotpluto.horizonsdiner.DropPool;
import com.dotpluto.horizonsdiner.HorizonsDiner;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.UniqueIdentifier;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static cpw.mods.fml.common.registry.GameRegistry.findUniqueIdentifierFor;

public abstract class BaseGarden extends BlockFlower implements IPlantable {

    BaseGarden(String id) {
        super(0); //only used in the icon code for the vanilla flowers

        final float size = 0.8f; //fraction of block

        final float gapSize = (1.0f - size) / 2;
        setBlockBounds(gapSize, 0.0f, gapSize, 1.0f - gapSize, size, 1.0f - gapSize);

        setTickRandomly(true);
        setCreativeTab(HorizonsDiner.creativeTab);
        setBlockName(id);

        GameRegistry.registerBlock(this, id);
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        this.blockIcon = reg.registerIcon(HorizonsDiner.MODID + ":garden");
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return this.blockIcon;
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        list.add(new ItemStack(this));
    }

    @Override
    public boolean canBlockStay(World worldIn, int x, int y, int z) {
        if(worldIn.blockExists(x, y - 1, z)) {
            var block = worldIn.getBlock(x, y - 1, z);
            return getGroundBlocks().contains(findUniqueIdentifierFor(block));
        } else {
            return false;
        }
    }

    public abstract HashSet<UniqueIdentifier> getGroundBlocks();

    @Override
    public int quantityDropped(Random random) {
        return 3;
    }

    abstract DropPool getDropPool();

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return getDropPool().getRandomItem(random);
    }
}
