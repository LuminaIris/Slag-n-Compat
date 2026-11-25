package dev.luminairis.slagcompat.compat.fluids.irons_spellbooks;

import com.tterrag.registrate.util.entry.FluidEntry;
import dev.lopyluna.slag.register.AllFluids.LavaLikeFluid;
import dev.lopyluna.slag.register.AllFluids;
import dev.luminairis.slagcompat.compat.fluids.CompatFluid;
import java.util.List;

import static dev.luminairis.slagcompat.SlagCompat.REG;

@SuppressWarnings("unused")
public class IronsSpellbooksFluids extends CompatFluid {
    public static FluidEntry<LavaLikeFluid.Flowing> MOLTEN_ARCANE =
            AllFluids.newMoltenFluid(REG, "Arcane", () -> 0xFFFFFF).register();
    public static FluidEntry<LavaLikeFluid.Flowing> MOLTEN_MITHRIL =
            AllFluids.newMoltenFluid(REG, "Mithril", () -> 0xFFFFFF).register();
    public static FluidEntry<LavaLikeFluid.Flowing> MOLTEN_PYRIUM =
            AllFluids.newMoltenFluid(REG, "Pyrium", () -> 0xFFFFFF).register();

    public static void register() {
        registerFluids(List.of(MOLTEN_ARCANE, MOLTEN_MITHRIL, MOLTEN_PYRIUM), "irons_spellbooks");
    }
}
