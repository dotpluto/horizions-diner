package com.dotpluto.horizonsdiner;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static String greeting = "Hello World";

    public static boolean enableGardenSpread = true;
    public static int gardenSpreadTimeMultiplier = 10;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        greeting = configuration.getString("greeting", Configuration.CATEGORY_GENERAL, greeting, "How shall I greet?");
        enableGardenSpread = configuration.getBoolean("enabledGardenSpread", Configuration.CATEGORY_GENERAL, enableGardenSpread, "");
        gardenSpreadTimeMultiplier = configuration.getInt("gardenSpreadTimeMultiplier", Configuration.CATEGORY_GENERAL, gardenSpreadTimeMultiplier, 1, Integer.MAX_VALUE, "How long a garden takes to spread. On average a garden will grow in the span of gardenSpreadTimeMultiplier * 68 seconds");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
