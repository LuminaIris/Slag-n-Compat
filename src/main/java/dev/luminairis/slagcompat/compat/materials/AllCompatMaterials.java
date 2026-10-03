package dev.luminairis.slagcompat.compat.materials;

import dev.lopyluna.slag.content.types.MaterialType;
import dev.luminairis.slagcompat.compat.materials.allthemodium.AllthemodiumMaterials;
import dev.luminairis.slagcompat.compat.materials.iceandfire.IceAndFireMaterials;
import dev.luminairis.slagcompat.compat.materials.mekanism.MekanismMaterials;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllCompatMaterials {
    public static final List<MaterialType> ALL_COMPAT_MATERIALS = new ArrayList<>();
    public static final Map<String, Boolean> MATERIAL_HAS_RAW = new HashMap<>();

    public static void register() {
        MekanismMaterials.registerMekanismMaterials();
        AllthemodiumMaterials.registerAllthemodiumMaterials();
        IceAndFireMaterials.registerIceAndFireMaterials();
    }
}
