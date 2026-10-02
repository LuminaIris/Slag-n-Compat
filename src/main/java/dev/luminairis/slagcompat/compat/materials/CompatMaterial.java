package dev.luminairis.slagcompat.compat.materials;

import dev.lopyluna.slag.content.types.MaterialType;
import dev.lopyluna.slag.register.AllDynamicTypes;

public class CompatMaterial {
    public static MaterialType register(MaterialType material, boolean hasRaw) {
        AllCompatMaterials.ALL_COMPAT_MATERIALS.add(material);
        AllCompatMaterials.MATERIAL_HAS_RAW.put(material.id.getPath(), hasRaw);
        return AllDynamicTypes.registerMaterial(material);
    }
}
