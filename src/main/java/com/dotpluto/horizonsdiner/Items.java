package com.dotpluto.horizonsdiner;

import com.dotpluto.horizonsdiner.blocks.Blocks;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

import java.util.ArrayList;

public class Items {

    static boolean areFinalized = false;
    static ArrayList<Runnable> finalizationRunnables = new ArrayList<>();

    public static void runAfterFinalization(Runnable runnable) {
        if(areFinalized) {
            throw new RuntimeException("Items were already finalized.");
        }
        finalizationRunnables.add(runnable);
    }

    //food
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

    //misc
    public static CandleItem candle;

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

        //misc
        candle = new CandleItem(Blocks.candle);

        defaultGardenDrops = new DropPool(new Item[]{pot, juicer, cuttingBoard});

        areFinalized = true;
        finalizationRunnables.forEach(Runnable::run);
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
