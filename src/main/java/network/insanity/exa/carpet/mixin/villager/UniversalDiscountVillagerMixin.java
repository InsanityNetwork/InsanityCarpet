package network.insanity.exa.carpet.mixin.villager;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityInteraction;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AbstractTraderEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillageGossipType;
import net.minecraft.village.VillagerGossips;
import net.minecraft.world.World;

@Mixin(VillagerEntity.class)
public abstract class UniversalDiscountVillagerMixin extends AbstractTraderEntity {

    public UniversalDiscountVillagerMixin(EntityType<? extends AbstractTraderEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    protected VillagerGossips gossip;

    @Overwrite
    public void onInteractionWith(EntityInteraction interaction, Entity entity) {
        if(interaction == EntityInteraction.ZOMBIE_VILLAGER_CURED) {
            this.gossip.startGossip(entity.getUuid(), VillageGossipType.MAJOR_POSITIVE, 20);
            this.gossip.startGossip(entity.getUuid(), VillageGossipType.MAJOR_POSITIVE, 20);        
            
            for (TradeOffer offer : this.offers) {
                ItemStack firstBuyItem = ((AccessibleTradeItemMixin)offer).getFirstBuyItem();
                firstBuyItem.count -= Math.max(new Random().nextInt(11), 7);
                ((AccessibleTradeItemMixin)offer).setFirstBuyItem(firstBuyItem);
            }
        } else if (interaction == EntityInteraction.TRADE) {
            this.gossip.startGossip(entity.getUuid(), VillageGossipType.TRADING, 5);
        } else if (interaction == EntityInteraction.VILLAGER_HURT) {
            this.gossip.startGossip(entity.getUuid(), VillageGossipType.MINOR_NEGATIVE, 25);
        } else if (interaction == EntityInteraction.VILLAGER_KILLED) {
            this.gossip.startGossip(entity.getUuid(), VillageGossipType.MAJOR_NEGATIVE, 25);
        }
    }
}
