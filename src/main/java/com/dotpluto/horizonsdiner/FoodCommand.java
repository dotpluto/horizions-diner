package com.dotpluto.horizonsdiner;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

import java.util.List;

public class FoodCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "food";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/food clear";
    }


    @Override
    public void processCommand(ICommandSender sender, String[] args) {

        if(sender instanceof EntityPlayer e && args[0].equals("clear")) {
            e.getFoodStats().setFoodLevel(0);
            e.getFoodStats().setFoodSaturationLevel(0.0f);
        }
    }

}
