package tfar.nouseactionslowdown.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import tfar.nouseactionslowdown.platform.MLConfig;
import tfar.nouseactionslowdown.platform.Services;

import javax.annotation.Nullable;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin extends Player {

    public LocalPlayerMixin(Level $$0, BlockPos $$1, float $$2, GameProfile $$3) {
        super($$0, $$1, $$2, $$3);
    }

    @Shadow public abstract boolean isUsingItem();

    @Shadow public abstract InteractionHand getUsedItemHand();

    @Shadow @Nullable private InteractionHand usingItemHand;

    @ModifyConstant(method = "aiStep", constant = @Constant(floatValue = .2f))
    private float init(float constant) {

        if (isUsingItem()) {
            ItemStack stack = getItemInHand(getUsedItemHand());
            if (!stack.isEmpty()) {
                UseAnim useAnim = stack.getUseAnimation();
                MLConfig config = Services.PLATFORM.getConfig();

                return switch (useAnim) {
                    case NONE -> 1;
                    case EAT -> config.disableEatingSlowdown() ? 1 :constant;
                    case DRINK -> config.disableDrinkingSlowdown() ? 1 :constant;
                    case BLOCK -> config.disableBlockingSlowdown() ? 1 :constant;
                    case BOW -> config.disableBowSlowdown() ? 1 :constant;
                    case SPEAR -> 1;
                    case CROSSBOW -> config.disableCrossbowSlowdown() ? 1 :constant;
                    case SPYGLASS -> 1;
                    case TOOT_HORN -> 1;
                    case BRUSH -> 1;
                    default -> 1;
                };
            }
        }
        return constant;
    }

    @ModifyVariable(method = "aiStep",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isSwimming()Z",ordinal = 1),ordinal = 8)
    private boolean preventSprint(boolean value) {
        return isUsingItem()||value;
    }
}