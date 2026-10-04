package dev.luminairis.slagcompat.compat.traits;

import dev.lopyluna.slag.content.traits.TraitType;
import dev.luminairis.slagcompat.compat.traits.general.GeneralTraits;
import dev.luminairis.slagcompat.compat.traits.iceandfire.IceAndFireTraits;
import net.neoforged.fml.ModList;
import java.util.ArrayList;
import java.util.List;

public class AllCompatTraits {
    public static List<TraitType> compatTraits = new ArrayList<>();

    public static void register() {
        GeneralTraits.register();
        if (ModList.get().isLoaded("iceandfire")) {
            IceAndFireTraits.register();
        }
    }
}
