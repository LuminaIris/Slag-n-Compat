package dev.luminairis.slagcompat.compat.fluids;

import com.tterrag.registrate.util.entry.FluidEntry;
import dev.lopyluna.slag.register.AllFluids;
import dev.luminairis.slagcompat.compat.fluids.allthemodium.AllthemodiumFluids;
import dev.luminairis.slagcompat.compat.fluids.iceandfire.IceAndFireFluids;
import dev.luminairis.slagcompat.compat.fluids.irons_spellbooks.IronsSpellbooksFluids;
import dev.luminairis.slagcompat.compat.fluids.mekanism.MekanismFluids;
import dev.luminairis.slagcompat.compat.fluids.twilightforest.TwilightForestFluids;
import java.util.ArrayList;

public class AllCompatFluids {
    public static ArrayList<FluidEntry<AllFluids.LavaLikeFluid.Flowing>> ALL_COMPAT_FLUIDS = new ArrayList<>();
    public static ArrayList<FluidEntry<AllFluids.LavaLikeFluid.Flowing>> HIDDEN_COMPAT_FLUIDS = new ArrayList<>();

    public static void register() {
        MekanismFluids.register();
        AllthemodiumFluids.register();
        IceAndFireFluids.register();
        TwilightForestFluids.register();
        IronsSpellbooksFluids.register();
    }
}
