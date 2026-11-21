package dev.luminairis.slagcompat.compat.traits.irons_spellbooks;

import dev.lopyluna.slag.content.traits.TraitType;
import dev.luminairis.slagcompat.compat.traits.CompatTrait;
import dev.luminairis.slagcompat.compat.traits.effects.BonusDamageEffect;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import java.util.Optional;

@SuppressWarnings("unused")
public class IronsSpellbooksTraits extends CompatTrait {

    public static final TraitType ARCANE = register(new TraitType.Builder("arcane").modLoaded("irons_spellbooks").color(0x99E9FF)
            .effect(new BonusDamageEffect(Optional.empty(), AttributeRegistry.MAX_MANA.unwrapKey(), Optional.of(0.03f), Optional.of(BonusDamageEffect.AttributeTarget.SELF), Optional.empty())));
}
