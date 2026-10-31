package dev.luminairis.slagcompat.compat.fluids.allthemodium;

import com.tterrag.registrate.util.entry.FluidEntry;
import dev.lopyluna.slag.register.AllFluids.LavaLikeFluid;
import dev.lopyluna.slag.register.AllFluids;
import dev.luminairis.slagcompat.compat.fluids.AllCompatFluids;
import java.util.List;

import static dev.luminairis.slagcompat.SlagCompat.LOGGER;
import static dev.luminairis.slagcompat.SlagCompat.REG;

@SuppressWarnings("unused")
public class AllthemodiumFluids {
    public static final FluidEntry<LavaLikeFluid.Flowing> MOLTEN_ALLTHEMODIUM =
            AllFluids.compatFluid(REG, "Allthemodium", () -> 0xffffff);
    public static final FluidEntry<LavaLikeFluid.Flowing> MOLTEN_VIBRANIUM =
            AllFluids.compatFluid(REG, "Vibranium", () -> 0xffffff);
    public static final FluidEntry<LavaLikeFluid.Flowing> MOLTEN_UNOBTAINIUM =
            AllFluids.compatFluid(REG, "Unobtainium", () -> 0xffffff);

    public static void register() {
        AllCompatFluids.ALL_COMPAT_FLUIDS.addAll(List.of(MOLTEN_ALLTHEMODIUM, MOLTEN_VIBRANIUM, MOLTEN_UNOBTAINIUM));
        LOGGER.info("Registering fluids for mod: Allthemodium");
    }
}
