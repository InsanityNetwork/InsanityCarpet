package network.insanity.exa.carpet.mixin.villager;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AbstractTraderEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.world.World;

@Mixin(VillagerEntity.class)
public abstract class NoGossipDecayMixin extends AbstractTraderEntity {

    public NoGossipDecayMixin(EntityType<? extends AbstractTraderEntity> entityType, World world) {
        super(entityType, world);
    }

    @Overwrite
    public void tick() {
        super.tick();

        if(this.getHeadRollingTimeLeft() > 0) {
            this.setHeadRollingTimeLeft(this.getHeadRollingTimeLeft() - 1);
        }
    }

    @Inject(method = "decayGossip()V", at = @At("HEAD"))
    public void cancelDecayGossip(CallbackInfo ci)
    {
        ci.cancel();
    }
}
