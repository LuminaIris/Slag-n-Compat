package dev.luminairis.slagcompat.compat.traits.general;

import dev.lopyluna.slag.content.traits.TraitType;
import dev.lopyluna.slag.content.traits.effects.ComponentsEffect;
import dev.luminairis.slagcompat.SlagCompat;
import dev.luminairis.slagcompat.compat.traits.CompatTrait;
import dev.luminairis.slagcompat.compat.traits.effects.BonusDamageEffect;
import dev.luminairis.slagcompat.compat.traits.effects.BonusDamageEffect.AttributeTarget;
import dev.luminairis.slagcompat.compat.traits.effects.OnHitAbility;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Unbreakable;
import java.util.Optional;

@SuppressWarnings("unused")
public class GeneralTraits extends CompatTrait {
    public static final TraitType IGNITING = register(new TraitType.Builder("igniting").color(0xF23800)
            .effect(new OnHitAbility(OnHitAbility.HitAbility.IGNITING)));

    public static final TraitType ARMORSLAYER = register(new TraitType.Builder("armorslayer").color(0xC9D6C5)
            .effect(new BonusDamageEffect(Optional.empty(), Attributes.ARMOR.unwrapKey(), Optional.of(0.4f), Optional.of(AttributeTarget.TARGET), Optional.empty())));

    public static final TraitType UNBREAKABLE = register(new TraitType.Builder("unbreakable").color(0x5555FF)
            .effect(new ComponentsEffect(DataComponentPatch.builder().set(DataComponents.UNBREAKABLE, new Unbreakable(false)).build())));

    public static void register() {
        OnHitAbility.registerOperation(OnHitAbility.HitAbility.IGNITING, GeneralTraits::igniteTarget);
        SlagCompat.LOGGER.info("Registering general compat traits");
    }

    public static void igniteTarget(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.igniteForSeconds(4);
    }
}
