package dev.luminairis.slagcompat.compat.fluids.twilightforest;

import com.tterrag.registrate.util.entry.FluidEntry;
import dev.lopyluna.slag.register.AllFluids.LavaLikeFluid;
import dev.lopyluna.slag.register.AllFluids;
import dev.luminairis.slagcompat.compat.fluids.CompatFluid;
import java.util.List;

import static dev.luminairis.slagcompat.SlagCompat.REG;

@SuppressWarnings("unused")
public class TwilightForestFluids extends CompatFluid {
    public static FluidEntry<LavaLikeFluid.Flowing> MOLTEN_IRONWOOD =
            AllFluids.newMoltenFluid(REG, "Ironwood", () -> 0xffffff).register();
    public static FluidEntry<LavaLikeFluid.Flowing> MOLTEN_STEELEAF =
            AllFluids.newMoltenFluid(REG, "Steeleaf", () -> 0xffffff).register();
    public static FluidEntry<LavaLikeFluid.Flowing> MOLTEN_KNIGHTMETAL =
            AllFluids.newMoltenFluid(REG, "Knightmetal", () -> 0xffffff).register();
    public static FluidEntry<LavaLikeFluid.Flowing> MOLTEN_FIERY =
            AllFluids.newMoltenFluid(REG, "Fiery", () -> 0xffffff).register();

    public static void register() {
        registerFluids(List.of(MOLTEN_IRONWOOD, MOLTEN_STEELEAF, MOLTEN_KNIGHTMETAL, MOLTEN_FIERY), "twilightforest");
    }
}
