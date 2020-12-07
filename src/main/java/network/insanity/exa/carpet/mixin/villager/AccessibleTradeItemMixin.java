package network.insanity.exa.carpet.mixin.villager;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.item.ItemStack;
import net.minecraft.village.TradeOffer;

@Mixin(TradeOffer.class)
public interface AccessibleTradeItemMixin {

    @Accessor("firstBuyItem")
    public abstract ItemStack getFirstBuyItem();

    @Accessor("firstBuyItem")
    public abstract void setFirstBuyItem(ItemStack firstBuyItem);
}
