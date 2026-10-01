package dev.luminairis.slagcompat.compat.fluids.mekanism;

import com.tterrag.registrate.util.entry.FluidEntry;
import dev.lopyluna.slag.register.AllFluids.LavaLikeFluid;
import dev.lopyluna.slag.register.AllFluids;
import static dev.luminairis.slagcompat.SlagCompat.REG;

@SuppressWarnings("unused")
public class MekanismFluids {
    public static final FluidEntry<LavaLikeFluid.Flowing> MOLTEN_REFINED_OBSIDIAN =
            AllFluids.compatFluid(REG, "Refined Obsidian", () -> 0xffffff);
    public static final FluidEntry<LavaLikeFluid.Flowing> MOLTEN_REFINED_GLOWSTONE =
            AllFluids.compatFluid(REG, "Refined Glowstone", () -> 0xffffff);

    public static void registerMekanismFluids() {}
}
