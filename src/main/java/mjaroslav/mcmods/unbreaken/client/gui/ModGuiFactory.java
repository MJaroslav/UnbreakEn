package mjaroslav.mcmods.unbreaken.client.gui;

import java.util.Set;

import mjaroslav.mcmods.unbreaken.common.config.ModConfiguration;
import mjaroslav.mcmods.unbreaken.lib.ModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;

public class ModGuiFactory implements IModGuiFactory {
    @Override
    public void initialize(Minecraft minecraftInstance) {

    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }

    @Override
    public boolean hasConfigGui() {
        return true;
    }

    @Override
    public GuiScreen createConfigGui(GuiScreen parentScreen) {
        return new ModConfigurationGui(parentScreen);
    }

    public static class ModConfigurationGui extends GuiConfig {
        public ModConfigurationGui(GuiScreen parentScreen) {
            super(parentScreen, new ConfigElement(ModConfiguration.instance.getCategory(ModConfiguration.category))
                    .getChildElements(), ModInfo.MODID, false, false, ModInfo.NAME);
        }

    }
}
