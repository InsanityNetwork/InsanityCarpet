package network.insanity.exa.carpet.mixin.villager;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.village.TradeOffer;

@Mixin(TradeOffer.class)
public abstract class NoDemandMixin {

    @Shadow
    protected int demandBonus;

    @Overwrite
    public void UpdatePriceOnDemand()
    {
        this.demandBonus = 0;
    }
}
