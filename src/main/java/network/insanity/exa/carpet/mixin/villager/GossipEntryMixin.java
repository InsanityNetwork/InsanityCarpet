package network.insanity.exa.carpet.mixin.villager;

import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.village.VillageGossipType;
import net.minecraft.village.VillagerGossips;

@SuppressWarnings("all")
@Mixin(VillagerGossips.GossipEntry.class)
public abstract class GossipEntryMixin {

    @Shadow
    protected UUID target;

    @Shadow 
    protected VillageGossipType type;
    
    @Inject(method = "..init(Ljava/util/UUID;Lnet/minecraft/village/VillageGossipType;I)V", at = @At("HEAD"), cancellable = true)
    public void globalizeGossipEntryCtor(CallbackInfo ci)
    {
        if(type != type.MAJOR_NEGATIVE && type != type.MINOR_NEGATIVE) {
            this.type = type;
        } else {
            ci.cancel();
        }

        this.target = new UUID(0, 0);
    }
}
