package mjaroslav.mcmods.unbreaken.common.config;

import static mjaroslav.mcmods.unbreaken.lib.ModInfo.LOG;
import static mjaroslav.mcmods.unbreaken.lib.ModInfo.MODID;

import java.io.File;

import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModConfiguration {
    public static boolean enable = true;
    public static int enchantmentId = 63;
    public static String enchantmentRarity = Rarity.UNCOMMON.name();
    public static int enchantmentMinCost = 10;
    public static int enchantmentMaxCost = 20;
    public static boolean onlyChestLoot = false;
    public static boolean enchantmentIsCurse = false;

    public static final String category = Configuration.CATEGORY_GENERAL;
    public static Configuration instance;

    public static void init(File folder) {
        if (instance == null)
            instance = new Configuration(new File(folder + "/" + MODID + ".cfg"));
        try {
            instance.load();
        } catch (Exception e) {
            LOG.error("Unable to load configuration!");
        } finally {
            if (instance.hasChanged())
                instance.save();
        }
        sync();
    }

    public static void sync() {
        if (instance == null) {
            LOG.error("Configuration not found!");
            return;
        }
        enable = instance.getBoolean("enable", category, true,
                "Enable the functioning of the enchantment. The option works without restarting the game.");
        enchantmentId = instance.get(category, "enchantment_id", 63, "Enchantment ID").setMinValue(0).setMaxValue(255)
                .setRequiresMcRestart(true).getInt();
        enchantmentRarity = instance.get(category, "enchantment_rarity", Rarity.UNCOMMON.name(),
                "Enchantment rarity, valid values: COMMON | UNCOMMON | RARE | VERY_RARE", rarity).getString();
        enchantmentMinCost = instance.getInt("enchantment_min_cost", category, 10, 1, 30,
                "The minimum level at which enchantment occurs.");
        enchantmentMaxCost = instance.getInt("enchantment_max_cost", category, 20, 1, 30,
                "The maximum level at which enchantment occurs.");
        if (enchantmentMinCost > enchantmentMaxCost)
            enchantmentMaxCost = enchantmentMinCost;
        if (instance.hasChanged())
            instance.save();
        onlyChestLoot = instance.getBoolean("only_chest_loot", category, false,
                "This enchantment can only be obtained by finding in dungeons.");
        enchantmentIsCurse = instance.getBoolean("enchantment_is_curse", category, false,
                "Enchantment is a curse. The name turns red.");
    }

    private static String[] rarity = new String[] { Rarity.COMMON.name(), Rarity.UNCOMMON.name(), Rarity.RARE.name(),
            Rarity.VERY_RARE.name() };

    @SubscribeEvent
    public void onConfigChangedEvent(OnConfigChangedEvent event) {
        if (event.getModID().equals(MODID)) {
            sync();
            LOG.info("Configuration reloaded!");
        }
    }
}
