package dev.luminairis.slagcompat.compat.traits.iceandfire;

import com.iafenvoy.iceandfire.item.ability.BuiltinAbilities;
import com.iafenvoy.iceandfire.registry.IafDamageTypes;
import com.iafenvoy.iceandfire.registry.tag.IafEntityTags;
import dev.lopyluna.slag.content.traits.TraitType;
import dev.luminairis.slagcompat.SlagCompat;
import dev.luminairis.slagcompat.compat.traits.CompatTrait;
import dev.luminairis.slagcompat.compat.traits.effects.BonusDamageEffect;
import dev.luminairis.slagcompat.compat.traits.effects.OnHitEffect;
import java.util.List;

@SuppressWarnings("unused")
public class IceAndFireTraits extends CompatTrait {

    public static final TraitType DRAGONSLAYER = register(new TraitType.Builder("dragonslayer").modLoaded("iceandfire").color(0xE6A895)
            .effect(new BonusDamageEffect(4f, IafDamageTypes.BONUS, List.of(IafEntityTags.FIRE_DRAGON, IafEntityTags.ICE_DRAGON, IafEntityTags.LIGHTNING_DRAGON))));

    public static final TraitType FIRE_IMBUED = register(new TraitType.Builder("fire_imbued").modLoaded("iceandfire").color(0xFF0000)
            .effect(new OnHitEffect(OnHitEffect.HitEffect.FIRE_IMBUED)));

    public static final TraitType ICE_IMBUED = register(new TraitType.Builder("ice_imbued").modLoaded("iceandfire").color(0xADD8E6)
            .effect(new OnHitEffect(OnHitEffect.HitEffect.ICE_IMBUED)));

    public static final TraitType LIGHTNING_IMBUED = register(new TraitType.Builder("lightning_imbued").modLoaded("iceandfire").color(0xAF32D9)
            .effect(new OnHitEffect(OnHitEffect.HitEffect.LIGHTNING_IMBUED)));

    public static void register() {
        OnHitEffect.registerOperation(OnHitEffect.HitEffect.FIRE_IMBUED, BuiltinAbilities.DRAGONSTEEL_FIRE_TOOL::active);
        OnHitEffect.registerOperation(OnHitEffect.HitEffect.ICE_IMBUED, BuiltinAbilities.DRAGONSTEEL_ICE_TOOL::active);
        OnHitEffect.registerOperation(OnHitEffect.HitEffect.LIGHTNING_IMBUED, BuiltinAbilities.DRAGONSTEEL_LIGHTNING_TOOL::active);
        SlagCompat.LOGGER.info("Registering traits for mod: Ice and Fire CE");
    }
}
