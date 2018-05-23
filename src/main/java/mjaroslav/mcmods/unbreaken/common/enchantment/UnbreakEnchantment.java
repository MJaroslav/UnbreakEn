package mjaroslav.mcmods.unbreaken.common.enchantment;

import static mjaroslav.mcmods.unbreaken.common.config.ModConfiguration.enchantmentMaxCost;
import static mjaroslav.mcmods.unbreaken.common.config.ModConfiguration.enchantmentMinCost;

import mjaroslav.mcmods.unbreaken.common.config.ModConfiguration;
import mjaroslav.mcmods.unbreaken.lib.ModInfo;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class UnbreakEnchantment extends Enchantment {
    public UnbreakEnchantment() {
        super(Rarity.valueOf(ModConfiguration.enchantmentRarity), EnumEnchantmentType.BREAKABLE,
                new EntityEquipmentSlot[] { EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND });
        setName(ModInfo.MODID + ":unbreak");
        setRegistryName(ModInfo.MODID + ":unbreak");
    }

    @Override
    public int getMinEnchantability(int lvl) {
        return enchantmentMinCost;
    }

    @Override
    public int getMaxEnchantability(int lvl) {
        return enchantmentMaxCost;
    }

    @Override
    public boolean canApply(ItemStack stack) {
        return super.canApply(stack) && !(stack.getItem() instanceof ItemArmor);
    }
}
