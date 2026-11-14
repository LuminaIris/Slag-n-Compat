package dev.luminairis.slagcompat.mixin;

import dev.lopyluna.slag.content.items.modular.ModularToolsItem;
import dev.lopyluna.slag.content.traits.Traits;
import dev.lopyluna.slag.register.AllTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ModularToolsItem.class)
public class ModularToolsItemMixin {
    @Inject(method = "isCorrectToolForDrops", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    public void isCorrectToolForDrops(ItemStack stack, BlockState state, CallbackInfoReturnable<Boolean> cir, Traits traits, float i) {
        if (state.is(AllTags.blockC("incorrect_for_allthemodium_tool"))) i = 7;
        if (state.is(AllTags.blockC("incorrect_for_vibranium_tool"))) i = 8;
        if (state.is(AllTags.blockC("incorrect_for_unobtainium_tool"))) i = 9;
        cir.setReturnValue(traits.miningTier > i + .5);
    }
}
