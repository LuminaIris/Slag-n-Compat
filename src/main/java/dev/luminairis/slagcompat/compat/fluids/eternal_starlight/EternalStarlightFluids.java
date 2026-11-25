package dev.luminairis.slagcompat.compat.fluids.eternal_starlight;

import com.tterrag.registrate.util.entry.FluidEntry;
import dev.lopyluna.slag.register.AllFluids;
import dev.luminairis.slagcompat.compat.fluids.CompatFluid;
import java.util.List;

import static dev.luminairis.slagcompat.SlagCompat.REG;

@SuppressWarnings("unused")
public class EternalStarlightFluids extends CompatFluid {
    public static FluidEntry<AllFluids.LavaLikeFluid.Flowing> MOLTEN_DEEPSILVER =
            AllFluids.newMoltenFluid(REG, "Deepsilver", () -> 0xFFFFFF).register();
    public static FluidEntry<AllFluids.LavaLikeFluid.Flowing> MOLTEN_GLACITE =
            AllFluids.newMoltenFluid(REG, "Glacite", () -> 0xFFFFFF).register();
    public static FluidEntry<AllFluids.LavaLikeFluid.Flowing> MOLTEN_THERMAL_SPRINGSTONE =
            AllFluids.newMoltenFluid(REG, "Thermal Springstone", () -> 0xFFFFFF).register();

    public static void register() {
        registerFluids(List.of(MOLTEN_DEEPSILVER, MOLTEN_GLACITE, MOLTEN_THERMAL_SPRINGSTONE), "eternal_starlight");
    }
}
