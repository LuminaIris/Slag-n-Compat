package dev.luminairis.slagcompat.compat.materials;

import dev.lopyluna.slag.content.types.MaterialType;
import dev.luminairis.slagcompat.compat.materials.allthemodium.AllthemodiumMaterials;
import dev.luminairis.slagcompat.compat.materials.eternal_starlight.EternalStarlightMaterials;
import dev.luminairis.slagcompat.compat.materials.iceandfire.IceAndFireMaterials;
import dev.luminairis.slagcompat.compat.materials.irons_spellbooks.IronsSpellbooksMaterials;
import dev.luminairis.slagcompat.compat.materials.mekanism.MekanismMaterials;
import dev.luminairis.slagcompat.compat.materials.twilightforest.TwilightForestMaterials;
import net.neoforged.fml.ModList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllCompatMaterials {
    public static final List<MaterialType> COMPAT_MATERIALS = new ArrayList<>();
    public static List<MaterialType> BLOCK_COMPAT_MATERIALS = new ArrayList<>();
    public static List<MaterialType> ONE_INGOT_COMPAT_MATERIALS = new ArrayList<>();
    public static Map<MaterialType, MaterialType> POURING_MATERIALS = new HashMap<>();

    public static final Map<String, String> MATERIAL_FORM = new HashMap<>();
    public static final Map<String, Boolean> MATERIAL_HAS_RAW = new HashMap<>();

    public static void register() {
        if (ModList.get().isLoaded("mekanism")) {
            MekanismMaterials.register();
        }
        if (ModList.get().isLoaded("allthemodium")) {
            AllthemodiumMaterials.register();
        }
        if (ModList.get().isLoaded("iceandfire")) {
            IceAndFireMaterials.register();
        }
        if (ModList.get().isLoaded("twilightforest")) {
            TwilightForestMaterials.register();
        }
        if (ModList.get().isLoaded("irons_spellbooks")) {
            IronsSpellbooksMaterials.register();
        }
        if (ModList.get().isLoaded("eternal_starlight")) {
            EternalStarlightMaterials.register();
        }
    }
}
