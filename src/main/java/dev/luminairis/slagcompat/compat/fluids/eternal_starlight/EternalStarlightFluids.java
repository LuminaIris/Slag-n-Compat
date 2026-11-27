package dev.luminairis.slagcompat.compat.fluids.eternal_starlight;

import com.tterrag.registrate.util.entry.FluidEntry;
import dev.lopyluna.slag.register.AllFluids;
import dev.lopyluna.slag.register.AllFluids.LavaLikeFluid;
import dev.luminairis.slagcompat.compat.fluids.CompatFluid;
import java.util.List;

import static dev.luminairis.slagcompat.SlagCompat.REG;

@SuppressWarnings("unused")
public class EternalStarlightFluids extends CompatFluid {
    public static FluidEntry<LavaLikeFluid.Flowing> MOLTEN_DEEPSILVER =
            AllFluids.newMoltenFluid(REG, "Deepsilver", () -> 0xFFFFFF).register();
    public static FluidEntry<LavaLikeFluid.Flowing> MOLTEN_GLACITE =
            AllFluids.newMoltenFluid(REG, "Glacite", () -> 0xFFFFFF).register();
    public static FluidEntry<LavaLikeFluid.Flowing> MOLTEN_THERMAL_SPRINGSTONE =
            AllFluids.newMoltenFluid(REG, "Thermal Springstone", () -> 0xFFFFFF).register();
    public static FluidEntry<LavaLikeFluid.Flowing> MOLTEN_MALARITE =
            AllFluids.newMoltenFluid(REG, "Malarite", () -> 0xFFFFFF).register();
    public static FluidEntry<LavaLikeFluid.Flowing> MOLTEN_STARLIT_DIAMOND =
            AllFluids.newMoltenFluid(REG, "Starlit Diamond", () -> 0xFFFFFF).register();
    public static FluidEntry<LavaLikeFluid.Flowing> MOLTEN_STARFIRE =
            AllFluids.newMoltenFluid(REG, "Starfire", () -> 0xFFFFFF).register();
    public static FluidEntry<LavaLikeFluid.Flowing> MOLTEN_FLOWGLAZE =
            AllFluids.newMoltenFluid(REG, "Flowglaze", () -> 0xFFFFFF).register();
    public static FluidEntry<LavaLikeFluid.Flowing> MOLTEN_AMARAMBER =
            AllFluids.newMoltenFluid(REG, "Amaramber", () -> 0xFFFFFF).register();
    public static FluidEntry<LavaLikeFluid.Flowing> MOLTEN_AETHERSENT =
            AllFluids.newMoltenFluid(REG, "Aethersent", () -> 0xFFFFFF).register();
    public static FluidEntry<LavaLikeFluid.Flowing> MOLTEN_GOLEM_STEEL =
            AllFluids.newMoltenFluid(REG, "Golem Steel", () -> 0xFFFFFF).register();
    public static FluidEntry<LavaLikeFluid.Flowing> MOLTEN_UNREALIUM =
            AllFluids.newMoltenFluid(REG, "Unrealium", () -> 0xFFFFFF).register();
    public static FluidEntry<LavaLikeFluid.Flowing> MOLTEN_SOUL_DEW =
            AllFluids.newMoltenFluid(REG, "Soul Dew", () -> 0xFFFFFF).register();

    public static void register() {
        registerFluids(List.of(MOLTEN_DEEPSILVER, MOLTEN_GLACITE, MOLTEN_THERMAL_SPRINGSTONE, MOLTEN_MALARITE, MOLTEN_STARLIT_DIAMOND,
                MOLTEN_STARFIRE, MOLTEN_FLOWGLAZE, MOLTEN_AMARAMBER, MOLTEN_AETHERSENT, MOLTEN_GOLEM_STEEL, MOLTEN_UNREALIUM, MOLTEN_SOUL_DEW),
                "eternal_starlight");
    }
}
