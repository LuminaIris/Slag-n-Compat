package dev.luminairis.slagcompat.compat.traits;

import com.iafenvoy.iceandfire.item.ability.BuiltinAbilities;
import com.iafenvoy.iceandfire.registry.IafDamageTypes;
import com.iafenvoy.iceandfire.registry.tag.IafEntityTags;
import dev.lopyluna.slag.content.traits.TraitType;
import dev.lopyluna.slag.content.traits.effects.ComponentsEffect;
import dev.lopyluna.slag.register.AllDynamicTypes;
import dev.luminairis.slagcompat.compat.traits.effects.BonusDamageEffect;
import dev.luminairis.slagcompat.compat.traits.effects.OnHitEffect;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.Unbreakable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class AllCompatTraits {
    private static int order = 1000;
    public static List<TraitType> compatTraits = new ArrayList<>();

    public static final TraitType UNBREAKABLE = register(new TraitType.Builder("unbreakable").color(0x5555FF)
            .effect(new ComponentsEffect(DataComponentPatch.builder().set(DataComponents.UNBREAKABLE, new Unbreakable(false)).build())));

    public static final TraitType DRAGONSLAYER = register(new TraitType.Builder("dragonslayer").modLoaded("iceandfire").color(0xE6A895)
            .effect(new BonusDamageEffect(4f, IafDamageTypes.BONUS, List.of(IafEntityTags.FIRE_DRAGON, IafEntityTags.ICE_DRAGON, IafEntityTags.LIGHTNING_DRAGON))));

    public static final TraitType FIRE_IMBUED = register(new TraitType.Builder("fire_imbued").modLoaded("iceandfire").color(0xFF0000)
            .effect(OnHitEffect.FIRE_IMBUED));

    public static final TraitType ICE_IMBUED = register(new TraitType.Builder("ice_imbued").modLoaded("iceandfire").color(0xADD8E6)
            .effect(OnHitEffect.ICE_IMBUED));

    public static final TraitType LIGHTNING_IMBUED = register(new TraitType.Builder("lightning_imbued").modLoaded("iceandfire").color(0xAF32D9)
            .effect(OnHitEffect.LIGHTNING_IMBUED));


    private static TraitType register(TraitType.Builder builder) {
        TraitType trait = builder.sortOrder(order).register();
        compatTraits.add(trait);
        order += 10;
        return AllDynamicTypes.registerTrait(trait);
    }

    public static void register() {}
}
