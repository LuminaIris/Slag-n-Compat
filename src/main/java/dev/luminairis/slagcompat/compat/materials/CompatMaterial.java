package dev.luminairis.slagcompat.compat.materials;

import dev.lopyluna.slag.content.types.MaterialType;
import dev.lopyluna.slag.register.AllDynamicTypes;

public class CompatMaterial {
    public static MaterialType register(MaterialType material) {
        AllCompatMaterials.ALL_COMPAT_MATERIALS.add(material);
        return AllDynamicTypes.registerMaterial(material);
    }
}
