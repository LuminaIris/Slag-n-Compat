package dev.luminairis.slagcompat.compat.traits;

import dev.lopyluna.slag.content.traits.TraitType;
import dev.lopyluna.slag.content.traits.effects.ComponentsEffect;
import dev.lopyluna.slag.register.AllDynamicTypes;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Unit;
import net.minecraft.world.item.component.Unbreakable;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class AllCompatTraits {
    private static int order = 1000;
    public static List<TraitType> compatTraits = new ArrayList<>();

    public static final TraitType UNBREAKABLE = register(new TraitType.Builder("unbreakable").color(0x5555FF)
            .effect(new ComponentsEffect(DataComponentPatch.builder().set(DataComponents.UNBREAKABLE, new Unbreakable(false)).build())));

    private static TraitType register(TraitType.Builder builder) {
        TraitType trait = builder.sortOrder(order).register();
        compatTraits.add(trait);
        order += 10;
        return AllDynamicTypes.registerTrait(trait);
    }

    public static void register() {}
}
