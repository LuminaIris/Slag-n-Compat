package dev.luminairis.slagcompat.compat.mobeffect;

import dev.luminairis.slagcompat.SlagCompat;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CompatMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, SlagCompat.MODID);

    public static final Holder<MobEffect> UNSTABLE = MOB_EFFECTS.register("unstable", () -> new UnstableMobEffect(
            MobEffectCategory.HARMFUL,
            0xB9892E
    ));

    public static void register() {}
}
