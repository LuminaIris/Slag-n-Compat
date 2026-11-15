package dev.luminairis.slagcompat.compat.fluids.mekanism;

import com.tterrag.registrate.util.entry.FluidEntry;
import dev.lopyluna.slag.register.AllFluids.LavaLikeFluid;
import dev.lopyluna.slag.register.AllFluids;
import dev.luminairis.slagcompat.compat.fluids.CompatFluid;
import java.util.List;

import static dev.luminairis.slagcompat.SlagCompat.REG;

@SuppressWarnings("unused")
public class MekanismFluids extends CompatFluid {
    public static final FluidEntry<LavaLikeFluid.Flowing> MOLTEN_REFINED_OBSIDIAN =
            AllFluids.compatFluid(REG, "Refined Obsidian", () -> 0xffffff);
    public static final FluidEntry<LavaLikeFluid.Flowing> MOLTEN_REFINED_GLOWSTONE =
            AllFluids.compatFluid(REG, "Refined Glowstone", () -> 0xffffff);

    public static void register() {
        registerFluids(List.of(MOLTEN_REFINED_OBSIDIAN, MOLTEN_REFINED_GLOWSTONE), "mekanism");
    }
}
