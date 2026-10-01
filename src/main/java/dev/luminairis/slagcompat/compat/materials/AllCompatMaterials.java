package dev.luminairis.slagcompat.compat.materials;

import dev.lopyluna.slag.content.types.MaterialType;
import dev.luminairis.slagcompat.compat.materials.mekanism.MekanismMaterials;
import java.util.ArrayList;
import java.util.List;

public class AllCompatMaterials {
    public static final List<MaterialType> ALL_COMPAT_MATERIALS = new ArrayList<>();

    public static void register() {
        MekanismMaterials.registerMekanismMaterials();
    }
}
