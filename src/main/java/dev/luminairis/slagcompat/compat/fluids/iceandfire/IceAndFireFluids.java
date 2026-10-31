package dev.luminairis.slagcompat.compat.fluids.iceandfire;

import com.tterrag.registrate.util.entry.FluidEntry;
import dev.lopyluna.slag.register.AllFluids.LavaLikeFluid;
import dev.lopyluna.slag.register.AllFluids;
import dev.luminairis.slagcompat.compat.fluids.AllCompatFluids;
import java.util.List;

import static dev.luminairis.slagcompat.SlagCompat.LOGGER;
import static dev.luminairis.slagcompat.SlagCompat.REG;

@SuppressWarnings("unused")
public class IceAndFireFluids {
    public static FluidEntry<LavaLikeFluid.Flowing> MOLTEN_FIRE_DRAGONSTEEL =
            AllFluids.compatFluid(REG, "Fire Dragonsteel", () -> 0xffffff);
    public static FluidEntry<LavaLikeFluid.Flowing> MOLTEN_ICE_DRAGONSTEEL =
            AllFluids.compatFluid(REG, "Ice Dragonsteel", () -> 0xffffff);
    public static FluidEntry<LavaLikeFluid.Flowing> MOLTEN_LIGHTNING_DRAGONSTEEL =
            AllFluids.compatFluid(REG, "Lightning Dragonsteel", () -> 0xffffff);

    public static void register() {
        AllCompatFluids.ALL_COMPAT_FLUIDS.addAll(List.of(MOLTEN_FIRE_DRAGONSTEEL, MOLTEN_ICE_DRAGONSTEEL, MOLTEN_LIGHTNING_DRAGONSTEEL));
        LOGGER.info("Registering fluids for mod: Ice and Fire: CE");
    }
}
