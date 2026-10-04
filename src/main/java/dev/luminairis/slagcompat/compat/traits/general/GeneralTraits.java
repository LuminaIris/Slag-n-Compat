package dev.luminairis.slagcompat.compat.traits.general;

import dev.lopyluna.slag.content.traits.TraitType;
import dev.lopyluna.slag.content.traits.effects.ComponentsEffect;
import dev.luminairis.slagcompat.SlagCompat;
import dev.luminairis.slagcompat.compat.traits.CompatTrait;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.Unbreakable;

@SuppressWarnings("unused")
public class GeneralTraits extends CompatTrait {
    public static final TraitType UNBREAKABLE = register(new TraitType.Builder("unbreakable").color(0x5555FF)
            .effect(new ComponentsEffect(DataComponentPatch.builder().set(DataComponents.UNBREAKABLE, new Unbreakable(false)).build())));

    public static void register() {
        SlagCompat.LOGGER.info("Registering general compat traits");
    }
}
