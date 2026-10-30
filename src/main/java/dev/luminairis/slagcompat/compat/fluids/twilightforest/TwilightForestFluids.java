package dev.luminairis.slagcompat.compat.fluids.twilightforest;

import com.tterrag.registrate.util.entry.FluidEntry;
import dev.lopyluna.slag.register.AllFluids.LavaLikeFluid;
import dev.lopyluna.slag.register.AllFluids;
import static dev.luminairis.slagcompat.SlagCompat.LOGGER;
import static dev.luminairis.slagcompat.SlagCompat.REG;

@SuppressWarnings("unused")
public class TwilightForestFluids {
    public static FluidEntry<LavaLikeFluid.Flowing> MOLTEN_IRONWOOD =
            AllFluids.compatFluid(REG, "Ironwood", () -> 0xffffff);
    public static FluidEntry<LavaLikeFluid.Flowing> MOLTEN_STEELEAF =
            AllFluids.compatFluid(REG, "Steeleaf", () -> 0xffffff);
    public static FluidEntry<LavaLikeFluid.Flowing> MOLTEN_KNIGHTMETAL =
            AllFluids.compatFluid(REG, "Knightmetal", () -> 0xffffff);
    public static FluidEntry<LavaLikeFluid.Flowing> MOLTEN_FIERY =
            AllFluids.compatFluid(REG, "Fiery", () -> 0xffffff);

    public static void register() {
        LOGGER.info("Registering fluids for mod: Twilight Forest");
    }
}
