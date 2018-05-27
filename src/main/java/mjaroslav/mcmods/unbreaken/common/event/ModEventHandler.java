package mjaroslav.mcmods.unbreaken.common.event;

import static mjaroslav.mcmods.unbreaken.ModUnbreakEn.hasUnbreaklEnchantment;
import static mjaroslav.mcmods.unbreaken.common.config.ModConfiguration.enable;

import com.mojang.realmsclient.gui.ChatFormatting;

import mjaroslav.mcmods.unbreaken.ModUnbreakEn;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModEventHandler {
    @SubscribeEvent
    public void tooltipEvent(ItemTooltipEvent event) {
        if (!enable && hasUnbreaklEnchantment(event.getItemStack()))
            event.getToolTip().add(ChatFormatting.RED
                    + I18n.format("tooltip.unbreaken.disabled", ModUnbreakEn.UNBREAK.getTranslatedName(1)));
    }

    @SubscribeEvent
    public void onLivingDamageEvent(LivingDamageEvent event) {
        if (enable && event.getSource().damageType.equals("player")) {
            EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
            if (player == null || player.getHeldItemMainhand().isEmpty())
                return;
            if (hasUnbreaklEnchantment(player.getHeldItemMainhand()) && player.getHeldItemMainhand()
                    .getItemDamage() >= player.getHeldItemMainhand().getMaxDamage() - 2) {
                player.getHeldItemMainhand().setItemDamage(player.getHeldItemMainhand().getItemDamage() - 2);
                event.setAmount(1.0F);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        if (!enable || event.getEntityPlayer() == null
                || event.getEntityPlayer().getHeldItem(event.getHand()).isEmpty())
            return;
        if (hasUnbreaklEnchantment(event.getEntityPlayer().getHeldItem(event.getHand()))
                && event.getEntityPlayer().getHeldItem(event.getHand())
                        .getItemDamage() >= event.getEntityPlayer().getHeldItem(event.getHand()).getMaxDamage() - 3)
            if (event.isCancelable())
                event.setCanceled(true);
    }

    @SubscribeEvent
    public void onPlayerEventBreakSpeed(PlayerEvent.BreakSpeed event) {
        if (!enable || event.getEntityPlayer() == null
                || event.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND) == null)
            return;
        if (hasUnbreaklEnchantment(event.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND))
                && event.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND)
                        .getItemDamage() >= event.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND).getMaxDamage() - 3)
            event.setNewSpeed(0.0F);
    }
}
