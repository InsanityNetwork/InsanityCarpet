package network.insanity.exa.carpet.mixin.villager;

import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityInteraction;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AbstractTraderEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.village.VillageGossipType;
import net.minecraft.village.VillagerGossips;
import net.minecraft.world.World;

@Mixin(VillagerEntity.class)
public abstract class UniversalGossipMixin extends AbstractTraderEntity {

    public UniversalGossipMixin(EntityType<? extends AbstractTraderEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    protected VillagerGossips gossip;

    @Overwrite
    public void onInteractionWith(EntityInteraction interaction, Entity entity) {
        if(interaction == EntityInteraction.ZOMBIE_VILLAGER_CURED) {
            this.gossip.startGossip(new UUID(0, 0), VillageGossipType.MAJOR_POSITIVE, 20);
            this.gossip.startGossip(new UUID(0, 0), VillageGossipType.MAJOR_POSITIVE, 20);
        } else if (interaction == EntityInteraction.TRADE) {
            this.gossip.startGossip(new UUID(0, 0), VillageGossipType.MINOR_POSITIVE, 5);
        }
    }    

    @Overwrite
    public int getReputation(PlayerEntity player) {
        return this.gossip.getReputationFor(new UUID(0, 0), (arg) -> {
            return true;
        });
    }
}
