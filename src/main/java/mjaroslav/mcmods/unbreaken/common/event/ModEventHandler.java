package mjaroslav.mcmods.unbreaken.common.event;

import static mjaroslav.mcmods.unbreaken.ModUnbreakEn.hasUnbreaklEnchantment;
import static mjaroslav.mcmods.unbreaken.common.config.ModConfiguration.enable;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class ModEventHandler {
	@SubscribeEvent
	public void onLivingAttackEvent(AttackEntityEvent event) {
		if (!enable || event.entityPlayer == null || event.entityPlayer.getHeldItem() == null)
			return;
		if (hasUnbreaklEnchantment(event.entityPlayer.getHeldItem())
				&& event.entityPlayer.getHeldItem().getItemDamage() >= event.entityPlayer.getHeldItem().getMaxDamage())
			event.setCanceled(true);
	}

	@SubscribeEvent
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		if (!enable || event.entityPlayer == null || event.entityPlayer.getHeldItem() == null)
			return;
		if (hasUnbreaklEnchantment(event.entityPlayer.getHeldItem())
				&& event.entityPlayer.getHeldItem().getItemDamage() >= event.entityPlayer.getHeldItem().getMaxDamage())
			event.setCanceled(true);

	}

	@SubscribeEvent
	public void onPlayerEventBreakSpeed(PlayerEvent.BreakSpeed event) {
		if (!enable || event.entityPlayer == null || event.entityPlayer.getHeldItem() == null)
			return;
		if (hasUnbreaklEnchantment(event.entityPlayer.getHeldItem())
				&& event.entityPlayer.getHeldItem().getItemDamage() >= event.entityPlayer.getHeldItem().getMaxDamage())
			event.newSpeed = 0.0F;
	}
}
