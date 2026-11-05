package com.dotpluto.horizonsdiner;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

public class Items {
    public static CreativeTabs creativeTab = new CreativeTabs(HorizonsDiner.MODID + ".default") {
        @Override
        public Item getTabIconItem() {
            return woodChips;
        }
    };



    public static ItemFood woodChips;

    //tools
    public static Item fryingPan;
    public static Item saucePan;
    public static Item bakeWare;
    public static Item mortarAndPestle;
    public static Item cuttingBoard;
    public static Item juicer;
    public static Item pot;




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




    }

    static Item CreateItem(String id) {
        Item item = new Item()
            .setUnlocalizedName(id)
            .setCreativeTab(creativeTab);
        GameRegistry.registerItem(item, id);
        return item;
    }

    static ItemFood CreateItemFood(String id, int hunger, float sat) {
        ItemFood item = new ItemFood(hunger, sat, false);
        item.setUnlocalizedName(id);
        GameRegistry.registerItem(item, id);
        return item;
    }
}
