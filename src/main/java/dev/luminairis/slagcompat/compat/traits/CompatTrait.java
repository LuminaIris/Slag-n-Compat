package dev.luminairis.slagcompat.compat.traits;

import dev.lopyluna.slag.content.traits.TraitType;
import dev.lopyluna.slag.register.AllDynamicTypes;

public class CompatTrait {
    private static int order = 1000;

    protected static TraitType register(TraitType.Builder builder) {
        TraitType trait = builder.sortOrder(order).register();
        AllCompatTraits.compatTraits.add(trait);
        order += 10;
        return AllDynamicTypes.registerTrait(trait);
    }
}
