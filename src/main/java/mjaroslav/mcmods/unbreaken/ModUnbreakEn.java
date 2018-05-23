package mjaroslav.mcmods.unbreaken;

import static mjaroslav.mcmods.unbreaken.lib.ModInfo.*;

import mjaroslav.mcmods.unbreaken.common.config.ModConfiguration;
import mjaroslav.mcmods.unbreaken.common.enchantment.UnbreakEnchantment;
import mjaroslav.mcmods.unbreaken.common.event.ModEventHandler;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@Mod(modid = MODID, name = NAME, version = VERSION, guiFactory = GUIFACTORY)
public class ModUnbreakEn {
    public static Enchantment UNBREAK;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModConfiguration.init(event.getModConfigurationDirectory());
        UNBREAK = new UnbreakEnchantment();
        ForgeRegistries.ENCHANTMENTS.register(UNBREAK);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ModEventHandler());
        MinecraftForge.EVENT_BUS.register(new ModConfiguration());
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }

    public static boolean hasUnbreaklEnchantment(ItemStack stack) {
        return EnchantmentHelper.getEnchantmentLevel(UNBREAK, stack) > 0;
    }
}
