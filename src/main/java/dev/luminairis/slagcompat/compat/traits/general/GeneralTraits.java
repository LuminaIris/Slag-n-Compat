package dev.luminairis.slagcompat.compat.traits.general;

import dev.lopyluna.slag.content.traits.TraitType;
import dev.lopyluna.slag.content.traits.effects.ComponentsEffect;
import dev.luminairis.slagcompat.SlagCompat;
import dev.luminairis.slagcompat.compat.traits.CompatTrait;
import dev.luminairis.slagcompat.compat.traits.effects.OnHitEffect;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Unbreakable;

@SuppressWarnings("unused")
public class GeneralTraits extends CompatTrait {
    public static final TraitType IGNITE = register(new TraitType.Builder("ignite").color(0xF23800)
            .effect(new OnHitEffect(OnHitEffect.HitEffect.IGNITE)));

    public static final TraitType UNBREAKABLE = register(new TraitType.Builder("unbreakable").color(0x5555FF)
            .effect(new ComponentsEffect(DataComponentPatch.builder().set(DataComponents.UNBREAKABLE, new Unbreakable(false)).build())));

    public static void register() {
        OnHitEffect.registerOperation(OnHitEffect.HitEffect.IGNITE, GeneralTraits::igniteTarget);
        SlagCompat.LOGGER.info("Registering general compat traits");
    }

    public static void igniteTarget(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.igniteForSeconds(4);
    }
}
