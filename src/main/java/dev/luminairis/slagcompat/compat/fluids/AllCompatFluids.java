package dev.luminairis.slagcompat.compat.fluids;

import dev.luminairis.slagcompat.compat.fluids.allthemodium.AllthemodiumFluids;
import dev.luminairis.slagcompat.compat.fluids.mekanism.MekanismFluids;
import dev.luminairis.slagcompat.compat.fluids.twilightforest.TwilightForestFluids;
import net.neoforged.fml.ModList;

public class AllCompatFluids {
    public static void register() {
        if (ModList.get().isLoaded("mekanism")) {
            MekanismFluids.register();
        }
        if (ModList.get().isLoaded("allthemodium")) {
            AllthemodiumFluids.register();
        }
        if (ModList.get().isLoaded("twilightforest")) {
            TwilightForestFluids.register();
        }
    }
}
