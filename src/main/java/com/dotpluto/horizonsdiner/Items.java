package com.dotpluto.horizonsdiner;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

public class Items {


    public static ItemFood woodChips;

    //tools
    public static Item fryingPan;
    public static Item saucePan;
    public static Item bakeWare;
    public static Item mortarAndPestle;
    public static Item cuttingBoard;
    public static Item juicer;
    public static Item pot;

    //seeds
    public static SeedItem testSeed;

    //drop pools
    public static DropPool defaultGardenDrops;

    public static void Register() {
        //food
        woodChips = CreateItemFood("wood_chips", 5, 1.0f);

        //tools
        fryingPan = CreateItem("skillet");
        saucePan = CreateItem("sauce_pan");
        bakeWare = CreateItem("bake_ware");
        mortarAndPestle = CreateItem("mortar_and_pestle");
        cuttingBoard = CreateItem("cutting_board");
        juicer = CreateItem("juicer");
        pot = CreateItem("pot");

        //seeds
        testSeed = new SeedItem("test_seed", () -> { return Blocks.testCrop; });

        defaultGardenDrops = new DropPool(new Item[]{pot, juicer, cuttingBoard});


    }

    static Item CreateItem(String rawId) {
        Item item = new Item()
            .setUnlocalizedName(HorizonsDiner.appendModId(rawId))
            .setCreativeTab(HorizonsDiner.creativeTab);
        GameRegistry.registerItem(item, rawId);
        return item;
    }

    static ItemFood CreateItemFood(String rawId, int hunger, float sat) {
        ItemFood item = new ItemFood(hunger, sat, false);
        item.setUnlocalizedName(HorizonsDiner.appendModId(rawId));
        GameRegistry.registerItem(item, rawId);
        return item;
    }
}
