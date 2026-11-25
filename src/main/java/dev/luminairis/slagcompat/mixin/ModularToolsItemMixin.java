package dev.luminairis.slagcompat.mixin;

import dev.lopyluna.slag.content.items.modular.ModularItem;
import dev.lopyluna.slag.content.items.modular.ModularToolsItem;
import dev.lopyluna.slag.content.traits.Traits;
import dev.lopyluna.slag.register.AllTags;
import dev.luminairis.slagcompat.SlagCompat;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@SuppressWarnings("unused")
@Mixin(ModularToolsItem.class)
public class ModularToolsItemMixin extends ModularItem {
    protected ModularToolsItemMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "isCorrectToolForDrops", at = @At("HEAD"), cancellable = true)
    public void isCorrectToolForDrops(ItemStack stack, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        var traits = Traits.of(stack);
        if (!traits.tool) {
            cir.setReturnValue(super.isCorrectToolForDrops(stack, state));
            return;
        }
        if (!traits.isCorrectForDrops(state)) {
            cir.setReturnValue(false);
            return;
        }
        for (int i = 20; i >= 0; i--) {
            if (state.is(TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(SlagCompat.MODID, "incorrect_for_tier_" + i + "_tool")))) {
                cir.setReturnValue(traits.miningTier > (float) i + .5);
                return;
            }
            cir.setReturnValue(true);
        }
    }
}
