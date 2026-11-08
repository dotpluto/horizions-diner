package com.dotpluto.horizonsdiner.blocks;

import com.dotpluto.horizonsdiner.CandleItem;
import com.dotpluto.horizonsdiner.HorizonsDiner;
import com.dotpluto.horizonsdiner.Items;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.Random;

public class Candle extends Block {
    final float HEIGHT = 0.7f;
    final String rawId = "candle";

    CandleItem candleItem;

    public static String[] colorPrefixes = {
        "white", "orange", "magenta", "light_blue",
        "yellow", "lime", "pink", "dark_grey",
        "light_grey", "cyan", "purple", "blue",
        "brown", "green", "red", "black"
    };

    public static IIcon[] icons; //populated in registerBlockIcons

    public Candle() {
        super(Material.carpet);

        setTickRandomly(true);


        final String fullId = HorizonsDiner.appendModId(rawId);

        setBlockTextureName(HorizonsDiner.appendTextureId(rawId));
        setCreativeTab(HorizonsDiner.creativeTab);
        setBlockName(fullId);
        setStepSound(Block.soundTypeCloth);
        setLightLevel(1.0f);


        final float vSize = 0.4f; //fraction of block

        final float gapSize = (1.0f - vSize) / 2;
        setBlockBounds(gapSize, 0.0f, gapSize, 1.0f - gapSize, HEIGHT, 1.0f - gapSize);

        Items.runAfterFinalization(() -> {
            candleItem = Items.candle;
        });

        GameRegistry.registerBlock(this, null, rawId);
    }

    @Override
    public int getRenderType() {
        return 1; //crossed squares meaning like a flower
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return icons[meta];
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        icons = new IIcon[colorPrefixes.length];
        int index = 0;
        for(String colorPrefix : colorPrefixes) {
            icons[index] = reg.registerIcon(HorizonsDiner.appendTextureId(colorPrefix + "_candle"));
            index++;
        }
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, int x, int y, int z)
    {
        return super.canPlaceBlockAt(worldIn, x, y, z) && this.canBlockStay(worldIn, x, y, z);
    }

    public void updateTick(World worldIn, int x, int y, int z, Random random)
    {
        this.checkAndDropBlock(worldIn, x, y, z);
    }

    @Override
    public void onNeighborBlockChange(World worldIn, int x, int y, int z, Block neighbor) {
        this.checkAndDropBlock(worldIn, x, y, z);
    }

    void checkAndDropBlock(World worldIn, int x, int y, int z)
    {
        if (!this.canBlockStay(worldIn, x, y, z))
        {
            this.dropBlockAsItem(worldIn, x, y, z, worldIn.getBlockMetadata(x, y, z), 0);
            worldIn.setBlock(x, y, z, getBlockById(0), 0, 2);
        }
    }

    @Override
    public boolean canBlockStay(World worldIn, int x, int y, int z) {
        return worldIn.getBlock(x, y - 1, z).getMaterial().isSolid();
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public void randomDisplayTick(World worldIn, int x, int y, int z, Random random) {
        worldIn.spawnParticle("flame", (float)x + 0.5f, (float)y + HEIGHT + 0.2f, (float)z + 0.5f, 0.0f, 0.0f, 0.0f);
        worldIn.spawnParticle("smoke", (float)x + 0.5f, (float)y + HEIGHT + 0.2f, (float)z + 0.5f, 0.0f, 0.0f, 0.0f);
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
        return new ItemStack(candleItem, 1, world.getBlockMetadata(x, y , z));
    }
}
