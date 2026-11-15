package dev.luminairis.slagcompat.compat.fluids;

import com.tterrag.registrate.util.entry.FluidEntry;
import dev.lopyluna.slag.register.AllFluids;
import net.neoforged.fml.ModList;
import java.util.List;

import static dev.luminairis.slagcompat.SlagCompat.LOGGER;

public class CompatFluid {
    protected static void registerFluids(List<FluidEntry<AllFluids.LavaLikeFluid.Flowing>> fluids, String modid) {
        AllCompatFluids.ALL_COMPAT_FLUIDS.addAll(fluids);
        if (ModList.get().isLoaded(modid)) {
            LOGGER.info("Registering fluids for mod: " + modid);
        } else {
            AllCompatFluids.HIDDEN_COMPAT_FLUIDS.addAll(fluids);
            LOGGER.info("Registering hidden fluids for uninstalled mod: " + modid);
        }
    }
}
