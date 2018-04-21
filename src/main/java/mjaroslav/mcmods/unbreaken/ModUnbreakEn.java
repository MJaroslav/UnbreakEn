package mjaroslav.mcmods.unbreaken;

import static mjaroslav.mcmods.unbreaken.lib.ModInfo.GUIFACTORY;
import static mjaroslav.mcmods.unbreaken.lib.ModInfo.MODID;
import static mjaroslav.mcmods.unbreaken.lib.ModInfo.NAME;
import static mjaroslav.mcmods.unbreaken.lib.ModInfo.VERSION;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import mjaroslav.mcmods.unbreaken.common.config.ModConfiguration;
import mjaroslav.mcmods.unbreaken.common.enchantment.UnbreakEnchantment;
import mjaroslav.mcmods.unbreaken.common.event.ModEventHandler;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = MODID, name = NAME, version = VERSION, guiFactory = GUIFACTORY)
public class ModUnbreakEn {
	public static Enchantment UNBREAK;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ModConfiguration.init(event.getModConfigurationDirectory());
		UNBREAK = new UnbreakEnchantment();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new ModEventHandler());
		FMLCommonHandler.instance().bus().register(new ModConfiguration());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}

	public static boolean hasUnbreaklEnchantment(ItemStack stack) {
		return EnchantmentHelper.getEnchantmentLevel(UNBREAK.effectId, stack) > 0;
	}
}
