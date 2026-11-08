package com.dotpluto.horizonsdiner.blocks;

import com.dotpluto.horizonsdiner.Config;
import com.dotpluto.horizonsdiner.DropPool;
import com.dotpluto.horizonsdiner.HorizonsDiner;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.UniqueIdentifier;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
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

    BaseGarden(String rawId) {
        super(0); //only used in the icon code for the vanilla flowers

        final float size = 0.8f; //fraction of block

        final float gapSize = (1.0f - size) / 2;
        setBlockBounds(gapSize, 0.0f, gapSize, 1.0f - gapSize, size, 1.0f - gapSize);

        setStepSound(Block.soundTypeGrass);
        setTickRandomly(true);
        setCreativeTab(HorizonsDiner.creativeTab);
        setBlockName(HorizonsDiner.appendModId(rawId));

        GameRegistry.registerBlock(this, rawId);
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

    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ) {
        if(!worldIn.isRemote && !player.capabilities.isCreativeMode) {
            dropBlockAsItem(worldIn, x, y, z, new ItemStack(this));
            worldIn.setBlockToAir(x, y, z);
        }
        return true;
    }

    @Override
    public void updateTick(World worldIn, int myX, int myY, int myZ, Random random) {
        if(Config.enableGardenSpread) {
            int data = worldIn.getBlockMetadata(myX, myY, myZ);
            if(data >= Config.gardenSpreadTimeMultiplier - 1) {
                data = 0;
                doGardenSpread(worldIn, myX, myY, myZ, random);
            } else {
                data += 1;
            }
            final int flags = 0; //do a server side only update without block updates
            worldIn.setBlockMetadataWithNotify(myX, myY, myZ, data, flags);
        }



        //check if this garden is invalid and drop if it is
        super.updateTick(worldIn, myX, myY, myZ, random);
    }

    public void doGardenSpread(World worldIn, int myX, int myY, int myZ, Random random) {
        final int maximumHSpread = 4; //meaning in each direction so 4 means a square of 9x9
        final int maximumVSpread = 1; // x means up x and x down

        final int maxGardens = 3;

        final int originX = myX - maximumHSpread;
        final int originY = myY - maximumVSpread;
        final int originZ = myZ - maximumHSpread;

        //check if there are too many gardens
        int countedGardens = 0;
        for(int targetX = 0; targetX <= maximumHSpread * 2 + 1; targetX++) {
            for(int targetY = 0; targetY <= maximumVSpread * 2 + 1; targetY++) {
                for(int targetZ = 0; targetZ <= maximumHSpread * 2 + 1; targetZ++) {
                    if(worldIn.getBlock(originX + targetX, originY + targetY, originZ + targetZ) instanceof BaseGarden) {
                        countedGardens++;
                    }
                }
            }
        }

        if(countedGardens >= maxGardens + 1) { //plus itself
            return;
        }


        //for each y layer attempt to spread once
        for(int layerY = 0; layerY <= maximumVSpread * 2 + 1; layerY++) {
            int targetY = originY + layerY;
            int targetX = originX + random.nextInt(maximumHSpread * 2 + 1);
            int targetZ = originZ + random.nextInt(maximumHSpread * 2 + 1);
            if(worldIn.blockExists(targetX, targetY, targetZ)
                && canPlaceBlockAt(worldIn, targetX, targetY, targetZ)
                && canBlockStay(worldIn, targetX, targetY, targetZ)) {
                worldIn.setBlock(targetX, targetY, targetZ, this);
                break; //only one garden per spread max
            }
        }
    }
}
