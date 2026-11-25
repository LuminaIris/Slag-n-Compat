package dev.luminairis.slagcompat.compat.materials;

import dev.lopyluna.slag.content.types.MaterialType;
import dev.lopyluna.slag.register.AllDynamicTypes;

public class CompatMaterial {
    protected static int sortOrder = 1000;
    public static final String INGOTS = "ingots";
    public static final String GEMS = "gems";
    public static final String BLOCKS = "blocks";
    public static final String MISC = "misc";

    protected static MaterialType register(MaterialType material, String form, boolean hasRaw) {
        AllCompatMaterials.COMPAT_MATERIALS.add(material);
        AllCompatMaterials.MATERIAL_FORM.put(material.id.getPath(), form);
        AllCompatMaterials.MATERIAL_HAS_RAW.put(material.id.getPath(), hasRaw);
        sortOrder += 10;
        return AllDynamicTypes.registerMaterial(material);
    }
}
