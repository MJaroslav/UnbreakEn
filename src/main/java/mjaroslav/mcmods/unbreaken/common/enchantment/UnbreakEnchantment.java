package mjaroslav.mcmods.unbreaken.common.enchantment;

import static mjaroslav.mcmods.unbreaken.common.config.ModConfiguration.enchantmentId;
import static mjaroslav.mcmods.unbreaken.common.config.ModConfiguration.enchantmentMaxCost;
import static mjaroslav.mcmods.unbreaken.common.config.ModConfiguration.enchantmentMinCost;
import static mjaroslav.mcmods.unbreaken.common.config.ModConfiguration.enchantmentWeight;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class UnbreakEnchantment extends Enchantment {
	public UnbreakEnchantment() {
		super(enchantmentId, enchantmentWeight, EnumEnchantmentType.breakable);
		setName("unbreak");
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
