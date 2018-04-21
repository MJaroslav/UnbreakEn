package mjaroslav.mcmods.unbreaken.client.gui;

import java.util.Set;

import cpw.mods.fml.client.IModGuiFactory;
import cpw.mods.fml.client.config.GuiConfig;
import mjaroslav.mcmods.unbreaken.common.config.ModConfiguration;
import mjaroslav.mcmods.unbreaken.lib.ModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;

public class ModGuiFactory implements IModGuiFactory {
	@Override
	public void initialize(Minecraft minecraftInstance) {

	}

	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() {
		return ModConfigurationGui.class;
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}

	@Override
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
		return null;
	}

	public static class ModConfigurationGui extends GuiConfig {
		public ModConfigurationGui(GuiScreen parentScreen) {
			super(parentScreen, new ConfigElement(ModConfiguration.instance.getCategory(ModConfiguration.category))
					.getChildElements(), ModInfo.MODID, false, false, ModInfo.NAME);
		}

	}
}
