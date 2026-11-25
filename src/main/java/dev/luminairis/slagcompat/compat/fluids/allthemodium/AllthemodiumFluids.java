package dev.luminairis.slagcompat.compat.fluids.allthemodium;

import com.tterrag.registrate.util.entry.FluidEntry;
import dev.lopyluna.slag.register.AllFluids.LavaLikeFluid;
import dev.lopyluna.slag.register.AllFluids;
import dev.luminairis.slagcompat.compat.fluids.CompatFluid;
import java.util.List;

import static dev.luminairis.slagcompat.SlagCompat.REG;

@SuppressWarnings("unused")
public class AllthemodiumFluids extends CompatFluid {
    public static final FluidEntry<LavaLikeFluid.Flowing> MOLTEN_ALLTHEMODIUM =
            AllFluids.newMoltenFluid(REG, "Allthemodium", () -> 0xffffff).register();
    public static final FluidEntry<LavaLikeFluid.Flowing> MOLTEN_VIBRANIUM =
            AllFluids.newMoltenFluid(REG, "Vibranium", () -> 0xffffff).register();
    public static final FluidEntry<LavaLikeFluid.Flowing> MOLTEN_UNOBTAINIUM =
            AllFluids.newMoltenFluid(REG, "Unobtainium", () -> 0xffffff).register();

    public static void register() {
        registerFluids(List.of(MOLTEN_ALLTHEMODIUM, MOLTEN_VIBRANIUM, MOLTEN_UNOBTAINIUM), "allthemodium");
    }
}
