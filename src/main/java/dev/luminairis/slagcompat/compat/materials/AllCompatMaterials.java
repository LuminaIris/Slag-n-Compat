package dev.luminairis.slagcompat.compat.materials;

import dev.lopyluna.slag.content.types.MaterialType;
import dev.luminairis.slagcompat.compat.materials.mekanism.MekanismMaterials;
import java.util.ArrayList;
import java.util.List;
import dev.luminairis.slagcompat.SlagCompat;

public class AllCompatMaterials {
    public static final List<MaterialType> ALL_COMPAT_MATERIALS = new ArrayList<>();

    public static void register() {
        MekanismMaterials.registerMekanismMaterials();
        SlagCompat.LOGGER.info("All compat materials: " + ALL_COMPAT_MATERIALS.toString());
    }
}
